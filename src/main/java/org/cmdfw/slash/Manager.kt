package org.cmdfw.slash

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData
import org.cmdfw.slash.builders.SlashCommand

internal class Manager(private val jda: JDA) : SlashCommandManager {
    val commands = mutableMapOf<String, Command>()
    val groups = mutableMapOf<String, SubcommandGroup>()

    private fun getCommands(): List<SlashCommandData> {
        return commands.values.map { it.getData() }
    }

    override fun registerAll() {
        jda.updateCommands()
            .addCommands(*getCommands().toTypedArray())
            .queue()
    }

    override fun registerAtGuild(guild: Long) {
        jda.getGuildById(guild)?.updateCommands()?.addCommands(*getCommands().toTypedArray())?.queue()
    }

    override fun register(command: SlashCommand?) {
        if (command == null)
            return
        BaseBuilder(command).build(this)
    }

    @Throws(Exception::class)
    fun runCommand(event: SlashCommandInteractionEvent) {

    }

    fun autocomplete(event: CommandAutoCompleteInteractionEvent) {

    }
}