package ru.ruben.crud.controllers;

import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddLanguageServletTest {

    @Test
    void doGet() throws ServletException, IOException {
        AddLanguageServlet addLanguageServlet = new AddLanguageServlet();

        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher("addLanguage.jsp")).thenReturn(dispatcher);

        addLanguageServlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher("addLanguage.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    void doPost() throws IOException {
        AddLanguageServlet addLanguageServlet = new AddLanguageServlet();

        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("language")).thenReturn("Java");
        when(request.getContextPath()).thenReturn("/Crud_war");

        addLanguageServlet.doPost(request, response);

        verify(request, times(1)).getParameter("language");
        verify(response).sendRedirect(request.getContextPath());
    }
}