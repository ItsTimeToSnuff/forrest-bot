package com.itstimetosnuff.forrest.bot.session;

import com.itstimetosnuff.forrest.bot.entity.User;
import com.itstimetosnuff.forrest.bot.enums.EventType;
import com.itstimetosnuff.forrest.bot.handler.DialogueInfo;
import com.itstimetosnuff.forrest.bot.handler.Handler;
import com.itstimetosnuff.forrest.bot.handler.HandlerRegistry;
import com.itstimetosnuff.forrest.bot.service.GoogleService;
import com.itstimetosnuff.forrest.bot.store.SessionStore;
import com.itstimetosnuff.forrest.bot.utils.Buttons;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class DefaultSession extends Session {

    public DefaultSession(
            User user,
            DialogueInfo dialogueInfo,
            List<BotApiMethod> executes,
            HandlerRegistry handlerRegistry,
            SessionStore sessionStore,
            GoogleService googleService) {
        super(
                user,
                dialogueInfo,
                executes,
                handlerRegistry,
                sessionStore,
                googleService
        );
    }

    @Override
    public BotApiMethod onUpdate(Update update) {
        String eventType = getEventType(update);
        Handler handler = handlerRegistry.getHandler(EventType.byType(eventType));
        if (handler == null) {
            handler = handlerRegistry.getHandler(EventType.ERROR);
        }
        return handler.handleEvent(update);
    }

    @Override
    public void close() {
        sessionStore.closeSession(this);
    }

    private String getEventType(Update update) {
        String text = getText(update);
        EventType eventLock = dialogueInfo.getEventLock();
        if (!eventLock.equals(EventType.LOCK_FREE)) {
            if (text.equals(Buttons.CANCEL) ||
                    text.contains(Buttons.CALENDAR_SCROLL_FORWARD_CALLBACK) ||
                    text.contains(Buttons.CALENDAR_SCROLL_BACKWARD_CALLBACK)) {
                return text;
            } else {
                return eventLock.getValue();
            }
        } else {
            return text;
        }
    }

    private String getText(Update update) {
        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getData();
        } else {
            return update.getMessage().getText();
        }
    }

}
