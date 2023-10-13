package org.cmdfw.slash

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.interactions.commands.OptionType
import java.util.function.Function

internal class Argument(
    private val parent: BuilderImpl
) : SlashCommandArgument {
    private lateinit var name: String
    private lateinit var description: String
    private lateinit var jda: JDA
    private lateinit var optionType: OptionType
    private val choices: MutableList<NameValue> = mutableListOf()
    private var autocompleteProvider: Function<AutocompleteContext, Void>? = null
    override fun autocomplete(provider: Function<AutocompleteContext, Void>?): SlashCommandArgument {
        this.autocompleteProvider = provider
        return this
    }

    override fun setOptionType(t: OptionType): SlashCommandArgument {
        this.optionType = t
        return this
    }

    override fun setName(name: String): SlashCommandArgument {
        this.name = name
        return this
    }

    override fun setDescription(description: String): SlashCommandArgument {
        this.description = description
        return this
    }

    override fun addChoices(vararg choices: NameValue?): SlashCommandArgument {
        this.choices.addAll(choices.filterNotNull())
        return this
    }

    override fun finish(): SlashCommandBuilder {
        this.parent.arguments.add(this)
        return this.parent
    }
}