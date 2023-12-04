package org.cmdfw

import org.slf4j.LoggerFactory
import java.lang.Exception
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread


internal class ExecutorManagerImpl(
    private val mode: ExecutionMode
) : ExecutorManager {
    private var pool: ThreadPoolExecutor? = null
    private val logger = LoggerFactory.getLogger(ExecutorManager::class.java)

    init {
        if(mode == ExecutionMode.ThreadPool) {
            pool = ThreadPoolExecutor(
                mode.getMinThreads(),
                if (mode.getMaxThreads() == null) {
                    mode.getMinThreads() * 2
                } else {
                    mode.getMaxThreads()!!
                },
                3,
                TimeUnit.SECONDS,
                LinkedBlockingQueue()
            )
        }
    }

    private fun executeTask(function: () -> Unit) {
        when(mode) {
            ExecutionMode.OwnThread -> thread { function() }
            ExecutionMode.SameThread -> function()
            ExecutionMode.ThreadPool -> pool!!.execute(function)
        }
    }

    override fun execute(lambda: VoidLambda) {
        executeTask {
            try {
                lambda.call()
            } catch (e: Exception) {
                logger.warn("Error on task", e)
            }
        }
    }
}