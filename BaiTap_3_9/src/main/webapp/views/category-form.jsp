<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Category Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

<h2>${category == null ? "Add New Category" : "Edit Category"}</h2>

<form action="categories" method="post">
    <c:if test="${category != null}">
        <input type="hidden" name="id" value="${category.id}">
    </c:if>

    <div class="mb-3">
        <label class="form-label">Name</label>
        <input type="text" name="name" class="form-control"
               value="${category != null ? category.name : ''}" required>
    </div>

    <div class="mb-3">
        <label class="form-label">Description</label>
        <input type="text" name="description" class="form-control"
               value="${category != null ? category.description : ''}">
    </div>

    <button type="submit" class="btn btn-success">Save</button>
    <a href="categories" class="btn btn-secondary">Cancel</a>
</form>

</body>
</html>
