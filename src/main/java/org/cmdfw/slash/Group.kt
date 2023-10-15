package org.cmdfw.slash

import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions

internal class SubcommandGroup {
    val groups: MutableMap<String, SimpleCommandGroup> = mutableMapOf()
    lateinit var name: String
    var requiredPermissions: DefaultMemberPermissions = DefaultMemberPermissions.ENABLED
    var isNsfw = false
    var onlyGuilds = false
}

internal class SimpleCommandGroup {
    val commands: MutableMap<String, Command> = mutableMapOf()
    lateinit var name: String
    lateinit var description: String
}
