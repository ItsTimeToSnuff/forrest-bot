package com.itstimetosnuff.forrest.bot.hendler;

import com.itstimetosnuff.forrest.bot.keyboard.MainMenu;
import com.itstimetosnuff.forrest.bot.request.StartRequest;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class StartHandler implements Handler<StartRequest, SendMessage> {

    @Override
    public SendMessage handleEvent(StartRequest request) {
        SendMessage sm = new SendMessage();
        sm.setReplyMarkup(MainMenu.mainMenu());
        sm.setChatId(request.getUpdate().getMessage().getChatId());
        String name = request.getUpdate().getMessage().getFrom().getFirstName();
        sm.setParseMode("HTML");
        sm.setText(String.format("<b>%s</b>,\nПриветствую тебя в боте клуба <i>For.Rest.</i>\nЧем могу помочь?", name));
        return sm;
    }
}
