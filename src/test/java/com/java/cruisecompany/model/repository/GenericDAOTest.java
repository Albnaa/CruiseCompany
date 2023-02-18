package com.java.cruisecompany.model.repository;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.junit.jupiter.api.Assertions.*;

class GenericDAOTest {

    @Mock
    Connection connection;

    @Mock
    PreparedStatement preparedStatement;




    @Test
    void executeNoReturnSuccess() {
    }

    @Test
    void executeOneReturn() {
    }

    @Test
    void executeListReturn() {
    }

    @Test
    void executeNumOfRowsReturn() {
    }
}