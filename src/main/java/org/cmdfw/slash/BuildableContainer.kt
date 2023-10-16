package org.cmdfw.slash

internal interface BuildableContainer<T> {
    fun build(): T
}