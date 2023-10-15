package org.cmdfw.slash

import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData

internal class Command(builder: CommandBuilder) {
    var arguments = mutableListOf<Argument>()
    var name: String
    var description: String
    var defaultMemberPermissions = DefaultMemberPermissions.ENABLED
    var guildOnly: Boolean
    var isNsfw: Boolean

    init {
        arguments = builder.arguments
        name = builder.name
        description = builder.description
        defaultMemberPermissions = builder.defaultMemberPermissions
        guildOnly = builder.guildOnly
        isNsfw = builder.isNsfw
    }

    fun create(): SlashCommandData {
        return Commands.slash(name, name)
            .addOptions(*arguments.map { a -> a.asOption() }.toTypedArray())
            .setDefaultPermissions(defaultMemberPermissions)
            .setGuildOnly(guildOnly)
            .setNSFW(isNsfw)
    }

    fun execute() {
        TODO("Not yet implemented")
    }
}
