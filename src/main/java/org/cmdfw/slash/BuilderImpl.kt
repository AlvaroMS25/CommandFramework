package org.cmdfw.slash

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions
import net.dv8tion.jda.api.interactions.commands.build.Commands
import java.util.Optional

internal class BuilderImpl(private val jda: JDA, private val slashCommand: SlashCommand) : SlashCommandBuilder {
    internal val arguments = mutableListOf<Argument>()
    private var syncGlobally = false
    private val guildsToSync = mutableListOf<Long>()
    private lateinit var name: String
    private lateinit var description: String
    private var defaultMemberPermissions = DefaultMemberPermissions.ENABLED


    override fun syncGlobally(): SlashCommandBuilder {
        this.syncGlobally = true
        return this
    }

    override fun syncOnGuilds(vararg id: Long): SlashCommandBuilder {
        this.guildsToSync.addAll(id.toList())
        return this
    }

    override fun setName(name: String): SlashCommandBuilder {
        this.name = name
        return this
    }

    override fun setDescription(description: String): SlashCommandBuilder {
        this.description = description
        return this
    }

    override fun setDefaultPermissions(permission: DefaultMemberPermissions): SlashCommandBuilder {
        this.defaultMemberPermissions = permission
        return this
    }

    override fun addArgument(): SlashCommandArgument {
        return Argument(this)
    }

    fun build(): Command {
        this.slashCommand.register(this)
        TODO()
    }
}