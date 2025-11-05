<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Attendance</title></head>
<body>
<h2>Attendance List</h2>

<form method="get" action="/attendance">
    <select name="group" onchange="this.form.submit()">
        <option value="">All groups</option>
        <% for (String g : (java.util.List<String>)request.getAttribute("groups")) { %>
            <option value="<%= g %>" <%= g.equals(request.getAttribute("selectedGroup")) ? "selected" : "" %>>
                <%= g %>
            </option>
        <% } %>
    </select>
</form>

<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Group</th>
        <th>Attended</th>
        <th>Action</th>
    </tr>
    <% for (java.util.Map<String, Object> s : (java.util.List<java.util.Map<String, Object>>)request.getAttribute("students")) { %>
        <tr>
            <td><%= s.get("id") %></td>
            <td><%= s.get("name") %></td>
            <td><%= s.get("groupName") %></td>
            <td><%= (Boolean)s.get("isAttended") ? "✅" : "❌" %></td>
            <td>
                <form method="post" action="/delete-student" style="display:inline;">
                    <input type="hidden" name="id" value="<%= s.get("id") %>">
                    <button type="submit">Удалить</button>
                </form>
            </td>
        </tr>
    <% } %>
</table>

<hr>
<a href="/addStudentForm.jsp">Добавить студента</a>
</body>
</html>
