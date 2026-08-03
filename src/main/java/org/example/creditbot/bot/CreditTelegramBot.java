package org.example.creditbot.bot;

import org.example.creditbot.command.CommandDispatcher;
import org.example.creditbot.config.BotConfig;
import org.example.creditbot.handler.MessageHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.example.creditbot.handler.LoginHandler;
import org.example.creditbot.util.TelegramMessageSplitter;

public class CreditTelegramBot extends TelegramLongPollingBot{

    private final BotConfig config;
    private final CommandDispatcher dispatcher;
    private final MessageHandler messageHandler;
    private final LoginHandler loginHandler;

    private final TelegramMessageSplitter splitter=
            new TelegramMessageSplitter();

    public CreditTelegramBot(BotConfig config,
                             CommandDispatcher dispatcher,
                             MessageHandler messageHandler,
                             LoginHandler loginHandler){
        this.config = config;
        this.dispatcher = dispatcher;
        this.messageHandler = messageHandler;
        this.loginHandler = loginHandler;
    }
    private void sendMessage(long chatId, String text){
        if (text==null || text.isBlank()){
            return;
        }

        for(String part : splitter.split(text)){

            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(chatId));
            message.setText(part);
            try {
                execute(message);
            }catch (TelegramApiException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotToken(){
        return config.getBotToken();
    }

    @Override
    public String getBotUsername(){
        return config.getBotName();
    }

    @Override
    public void onUpdateReceived(Update update){
        if(!update.hasMessage() || !update.getMessage().hasText()){
            return;
        }

        long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();

        String response;

        if(text.startsWith("/")){
            response = dispatcher.dispatch(chatId, text);
        } else {
            String loginResponse = loginHandler.handle(chatId,text);

            if(!loginResponse.isEmpty()){
                response=loginResponse;
            }else {
                response=messageHandler.handle(chatId, text);
            }
        }

        sendMessage(chatId,response);
    }
}