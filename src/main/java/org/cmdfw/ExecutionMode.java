package org.cmdfw;

import org.jetbrains.annotations.Nullable;

public enum ExecutionMode {
    /**
     * Executes handlers on their own thread each, allowing handlers to block
     * without worrying about gateway disconnection.
     */
    OwnThread,
    /**
     * Executes handlers on the thread provided by JDA.
     * If using this method, <code>.queue</code> should always be used.
     */
    SameThread,
    /**
     * Executes handlers on an internal thread pool. This pool can be
     * configured to modify the behaviour of the framework.
     */
    ThreadPool;

    Integer maxThreads;
    Integer minThreads;

    public int getMinThreads() {
        return minThreads != null ? minThreads
                : Runtime.getRuntime().availableProcessors();
    }

    public @Nullable Integer getMaxThreads() {
        return maxThreads;
    }
}
