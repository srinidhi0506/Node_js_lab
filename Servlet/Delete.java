package coma4;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/deleteEmployeeServlet")
public class Delete extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        try {
            String eidStr = request.getParameter("eid");

            if (eidStr == null) {
                out.println("Please provide eid");
                return;
            }

            int eid = Integer.parseInt(eidStr);

            EmployeeDAO dao = new EmployeeDAO();
            int result = dao.deleteEmployee(eid);

            if (result > 0) {
                out.println("Employee Deleted Successfully\n");
            } else {
                out.println("Employee Not Found\n");
            }

            out.println("Database selected: 24wh1a05a4");
            out.println("Updated Employee Table:");
            out.println("EID | ENAME | SALARY");

            List<Employee> list = dao.getAllEmployees();

            if (list.isEmpty()) {
                out.println("No employees found in table");
            } else {
                for (Employee e : list) {
                    out.println(
                        e.getEid() + " | " +
                        e.getEmpname() + " | " +
                        e.getEsal()
                    );
                }
            }

        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
