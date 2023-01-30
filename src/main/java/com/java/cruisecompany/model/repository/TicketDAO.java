package com.java.cruisecompany.model.repository;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.model.entity.Ticket;
import com.java.cruisecompany.model.entity.enums.Status;

public interface TicketDAO extends EntityDAO<Ticket> {
    void updateTicketStatus(long ticketId, Status status) throws DAOException;
}
