package org.cmdfw.slash

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.interactions.commands.build.Commands
import java.util.Optional

class BuilderImpl(private val jda: JDA, private val slashCommand: SlashCommand) : SlashCommandBuilder {
    internal val arguments = mutableListOf<Argument>()
    private val syncGlobally = false
    private val guildsToSync = mutableListOf<Long>()
    private lateinit var name: String
    private lateinit var description: String
    override fun syncGlobally(): SlashCommandBuilder {
        return this
    }

    override fun syncOnGuilds(vararg id: Long): SlashCommandBuilder {
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

    override fun addArgument(): SlashCommandArgument {
        return Argument(this)
    }
}