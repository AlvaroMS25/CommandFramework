package commands;

import org.cmdfw.message.MessageCommand;
import org.cmdfw.message.MessageCommandBuilder;
import org.cmdfw.message.MessageCommandContext;

public class MessageTestImpl implements MessageCommand {
    @Override
    public void register(MessageCommandBuilder builder) {
        builder.setName("ping")
                .setDescription("Responds with pong")
                .addChecks(c -> {
                    System.out.println("Executing check");
                    return !c.getEvent().getAuthor().isBot();
                })
                .setAliases("juanpedrioaymimadrejesu");
    }

    @Override
    public void execute(MessageCommandContext context) throws Exception {
        context.getEvent().getChannel().sendMessage("Pong").queue();
    }

    @Override
    public boolean before(MessageCommandContext context) {
        System.out.println("Before hook executed");
        return true;
    }

    @Override
    public void after(MessageCommandContext context) {
        System.out.println("After hook executed");
    }
}
