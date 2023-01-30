package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.exceptions.NoSuchTicketException;
import com.java.cruisecompany.exceptions.NoSuchUserException;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.connectionpool.DBManager;
import com.java.cruisecompany.model.dto.TicketDTO;
import com.java.cruisecompany.model.entity.Ticket;
import com.java.cruisecompany.model.entity.User;
import com.java.cruisecompany.model.entity.enums.Status;
import com.java.cruisecompany.model.repository.TicketDAO;
import com.java.cruisecompany.model.repository.UserDAO;
import com.java.cruisecompany.model.service.TicketService;
import com.java.cruisecompany.model.utils.MapperDTO;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.java.cruisecompany.model.utils.MapperDTO.mapDTOToTicket;
import static com.java.cruisecompany.model.utils.MapperDTO.mapTicketToDTO;

public class TicketServiceImpl implements TicketService {
    private final TicketDAO ticketDAO;
    private final UserDAO userDAO;

    public TicketServiceImpl(TicketDAO ticketDAO, UserDAO userDAO) {
        this.ticketDAO = ticketDAO;
        this.userDAO = userDAO;
    }

    @Override
    public void create(TicketDTO ticketDTO) throws ServiceException {
        try {
            ticketDAO.create(mapDTOToTicket(ticketDTO));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(TicketDTO ticketDTO) throws ServiceException {
        try {
            ticketDAO.update(mapDTOToTicket(ticketDTO));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(long id) throws ServiceException {
        try {
            ticketDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<TicketDTO> findById(long id) throws ServiceException {
        TicketDTO ticketDTO;
        try {
            ticketDTO = mapTicketToDTO(ticketDAO.findById(id).orElseThrow(NoSuchTicketException::new));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return Optional.of(ticketDTO);
    }

    @Override
    public List<TicketDTO> findAll() throws ServiceException {
        List<TicketDTO> ticketDTOs;
        try {
            ticketDTOs = ticketDAO.findAll().stream()
                    .map(MapperDTO::mapTicketToDTO)
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return ticketDTOs;
    }

    @Override
    public List<TicketDTO> findSorted(String query) throws ServiceException {
        List<TicketDTO> ticketDTOs;
        try {
            ticketDTOs = ticketDAO.findSorted(query).stream()
                    .map(MapperDTO::mapTicketToDTO)
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return ticketDTOs;
    }

    @Override
    public void updateTicketStatus(long ticketId, Status status) throws ServiceException {
        try {
            ticketDAO.updateTicketStatus(ticketId, status);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void payForTicket(long userId, long ticketId) throws ServiceException{
        try {
            Connection con = DBManager.getConnection();
            con.setAutoCommit(false);
            try {
                User user = userDAO.findById(userId).orElseThrow(NoSuchUserException::new);
                Ticket ticket = ticketDAO.findById(ticketId).orElseThrow(NoSuchTicketException::new);
                if (user.getBalance().compareTo(ticket.getPrice()) < 0) {
                    throw new ServiceException("no balance");
                }
                ticketDAO.updateTicketStatus(ticketId, Status.PAID);
                userDAO.deductFromBalance(userId, ticket.getPrice());
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new ServiceException(e);
            } finally {
                con.setAutoCommit(true);
                con.close();
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }

    }


    @Override
    public long getNumOfRows(String query) throws ServiceException {
        try {
            return ticketDAO.getNumOfRows(query);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
