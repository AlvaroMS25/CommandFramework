package org.cmdfw.slash

import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions
import org.cmdfw.slash.builders.*
import java.util.function.Consumer

internal class CommandBuilder(
    container: InternalSlashCommandContainer,
    val slashCommand: SlashCommand
) : SlashCommandBuilder, InternalSlashCommand {
    private val arguments = mutableListOf<Argument>()
    private lateinit var name: String
    private lateinit var description: String
    private var defaultMemberPermissions: DefaultMemberPermissions = DefaultMemberPermissions.ENABLED
    private var guildOnly = false
    private var isNsfw = false

    init {
        slashCommand.register(this)
        container.registerCommand(this)
    }

    override fun setName(name: String): SlashCommandBuilder {
        this.name = name
        return this
    }

    override fun setDescription(description: String): SlashCommandBuilder {
        this.description = description
        return this
    }

    override fun setDefaultPermissions(permission: DefaultMemberPermissions): SlashCommandBuilder {
        this.defaultMemberPermissions = permission
        return this
    }

    override fun setGuildOnly(isOnlyGuilds: Boolean): SlashCommandBuilder {
        this.guildOnly = isOnlyGuilds
        return this
    }

    override fun setNsfw(isNsfw: Boolean): SlashCommandBuilder {
        this.isNsfw = isNsfw
        return this
    }

    override fun addArgument(): SlashCommandArgument {
        val arg = Argument(this)
        this.arguments.add(arg)
        return arg
    }

    override fun build(): Command {
        return Command(this)
    }

    override fun getArguments(): MutableList<Argument> {
        return arguments
    }

    override fun getSlashCommand(): SlashCommand {
        return slashCommand
    }

    override fun getNsfw(): Boolean {
        return isNsfw
    }

    override fun getGuildOnly(): Boolean {
        return guildOnly
    }

    override fun getDefaultPermissions(): DefaultMemberPermissions {
        return defaultMemberPermissions
    }

    override fun getName(): String {
        return name
    }

    override fun getDescription(): String {
        return description
    }
}

internal class DeferredCommandBuilder(
    container: InternalSlashCommandContainer
): DeferredSlashCommandBuilder, InternalSlashCommand {
    private val arguments = mutableListOf<Argument>()
    private lateinit var name: String
    private lateinit var description: String
    private lateinit var handler: Consumer<SlashCommandContext>
    private var defaultMemberPermissions: DefaultMemberPermissions = DefaultMemberPermissions.ENABLED
    private var guildOnly = false
    private var isNsfw = false

    init {
        container.registerCommand(this)
    }

    override fun setName(name: String): DeferredSlashCommandBuilder {
        this.name = name
        return this
    }

    override fun setDescription(description: String): DeferredSlashCommandBuilder {
        this.description = description
        return this
    }

    override fun setDefaultPermissions(permission: DefaultMemberPermissions): DeferredSlashCommandBuilder {
        this.defaultMemberPermissions = permission
        return this
    }

    override fun setGuildOnly(isOnlyGuilds: Boolean): DeferredSlashCommandBuilder {
        this.guildOnly = isOnlyGuilds
        return this
    }

    override fun setNsfw(isNsfw: Boolean): DeferredSlashCommandBuilder {
        this.isNsfw = isNsfw
        return this
    }

    override fun addArgument(): SlashCommandArgument {
        val arg = Argument(this)
        this.arguments.add(arg)
        return arg
    }

    override fun setHandler(handler: Consumer<SlashCommandContext>): DeferredSlashCommandBuilder? {
        this.handler = handler
        return this
    }

    override fun build(): Command {
        TODO()
    }

    override fun getArguments(): MutableList<Argument> {
        return arguments
    }

    override fun getSlashCommand(): SlashCommand {
        val handler = this.handler

        return object : SlashCommand {
            override fun register(builder: SlashCommandBuilder?) {
                throw UnsupportedOperationException(
                    "Unreachable code entered"
                )
            }

            override fun execute(context: SlashCommandContext?) {
                if(context != null)
                    handler.accept(context)
            }
        }
    }

    override fun getNsfw(): Boolean {
        return isNsfw
    }

    override fun getGuildOnly(): Boolean {
        return guildOnly
    }

    override fun getDefaultPermissions(): DefaultMemberPermissions {
        return defaultMemberPermissions
    }

    override fun getName(): String {
        return name
    }

    override fun getDescription(): String {
        return description
    }
}

