package org.cmdfw.slash

internal interface InternalSlashCommandContainer {
    fun registerCommand(builder: CommandBuilder) {}
    fun registerSimpleGroup(builder: SimpleBuilder) {}
    fun registerSubCommandGroup(builder: GroupBuilder) {}
}