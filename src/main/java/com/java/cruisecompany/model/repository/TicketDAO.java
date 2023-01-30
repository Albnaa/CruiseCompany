package com.java.cruisecompany.model.repository;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.model.entity.Ticket;

import java.util.List;

public interface TicketDAO extends EntityDAO<Ticket> {
//    List<Ticket> findSortedByUser(Long userId, String query) throws DAOException;
}
