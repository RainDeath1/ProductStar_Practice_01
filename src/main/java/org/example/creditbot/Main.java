package org.example.creditbot;

import org.example.creditbot.bot.CreditTelegramBot;
import org.example.creditbot.command.CalculateCommand;
import org.example.creditbot.command.CommandDispatcher;
import org.example.creditbot.command.HistoryCommand;
import org.example.creditbot.command.LoginCommand;
import org.example.creditbot.command.ManagerCommand;
import org.example.creditbot.command.StartCommand;
import org.example.creditbot.config.BotConfig;
import org.example.creditbot.factory.CalculatorFactory;
import org.example.creditbot.handler.LoginHandler;
import org.example.creditbot.handler.MessageHandler;
import org.example.creditbot.repository.RequestRepository;
import org.example.creditbot.service.AnalyticsService;
import org.example.creditbot.service.CreditService;
import org.example.creditbot.service.HistoryService;
import org.example.creditbot.session.LoginSessionRepository;
import org.example.creditbot.session.ManagerSessionRepository;
import org.example.creditbot.session.SessionRepository;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {

    public static void main(String[] args) {

        try {

            BotConfig config = new BotConfig();


            // Хранилище запросов
            RequestRepository requestRepository =
                    new RequestRepository();


            // Сессии расчета кредита
            SessionRepository sessionRepository =
                    new SessionRepository();


            // Сессии авторизации
            LoginSessionRepository loginSessionRepository =
                    new LoginSessionRepository();


            // Авторизованные менеджеры
            ManagerSessionRepository managerSessionRepository =
                    new ManagerSessionRepository();



            CalculatorFactory calculatorFactory =
                    new CalculatorFactory();



            CreditService creditService =
                    new CreditService(
                            calculatorFactory,
                            requestRepository
                    );


            HistoryService historyService =
                    new HistoryService(
                            requestRepository
                    );


            AnalyticsService analyticsService =
                    new AnalyticsService(
                            requestRepository
                    );



            MessageHandler messageHandler =
                    new MessageHandler(
                            sessionRepository,
                            creditService
                    );



            LoginHandler loginHandler =
                    new LoginHandler(
                            loginSessionRepository,
                            managerSessionRepository,
                            config
                    );



            CommandDispatcher dispatcher =
                    new CommandDispatcher(

                            new StartCommand(),

                            new CalculateCommand(
                                    sessionRepository
                            ),

                            new HistoryCommand(
                                    historyService
                            ),

                            new LoginCommand(
                                    loginSessionRepository
                            ),

                            new ManagerCommand(
                                    analyticsService,
                                    managerSessionRepository
                            )
                    );



            TelegramBotsApi botsApi =
                    new TelegramBotsApi(
                            DefaultBotSession.class
                    );


            botsApi.registerBot(
                    new CreditTelegramBot(
                            config,
                            dispatcher,
                            messageHandler,
                            loginHandler
                    )
            );


            System.out.println("Бот успешно запущен!");


        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}