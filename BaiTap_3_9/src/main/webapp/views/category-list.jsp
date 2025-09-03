<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Category List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

    <%@ include file="/views/parts/topbar.jsp" %>
    
    <h2>Categories</h2>
    <a href="categories?action=new" class="btn btn-success mb-3">Add Category</a>

    <table class="table table-bordered">
        <tr>
            <th>ID</th><th>Name</th><th>Description</th><th>Actions</th>
        </tr>
        <c:forEach var="c" items="${list}">
            <tr>
                <td>${c.id}</td>
                <td>${c.name}</td>
                <td>${c.description}</td>
                <td>
                    <a href="categories?action=edit&id=${c.id}" class="btn btn-primary btn-sm">Edit</a>
                    <a href="categories?action=delete&id=${c.id}" class="btn btn-danger btn-sm"
                       onclick="return confirm('Are you sure?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>