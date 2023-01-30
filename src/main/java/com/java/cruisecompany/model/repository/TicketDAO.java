package com.java.cruisecompany.model.repository;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.model.entity.Ticket;
import com.java.cruisecompany.model.entity.enums.Status;

import java.util.List;

public interface TicketDAO extends EntityDAO<Ticket> {
    void updateTicketStatus(long ticketId, Status status) throws DAOException;
}
