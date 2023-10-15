package org.cmdfw.slash.builders;

import org.jetbrains.annotations.NotNull;

interface BasePropertiesSetter<T> {
    fun setName(name: String): T
    fun setDescription(description: String): T
}
