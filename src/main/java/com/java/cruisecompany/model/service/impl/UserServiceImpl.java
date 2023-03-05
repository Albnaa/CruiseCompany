package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.exceptions.InvalidInputException;
import com.java.cruisecompany.exceptions.NoSuchUserException;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.entity.User;
import com.java.cruisecompany.model.dao.UserDAO;
import com.java.cruisecompany.model.service.UserService;
import com.java.cruisecompany.model.utils.MapperDTO;
import com.java.cruisecompany.model.utils.PasswordHashUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.java.cruisecompany.model.utils.MapperDTO.mapDTOtoUser;
import static com.java.cruisecompany.model.utils.MapperDTO.mapUserToDTO;

/**
 * This class implements the UserService interface and provides methods for creating, updating, deleting,
 * finding and sorting users. It also provides a method to get the number of rows in the database matching a given query.
 * <p>
 * The class uses a UserDAO object to access the database and Log4j2 for logging.
 */
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    /**
     * Constructs a new UserServiceImpl with the given UserDAO.
     *
     * @param userDAO the UserDAO to use for database access.
     */
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    /**
     * Creates a new User in the database.
     *
     * @param userDTO the UserDTO object to be created
     * @throws ServiceException if an error occurs while creating the UserDTO object in the database
     */
    @Override
    public void create(UserDTO userDTO) throws ServiceException {
        try {
            userDAO.create(mapDTOtoUser(userDTO));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Register a new user with the provided UserDTO and password.
     *
     * @param userDTO  the UserDTO object containing user details.
     * @param password the password for the user.
     * @throws ServiceException if an error occurs while registering the UserDTO object in the database
     */
    @Override
    public void register(UserDTO userDTO, String password) throws ServiceException {
        User user = mapDTOtoUser(userDTO);
        user.setPassword(PasswordHashUtil.hash(password));

        try {
            userDAO.create(user);
        } catch (DAOException e) {
            validateSQLError(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Updates an existing User in the database.
     *
     * @param userDTO the UserDTO object to be updated
     * @throws ServiceException if an error occurs while updating the UserDTO object in the database
     */
    @Override
    public void update(UserDTO userDTO) throws ServiceException {
        User user = mapDTOtoUser(userDTO);
        try {
            userDAO.update(user);
        } catch (DAOException e) {
            validateSQLError(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Deletes an existing User from the database by id.
     *
     * @param id the id of the UserDTO object to be deleted
     * @throws ServiceException if an error occurs while deleting the UserDTO object from the database
     */
    @Override
    public void delete(long id) throws ServiceException {
        try {
            userDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Finds an existing User in the database by id and returns it wrapped in an Optional.
     *
     * @param id the id of the UserDTO object to be found
     * @return an Optional containing the found UserDTO object, or an empty Optional if the UserDTO was not found
     * @throws ServiceException if an error occurs while finding the UserDTO object in the database
     */
    @Override
    public Optional<UserDTO> findById(long id) throws ServiceException {
        UserDTO userDTO;
        try {
            userDTO = mapUserToDTO(userDAO.findById(id).orElseThrow(NoSuchUserException::new));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return Optional.of(userDTO);
    }

    /**
     * Finds all User objects in the database and returns them as a List.
     *
     * @return a List containing all UserDTO objects in the database
     * @throws ServiceException if an error occurs while finding the UserDTO objects in the database
     */
    @Override
    public List<UserDTO> findAll() throws ServiceException {
        List<UserDTO> userDTOs;
        try {
            userDTOs = userDAO.findAll().stream()
                    .map(MapperDTO::mapUserToDTO)
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return userDTOs;
    }

    /**
     * Find a user by login and password.
     *
     * @param login    the login of the user.
     * @param password the password of the user.
     * @return an Optional of UserDTO object containing user details, empty if no user found.
     * @throws ServiceException if an error occurs while finding the user
     */
    @Override
    public Optional<UserDTO> findByLoginAndPass(String login, String password) throws ServiceException {
        UserDTO userDTO;
        try {
            User user = userDAO.findByLogin(login).orElseThrow(NoSuchUserException::new);

            if (!PasswordHashUtil.verify(user.getPassword(), password)) {
                throw new NoSuchUserException();
            }

            userDTO = mapUserToDTO(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return Optional.of(userDTO);
    }

    /**
     * Top up the balance of a user.
     *
     * @param userId the id of the user to top up balance for.
     * @param amount the amount to be topped up.
     * @throws ServiceException if there's a problem topping up the balance.
     */
    @Override
    public void topUpBalance(long userId, BigDecimal amount) throws ServiceException {
        try {
            userDAO.topUpBalance(userId, amount);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find a list of users by their initials.
     *
     * @param initials the initials to search by.
     * @return a List of UserDTO objects containing the details of the users found.
     * @throws ServiceException if there's a problem finding the users.
     */
    @Override
    public List<UserDTO> findByInitials(String initials) throws ServiceException {
        List<UserDTO> userDTOs;
        try {
            userDTOs = userDAO.findByInitials(initials).stream()
                    .map(MapperDTO::mapUserToDTO)
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return userDTOs;
    }

    /**
     * Find a user by login.
     *
     * @param login the login of the user.
     * @return an Optional of UserDTO object containing user details, empty if no user found.
     * @throws ServiceException if there's a problem finding the user.
     */
    @Override
    public Optional<UserDTO> findByLogin(String login) throws ServiceException {
        UserDTO userDTO;
        try {
            userDTO = mapUserToDTO(userDAO.findByLogin(login).orElseThrow(NoSuchUserException::new));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return Optional.of(userDTO);
    }

    /**
     * Retrieves a sorted list of all Users based on the given query.
     *
     * @param query the query to sort by.
     * @return a list of UserDTOs sorted by the given query.
     * @throws ServiceException if there is an error retrieving the sorted list from the persistence layer.
     */
    @Override
    public List<UserDTO> findSorted(String query) throws ServiceException {
        List<UserDTO> userDTOs;
        try {
            userDTOs = userDAO.findSorted(query).stream()
                    .map(MapperDTO::mapUserToDTO)
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return userDTOs;
    }

    /**
     * Retrieves the number of rows from the {@link UserDAO} based on the given query.
     *
     * @param query the query to count the number of rows for.
     * @return the number of rows that match the query.
     * @throws ServiceException if there is an error retrieving the number of rows from the persistence layer.
     */
    @Override
    public long getNumOfRows(String query) throws ServiceException {
        try {
            return userDAO.getNumOfRows(query);
        } catch (DAOException e) {
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
            if (message.contains("login")) {
                throw new InvalidInputException("error.user.login.exist", e);
            } else if (message.contains("email")) {
                throw new InvalidInputException("error.user.email.exist", e);
            }
        }
    }
}
