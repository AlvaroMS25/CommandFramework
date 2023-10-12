package org.cmdfw.slash

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.interactions.commands.build.Commands
import java.util.Optional

class BuilderImpl(private val jda: JDA, private val slashCommand: SlashCommand?) : SlashCommandBuilder {
    internal val arguments = mutableListOf<Argument>()
    private val syncGlobally = false
    private val guildsToSync = mutableListOf<Long>()
    fun a() {
        //jda.updateCommands().addCommands(Commands.slash())
    }
    override fun syncGlobally(): SlashCommandBuilder {
        return this
    }

    override fun syncOnGuilds(vararg id: Long): SlashCommandBuilder {
        return this
    }

    override fun addArgument(): SlashCommandArgument {
        return Argument(this)
    }
}