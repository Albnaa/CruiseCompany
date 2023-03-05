package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.exceptions.NoSuchPortException;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.PortDTO;
import com.java.cruisecompany.model.entity.Port;
import com.java.cruisecompany.model.dao.PortDAO;
import com.java.cruisecompany.model.service.PortService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PortServiceImplTest {

    @Mock
    private PortDAO portDAO;

    private PortService portService;

    @BeforeEach
    void setUp() {
        this.portService = new PortServiceImpl(portDAO);
    }

    @Test
    public void testCreateSuccess() throws ServiceException, DAOException {
        PortDTO portDTO = getPortDTO();
        portService.create(portDTO);
        verify(portDAO).create(any(Port.class));
    }

    @Test
    public void testCreateServiceException() throws DAOException {
        PortDTO portDTO = getPortDTO();

        Mockito.doThrow(new DAOException()).when(portDAO).create(any(Port.class));

        assertThrows(ServiceException.class, () -> portService.create(portDTO));
        verify(portDAO).create(any(Port.class));
    }

    @Test
    public void testUpdateSuccess() throws ServiceException, DAOException {
        PortDTO portDTO = getPortDTO();
        portService.update(portDTO);
        verify(portDAO).update(any(Port.class));
    }

    @Test
    public void testUpdateServiceException() throws DAOException {
        PortDTO portDTO = getPortDTO();

        Mockito.doThrow(new DAOException()).when(portDAO).update(any(Port.class));

        assertThrows(ServiceException.class, () -> portService.update(portDTO));
        verify(portDAO).update(any(Port.class));
    }

    @Test
    public void testDeleteSuccess() throws ServiceException, DAOException {
        portService.delete(1);
        verify(portDAO).delete(1);
    }

    @Test
    public void testDeleteServiceException() throws DAOException {
        Mockito.doThrow(new DAOException()).when(portDAO).delete(1);

        assertThrows(ServiceException.class, () -> portService.delete(1));
        verify(portDAO).delete(1);
    }

    @Test
    void findByIdSuccess() throws ServiceException {
        Port expectedPort = getPort();
        PortDTO expectedPortDTO = getPortDTO();

        when(portDAO.findById(1)).thenReturn(Optional.of(expectedPort));

        Optional<PortDTO> actualPort = portService.findById(1);

        assertTrue(actualPort.isPresent());
        assertEquals(expectedPortDTO, actualPort.get());
        verify(portDAO).findById(1);
    }

    @Test
    public void testFindByIdNoSuchPortException() throws DAOException {
        when(portDAO.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchPortException.class, () -> portService.findById(1));
    }

    @Test
    public void testFindByIdServiceException() throws DAOException {
        when(portDAO.findById(1L)).thenThrow(new DAOException());
        assertThrows(ServiceException.class, () -> portService.findById(1L));
    }

    @Test
    public void testFindAllSuccess() throws DAOException, ServiceException {
        List<Port> ports = Collections.singletonList(getPort());
        List<PortDTO> portDTOs = Collections.singletonList(getPortDTO());

        when(portDAO.findAll()).thenReturn(ports);

        assertIterableEquals(portService.findAll(), portDTOs);
    }

    @Test
    public void testFindAllServiceException() throws DAOException {
        Mockito.doThrow(new DAOException()).when(portDAO).findAll();

        assertThrows(ServiceException.class, () -> portService.findAll());
        verify(portDAO).findAll();
    }

    @Test
    public void testFindSortedSuccess() throws DAOException, ServiceException {
        List<Port> ports = Collections.singletonList(getPort());
        List<PortDTO> portDTOs = Collections.singletonList(getPortDTO());

        when(portDAO.findSorted(any())).thenReturn(ports);

        assertIterableEquals(portService.findSorted(any()), portDTOs);
    }

    @Test
    public void testFindSortedServiceException() throws DAOException {
        Mockito.doThrow(new DAOException()).when(portDAO).findSorted(any());

        assertThrows(ServiceException.class, () -> portService.findSorted(any()));
        verify(portDAO).findSorted(any());
    }

    @Test
    public void testGetNumOfRowsSuccess() throws DAOException, ServiceException {
        long rows = 5L;

        when(portDAO.getNumOfRows(any())).thenReturn(rows);

        assertEquals(rows, portService.getNumOfRows(any()));
        verify(portDAO).getNumOfRows(any());
    }

    @Test
    public void testGetNumOfRowsServiceException() throws DAOException {
        Mockito.doThrow(new DAOException()).when(portDAO).getNumOfRows(any(String.class));

        assertThrows(ServiceException.class, () -> portService.getNumOfRows("String"));
        verify(portDAO).getNumOfRows(any(String.class));
    }



    private static PortDTO getPortDTO() {
        return PortDTO.builder()
                .id(1)
                .name("Boston")
                .build();

    }

    private static Port getPort() {
        return Port.builder()
                .id(1)
                .name("Boston")
                .build();
    }
}