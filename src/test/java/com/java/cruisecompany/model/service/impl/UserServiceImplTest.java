package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.exceptions.NoSuchUserException;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.entity.User;
import com.java.cruisecompany.model.entity.enums.Role;
import com.java.cruisecompany.model.repository.UserDAO;
import com.java.cruisecompany.model.service.UserService;
import com.java.cruisecompany.model.utils.PasswordHashUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    UserDAO userDAO;

    UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userDAO);
    }


    @Test
    void testCreateSuccess() throws ServiceException {
        userService.create(getUserDTO());
        verify(userDAO).create(any(User.class));
    }

    @Test
    void testCreateServiceException() throws DAOException  {
        Mockito.doThrow(new DAOException()).when(userDAO).create(any(User.class));

        assertThrows(ServiceException.class, () -> userService.create(getUserDTO()));
        verify(userDAO).create(any(User.class));
    }

    @Test
    void testRegisterSuccess() throws ServiceException {
        userService.register(getUserDTO(), "password");
        verify(userDAO).create(any(User.class));
    }

    @Test
    void testRegisterServiceException() throws DAOException  {
        Mockito.doThrow(new DAOException()).when(userDAO).create(any(User.class));

        assertThrows(ServiceException.class, () -> userService.register(getUserDTO(), "password"));
        verify(userDAO).create(any(User.class));
    }

    @Test
    void testUpdateSuccess() throws ServiceException {
        userService.update(getUserDTO());
        verify(userDAO).update(any(User.class));
    }

    @Test
    void testUpdateServiceException() throws DAOException  {
        Mockito.doThrow(new DAOException()).when(userDAO).update(any(User.class));

        assertThrows(ServiceException.class, () -> userService.update(getUserDTO()));
        verify(userDAO).update(any(User.class));
    }

    @Test
    void testDeleteSuccess() throws ServiceException {
        userService.delete(1L);
        verify(userDAO).delete(eq(1L));
    }

    @Test
    void testDeleteServiceException() throws DAOException  {
        Mockito.doThrow(new DAOException()).when(userDAO).delete(1L);

        assertThrows(ServiceException.class, () -> userService.delete(1L));
        verify(userDAO).delete(1L);
    }

    @Test
    void testFindByIdSuccess() throws ServiceException {
        when(userDAO.findById(1)).thenReturn(Optional.of(getUser()));

        Optional<UserDTO> actualPort = userService.findById(1);

        assertTrue(actualPort.isPresent());
        assertEquals(getUserDTO(), actualPort.get());
        verify(userDAO).findById(1);
    }

    @Test
    public void testFindByIdNoSuchUserException() throws DAOException {
        when(userDAO.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchUserException.class, () -> userService.findById(1L));
    }

    @Test
    void testFindByIdServiceException() throws DAOException  {
        Mockito.doThrow(new DAOException()).when(userDAO).findById(1L);

        assertThrows(ServiceException.class, () -> userService.findById(1L));
        verify(userDAO).findById(1L);
    }

    @Test
    void testFindAllSuccess() throws ServiceException {
        List<User> users = Collections.singletonList(getUser());
        List<UserDTO> userDTOs = Collections.singletonList(getUserDTO());

        when(userDAO.findAll()).thenReturn(users);

        assertIterableEquals(userService.findAll(), userDTOs);
    }

    @Test
    void testFindAllServiceException() throws DAOException  {
        Mockito.doThrow(new DAOException()).when(userDAO).findAll();

        assertThrows(ServiceException.class, () -> userService.findAll());
        verify(userDAO).findAll();
    }

    @Test
    void testFindSortedSuccess() throws ServiceException {
        List<User> users = Collections.singletonList(getUser());
        List<UserDTO> userDTOs = Collections.singletonList(getUserDTO());

        when(userDAO.findSorted(any())).thenReturn(users);

        assertIterableEquals(userService.findSorted(any()), userDTOs);
    }

    @Test
    void testFindSortedServiceException() throws DAOException  {
        Mockito.doThrow(new DAOException()).when(userDAO).findSorted(any());

        assertThrows(ServiceException.class, () -> userService.findSorted(any()));
        verify(userDAO).findSorted(any());
    }

    @Test
    void testFindByLoginAndPassSuccess() throws ServiceException {
        
        when(userDAO.findByLogin(any(String.class))).thenReturn(Optional.of(getUser()));
        try (MockedStatic<PasswordHashUtil> mocked = Mockito.mockStatic(PasswordHashUtil.class)) {
            mocked.when(() -> PasswordHashUtil.verify(Mockito.anyString(), Mockito.anyString())).thenReturn(true);

            Optional<UserDTO> actualUser = userService.findByLoginAndPass("Login", "Pass");

            assertTrue(actualUser.isPresent());
            assertEquals(getUserDTO(), actualUser.get());
            verify(userDAO).findByLogin(any(String.class));
        }
    }

    @Test
    public void testFindByLoginAndPassNoSuchUserException() throws DAOException {
        when(userDAO.findByLogin(any(String.class))).thenReturn(Optional.empty());
        assertThrows(NoSuchUserException.class, () -> userService.findByLoginAndPass("Login", "Pass"));
    }

    @Test
    void testFindByLoginAndPassServiceException() throws DAOException  {
        Mockito.doThrow(new DAOException()).when(userDAO).findByLogin(any(String.class));

        assertThrows(ServiceException.class, () -> userService.findByLoginAndPass("Login", "Pass"));
        verify(userDAO).findByLogin(any(String.class));
    }

    @Test
    void testTopUpBalanceSuccess() throws ServiceException {
        userService.topUpBalance(1L, BigDecimal.valueOf(1000));
        verify(userDAO).topUpBalance(eq(1L), any());
    }

    @Test
    void testTopUpBalanceServiceException() throws DAOException  {
        Mockito.doThrow(new DAOException()).when(userDAO).topUpBalance(eq(1L), any());

        assertThrows(ServiceException.class, () -> userService.topUpBalance(1L, BigDecimal.valueOf(1000)));
        verify(userDAO).topUpBalance(eq(1L), any());
    }

    @Test
    void testFindByInitialsSuccess() throws ServiceException {
        List<User> users = Collections.singletonList(getUser());
        List<UserDTO> userDTOs = Collections.singletonList(getUserDTO());

        when(userDAO.findByInitials(any())).thenReturn(users);

        assertIterableEquals(userService.findByInitials(any()), userDTOs);
        verify(userDAO).findByInitials(any());
    }

    @Test
    void testFindByInitialsServiceException() throws DAOException  {
        Mockito.doThrow(new DAOException()).when(userDAO).findByInitials(any());

        assertThrows(ServiceException.class, () -> userService.findByInitials(any()));
        verify(userDAO).findByInitials(any());
    }

    @Test
    void testFindByLoginSuccess() throws ServiceException {
        when(userDAO.findByLogin(any(String.class))).thenReturn(Optional.of(getUser()));

        Optional<UserDTO> actualUser = userService.findByLogin("Login");

        assertTrue(actualUser.isPresent());
        assertEquals(getUserDTO(), actualUser.get());
        verify(userDAO).findByLogin(any(String.class));
    }

    @Test
    public void testFindByLoginNoSuchUserException() throws DAOException {
        when(userDAO.findByLogin(any(String.class))).thenReturn(Optional.empty());
        assertThrows(NoSuchUserException.class, () -> userService.findByLogin("Login"));
    }

    @Test
    void testFindByLoginServiceException() throws DAOException  {
        Mockito.doThrow(new DAOException()).when(userDAO).findByLogin(any());

        assertThrows(ServiceException.class, () -> userService.findByLogin(any()));
        verify(userDAO).findByLogin(any());
    }

    @Test
    void testGetNumOfRowsSuccess() throws ServiceException {
        long rows = 5L;

        when(userDAO.getNumOfRows(any())).thenReturn(rows);

        assertEquals(rows, userService.getNumOfRows(any()));
        verify(userDAO).getNumOfRows(any());
    }

    @Test
    void testGetNumOfRowsServiceException() {
        Mockito.doThrow(new DAOException()).when(userDAO).getNumOfRows(any());

        assertThrows(ServiceException.class, () -> userService.getNumOfRows(any()));
        verify(userDAO).getNumOfRows(any());
    }

    private static User getUser() {
        return User.builder()
                .id(1)
                .login("admin")
                .email("admin@gmail.com")
                .password("password")
                .firstName("John")
                .lastName("Doe")
                .balance(BigDecimal.valueOf(1000))
                .role(Role.ADMIN)
                .build();
    }

    private static UserDTO getUserDTO() {
        return UserDTO.builder()
                .id(1)
                .login("admin")
                .email("admin@gmail.com")
                .firstName("John")
                .lastName("Doe")
                .balance(BigDecimal.valueOf(1000))
                .role(Role.ADMIN)
                .build();
    }
}
