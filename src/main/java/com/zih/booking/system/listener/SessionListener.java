package com.zih.booking.system.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author shahy
 */
@Slf4j
@WebListener
public class SessionListener implements HttpSessionListener {
    private int onlineCount = 0;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        onlineCount++;
        log.debug("【HttpSessionListener监听器】 sessionCreated, onlineCount:" + onlineCount);
        se.getSession().getServletContext().setAttribute("onlineCount", onlineCount);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        if (onlineCount > 0) {
            onlineCount--;
        }
        log.debug("【HttpSessionListener监听器】 sessionDestroyed, onlineCount:" + onlineCount);
        se.getSession().getServletContext().setAttribute("onlineCount", onlineCount);
    }
}
