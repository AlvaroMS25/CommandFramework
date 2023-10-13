package org.cmdfw

import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.cmdfw.slash.Manager as SlashCommandManager
import org.cmdfw.message.Manager as MessageCommandManager

internal class EventListenerImpl(
    private val messageCommandManager: MessageCommandManager,
    private val slashCommandManager: SlashCommandManager
) : ListenerAdapter() {


    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        TODO("Implement slash commands")
    }

    override fun onCommandAutoCompleteInteraction(event: CommandAutoCompleteInteractionEvent) {

    }

    override fun onMessageReceived(event: MessageReceivedEvent) {
        messageCommandManager.processEvent(event)
    }

}