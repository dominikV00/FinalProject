import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        boolean isAdmin;

        try {
            isAdmin = CRUDapp.isAdmin(user);
            if (LoginDemo.validateLogin(user) && isAdmin)
                response.sendRedirect("vehiclelist.jsp");
            else
                response.sendRedirect("loginsuccess.html");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}