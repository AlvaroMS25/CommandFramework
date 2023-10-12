package org.cmdfw.message

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.message.GenericMessageEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import org.cmdfw.exceptions.UncompleteQuotedArgumentException


internal class Manager(
    private val jda: JDA,
    private val provider: PrefixProvider,
    private var separator: String,
) : MessageCommandManager {
    private val commands: MutableList<Command> = mutableListOf()

    @Throws(Exception::class, UncompleteQuotedArgumentException::class)
    fun processEvent(event: MessageReceivedEvent) {
        val commandPrefix = provider.get(event)
        if (!event.message.contentRaw.startsWith(commandPrefix)) {
            return
        }

        val content = event.message.contentRaw.substring(separator.length)
        val firstSpace = content.indexOf(" ")
        val command: Command?
        var args: MutableList<String> = mutableListOf()

        if(firstSpace == -1) {
            command = this.getCommand(mutableListOf(content))
        } else {
            val (separated, consumed) = this.parseSpaces(content)
            command = this.getCommand(separated.toMutableList())

            if(command != null) {
                val argsSubstring = content.substring(consumed)
                args = this.parseArgs(argsSubstring.split(separator).toMutableList())
            }
        }

        if(command == null)
            return

        return command.execute(MessageCommandContextImpl(jda, event, args))
    }

    @Throws(UncompleteQuotedArgumentException::class)
    private fun parseArgs(items: MutableList<String>): MutableList<String> {
        val list = mutableListOf<String>()

        while(items.isNotEmpty()) {
            val current = items.removeAt(0)
            if(current.contains('"')) {
                list.add(this.parseQuoted(current, items))
            } else {
                list.add(current)
            }
        }


        return list
    }

    @Throws(UncompleteQuotedArgumentException::class)
    private fun parseQuoted(startItem: String, items: MutableList<String>) : String {
        var item = startItem
        while(items.isEmpty()) {
            var current = items.removeAt(0)
            item += current;
            if(current.contains('"')) {
                current = current.removeSurrounding("\"")
                return current
            }
        }

        throw UncompleteQuotedArgumentException(item)
    }

    private fun parseSpaces(item: String) : Pair<List<String>, Int> {
        val spaced = item.splitToSequence(" ")
            .toList()

        val totalChars = spaced.sumOf { it.length } + spaced.size - 1

        return Pair(spaced, totalChars)
    }

    private fun getCommand(items: MutableList<String>) : Command? {
        if(items.isEmpty()) return null
        val first = items.removeFirst()

        for(cmd in this.commands) {
            if(cmd.hasName(first))
                return cmd.getSubcommands(items)
        }

        items.add(0, first)

        return null
    }

    fun setSeparator(separator: String) {
        this.separator = separator
    }

    override fun register(command: MessageCommand?) {
        if(command == null)
            return

        this.commands.add(BuilderImpl(command).build())
    }
}