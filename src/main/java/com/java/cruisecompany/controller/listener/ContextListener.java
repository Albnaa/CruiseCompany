package com.java.cruisecompany.controller.listener;

import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.model.utils.TicketScheduler;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ContextListener implements ServletContextListener {
    TicketScheduler ticketScheduler;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ticketScheduler = new TicketScheduler(AppContext.getInstance().getTicketService());
        ticketScheduler.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ticketScheduler.shutdown();
    }
}
