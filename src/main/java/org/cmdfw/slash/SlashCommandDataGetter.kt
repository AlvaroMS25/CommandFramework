package org.cmdfw.slash

import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData

internal interface SlashCommandDataGetter {
    fun getData(): SlashCommandData = TODO()
    fun applySubcommand(data: SubcommandData): SubcommandData = data
    fun createSubcommands(): List<SubcommandData> = TODO()
}