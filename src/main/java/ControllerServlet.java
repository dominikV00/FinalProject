import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.Serial;
import java.sql.SQLException;
import java.util.List;

public class ControllerServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private CRUDapp cruDapp;

    public void init() {
        String URLDB = getServletContext().getInitParameter("jdbc:postgresql://localhost:5432/finaljavaproject");
        String USERNAMEDB = getServletContext().getInitParameter("postgres");
        String PASSWORDDB = getServletContext().getInitParameter("itdomi");

        cruDapp = new CRUDapp(URLDB, USERNAMEDB, PASSWORDDB);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new" -> newVehicleForm(request, response);
                case "/insert" -> insertVehicle(request, response);
                case "/delete" -> deleteVehicle(request, response);
                case "/edit" -> EditVehicleForm(request, response);
                case "/update" -> updateVehicle(request, response);
                default -> listVehicle(request, response);
            }
        } catch (SQLException exception) {
            throw new ServletException(exception);
        }
    }

    private void listVehicle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Vehicle> listVehicle = CRUDapp.listAllVehicles();
        request.setAttribute("listVehicle", listVehicle);
        RequestDispatcher dispatcher = request.getRequestDispatcher("vehiclelist.jsp");
        dispatcher.forward(request, response);
    }

    private void newVehicleForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("vehicleform.jsp");
        dispatcher.forward(request, response);
    }

    private void EditVehicleForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Vehicle existingVehicle = cruDapp.getVehicleId(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("vehicleform.jsp");
        request.setAttribute("vehicle", existingVehicle);
        dispatcher.forward(request, response);
    }

    private void insertVehicle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String brand = request.getParameter("brand");
        long year = Long.parseLong(request.getParameter("year"));
        long mileage = Long.parseLong(request.getParameter("mileage"));

        Vehicle newVehicle = new Vehicle(brand, year, mileage);
        CRUDapp.isVehicleInserted(newVehicle);
        response.sendRedirect("list");
    }

    private void updateVehicle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String brand = request.getParameter("brand");
        long year = Long.parseLong(request.getParameter("year"));
        long mileage = Long.parseLong(request.getParameter("mileage"));

        Vehicle vehicle = new Vehicle(id, brand, year, mileage);
        CRUDapp.isVehicleUpdated(vehicle);
        response.sendRedirect("list");
    }

    private void deleteVehicle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));

        Vehicle vehicle = new Vehicle(id);
        CRUDapp.isVehicleDeleted(vehicle);
        response.sendRedirect("list");

    }

}

