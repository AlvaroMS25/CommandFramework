package org.cmdfw.slash.builders;


public interface SubcommandGroupBuilder extends ExtendedPropertiesSetter<SubcommandGroupBuilder> {
    SimpleGroupBuilder addGroup();
}
