import commands.*;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.cmdfw.Framework;
import org.cmdfw.extras.music.GlobalMusicManager;
import org.cmdfw.slash.SlashCommandManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.nio.file.Path;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainTest {
    Dotenv dotenv;
    String token;
    String appId;

    @BeforeAll
    public void createDotenv() throws Exception {
        dotenv = Dotenv.configure()
                .directory(Path.of("", "src/test/resources").toString())
                .filename(".env")
                .load();

        token = dotenv.get("TOKEN");
        appId = dotenv.get("APP_ID");
    }

    @Test
    public void mainTest() throws InterruptedException {
        JDA j = JDABuilder.createDefault(token, GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS))
                .build();

        Framework f = new Framework(j);
        GlobalMusicManager musicManager = new GlobalMusicManager();

        f.getCommandManager().register(new MessageTestImpl()).register(new MessagePlay(musicManager));
        SlashCommandManager manager = f.getSlashCommandManager();
        manager.register(new SlashSimple());
        manager.register(new SlashSimpleGroup());
        manager.register(new SlashSubcommandGroup())
            .register(new Managed());

        j.addEventListener(f.getEventListener());
        j.awaitReady();

        System.out.println("Ready!");
        manager.registerAtGuild(<guild>);
        while (true) {
            Thread.sleep(Long.MAX_VALUE);
        }
    }
}
