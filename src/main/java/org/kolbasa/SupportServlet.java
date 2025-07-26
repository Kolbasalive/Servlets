/*
package org.kolbasa;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class SupportServlet extends HttpServlet {
    private SupportServiceImpl supportServiceImpl;

    public void setSupportService(SupportServiceImpl supportServiceImpl) {
        this.supportServiceImpl = supportServiceImpl;
    }

    @Override
    public void init() throws ServletException {
        if (supportServiceImpl == null) {
            SupportRepository repository = new SupportRepository();
            super.init();
            this.supportServiceImpl = new SupportServiceImpl(repository);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        out.println(supportServiceImpl.getSupportPhrase());
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        var phrase = req.getParameter("name");
        supportServiceImpl.setSupportPhrase(phrase);
    }
}*/
