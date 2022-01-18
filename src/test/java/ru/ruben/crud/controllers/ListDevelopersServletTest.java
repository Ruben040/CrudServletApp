package ru.ruben.crud.controllers;

import org.junit.jupiter.api.Test;
import ru.ruben.crud.model.Developer;
import ru.ruben.crud.service.DeveloperService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class ListDevelopersServletTest {

    @Test
    void doGet() throws IOException, ServletException {
        ListDevelopersServlet listDevelopersServlet = new ListDevelopersServlet();

        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher("listDevelopers.jsp")).thenReturn(dispatcher);

        listDevelopersServlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher("listDevelopers.jsp");
        verify(dispatcher).forward(request, response);

    }
}