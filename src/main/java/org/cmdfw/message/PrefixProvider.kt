package org.cmdfw.message

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import java.util.function.Function
import java.util.function.Supplier

class PrefixProvider {
    private var provider: (MessageReceivedEvent) -> String

    constructor(prefix: String) {
        this.provider = { prefix }
    }

    constructor(provider: Function<MessageReceivedEvent, String>) {
        this.provider = { provider.apply(it) }
    }

    internal fun get(event: MessageReceivedEvent): String {
        return provider(event)
    }

    fun setPrefix(prefix: String) {
        this.provider = { prefix }
    }

    fun setPrefix(provider: Function<MessageReceivedEvent, String>) {
        this.provider = { provider.apply(it) }
    }
}