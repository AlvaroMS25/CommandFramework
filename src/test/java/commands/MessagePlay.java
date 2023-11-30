package commands;

import net.dv8tion.jda.api.audio.hooks.ConnectionStatus;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;
import net.dv8tion.jda.api.managers.AudioManager;
import org.cmdfw.exceptions.NoGuildSetException;
import org.cmdfw.extras.BuiltinChecks;
import org.cmdfw.extras.music.*;
import org.cmdfw.message.MessageCommand;
import org.cmdfw.message.MessageCommandBuilder;
import org.cmdfw.message.MessageCommandContext;

public class MessagePlay implements MessageCommand {
    private GlobalMusicManager manager;
    public MessagePlay(GlobalMusicManager m) {
        manager = m;
    }

    @Override
    public void register(MessageCommandBuilder builder) {
        builder.setName("play")
                .setDescription("plays juan")
                .addChecks(BuiltinChecks::onlyGuilds, BuiltinChecks::userConnectedToVoiceChannel, this::joinIfNeeded);
    }

    @Override
    public void execute(MessageCommandContext context) throws Exception {
        String url = context.getArgs().get(0);

        GuildItem track = manager.getGuildPlayer(context.getEvent().getGuild())
                .getTrackSearchHelper()
                .search(Source.YoutubeOrLink, url);

        if(track != null){
            track.enqueue();
        }
    }

    public boolean joinIfNeeded(MessageCommandContext context) {
        GuildMusicManager m = manager.getGuildPlayer(context.getEvent().getGuild());
        try {
            GuildVoiceUtils utils = m.getUtils();
            if(utils.isConnectedToVoice()) {
                return true;
            }

            Member member = context.getEvent().getGuild().getMember(context.getEvent().getAuthor());

            if (member.getVoiceState() != null) {
                utils.joinChannel(member.getVoiceState().getChannel());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
