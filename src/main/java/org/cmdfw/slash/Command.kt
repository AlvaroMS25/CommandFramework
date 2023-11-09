package org.cmdfw.slash

import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData
import org.cmdfw.slash.builders.SlashCommand

internal class Command(builder: InternalSlashCommand): SlashCommandDataGetter {
    var arguments = mutableListOf<Argument>()
    var name: String
    var description: String
    var defaultMemberPermissions = DefaultMemberPermissions.ENABLED
    var guildOnly: Boolean
    var isNsfw: Boolean
    var inner: SlashCommand

    init {
        arguments = builder.getArguments()
        name = builder.getName()
        description = builder.getDescription()
        defaultMemberPermissions = builder.getDefaultPermissions()
        guildOnly = builder.getGuildOnly()
        isNsfw = builder.getNsfw()
        inner = builder.getSlashCommand()
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

    fun getArgument(name: String): Argument? {
        for(arg in this.arguments) {
            if(arg.name == name)
                return arg
        }
        return null
    }
}
