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
        val list = commands.values.map { it.getData() }.toMutableList()
        list.addAll(groups.values.map { it.getData() })

        return list
    }

    override fun registerAll() {
        jda.updateCommands()
            .addCommands(*getCommands().toTypedArray())
            .queue()
    }

    override fun registerAtGuild(guild: Long) {
        jda.getGuildById(guild)
            ?.updateCommands()
            ?.addCommands(*getCommands().toTypedArray())
            ?.queue()
    }

    override fun register(command: SlashCommand?) {
        if (command == null)
            return
        BaseBuilder(command).build(this)
    }

    @Throws(Exception::class)
    fun runCommand(event: SlashCommandInteractionEvent) {
        val command = if(event.interaction.subcommandGroup != null || event.interaction.subcommandName != null) {
            this.groups.get(event.interaction.name)?.getCommand(event.interaction)
        } else {
            this.commands.get(event.interaction.name)
        }

        command?.execute(SlashCommandContextImpl(event))
    }

    fun autocomplete(event: CommandAutoCompleteInteractionEvent) {

    }
}