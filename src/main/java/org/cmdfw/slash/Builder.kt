package org.cmdfw.slash

import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions
import org.cmdfw.slash.builders.*
import java.lang.RuntimeException

internal class BaseBuilder(
    private val slashCommand: SlashCommand
) : SlashCommandBuilder, InternalSlashCommandContainer
{
    private val commands = mutableListOf<CommandBuilder>()
    private val simpleGroups = mutableListOf<SimpleBuilder>()
    private val subcommandGroups = mutableListOf<GroupBuilder>()
    fun build(manager: Manager) {
        this.slashCommand.register(this)

        for(c in this.commands) {
            manager.commands[c.name] = c.build()
        }

        for(sg in this.simpleGroups) {
            manager.groups[sg.name] = sg.build()
        }

        for(scg in this.subcommandGroups) {
            manager.groups[scg.name] = scg.build()
        }
    }

    override fun simple(): SimpleCommandBuilder {
        return CommandBuilder(this)
    }

    override fun addSubcommands(): SimpleGroupBuilder {
        return SimpleBuilder(this)
    }

    override fun addSubcommandGroup(): SubcommandGroupBuilder {
        return GroupBuilder(this)
    }

    override fun registerCommand(builder: CommandBuilder) {
        this.commands.add(builder)
    }

    override fun registerSimpleGroup(builder: SimpleBuilder) {
        this.simpleGroups.add(builder)
    }

    override fun registerSubCommandGroup(builder: GroupBuilder) {
        this.subcommandGroups.add(builder)
    }
}

private class OnlySimpleCommandsBuilder() : SlashCommandBuilder, InternalSlashCommandContainer {
    var command: CommandBuilder? = null
    override fun registerCommand(builder: CommandBuilder) {
        if(this.command == null)
            this.command = builder
        else
            throw RuntimeException("Only one command expected")
    }
    override fun simple(): SimpleCommandBuilder {
        return CommandBuilder(this)
    }

    override fun addSubcommands(): SimpleGroupBuilder {
        throw RuntimeException("Only simple commands supported")
    }

    override fun addSubcommandGroup(): SubcommandGroupBuilder {
        throw RuntimeException("Only simple commands supported")
    }

}

internal class CommandBuilder(
    private val container: InternalSlashCommandContainer
) : SimpleCommandBuilder, BuildableContainer<Command> {
    val arguments = mutableListOf<Argument>()
    lateinit var name: String
    lateinit var description: String
    var defaultMemberPermissions: DefaultMemberPermissions = DefaultMemberPermissions.ENABLED
    var guildOnly = false
    var isNsfw = false

    init {
        container.registerCommand(this)
    }

    override fun setName(name: String): SimpleCommandBuilder {
        this.name = name
        return this
    }

    override fun setDescription(description: String): SimpleCommandBuilder {
        this.description = description
        return this
    }

    override fun setDefaultPermissions(permission: DefaultMemberPermissions): SimpleCommandBuilder {
        this.defaultMemberPermissions = permission
        return this
    }

    override fun setGuildOnly(isOnlyGuilds: Boolean): SimpleCommandBuilder {
        this.guildOnly = isOnlyGuilds
        return this
    }

    override fun setNsfw(isNsfw: Boolean): SimpleCommandBuilder {
        this.isNsfw = isNsfw
        return this
    }

    override fun addArgument(): SlashCommandArgument {
        return Argument(this)
    }

    override fun build(): Command {
        return Command(this)
    }
}

internal class SimpleBuilder(
    private val container: InternalSlashCommandContainer
): SimpleGroupBuilder, InternalSlashCommandContainer, BuildableContainer<SubcommandGroup>
{
    lateinit var name: String
    lateinit var description: String
    var defaultMemberPermissions: DefaultMemberPermissions = DefaultMemberPermissions.ENABLED
    var guildOnly = false
    var isNsfw = false
    val commands = mutableListOf<CommandBuilder>()

    init {
        container.registerSimpleGroup(this)
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

    override fun buildCommand(): SimpleCommandBuilder {
        return CommandBuilder(this)
    }


    override fun addCommand(command: SlashCommand): SimpleGroupBuilder {
        val builder = OnlySimpleCommandsBuilder()
        command.register(builder)

        if(builder.command != null)
            this.commands.add(builder.command!!)
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

    override fun build(): SubcommandGroup {
        return SubcommandGroup(this)
    }

    override fun registerCommand(builder: CommandBuilder) {
        this.commands.add(builder)
    }

}

internal class GroupBuilder(
    private val container: InternalSlashCommandContainer
): SubcommandGroupBuilder, InternalSlashCommandContainer, BuildableContainer<SubcommandGroup>
{
    lateinit var name: String
    lateinit var description: String
    var defaultMemberPermissions: DefaultMemberPermissions = DefaultMemberPermissions.ENABLED
    var guildOnly = false
    var isNsfw = false
    val groups = mutableListOf<SimpleBuilder>()

    init {
        container.registerSubCommandGroup(this)
    }

    override fun registerCommand(builder: CommandBuilder) {
        TODO("Not yet implemented")
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

    override fun build(): SubcommandGroup {
        return SubcommandGroup(this)
    }
}
