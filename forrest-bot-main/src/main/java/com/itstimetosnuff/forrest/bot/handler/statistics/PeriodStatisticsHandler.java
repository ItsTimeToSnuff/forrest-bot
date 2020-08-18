package com.itstimetosnuff.forrest.bot.handler.statistics;

import com.itstimetosnuff.forrest.bot.dto.StatisticsDto;
import com.itstimetosnuff.forrest.bot.handler.AbsBaseHandler;
import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.utils.Buttons;
import com.itstimetosnuff.forrest.bot.utils.MainMenuKeyboard;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;

@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public class PeriodStatisticsHandler extends AbsBaseHandler {

    public PeriodStatisticsHandler(Session session) {
        super(session);
    }

    @Override
    public BotApiMethod handleEvent(Update update) {
        variablesInit(update);
        StatisticsDto statisticsDto;
        if (data.equals(Buttons.STATISTICS_MONTH)) {
            statisticsDto = session.getGoogleService().statisticsGetMonth(LocalDate.now());
            return sendMessage(
                    formatDto(statisticsDto),
                    MainMenuKeyboard.back()
            );
        }
        if (data.equals(Buttons.STATISTICS_YEAR)) {
            statisticsDto = session.getGoogleService().statisticsGetYear(LocalDate.now());
            return sendMessage(
                    formatDto(statisticsDto),
                    MainMenuKeyboard.back()
            );
        }
        return null;
    }

    private String formatDto(StatisticsDto statisticsDto) {
        return "За <b>" + statisticsDto.getPeriod() + "</b>:" + "\n\n" +
                "<b><i>------------Статистика по кассе-------------</i></b>" + "\n" +
                "<b>Оборот</b>: " + statisticsDto.getRevenue() + " грн\n" +
                "<b>Чистая пибыль</b>: " + statisticsDto.getNetIncome() + " грн\n" +
                "<b>Потрачено</b>: " + statisticsDto.getSpendMoney() + " грн\n\n" +
                "<b><i>------------Статистика по играм------------</i></b>" + "\n" +
                "<b>Всего игр</b>: " + statisticsDto.getTotalGames() + " игр\n" +
                "<b>Покупон</b>: " + statisticsDto.getPokuponGames() + " игр\n" +
                "<b>Обычные</b>: " + statisticsDto.getUsualGame() + " игр\n" +
                "<b>Всего игроков</b>: " + statisticsDto.getTotalPeople() + " чел\n" +
                "<b>Сидели на беседках</b>: " + statisticsDto.getGazeboTimes() + " часов\n\n" +
                "<b><i>------Статистика затрат со склада------</i></b>" + "\n" +
                "<b>Шаров</b>: " + statisticsDto.getSpendBalls() + " ящиков\n" +
                "<b>Гранат</b>: " + statisticsDto.getSpendGrenades() + " шт\n" +
                "<b>Маленьких флешек</b>: " + statisticsDto.getSpendFlashS() + " шт\n" +
                "<b>Средних флешек</b>: " + statisticsDto.getSpendFlashM() + " шт\n" +
                "<b>Больших дымов</b>: " + statisticsDto.getSpendSmokeL() + " шт\n" +
                "<b>Салфеток</b>: " + statisticsDto.getSpendNapkins() + " пачек\n" +
                "<b>Моющего стредства</b>: " + statisticsDto.getSpendClean() + " бут\n";
    }
}
