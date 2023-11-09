package org.cmdfw.slash

import org.cmdfw.slash.builders.SlashCommand

internal interface InternalSlashCommand: ExtendedPropertiesGetter, BuildableContainer<Command> {
    fun getArguments(): MutableList<Argument>
    fun getSlashCommand(): SlashCommand
}