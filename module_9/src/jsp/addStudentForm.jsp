<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Add Student</title></head>
<body>
<h2>Добавить студента</h2>

<form method="post" action="/add-student">
    Имя: <input type="text" name="name" required><br><br>
    Группа: <input type="text" name="groupName" required><br><br>
    Посещал: <input type="checkbox" name="isAttended"><br><br>
    <button type="submit">Сохранить</button>
</form>

<hr>
<a href="/attendance">Назад к списку</a>
</body>
</html>
