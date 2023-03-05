package com.java.cruisecompany.model.dao;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.model.entity.Ticket;
import com.java.cruisecompany.model.entity.enums.Status;

/**
 * TicketDAO is an interface that extends the EntityDAO interface and defines additional methods for interacting with
 * the database for the Ticket entity.
 */
public interface TicketDAO extends EntityDAO<Ticket> {
    /**
     * Updates the status of a ticket with the given ID to the specified status.
     *
     * @param ticketId the ID of the ticket to update
     * @param status   the new status of the ticket
     * @throws DAOException if an error occurs while accessing the database
     */
    void updateTicketStatus(long ticketId, Status status) throws DAOException;
}
