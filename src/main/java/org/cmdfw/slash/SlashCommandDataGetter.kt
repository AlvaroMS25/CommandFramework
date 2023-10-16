package org.cmdfw.slash

import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData

internal interface SlashCommandDataGetter {
    fun getData(): SlashCommandData
}