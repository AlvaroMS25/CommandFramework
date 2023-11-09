package org.cmdfw.slash

import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions
import org.cmdfw.slash.builders.SlashCommand

internal interface ExtendedPropertiesGetter: BasePropertiesGetter {
    fun getNsfw(): Boolean
    fun getGuildOnly(): Boolean
    fun getDefaultPermissions(): DefaultMemberPermissions
}