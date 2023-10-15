package org.cmdfw.slash

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.Commands

internal class SlashCommandContextImpl(
    private val event: SlashCommandInteractionEvent
) : SlashCommandContext {
    override fun getJda(): JDA = event.jda

    override fun getEvent(): SlashCommandInteractionEvent = event
}

internal class AutocompleteContextImpl(private val event: CommandAutoCompleteInteractionEvent) : AutocompleteContext {
    override fun getEvent(): CommandAutoCompleteInteractionEvent {
        return this.event
    }

    override fun getJda(): JDA {
        return this.event.jda
    }

    override fun getFocusedContent(): String {
        return this.event.focusedOption.value
    }

    override fun getFocusedType(): OptionType {
        return this.event.focusedOption.type
    }

}
