package org.cmdfw.slash

import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData
import net.dv8tion.jda.api.interactions.commands.build.SubcommandGroupData

internal enum class GroupType {
    Simple,
    SubcommandGroup
}

internal class SubCommandGroupImpl(
    val name: String,
    val description: String,
    val requiredPermissions: DefaultMemberPermissions,
    val isNsfw: Boolean,
    val onlyGuilds: Boolean,
    val type: GroupType
): SlashCommandDataGetter {
    var groups: MutableMap<String, SimpleCommandGroup>? = null
    var commands: MutableMap<String, Command>? = null

    constructor(simple: SimpleBuilder)
    : this(
        simple.name,
        simple.description,
        simple.defaultMemberPermissions,
        simple.isNsfw,
        simple.guildOnly,
        GroupType.Simple
    )
    {
        this.commands = simple.getAsMap()
    }

    constructor(group: GroupBuilder)
    : this(
        group.name,
        group.description,
        group.defaultMemberPermissions,
        group.isNsfw,
        group.guildOnly,
        GroupType.SubcommandGroup
    )
    {
        this.groups = mutableMapOf()

        for(g in group.groups) {
            this.groups!![g.name] = SimpleCommandGroup(g)
        }
    }

    fun containsCommand(name: String): Boolean {
        return when(type) {
            GroupType.SubcommandGroup -> this.groups!!.containsKey(name)
            GroupType.Simple -> this.commands!!.containsKey(name)
        }
    }

    private fun isSimple(): Boolean = this.type == GroupType.Simple
    private fun isGroup(): Boolean = !isSimple()


    fun getCommand(interaction: SlashCommandInteraction): Command? {
        if(interaction.subcommandGroup != null && this.isGroup()) {
            val inner = this.groups!![interaction.subcommandGroup]
            return inner?.commands?.get(interaction.subcommandName)
        } else if(interaction.subcommandName != null && this.isSimple()) {
            return this.commands!![interaction.subcommandName]
        }

        return null
    }

    override fun getData(): SlashCommandData {
        val data = Commands.slash(this.name, this.description)
            .setDefaultPermissions(this.requiredPermissions)
            .setGuildOnly(this.onlyGuilds)
            .setNSFW(this.isNsfw)
            .addSubcommandGroups()

        if(this.isGroup()) {
            val subgroups = this.groups!!.values.map {
                val subgroupData = SubcommandGroupData(it.name, it.description)
                subgroupData.addSubcommands(it.createSubcommands())
            }

            data.addSubcommandGroups(subgroups)
        } else {
            val subcommands = this.commands!!.values.map {
                it.applySubcommand(SubcommandData(it.name, it.description))
            }
            data.addSubcommands(subcommands)
        }

        return data
    }
}

internal class SimpleCommandGroup(builder: SimpleBuilder) : SlashCommandDataGetter {
    val commands: MutableMap<String, Command>
    var name: String
    var description: String

    init {
        this.name = builder.name
        this.description = builder.description

        this.commands = builder.getAsMap()
    }

    override fun createSubcommands(): List<SubcommandData> {
        return this.commands.values.map {
            it.applySubcommand(SubcommandData(it.name, it.description))
        }
    }
}
