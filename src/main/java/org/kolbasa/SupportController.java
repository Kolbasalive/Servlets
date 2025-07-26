package org.kolbasa;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class SupportController {
    private final SupportService supportService;

    public SupportController(SupportService supportService) {
        this.supportService = supportService;
    }

    @GetMapping("/support")
    public RequestDto getSupportPhrase(HttpServletRequest req, HttpServletResponse resp){
        return new RequestDto(supportService.getSupportPhrase());
    }

    @PostMapping("/support")
    public void setSupportPhrase(HttpServletRequest req, HttpServletResponse resp){
        var phrase = req.getParameter("phrase");
        supportService.setSupportPhrase(phrase);
    }
}
