package org.cmdfw.slash

import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions

internal enum class GroupType {
    Simple,
    SubcommandGroup
}

internal class SubcommandGroup(
    val name: String,
    val description: String,
    val requiredPermissions: DefaultMemberPermissions,
    val isNsfw: Boolean,
    val onlyGuilds: Boolean,
    val type: GroupType
) {
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
}

internal class SimpleCommandGroup(builder: SimpleBuilder) {
    val commands: MutableMap<String, Command>
    var name: String
    var description: String

    init {
        this.name = builder.name
        this.description = builder.description

        this.commands = builder.getAsMap()
    }
}
