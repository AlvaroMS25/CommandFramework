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

        val content = event.message.contentRaw.substring(commandPrefix.length)
        val firstSpace = content.indexOf(" ")
        val command: Command?
        var args: MutableList<String> = mutableListOf()

        if(firstSpace == -1) {
            command = this.getCommand(mutableListOf(content))
        } else {
            val (separated, initialLength) = this.parseSpaces(content)
            command = this.getCommand(separated)

            if(command != null) {
                if(separated.isNotEmpty()) {
                    val splitIndex = initialLength - calculateLength(separated)
                    val argsSubstring = content.substring(splitIndex)
                    args = this.parseArgs(argsSubstring.split(separator).toMutableList())
                }
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

    private fun parseSpaces(item: String) : Pair<MutableList<String>, Int> {
        val spaced = item.splitToSequence(" ")
            .toMutableList()

        return Pair(spaced, calculateLength(spaced))
    }

    private fun calculateLength(item: List<String>): Int = item.sumOf { it.length } + item.size - 1

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