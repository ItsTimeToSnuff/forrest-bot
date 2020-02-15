package com.itstimetosnuff.forrest.bot.hendler;

import com.itstimetosnuff.forrest.bot.keyboard.MainMenu;
import com.itstimetosnuff.forrest.bot.request.Request;
import com.itstimetosnuff.forrest.bot.request.StartRequest;
import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.session.SessionStore;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@AllArgsConstructor
public class StartHandler implements Handler<StartRequest, SendMessage> {

    private SessionStore sessionStore;

    private Session session;

    @Override
    public SendMessage handleEvent(StartRequest request) {
        sessionStore.registerSession(session);
        SendMessage sm = new SendMessage();
        sm.setReplyMarkup(MainMenu.mainMenu());
        sm.setChatId(session.getChatId());
        String name = request.getUpdate().getMessage().getFrom().getFirstName();
        sm.setParseMode("HTML");
        sm.setText(String.format("<b>%s</b>,\nПриветствую тебя в боте клуба <i>For.Rest.</i>\nЧем могу помочь?", name));
        return sm;
    }
}
