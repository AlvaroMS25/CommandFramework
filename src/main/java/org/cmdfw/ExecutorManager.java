package org.cmdfw;

import org.jetbrains.annotations.NotNull;

public interface ExecutorManager {
    void execute(@NotNull VoidLambda lambda);
}