internal class SimpleBuilder(
    private val container: InternalSlashCommandContainer?,
): SimpleGroupBuilder, InternalSlashCommandContainer, BuildableContainer<SubCommandGroupImpl>
{
    lateinit var name: String
    lateinit var description: String
    var defaultMemberPermissions: DefaultMemberPermissions = DefaultMemberPermissions.ENABLED
    var guildOnly = false
    var isNsfw = false
    val commands = mutableListOf<InternalSlashCommand>()

    init {
        container?.registerSimpleGroup(this)
    }

    fun register(group: SubCommandGroup): Boolean {
        group.register(this)
        return this.commands.size > 0
    }

    fun getAsMap(): MutableMap<String, Command> {
        val map = mutableMapOf<String, Command>()

        for(c in this.commands) {
            map[c.getName()] = c.build()
        }

        return map
    }

    override fun setName(name: String): SimpleGroupBuilder {
        this.name = name
        return this
    }

    override fun addCommand(): DeferredSlashCommandBuilder {
        return DeferredCommandBuilder(this)
    }


    override fun addCommand(command: SlashCommand): SimpleGroupBuilder {
        CommandBuilder(this, command)
        return this
    }

    override fun setDescription(description: String): SimpleGroupBuilder {
        this.description = description
        return this
    }

    override fun setDefaultPermissions(permission: DefaultMemberPermissions): SimpleGroupBuilder {
        this.defaultMemberPermissions = permission
        return this
    }

    override fun setGuildOnly(isOnlyGuilds: Boolean): SimpleGroupBuilder {
        this.guildOnly = isOnlyGuilds
        return this
    }

    override fun setNsfw(isNsfw: Boolean): SimpleGroupBuilder {
        this.isNsfw = isNsfw
        return this
    }

    override fun build(): SubCommandGroupImpl {
        return SubCommandGroupImpl(this)
    }

    override fun registerCommand(builder: InternalSlashCommand) {
        this.commands.add(builder)
    }

}

internal class GroupBuilder
    : SubcommandGroupBuilder, InternalSlashCommandContainer, BuildableContainer<SubCommandGroupImpl>
{
    lateinit var name: String
    lateinit var description: String
    var defaultMemberPermissions: DefaultMemberPermissions = DefaultMemberPermissions.ENABLED
    var guildOnly = false
    var isNsfw = false
    val groups = mutableListOf<SimpleBuilder>()

    fun register(group: SubCommandGroup): Boolean {
        group.register(this)
        return this.groups.size > 0
    }

    override fun setGuildOnly(isOnlyGuilds: Boolean): SubcommandGroupBuilder {
        this.guildOnly = isOnlyGuilds
        return this
    }

    override fun setNsfw(isNsfw: Boolean): SubcommandGroupBuilder {
        this.isNsfw = isNsfw
        return this
    }

    override fun setDefaultPermissions(permission: DefaultMemberPermissions): SubcommandGroupBuilder {
        this.defaultMemberPermissions = permission
        return this
    }

    override fun setDescription(description: String): SubcommandGroupBuilder {
        this.description = description
        return this
    }

    override fun setName(name: String): SubcommandGroupBuilder {
        this.name = name
        return this
    }

    override fun addGroup(): SimpleGroupBuilder {
        return SimpleBuilder(this)
    }

    override fun registerSimpleGroup(builder: SimpleBuilder) {
        this.groups.add(builder)
    }

    override fun build(): SubCommandGroupImpl {
        return SubCommandGroupImpl(this)
    }
}
