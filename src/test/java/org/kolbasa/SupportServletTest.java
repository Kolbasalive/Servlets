/*
package org.kolbasa;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SupportServletTest {
    private static final String SUPPORT_MESSAGE = "У тебя всё получится!";

    private SupportServlet supportServlet;
    private SupportServiceImpl supportServiceImpl;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private StringWriter responseWriter;
    private PrintWriter printWriter;

    @BeforeEach
    void setUp() throws IOException {
        supportServiceImpl = mock(SupportServiceImpl.class);
        supportServlet = new SupportServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        responseWriter = new StringWriter();
        printWriter = new PrintWriter(responseWriter);
        supportServlet.setSupportService(supportServiceImpl);

        when(response.getWriter()).thenReturn(printWriter);
    }

    @Test
    void doGet() throws ServletException, IOException {
        when(supportServiceImpl.getSupportPhrase()).thenReturn(SUPPORT_MESSAGE);

        supportServlet.doGet(request, response);

        assertTrue(responseWriter.toString().contains(SUPPORT_MESSAGE));
    }

    @Test
    void doPost() throws ServletException, IOException {
        when(request.getParameter("name")).thenReturn(SUPPORT_MESSAGE);

        supportServlet.doPost(request, response);

        verify(response).setContentType("text/plain");
        verify(request).getParameter("name");
        verify(supportServiceImpl).setSupportPhrase(SUPPORT_MESSAGE);
    }
}*/
