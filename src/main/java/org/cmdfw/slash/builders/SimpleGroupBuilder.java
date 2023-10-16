package org.cmdfw.slash.builders;

import org.cmdfw.slash.BuildableContainer;

public interface SimpleGroupBuilder extends ExtendedPropertiesSetter<SimpleGroupBuilder> {
    SimpleCommandBuilder addCommand();
}
