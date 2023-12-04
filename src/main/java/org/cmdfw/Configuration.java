package org.cmdfw;


import org.jetbrains.annotations.NotNull;

public class Configuration {
    @NotNull private ExecutionMode executionMode;

    public Configuration() {
        executionMode = ExecutionMode.OwnThread;
    }

    public ExecutionMode getExecutionMode() {
        return executionMode;
    }

    public void setExecutionMode(ExecutionMode executionMode) {
        this.executionMode = executionMode;
    }
}
