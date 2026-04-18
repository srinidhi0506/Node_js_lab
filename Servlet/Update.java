package coma4;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.*;

@WebServlet("/updateEmployeeServlet")
public class Update extends HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    response.setContentType("text/plain");
    PrintWriter out = response.getWriter();

    try {

     String name = request.getParameter("ename");
     String eidStr = request.getParameter("eid");
     String salaryStr = request.getParameter("salary");


    int eid = Integer.parseInt(eidStr);
    int salary = Integer.parseInt(salaryStr);

    Employee e = new Employee();
    e.setEid(eid);
    e.setEmpname(name);
    e.setEsal(salary);

    EmployeeDAO dao = new EmployeeDAO();
    int result = dao.updateEmployee(e);

    if (result > 0) {
      out.println("Employee Updated Successfully");
     } 
    else {
    out.println("Employee Not Found");
    }
out.println("Database selected: 5a4");
out.println("Updated Employee Table:");
out.println("EID | ENAME | SALARY ");
for (Employee emp : dao.getAllEmployees()) {
out.println(emp.getEid()+" "+emp.getEmpname()+" "+emp.getEsal());

}

} 
    catch (Exception e) {
out.println("Error: " + e.getMessage());
}
}
}
