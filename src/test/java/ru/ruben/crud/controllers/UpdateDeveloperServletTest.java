package ru.ruben.crud.controllers;

import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateDeveloperServletTest {

    @Test
    void doPost() throws ServletException, IOException {
        UpdateDeveloperServlet updateDeveloperServlet = new UpdateDeveloperServlet();

        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        String[] languages = new String[]{"Java, C++"};

        when(request.getContextPath()).thenReturn("/Crud_war");
        when(request.getParameter("id")).thenReturn("132");
        when(request.getParameter("firstName")).thenReturn("Ruben");
        when(request.getParameter("lastName")).thenReturn("Ovsepyan");
        when(request.getParameter("age")).thenReturn("30");
        when(request.getParameterValues("language")).thenReturn(languages);

        updateDeveloperServlet.doPost(request, response);

        verify(request, times(1)).getParameter("id");
        verify(request, times(1)).getParameter("firstName");
        verify(request, times(1)).getParameter("lastName");
        verify(request, times(1)).getParameter("age");
        verify(response, times(1)).sendRedirect(request.getContextPath() + "/allDevelopers");

    }

    @Test
    void doGet() throws ServletException, IOException {

        UpdateDeveloperServlet updateDeveloperServlet = new UpdateDeveloperServlet();

        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher("updateDeveloper.jsp")).thenReturn(dispatcher);

        updateDeveloperServlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher("updateDeveloper.jsp");
        verify(dispatcher).forward(request, response);
    }
}