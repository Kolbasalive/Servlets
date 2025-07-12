package org.kolbasa;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class SupportServlet extends HttpServlet {
    private SupportService supportService;

    public void setSupportService(SupportService supportService) {
        this.supportService = supportService;
    }

    @Override
    public void init() throws ServletException {
        if (supportService == null) {
            SupportRepository repository = new SupportRepository();
            super.init();
            this.supportService = new SupportService(repository);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        out.println(supportService.getSupportPhrases());
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        var phrase = req.getParameter("name");
        supportService.setSupportPhrases(phrase);
    }
}