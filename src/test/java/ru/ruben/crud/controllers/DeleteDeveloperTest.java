package ru.ruben.crud.controllers;

import org.junit.jupiter.api.Test;
import ru.ruben.crud.DAO.DeveloperDaoImpl;
import ru.ruben.crud.model.Developer;
import ru.ruben.crud.service.DeveloperService;
import ru.ruben.crud.service.DeveloperServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteDeveloperTest {

    @Test
    void doPost() throws IOException {
        DeleteDeveloper deleteDeveloper = new DeleteDeveloper();

        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        DeveloperService developerService = mock(DeveloperService.class);
        Developer mock = mock(Developer.class);

        when(request.getParameter("id")).thenReturn("126");
        when(request.getContextPath()).thenReturn("/Crud_war");
        when(developerService.findById("100")).thenReturn(mock);
        doNothing().when(developerService).delete(mock);

        deleteDeveloper.doPost(request, response);

        verify(request, times(1)).getParameter("id");
        verify(response).sendRedirect(request.getContextPath() + "/allDevelopers");
    }
}