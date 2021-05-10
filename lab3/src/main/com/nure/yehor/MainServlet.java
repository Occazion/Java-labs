package com.nure.yehor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/home")
public class MainServlet extends HttpServlet {

    private Map<String, CountryContainer> nationality;

    public MainServlet() {
        super();
        nationality = new HashMap<>();
        nationality.put("Ukraine", new CountryContainer("украинец", "украинка"));
        nationality.put("Russia", new CountryContainer("россиянин", "россиянка"));
        nationality.put("Germany", new CountryContainer("немец", "немка"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message", "");
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("Вы ");
        sb.append(getInfo(req));
        req.setAttribute("message", sb.toString());
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private String getInfo(HttpServletRequest req) {
        String country = req.getParameter("country");
        String sex = req.getParameter("sex");
        CountryContainer container = nationality.get(country);
        return sex.equals("male") ? container.getMale() : container.getFemale();
    }
}
