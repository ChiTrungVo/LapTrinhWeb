<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Đăng nhập</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">

  <%@ include file="/views/parts/topbar.jsp" %>

  <div class="row justify-content-center">
    <div class="col-md-5">
      <div class="card">
        <div class="card-header"><h4 class="mb-0">Đăng nhập</h4></div>
        <div class="card-body">

          <c:if test="${not empty alert}">
            <div class="alert alert-danger">${alert}</div>
          </c:if>

          <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="mb-3">
              <label for="username" class="form-label">Tài khoản</label>
              <input type="text" class="form-control" id="username" name="username"
                     value="${param.username != null ? param.username : (requestScope.username != null ? requestScope.username : '')}" required>
            </div>

            <div class="mb-3">
              <label for="password" class="form-label">Mật khẩu</label>
              <input type="password" class="form-control" id="password" name="password" required>
            </div>

            <div class="d-grid">
              <button type="submit" class="btn btn-primary">Đăng nhập</button>
            </div>
          </form>
			<div class="mt-3 text-center">
            <a href="${pageContext.request.contextPath}/auth/forgot">Quên mật khẩu?</a>
          </div>
        </div>
      </div>
    </div>
  </div>

</body>
</html>
