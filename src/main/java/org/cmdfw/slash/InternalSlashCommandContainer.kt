package org.cmdfw.slash

internal interface InternalSlashCommandContainer {
    fun registerCommand(builder: InternalSlashCommand) {}
    fun registerSimpleGroup(builder: SimpleBuilder) {}
    fun registerSubCommandGroup(builder: GroupBuilder) {}
}