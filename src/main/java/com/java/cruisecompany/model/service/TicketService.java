package com.java.cruisecompany.model.service;

import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.TicketDTO;
import com.java.cruisecompany.model.entity.enums.Status;

/**
 * The ShipService interface provides methods to manage the business logic related to tickets.
 * <p>
 * It extends the Service interface and provides additional methods specific to ticket entity.
 */
public interface TicketService extends Service<TicketDTO> {
    /**
     * Pay for a ticket for a specific user
     *
     * @param userId   the id of the user
     * @param ticketId the id of the ticket
     * @throws ServiceException if an error occurred during the operation
     */
    void payForTicket(long userId, long ticketId) throws ServiceException;

    /**
     * Update the status of a ticket
     *
     * @param ticketId the id of the ticket
     * @param status   the new status of the ticket
     * @throws ServiceException if an error occurred during the operation
     */
    void updateTicketStatus(long ticketId, Status status) throws ServiceException;

    /**
     * Mark all tickets as completed
     */
    void completeTickets();
}
