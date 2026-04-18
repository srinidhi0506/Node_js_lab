package coma4;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/getEmployeeServlet")
public class Retrive extends HttpServlet {
      protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     response.setContentType("text/plain");
     PrintWriter out = response.getWriter();
     try {
     EmployeeDAO dao = new EmployeeDAO();
     List<Employee> list = dao.getAllEmployees();
     out.println("List size: " + list.size());
     if (list.isEmpty()) {
       out.println("No employees found in table");
        return;
        }
     out.println("Database selected: 5a4");
   out.println("empname|eid|esal");

    for (Employee e : list) {
    	out.println(e.getEmpname()+"|"+e.getEid()+"|"+e.getEsal());
    }

     } 
     catch (Exception e) {
      out.println("Error:" + e.getMessage());
}
}
}
