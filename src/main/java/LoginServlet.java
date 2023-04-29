import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Serial;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        boolean isAdmin;

        try {
            isAdmin = CRUDapp.isAdmin(user);
            String destinationPage = "login.jsp";

            if (user != null && LoginDemo.validateLogin(user) && isAdmin) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                destinationPage = "vehiclelistadmin.jsp";
            } else if (user != null && LoginDemo.validateLogin(user) && !isAdmin) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                destinationPage = "vehiclelistuser.jsp";
            } else {
                String message = "Invalid username or password!";
                request.setAttribute("message", message);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(destinationPage);
            dispatcher.forward(request, response);

        } catch (SQLException | ClassNotFoundException | ServletException ex) {
            throw new ServletException(ex);
        }

    }
}