package org.example.creditbot.command;

import org.example.creditbot.service.AnalyticsService;
import org.example.creditbot.session.ManagerSessionRepository;

public class ManagerCommand implements Command {

    private final AnalyticsService analyticsService;
    private final ManagerSessionRepository managerSessionRepository;


    public ManagerCommand(AnalyticsService analyticsService,
                          ManagerSessionRepository managerSessionRepository) {

        this.analyticsService = analyticsService;
        this.managerSessionRepository = managerSessionRepository;
    }


    @Override
    public String getCommand() {
        return "/manager";
    }


    @Override
    public String execute(long chatId, String[] args) {


        if (!managerSessionRepository.isManager(chatId)) {

            return """
                    ❌ Доступ запрещен.

                    Для просмотра статистики выполните:

                    /login
                    """;
        }


        return """
                📊 Статистика

                Всего запросов:
                %d

                Средняя сумма кредита:
                %.2f

                Популярный тип платежа:
                %s
                """.formatted(
                analyticsService.getTotalRequests(),
                analyticsService.getAverageAmount(),
                analyticsService.getMostPopularPaymentType()
        );
    }
}