package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.entity.Ticket;
import com.java.cruisecompany.model.repository.TicketDAO;
import com.java.cruisecompany.model.repository.impl.DAOFactory;
import com.java.cruisecompany.model.service.TicketService;

import java.util.List;
import java.util.Optional;

public class TicketServiceImpl implements TicketService {
    private final TicketDAO ticketDAO;

    public TicketServiceImpl(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    @Override
    public void create(Ticket entity) {

    }

    @Override
    public void update(Ticket entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Optional<Ticket> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Ticket> findAll() {
        return null;
    }

    @Override
    public List<Ticket> findSorted(String query) throws ServiceException {
        return null;
    }
}
