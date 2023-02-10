package com.java.cruisecompany.model.service;

import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.TicketDTO;
import com.java.cruisecompany.model.entity.enums.Status;

public interface TicketService extends Service<TicketDTO> {
    void payForTicket(long userId, long ticketId) throws ServiceException;
    void updateTicketStatus(long ticketId, Status status) throws ServiceException;

    void completeTicket();
}
