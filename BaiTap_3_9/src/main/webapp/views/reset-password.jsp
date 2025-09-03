<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Đặt lại mật khẩu</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">

  <%@ include file="/views/parts/topbar.jsp" %>

  <div class="row justify-content-center">
    <div class="col-md-5">
      <div class="card">
        <div class="card-header"><h4 class="mb-0">Đặt lại mật khẩu</h4></div>
        <div class="card-body">

          <c:if test="${not empty alert}">
            <div class="alert alert-danger">${alert}</div>
          </c:if>

          <form action="${pageContext.request.contextPath}/reset-password" method="post">
            <!-- giữ lại username để biết tài khoản nào đổi mật khẩu -->
            <input type="hidden" name="username" value="${username}">

            <div class="mb-3">
              <label for="newPassword" class="form-label">Mật khẩu mới</label>
              <input type="password" class="form-control" id="newPassword" name="newPassword" required>
            </div>

            <div class="mb-3">
              <label for="confirmPassword" class="form-label">Nhập lại mật khẩu</label>
              <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
            </div>

            <div class="d-grid">
              <button type="submit" class="btn btn-success">Đổi mật khẩu</button>
            </div>
          </form>

        </div>
      </div>
    </div>
  </div>

</body>
</html>
