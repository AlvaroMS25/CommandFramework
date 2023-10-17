package org.cmdfw.slash

import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData
import org.cmdfw.slash.builders.SlashCommand

internal class Command(builder: CommandBuilder): SlashCommandDataGetter {
    var arguments = mutableListOf<Argument>()
    var name: String
    var description: String
    var defaultMemberPermissions = DefaultMemberPermissions.ENABLED
    var guildOnly: Boolean
    var isNsfw: Boolean
    var inner: SlashCommand

    init {
        arguments = builder.arguments
        name = builder.name
        description = builder.description
        defaultMemberPermissions = builder.defaultMemberPermissions
        guildOnly = builder.guildOnly
        isNsfw = builder.isNsfw
        inner = builder.slashCommand
    }

    override fun getData(): SlashCommandData {
        return Commands.slash(name, name)
            .addOptions(*arguments.map { a -> a.asOption() }.toTypedArray())
            .setDefaultPermissions(defaultMemberPermissions)
            .setGuildOnly(guildOnly)
            .setNSFW(isNsfw)
    }

    override fun applySubcommand(data: SubcommandData): SubcommandData {
        return data.addOptions(*arguments.map { a -> a.asOption() }.toTypedArray())
    }

    fun execute(context: SlashCommandContext) {
        inner.execute(context)
    }
}
