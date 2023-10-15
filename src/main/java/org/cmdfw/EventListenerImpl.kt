package org.cmdfw

import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.cmdfw.exceptions.UncompleteQuotedArgumentException
import org.cmdfw.slash.Manager as SlashCommandManager
import org.cmdfw.message.Manager as MessageCommandManager

internal class EventListenerImpl(
    private val messageCommandManager: MessageCommandManager,
    private val slashCommandManager: SlashCommandManager
) : ListenerAdapter() {


    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        slashCommandManager.runCommand(event)
    }

    override fun onCommandAutoCompleteInteraction(event: CommandAutoCompleteInteractionEvent) {
        slashCommandManager.autocomplete(event)
    }

    @Throws(Exception::class, UncompleteQuotedArgumentException::class)
    override fun onMessageReceived(event: MessageReceivedEvent) {
        messageCommandManager.processEvent(event)
    }

}