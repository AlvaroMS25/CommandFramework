package org.cmdfw.message

import java.util.function.Function

internal class Command(builderImpl: BuilderImpl) {
    private val command: MessageCommand
    private val subCommands: MutableList<Command>
    private val name: String
    private val description: String
    private val aliases: MutableList<String>
    private val checks: MutableList<Function<MessageCommandContext, Boolean>>

    init {
        this.command = builderImpl.messageCommand
        this.subCommands = builderImpl.subCommands.map { it.build() }.toMutableList()
        this.name = builderImpl.name
        this.description = builderImpl.description
        this.aliases = builderImpl.aliases
        this.checks = builderImpl.checks
    }

    fun hasName(name: String): Boolean {
        return this.name == name
    }

    private fun hasChild(name: String) : Command? {
        for(impl in this.subCommands) {
            if(impl.name == name) {
                return impl
            }
        }

        return null
    }

    fun getSubcommands(items: MutableList<String>) : Command {
        if(items.isNotEmpty()) {
            val next = items.removeFirst()

            val nextChild = this.hasChild(next)

            if (nextChild != null)
                return nextChild.getSubcommands(items)

            items.add(0, next)
        }

        return this
    }

    @Throws(Exception::class)
    fun execute(context: MessageCommandContext) {
        if(this.command.before(context) && this.executeChecks(context)) {
            this.command.execute(context)
            this.command.after(context)
        }
    }

    private fun executeChecks(context: MessageCommandContext) : Boolean {
        for(check in this.checks) {
            if(!check.apply(context))
                return false
        }

        return true
    }
}