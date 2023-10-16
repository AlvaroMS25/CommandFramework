package org.cmdfw.slash.builders

import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions

interface ExtendedPropertiesSetter<T>: BasePropertiesSetter<T> {
    fun setNsfw(isNsfw: Boolean): T
    fun setGuildOnly(isOnlyGuilds: Boolean): T
    fun setDefaultPermissions(permission: DefaultMemberPermissions): T

}