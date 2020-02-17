package com.itstimetosnuff.forrest.bot.hendler;

import com.itstimetosnuff.forrest.bot.keyboard.MainMenu;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartHandler implements Handler {

    @Override
    public SendMessage handleEvent(Update update) {
        SendMessage sm = new SendMessage();
        sm.setReplyMarkup(MainMenu.mainMenu());
        sm.setChatId(update.getMessage().getChatId());
        String name = update.getMessage().getFrom().getFirstName();
        sm.setParseMode("HTML");
        sm.setText(String.format("<b>%s</b>,\nПриветствую тебя в боте клуба <i>For.Rest.</i>\nЧем могу помочь?", name));
        return sm;
    }
}
