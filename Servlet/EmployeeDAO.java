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

    // ✅ INSERT
    public int insert(Employee e) {

        int status = 0;

        try {
            Connection con = getConnection();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO emp VALUES (?, ?, ?)"
            );

            ps.setInt(1, e.getEid());
            ps.setString(2, e.getEmpname());
            ps.setInt(3, e.getEsal());

            status = ps.executeUpdate();

            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return status;
    }

    // ✅ GET ALL
    public List<Employee> getAllEmployees() {

        List<Employee> list = new ArrayList<>();

        try {
            Connection con = getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM emp");

            while (rs.next()) {
                Employee e = new Employee();

                e.setEid(rs.getInt("eid"));
                e.setEmpname(rs.getString("empname"));
                e.setEsal(rs.getInt("esal"));

                list.add(e);
            }

            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;
    }

    // ✅ DELETE
    public int deleteEmployee(int eid) {

        int status = 0;

        try {
            Connection con = getConnection();

            PreparedStatement ps = con.prepareStatement(
                "DELETE FROM emp WHERE eid=?"
            );

            ps.setInt(1, eid);

            status = ps.executeUpdate();

            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return status;
    }

    // ✅ UPDATE
    public int updateEmployee(Employee e) {

        int status = 0;

        try {
            Connection con = getConnection();

            PreparedStatement ps = con.prepareStatement(
                "UPDATE emp SET empname=?, esal=? WHERE eid=?"
            );

            ps.setString(1, e.getEmpname());
            ps.setInt(2, e.getEsal());
            ps.setInt(3, e.getEid());

            status = ps.executeUpdate();

            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return status;
    }
}
