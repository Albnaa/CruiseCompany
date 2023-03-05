package com.java.cruisecompany.model.service;

import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.UserDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * The UserService interface provides methods to manage the business logic related to users.
 * <p>
 * It extends the Service interface and provides additional methods specific to user entity.
 */
public interface UserService extends Service<UserDTO> {
    /**
     * Method to register a user with userDTO object and password
     *
     * @param userDTO  UserDTO object to create a new user
     * @param password Password of user to register
     * @throws ServiceException If an error occurs while registering a user
     */
    void register(UserDTO userDTO, String password) throws ServiceException;

    /**
     * Method to top-up balance of a user
     *
     * @param userId Id of user to top-up balance
     * @param amount Amount to top-up
     * @throws ServiceException If an error occurs while topping up balance
     */
    void topUpBalance(long userId, BigDecimal amount) throws ServiceException;

    /**
     * Method to find a user by login and password
     *
     * @param login    Login of user to find
     * @param password Password of user to find
     * @return Optional<UserDTO> Returns Optional UserDTO object if found, otherwise empty optional
     * @throws ServiceException If an error occurs while finding a user
     */
    Optional<UserDTO> findByLoginAndPass(String login, String password) throws ServiceException;

    /**
     * Method to find users by initials
     *
     * @param initials Initials of user to find
     * @return List<UserDTO> Returns list of UserDTO objects that match initials
     * @throws ServiceException If an error occurs while finding users
     */
    List<UserDTO> findByInitials(String initials) throws ServiceException;

    /**
     * Method to find a user by login
     *
     * @param login Login of user to find
     * @return Optional<UserDTO> Returns Optional UserDTO object if found, otherwise empty optional
     * @throws ServiceException If an error occurs while finding a user
     */
    Optional<UserDTO> findByLogin(String login) throws ServiceException;
}
