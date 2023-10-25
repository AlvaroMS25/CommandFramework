package commands;

import net.dv8tion.jda.api.audio.hooks.ConnectionStatus;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;
import net.dv8tion.jda.api.managers.AudioManager;
import org.cmdfw.extras.checks.BuiltinChecks;
import org.cmdfw.extras.music.GlobalMusicManager;
import org.cmdfw.extras.music.GuildMusicManager;
import org.cmdfw.extras.music.Source;
import org.cmdfw.extras.music.Track;
import org.cmdfw.message.MessageCommand;
import org.cmdfw.message.MessageCommandBuilder;
import org.cmdfw.message.MessageCommandContext;

import java.util.List;

public class MessagePlay implements MessageCommand {
    private GlobalMusicManager manager;
    public MessagePlay(GlobalMusicManager m) {
        manager = m;
    }

    @Override
    public void register(MessageCommandBuilder builder) {
        builder.setName("play")
                .setDescription("plays juan")
                .addChecks(BuiltinChecks::onlyGuilds, this::joinIfNeeded);
    }

    @Override
    public void execute(MessageCommandContext context) throws Exception {
        String url = context.getArgs().get(0);

        List<Track> tracks = manager.getGuildPlayer(context.getEvent().getGuild())
                .getTrackSearchHelper()
                .search(Source.YoutubeOrLink, url);

        tracks.get(0).enqueue();
    }

    public boolean joinIfNeeded(MessageCommandContext context) {
        GuildMusicManager m = manager.getGuildPlayer(context.getEvent().getGuild());
        AudioManager guildManager = context.getEvent().getGuild().getAudioManager();
        if(!guildManager.isConnected() && guildManager.getConnectionStatus() == ConnectionStatus.NOT_CONNECTED)
        {
            Member member = context.getEvent().getGuild().getMember(context.getEvent().getAuthor());

            if(member.getVoiceState() != null) {
                AudioChannelUnion c = member.getVoiceState().getChannel();
                guildManager.openAudioConnection(c);
            }
        }
        guildManager.setSendingHandler(m.getHandler());

        return true;
    }
}
