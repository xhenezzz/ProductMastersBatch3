import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/add-student")
public class AddStudentServlet extends HttpServlet {

    private static final String URL = "jdbc:postgresql://localhost:5432/attendance_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String name = request.getParameter("name");
        String groupName = request.getParameter("groupName");
        boolean isAttended = request.getParameter("isAttended") != null;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            int groupId;
            String selectGroup = "SELECT id FROM groups WHERE name = ?";
            try (PreparedStatement ps = conn.prepareStatement(selectGroup)) {
                ps.setString(1, groupName);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    groupId = rs.getInt("id");
                } else {

                    String insertGroup = "INSERT INTO groups (name) VALUES (?) RETURNING id";
                    try (PreparedStatement ins = conn.prepareStatement(insertGroup)) {
                        ins.setString(1, groupName);
                        ResultSet newRs = ins.executeQuery();
                        newRs.next();
                        groupId = newRs.getInt("id");
                    }
                }
            }


            String insertStudent = "INSERT INTO students (name, group_id, is_attended) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertStudent)) {
                ps.setString(1, name);
                ps.setInt(2, groupId);
                ps.setBoolean(3, isAttended);
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/attendance");
    }
}
