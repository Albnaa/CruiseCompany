package com.java.cruisecompany.model.utils;

import com.java.cruisecompany.model.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TicketSchedulerTest {
    @Mock
    private TicketService ticketService;

    @Mock
    private ScheduledExecutorService executor;

    private TicketScheduler scheduler;

    @BeforeEach
    public void setUp() {
        scheduler = new TicketScheduler(ticketService);
        scheduler.executor = executor;
    }

    @Test
    public void testStart() {
        scheduler.start();
        verify(executor, times(1)).scheduleAtFixedRate(any(TimerTask.class), eq(0L), eq(1L), eq(TimeUnit.DAYS));
    }

    @Test
    public void testShutdown() {
        scheduler.shutdown();
        verify(executor, times(1)).shutdown();
    }
}