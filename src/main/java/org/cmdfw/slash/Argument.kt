package org.cmdfw.slash

import net.dv8tion.jda.api.interactions.commands.Command
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import org.cmdfw.exceptions.UnsupportedChoicesException
import org.cmdfw.slash.builders.SlashCommandArgument
import org.cmdfw.slash.builders.SlashCommandBuilder
import java.util.function.Function

internal class Argument(
    private val parent: SlashCommandBuilder
) : SlashCommandArgument {
    lateinit var name: String
    lateinit var description: String
    var required = true
    lateinit var optionType: OptionType
    val choices: MutableList<NameValue> = mutableListOf()
    var autocompleteProvider: Function<AutocompleteContext, Void>? = null
    var maxValue: Number? = null
    var minValue: Number? = null
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

    override fun setRequired(required: Boolean): SlashCommandArgument {
        this.required = required
        return this
    }

    override fun setMinValue(value: Number): SlashCommandArgument? {
        this.minValue = value
        return this
    }

    override fun setMaxValue(value: Number): SlashCommandArgument? {
        this.maxValue = value
        return this
    }

    @Throws(UnsupportedChoicesException::class)
    override fun addChoices(vararg choices: NameValue?): SlashCommandArgument {
        if(!this.optionType.canSupportChoices())
            throw UnsupportedChoicesException()
        this.choices.addAll(choices.filterNotNull())
        return this
    }

    override fun finish(): SlashCommandBuilder {
        return this.parent
    }

    private fun setValues(data: OptionData) {
        this.minValue?.let {
            if (it is Float || it is Double) {
                data.setMinValue(it as Double)
            } else {
                data.setMinValue(it as Long)
            }
        }

        this.maxValue?.let {
            if (it is Float || it is Double) {
                data.setMinValue(it as Double)
            } else {
                data.setMinValue(it as Long)
            }
        }
    }

    fun asOption(): OptionData {
        val data = OptionData(optionType, name, description)

        setValues(data)

        data.setAutoComplete(this.autocompleteProvider != null)
            .setRequired(this.required)
            .addChoices(this.choices.map { Command.Choice(it.name, it.value) })

        return data
    }
}