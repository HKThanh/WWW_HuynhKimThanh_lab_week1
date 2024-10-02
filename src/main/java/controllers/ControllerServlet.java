package controllers;

import dto.AccountDTO;
import entities.Account;
import entities.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.LogRepository;
import services.AccountServices;
import services.RoleServices;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "controllerServlet", value = "/controller-servlet")
public class ControllerServlet extends HttpServlet {
    private final LogRepository logRepository = new LogRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        PrintWriter out = resp.getWriter();


        if (action.equals("login")) {
            Object un = req.getParameter("un");
            Object pw = req.getParameter("pw");

            AccountServices accountServices = new AccountServices();
            RoleServices roleServices = new RoleServices();

            Account account = accountServices.findAccountByIdAndPassword(un, pw);

            if (account == null) {
                out.println("<h1>Invalid Username or Password</h1>");
            } else {
                Role role = roleServices.getRoleIDByAccountID(account.getAccountId());
                String accountAndItsRole = account.getFullName() + ", role = " + role.getRoleName();

                List<AccountDTO> accountAndRoles = accountServices.getAllAccountAndRoles(un, pw);
                logRepository.addLog(account);

                if (role.getRoleId().equals("user")) {
                    req.setAttribute("account", account);
                    req.setAttribute("accountAndItsRole", accountAndItsRole);
                    req.getRequestDispatcher("/user.jsp").forward(req, resp);
                } else if (role.getRoleName().equals("admin")) {
                    req.setAttribute("accountAndRoles", accountAndRoles);
                    req.setAttribute("account", account);
                    req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
                } else {
                    out.println("<h1>Account hasn't been granted role</h1>");
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action.equals("logout")) {
            String accountId = req.getParameter("acc");
            logRepository.updateLog(accountId);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);

        }
    }
}
