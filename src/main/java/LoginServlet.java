import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.Serial;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends jakarta.servlet.http.HttpServlet {

    @Serial
    private static final long serialVersionUID = 1L;

    protected void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws IOException {

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