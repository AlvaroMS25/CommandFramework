package org.cmdfw.slash

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData
import org.cmdfw.slash.builders.SlashCommand
import org.cmdfw.slash.builders.SubCommandGroup

internal class Manager(private val jda: JDA) : SlashCommandManager, InternalSlashCommandContainer {
    val commands = mutableMapOf<String, Command>()
    val groups = mutableMapOf<String, SubCommandGroupImpl>()

    override fun registerSimpleGroup(builder: SimpleBuilder) {
        this.groups[builder.name] = builder.build()
    }

    override fun registerSubCommandGroup(builder: GroupBuilder) {
        this.groups[builder.name] = builder.build()
    }

    override fun registerCommand(builder: CommandBuilder) {
        this.commands[builder.name] = builder.build()
    }

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

    override fun register(command: SlashCommand?): SlashCommandManager {
        if (command == null)
            return this
        CommandBuilder(this, command)
        return this
    }

    override fun register(group: SubCommandGroup?): SlashCommandManager {
        if(group == null)
            return this
        val groupBuilder = GroupBuilder()
        val simpleBuilder = SimpleBuilder(null)
        val tuple = groupBuilder.register(group) to simpleBuilder.register(group)

        when {
            tuple.first && tuple.second -> {
                throw RuntimeException("A subcommand group can't define both Simple and Subcommand group")
            }
            tuple.first && !tuple.second -> {
                this.registerSubCommandGroup(groupBuilder)
            }
            !tuple.first && tuple.second -> {
                this.registerSimpleGroup(simpleBuilder)
            }
        }
        return this
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