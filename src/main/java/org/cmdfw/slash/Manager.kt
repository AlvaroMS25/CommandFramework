package org.cmdfw.slash

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData

internal class Manager(private val jda: JDA) : SlashCommandManager {
    private val commands = mutableListOf<Command>()

    private fun getCommands(): List<SlashCommandData> {
        return commands.map { it.create() }
    }

    override fun registerAll() {
        jda.updateCommands()
            .addCommands(*getCommands().toTypedArray())
            .queue()
    }

    override fun registerAtGuild(guild: Long) {
        jda.getGuildById(guild)?.updateCommands()?.addCommands(*getCommands().toTypedArray())?.queue()
    }

    @Throws(Exception::class)
    fun runCommand(event: SlashCommandInteractionEvent) {

    }

    fun autocomplete(event: CommandAutoCompleteInteractionEvent) {

    }
}