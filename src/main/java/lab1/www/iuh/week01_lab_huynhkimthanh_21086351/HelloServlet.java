package lab1.www.iuh.week01_lab_huynhkimthanh_21086351;

import java.io.*;

import entities.Account;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repositories.AccountRepository;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        String un = request.getParameter("un");
        String pw = request.getParameter("pw");

        AccountRepository accountRepository = new AccountRepository();

        Account account = accountRepository.findAccountByIdAndPassword(un, pw);

        if (account != null) {
            out.println("<html><body>");
            out.println("<h1>Welcome " + account.getFullName() + "</h1>");
            out.println("</body></html>");
        } else {
            out.println("<html><body>");
            out.println("<h1>Invalid username or password</h1>");
            out.println("</body></html>");
        }
    }

    public void destroy() {
    }
}