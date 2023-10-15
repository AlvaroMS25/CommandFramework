package org.cmdfw.slash

internal interface BuildableContainer {
    fun build(): InternalSlashCommandContainer
}