import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
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
                case "/newadmin" -> newVehicleFormAdmin(request, response);
                case "/new" -> newVehicleFormUser(request, response);
                case "/insertadmin" -> insertVehicleAdmin(request, response);
                case "/insert" -> insertVehicleUser(request, response);
                case "/delete" -> deleteVehicleAdmin(request, response);
                case "/edit" -> EditVehicleFormAdmin(request, response);
                case "/updateadmin" -> updateVehicleAdmin(request, response);
                case "/update" -> updateVehicleUser(request, response);
                case "/listadmin" -> listVehicleAdmin(request, response);
                default -> listVehicleUser(request, response);
            }
        } catch (SQLException exception) {
            throw new ServletException(exception);
        }
    }

    private void listVehicleAdmin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Vehicle> listVehicle = CRUDapp.listAllVehicles();
        request.setAttribute("listVehicle", listVehicle);
        RequestDispatcher dispatcher = request.getRequestDispatcher("vehiclelistadmin.jsp");
        dispatcher.forward(request, response);
    }

    private void listVehicleUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Vehicle> listVehicle = CRUDapp.listAllVehicles();
        request.setAttribute("listVehicle", listVehicle);
        RequestDispatcher dispatcher = request.getRequestDispatcher("vehiclelistuser.jsp");
        dispatcher.forward(request, response);
    }

    private void newVehicleFormAdmin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("vehicleformadmin.jsp");
        dispatcher.forward(request, response);
    }

    private void newVehicleFormUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("vehicleformuser.jsp");
        dispatcher.forward(request, response);
    }

    private void EditVehicleFormAdmin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Vehicle existingVehicle = cruDapp.getVehicleId(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("vehicleformadmin.jsp");
        request.setAttribute("vehicle", existingVehicle);
        dispatcher.forward(request, response);
    }

    private void insertVehicleAdmin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String brand = request.getParameter("brand");
        long year = Long.parseLong(request.getParameter("year"));
        long mileage = Long.parseLong(request.getParameter("mileage"));

        Vehicle newVehicle = new Vehicle(brand, year, mileage);
        CRUDapp.isVehicleInserted(newVehicle);
        response.sendRedirect("listadmin");
    }

    private void insertVehicleUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String brand = request.getParameter("brand");
        long year = Long.parseLong(request.getParameter("year"));
        long mileage = Long.parseLong(request.getParameter("mileage"));

        Vehicle newVehicle = new Vehicle(brand, year, mileage);
        CRUDapp.isVehicleInserted(newVehicle);
        response.sendRedirect("list");
    }

    private void updateVehicleAdmin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        long id = Long.parseLong(request.getParameter("id"));
        String brand = request.getParameter("brand");
        long year = Long.parseLong(request.getParameter("year"));
        long mileage = Long.parseLong(request.getParameter("mileage"));

        Vehicle vehicle = new Vehicle(id, brand, year, mileage);
        CRUDapp.isVehicleUpdated(vehicle);
        response.sendRedirect("listadmin");
    }

    private void updateVehicleUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        long id = Long.parseLong(request.getParameter("id"));
        String brand = request.getParameter("brand");
        long year = Long.parseLong(request.getParameter("year"));
        long mileage = Long.parseLong(request.getParameter("mileage"));

        Vehicle vehicle = new Vehicle(id, brand, year, mileage);
        CRUDapp.isVehicleUpdated(vehicle);
        response.sendRedirect("list");
    }

    private void deleteVehicleAdmin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        long id = Long.parseLong(request.getParameter("id"));

        Vehicle vehicle = new Vehicle(id);
        CRUDapp.isVehicleDeleted(vehicle);
        response.sendRedirect("listadmin");
    }

}

