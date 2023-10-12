package org.cmdfw.message

import java.util.function.Function

internal open class BuilderImpl(val messageCommand: MessageCommand) : MessageCommandBuilder {
    val subCommands: MutableList<BuilderImpl> = mutableListOf()
    lateinit var name: String
    lateinit var description: String
    val aliases: MutableList<String> = mutableListOf()
    val checks: MutableList<Function<MessageCommandContext, Boolean>> = mutableListOf()

    override fun setName(name: String): MessageCommandBuilder {
        this.name = name
        return this
    }

    override fun setDescription(description: String): MessageCommandBuilder {
        this.description = description
        return this
    }

    override fun setAliases(vararg aliases: String?): MessageCommandBuilder {
        this.aliases.addAll(aliases.filterNotNull())
        return this
    }

    override fun addSubcommands(vararg commands: MessageCommand?): MessageCommandBuilder {
        this.subCommands.addAll(commands.filterNotNull().map {
            val impl = BuilderImpl(it)
            it.register(impl)
            return@map impl
        })
        return this
    }

    override fun addChecks(vararg functions: Function<MessageCommandContext, Boolean>?): MessageCommandBuilder {
        this.checks.addAll(functions.filterNotNull())
        return this
    }

    fun build(): Command {
        this.messageCommand.register(this)
        return Command(this)
    }

}