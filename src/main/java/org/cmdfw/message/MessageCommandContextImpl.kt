package org.cmdfw.message

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.message.GenericMessageEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

internal class MessageCommandContextImpl(
    private val jda: JDA,
    private val event: MessageReceivedEvent,
    private val args: MutableList<String>
) : MessageCommandContext {
    override fun getArgs(): MutableList<String> = args

    override fun getJda(): JDA = jda

    override fun getEvent(): MessageReceivedEvent = event
}