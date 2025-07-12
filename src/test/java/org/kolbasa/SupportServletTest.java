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
    private SupportService supportService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private StringWriter responseWriter;
    private PrintWriter printWriter;

    @BeforeEach
    void setUp() throws IOException {
        supportService = mock(SupportService.class);
        supportServlet = new SupportServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        responseWriter = new StringWriter();
        printWriter = new PrintWriter(responseWriter);
        supportServlet.setSupportService(supportService);

        when(response.getWriter()).thenReturn(printWriter);
    }

    @Test
    void doGet() throws ServletException, IOException {
        when(supportService.getSupportPhrases()).thenReturn(SUPPORT_MESSAGE);

        supportServlet.doGet(request, response);

        assertTrue(responseWriter.toString().contains(SUPPORT_MESSAGE));
    }

    @Test
    void doPost() throws ServletException, IOException {
        when(request.getParameter("name")).thenReturn(SUPPORT_MESSAGE);

        supportServlet.doPost(request, response);

        verify(response).setContentType("text/plain");
        verify(request).getParameter("name");
        verify(supportService).setSupportPhrases(SUPPORT_MESSAGE);
    }
}