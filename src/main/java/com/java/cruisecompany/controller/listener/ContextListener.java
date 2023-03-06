package com.java.cruisecompany.controller.listener;

import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.model.utils.TicketScheduler;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

/**
 * A ServletContextListener implementation that starts and stops a TicketScheduler when the web application is
 * initialized and destroyed, respectively. TicketScheduler is responsible for periodically setting expired tickets
 * to Completed status.
 */
public class ContextListener implements ServletContextListener {
    TicketScheduler ticketScheduler;

    /**
     * Called by the web container when the web application is initialized. Creates a new TicketScheduler and starts it.
     *
     * @param sce The ServletContextEvent object that contains the ServletContext that triggered this event.
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ticketScheduler = new TicketScheduler(AppContext.getInstance().getTicketService());
        ticketScheduler.start();
    }

    /**
     * Called by the web container when the web application is about to be destroyed. Shuts down the TicketScheduler.
     *
     * @param sce The ServletContextEvent object that contains the ServletContext that triggered this event.
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ticketScheduler.shutdown();
    }
}
