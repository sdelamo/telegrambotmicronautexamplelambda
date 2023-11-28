
package telegrambot;

import io.micronaut.chatbots.core.SpaceParser;
import io.micronaut.chatbots.telegram.api.Chat;
import io.micronaut.chatbots.telegram.api.Update;
import io.micronaut.chatbots.telegram.api.send.SendMessage;
import io.micronaut.chatbots.telegram.core.SendMessageUtils;
import io.micronaut.chatbots.telegram.core.TelegramBotConfiguration;
import io.micronaut.chatbots.telegram.core.TelegramHandler;
import io.micronaut.chatbots.telegram.core.TelegramSlashCommandParser;
import jakarta.inject.Singleton;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

@Singleton
class UppercaseHandler implements TelegramHandler<SendMessage> {

    public static final String CMD = "/uppercase";
    private final SpaceParser<Update, Chat> spaceParser;
    private final TelegramSlashCommandParser commandParser;
    UppercaseHandler(TelegramSlashCommandParser commandParser,
                     SpaceParser<Update, Chat> spaceParser) {
        this.spaceParser = spaceParser;
        this.commandParser = commandParser;
    }

    @Override
    public boolean canHandle(TelegramBotConfiguration bot, @NotNull Update input) {
        return commandParser.parse(input).filter(cmd -> cmd.startsWith(CMD)).isPresent();
    }

    @Override
    public Optional<SendMessage> handle(TelegramBotConfiguration bot, @NotNull Update input) {
        return SendMessageUtils.compose(spaceParser, input, input.getMessage().getText().replaceAll(CMD, "").toUpperCase());
    }
}