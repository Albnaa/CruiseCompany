package com.java.cruisecompany.model.utils;

import com.java.cruisecompany.model.service.TicketService;
import lombok.extern.log4j.Log4j2;

import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Log4j2
public class TicketScheduler {
    private final ScheduledExecutorService executor;
    private final TicketService ticketService;

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
    public void start() {
        log.info("Ticket scheduler started");
        executor.scheduleAtFixedRate(timerTask, delay, period, TimeUnit.DAYS);
    }

    public void shutdown() {
        log.info("Ticket scheduler shutdown");
        executor.shutdown();
    }

}
