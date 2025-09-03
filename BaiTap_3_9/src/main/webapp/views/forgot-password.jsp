<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Quên mật khẩu</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">

  <div class="row justify-content-center">
    <div class="col-md-6">
      <div class="card">
        <div class="card-header"><h4 class="mb-0">Quên mật khẩu</h4></div>
        <div class="card-body">

          <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
          </c:if>

          <form action="${pageContext.request.contextPath}/auth/forgot" method="post">
            <div class="mb-3">
              <label for="username" class="form-label">Tài khoản</label>
              <input type="text" class="form-control" id="username" name="username" required>
            </div>

            <div class="mb-3">
              <label for="email" class="form-label">Địa chỉ Email</label>
              <input type="email" class="form-control" id="emailaddress" name="emailaddress" required>
            </div>

            <div class="d-grid">
              <button type="submit" class="btn btn-primary">Xác nhận</button>
            </div>
          </form>

          <div class="mt-3 text-center">
            <a href="${pageContext.request.contextPath}/login">Quay lại đăng nhập</a>
          </div>

        </div>
      </div>
    </div>
  </div>

</body>
</html>
