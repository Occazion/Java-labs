<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
</head>
<body>
<h2>${message}</h2>
<form action="/home" method="post">
    <select name="country" required>
        <option disabled>Выберите страну:</option>
        <option value="Ukraine">Украина</option>
        <option value="Russia">Россия</option>
        <option value="Germany">Германия</option>
    </select>

    <p><input name="sex" type="radio" value="male" checked>Мужчина</p>
    <p><input name="sex" type="radio" value="female">Женщина</p>

    <button type="submit">Submit</button>
</form>
</body>
</html>
