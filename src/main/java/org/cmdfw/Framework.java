package org.cmdfw;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.hooks.EventListener;
import org.cmdfw.message.Manager;
import org.cmdfw.message.MessageCommandManager;
import org.cmdfw.message.PrefixProvider;
import org.cmdfw.slash.SlashCommandManager;
import org.jetbrains.annotations.NotNull;

public class Framework {
    @NotNull private PrefixProvider provider;
    @NotNull private JDA jda;

    @NotNull private Manager messageCommandManager;
    @NotNull private org.cmdfw.slash.Manager slashCommandManager;

    public Framework(@NotNull JDA jda, @NotNull PrefixProvider provider) {
        this.jda = jda;
        this.provider = provider;
        this.messageCommandManager = new Manager(jda, provider, " ");
        this.slashCommandManager = new org.cmdfw.slash.Manager(jda);
    }
    public Framework(JDA jda) {
        this(jda, new PrefixProvider("!"));
    }

    @NotNull
    public JDA getJda() {
        return jda;
    }

    public EventListener getEventListener() {
        return new EventListenerImpl(this);
    }

    public void setPrefixProvider(PrefixProvider provider) {
        this.provider = provider;
    }

    public PrefixProvider getPrefixProvider() {
        return this.provider;
    }

    public void withSeparator(String separator) {
        this.messageCommandManager.setSeparator(separator);
    }

    public MessageCommandManager getCommandManager() {
        return this.messageCommandManager;
    }

    public SlashCommandManager getSlashCommandManager() {
        return this.slashCommandManager;
    }
}
