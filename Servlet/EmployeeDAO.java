package coma4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    private Connection getConnection() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/24wh1a05a4",
            "root",
            "root123"
        );
    }

    public List<Employee> getAllEmployees() {

        List<Employee> list = new ArrayList<>();

        try {
            Connection con = getConnection();
            System.out.println("Connected to DB successfully"); 
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * from emp");
            while (rs.next()){
                Employee e = new Employee();
                    //e.setEid(rs.getInt("eid"));
                    System.out.println("Row found");
                    e.setEmpname(rs.getString("empname")); // column name
                    e.setEid(rs.getInt("eid"));
                    e.setEsal(rs.getInt("esal"));      // column name

                    list.add(e);
                }

                con.close();


         

        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
