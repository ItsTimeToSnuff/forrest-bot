package com.itstimetosnuff.forrest.bot.handler.statistics;

import com.itstimetosnuff.forrest.bot.dto.StatisticsDto;
import com.itstimetosnuff.forrest.bot.handler.AbsBaseHandler;
import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.utils.Buttons;
import com.itstimetosnuff.forrest.bot.utils.MainMenuKeyboard;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public class PeriodStatisticsHandler extends AbsBaseHandler {

    public PeriodStatisticsHandler(Session session) {
        super(session);
    }

    @Override
    public BotApiMethod handleEvent(Update update) {
        variablesInit(update);
        StatisticsDto statisticsDto;
        if (data.equals(Buttons.STATISTICS_MONTH)){
            statisticsDto = new StatisticsDto();
            return sendMessage(
                    formatDto(statisticsDto),
                    MainMenuKeyboard.back()
            );
        }
        if (data.equals(Buttons.STATISTICS_YEAR)){
            statisticsDto = new StatisticsDto();
            return sendMessage(
                    formatDto(statisticsDto),
                    MainMenuKeyboard.back()
            );
        }
        return null;
    }

    private String formatDto(StatisticsDto statisticsDto) {
        return "За <b>" + statisticsDto.getPeriod() + "</b>:" + "\n" +
                "<b>Заработано</b>: " + statisticsDto.getEarnMoney() + " грн\n" +
                "<b>Потрачено</b>: " + statisticsDto.getSpendMoney() + " грн\n" +
                "<b>Потрачено шаров</b>: " + statisticsDto.getSpendBalls() + " ящиков\n" +
                "<b>Потрачено гранат</b>: " + statisticsDto.getSpendGrenades() + " шт\n" +
                "<b>Потрачено средних флешек</b>: " + statisticsDto.getSpendFlashM() + " шт\n" +
                "<b>Потрачено больших флешек</b>: " + statisticsDto.getSpendFlashL() + " шт\n" +
                "<b>Потрачено больших дымов</b>: " + statisticsDto.getSpendSmokeL() + " шт\n" +
                "<b>Сидели на беседках</b>: " + statisticsDto.getGazeboTimes() + " часов\n" +
                "<b>Потрачено салфеток</b>: " + statisticsDto.getSpendNapkins() + " пачек\n" +
                "<b>Потрачено моющего стредства</b>: " + statisticsDto.getSpendClean() + " бут\n";
    }
}
