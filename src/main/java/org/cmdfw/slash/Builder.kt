package org.cmdfw.slash

import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions
import org.cmdfw.slash.builders.*

internal class CommandBuilder(
    container: InternalSlashCommandContainer,
    val slashCommand: SlashCommand
) : BuildableContainer<Command>, SlashCommandBuilder {
    val arguments = mutableListOf<Argument>()
    lateinit var name: String
    lateinit var description: String
    var defaultMemberPermissions: DefaultMemberPermissions = DefaultMemberPermissions.ENABLED
    var guildOnly = false
    var isNsfw = false

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
}

internal class DeferredCommandBuilder: BuildableContainer<Command>, SlashCommandBuilder {
    val arguments = mutableListOf<Argument>()
    lateinit var name: String
    lateinit var description: String
    var defaultMemberPermissions: DefaultMemberPermissions = DefaultMemberPermissions.ENABLED
    var guildOnly = false
    var isNsfw = false

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
        TODO()
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
    val commands = mutableListOf<CommandBuilder>()

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
            map[c.name] = c.build()
        }

        return map
    }

    override fun setName(name: String): SimpleGroupBuilder {
        this.name = name
        return this
    }

    override fun addCommand(): DeferredSlashCommandBuilder {
        TODO()
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

    override fun registerCommand(builder: CommandBuilder) {
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
