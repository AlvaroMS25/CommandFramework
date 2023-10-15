package org.cmdfw.slash

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions
import org.cmdfw.slash.builders.*

internal class BaseBuilder(private val jda: JDA, private val slashCommand: SlashCommand) :
    SlashCommandBuilder {
    fun build(): InternalSlashCommandContainer {
        this.slashCommand.register(this)
        TODO()
    }

    override fun simple(): SimpleCommandBuilder {
        return CommandBuilder(this)
    }

    override fun addSubcommands(): SimpleGroupBuilder {
        TODO("Not yet implemented")
    }

    override fun addSubcommandGroup(): SubcommandGroupBuilder {
        TODO("Not yet implemented")
    }
}

internal class CommandBuilder(private val baseBuilder: BaseBuilder) : SimpleCommandBuilder {
    val arguments = mutableListOf<Argument>()
    lateinit var name: String
    lateinit var description: String
    var defaultMemberPermissions = DefaultMemberPermissions.ENABLED
    var guildOnly = false
    var isNsfw = false

    override fun setName(name: String): SimpleCommandBuilder {
        this.name = name
        return this
    }

    override fun setDescription(description: String): SimpleCommandBuilder {
        this.description = description
        return this
    }

    override fun setDefaultPermissions(permission: DefaultMemberPermissions): SimpleCommandBuilder {
        this.defaultMemberPermissions = permission
        return this
    }

    override fun setGuildOnly(isGuildOnly: Boolean): SimpleCommandBuilder {
        this.guildOnly = isGuildOnly
        return this
    }

    override fun setNsfw(isNsfw: Boolean): SimpleCommandBuilder {
        this.isNsfw = isNsfw
        return this
    }

    override fun addArgument(): SlashCommandArgument {
        return Argument(this)
    }

    override fun build(): InternalSlashCommandContainer {

        TODO()
    }
}

internal class SimpleBuilder(): SimpleGroupBuilder {
    override fun build(): InternalSlashCommandContainer {
        TODO("Not yet implemented")
    }

}
