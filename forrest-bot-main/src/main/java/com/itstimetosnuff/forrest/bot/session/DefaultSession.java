package com.itstimetosnuff.forrest.bot.session;

import com.itstimetosnuff.forrest.bot.enums.EventType;
import com.itstimetosnuff.forrest.bot.handler.Handler;
import com.itstimetosnuff.forrest.bot.handler.HandlerRegistry;
import com.itstimetosnuff.forrest.bot.store.SessionStore;
import com.itstimetosnuff.forrest.bot.utils.Buttons;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class DefaultSession implements Session {

    private Long chatId;

    private EventType eventLock;

    private List<BotApiMethod> executes;

    private HandlerRegistry handlerRegistry;

    private SessionStore sessionStore;

    @Override
    public Long getChatId() {
        return this.chatId;
    }

    @Override
    public void setEventLock(EventType eventType) {
        eventLock = eventType;
    }

    @Override
    public List<BotApiMethod> getExecutes() {
        return executes;
    }

    @Override
    public BotApiMethod onUpdate(Update update) {
        String eventType = getEventType(update);
        Handler handler = handlerRegistry.getHandler(EventType.byType(eventType));
        if (handler != null) {
            log.info(handler.toString());
            return handler.handleEvent(update);
        }
        return null;
    }

    @Override
    public void close() {
        sessionStore.closeSession(this);
    }

    private String getEventType(Update update) {
        String text = getText(update);
        if (!eventLock.equals(EventType.LOCK_FREE)){
            if (text.equals(Buttons.CANCEL) ||
                    text.contains(Buttons.CALENDAR_SCROLL_FORWARD_CALLBACK) ||
                    text.contains(Buttons.CALENDAR_SCROLL_BACKWARD_CALLBACK)) {
                return text;
            } else {
                return eventLock.getValue();
            }
        }else {
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
