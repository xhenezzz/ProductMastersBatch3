import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/attendance")
public class AttendanceServlet extends HttpServlet {

    private static final String URL = "jdbc:postgresql://localhost:5432/attendance_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String> groups = new ArrayList<>();
        Map<Integer, String> groupMap = new HashMap<>();
        String selectedGroup = request.getParameter("group");

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM groups ORDER BY name")) {
                while (rs.next()) {
                    groupMap.put(rs.getInt("id"), rs.getString("name"));
                    groups.add(rs.getString("name"));
                }
            }

            String sql = "SELECT s.id, s.name, s.is_attended, g.name AS group_name " +
                    "FROM students s JOIN groups g ON s.group_id = g.id";

            if (selectedGroup != null && !selectedGroup.isEmpty()) {
                sql += " WHERE g.name = ?";
            }

            List<Map<String, Object>> students = new ArrayList<>();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                if (selectedGroup != null && !selectedGroup.isEmpty()) {
                    ps.setString(1, selectedGroup);
                }
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    row.put("id", rs.getInt("id"));
                    row.put("name", rs.getString("name"));
                    row.put("groupName", rs.getString("group_name"));
                    row.put("isAttended", rs.getBoolean("is_attended"));
                    students.add(row);
                }
            }

            request.setAttribute("groups", groups);
            request.setAttribute("students", students);
            request.setAttribute("selectedGroup", selectedGroup);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/attendance.jsp").forward(request, response);
    }
}