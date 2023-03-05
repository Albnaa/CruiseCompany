package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.exceptions.InvalidInputException;
import com.java.cruisecompany.exceptions.NoSuchPortException;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.PortDTO;
import com.java.cruisecompany.model.dao.PortDAO;
import com.java.cruisecompany.model.service.PortService;
import com.java.cruisecompany.model.utils.MapperDTO;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.java.cruisecompany.model.utils.MapperDTO.mapDTOtoPort;
import static com.java.cruisecompany.model.utils.MapperDTO.mapPortToDTO;

/**
 * This class implements the PortService interface and provides methods for creating, updating, deleting,
 * finding and sorting ports. It also provides a method to get the number of rows in the database matching a given query.
 * <p>
 * The class uses a PortDAO object to access the database and Log4j2 for logging.
 */
@Log4j2
public class PortServiceImpl implements PortService {

    private final PortDAO portDAO;

    /**
     * Constructs a new PortServiceImpl with the given PortDAO.
     *
     * @param portDAO the PortDAO to use for database access.
     */
    public PortServiceImpl(PortDAO portDAO) {
        this.portDAO = portDAO;
    }

    /**
     * Creates a new Port in the database.
     *
     * @param portDTO the PortDTO object to be created
     * @throws ServiceException if an error occurs while creating the PortDTO object in the database
     */
    @Override
    public void create(PortDTO portDTO) throws ServiceException {
        try {
            portDAO.create(mapDTOtoPort(portDTO));
        } catch (DAOException e) {
            log.error("Port service create error " + e.getMessage());
            validateSQLError(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Updates an existing Port in the database.
     *
     * @param portDTO the PortDTO object to be updated
     * @throws ServiceException if an error occurs while updating the PortDTO object in the database
     */
    @Override
    public void update(PortDTO portDTO) throws ServiceException {
        try {
            portDAO.update(mapDTOtoPort(portDTO));
        } catch (DAOException e) {
            log.error("Port service update error " + e.getMessage());
            validateSQLError(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Deletes an existing Port from the database by id.
     *
     * @param id the id of the PortDTO object to be deleted
     * @throws ServiceException if an error occurs while deleting the PortDTO object from the database
     */
    @Override
    public void delete(long id) throws ServiceException {
        try {
            portDAO.delete(id);
        } catch (DAOException e) {
            log.error("Port service delete error " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    /**
     * Finds an existing Port in the database by id and returns it wrapped in an Optional.
     *
     * @param id the id of the PortDTO object to be found
     * @return an Optional containing the found PortDTO object, or an empty Optional if the PortDTO was not found
     * @throws ServiceException if an error occurs while finding the PortDTO object in the database
     */
    @Override
    public Optional<PortDTO> findById(long id) throws ServiceException {
        PortDTO portDTO;
        try {
            portDTO = mapPortToDTO(portDAO.findById(id).orElseThrow(NoSuchPortException::new));
        } catch (DAOException e) {
            log.error("Port service findById error " + e.getMessage());
            throw new ServiceException(e);
        }
        return Optional.of(portDTO);
    }

    /**
     * Finds all Port objects in the database and returns them as a List.
     *
     * @return a List containing all PortDTO objects in the database
     * @throws ServiceException if an error occurs while finding the PortDTO objects in the database
     */
    @Override
    public List<PortDTO> findAll() throws ServiceException {
        List<PortDTO> portDTOs;
        try {
            portDTOs = portDAO.findAll().stream()
                    .map(MapperDTO::mapPortToDTO)
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            log.error("Port service findAll error " + e.getMessage());
            throw new ServiceException(e);
        }
        return portDTOs;
    }

    /**
     * Retrieves a sorted list of all Ports based on the given query.
     *
     * @param query the query to sort by.
     * @return a list of PortDTOs sorted by the given query.
     * @throws ServiceException if there is an error retrieving the sorted list from the persistence layer.
     */
    @Override
    public List<PortDTO> findSorted(String query) throws ServiceException {
        List<PortDTO> portDTOs;
        try {
            portDTOs = portDAO.findSorted(query).stream()
                    .map(MapperDTO::mapPortToDTO)
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            log.error("Port service findSorted error " + e.getMessage());
            throw new ServiceException();
        }
        return portDTOs;
    }

    /**
     * Retrieves the number of rows from the {@link PortDAO} based on the given query.
     *
     * @param query the query to count the number of rows for.
     * @return the number of rows that match the query.
     * @throws ServiceException if there is an error retrieving the number of rows from the persistence layer.
     */
    @Override
    public long getNumOfRows(String query) throws ServiceException {
        try {
            return portDAO.getNumOfRows(query);
        } catch (DAOException e) {
            log.error("Port service getNumOfRows error " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    /**
     * Validates if the given DAOException is a SQL error caused by a duplicate entry and throws an InvalidInputException
     * with an error message if it is.
     *
     * @param e the DAOException to validate.
     * @throws InvalidInputException if the given DAOException is a SQL error caused by a duplicate entry.
     */
    private static void validateSQLError(DAOException e) throws InvalidInputException {
        String message = e.getMessage();
        if (message != null && message.contains("Duplicate entry")) {
            throw new InvalidInputException("error.port.name.exist", e);
        }
    }
}
