package com.java.cruisecompany.model.utils;

import com.java.cruisecompany.model.service.TicketService;
import lombok.extern.log4j.Log4j2;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * The {@code TicketScheduler} class provides a way to schedule periodic updates to the status of tickets.
 */
@Log4j2
public class TicketScheduler {
    ScheduledExecutorService executor;
    private final TicketService ticketService;

    /**
     * Constructs a new TicketScheduler with the specified TicketService.
     *
     * @param ticketService the TicketService to use for updating ticket status
     */
    public TicketScheduler(TicketService ticketService) {
        this.ticketService = ticketService;
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            log.info("Started ticket status checking");
            ticketService.completeTicket();
        }
    };

    long period = 1;
    long delay = 0;

    /**
     * Starts the TicketScheduler and schedules the TimerTask for periodic execution.
     */
    public void start() {
        log.info("Ticket scheduler started");
        executor.scheduleAtFixedRate(timerTask, delay, period, TimeUnit.DAYS);
    }

    /**
     * Shuts down the TicketScheduler and stops all scheduled tasks.
     */
    public void shutdown() {
        log.info("Ticket scheduler shutdown");
        executor.shutdown();
    }
}
