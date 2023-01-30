package com.java.cruisecompany.model.service;

import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.TicketDTO;

import java.util.List;

public interface TicketService extends Service<TicketDTO> {
    void payForTicket(long userId, long ticketId);
//    List<TicketDTO> findSortedByUser(Long userId, String query) throws ServiceException;
}
