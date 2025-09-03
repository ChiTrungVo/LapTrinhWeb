<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Đăng ký tài khoản</title>
</head>
<body>
  <h2>Đăng ký tài khoản mới</h2>
  
  <c:if test="${not empty requestScope.error}">
    <p style="color: red;">${requestScope.error}</p>
  </c:if>

  <form action="${pageContext.request.contextPath}/register" method="post">
    <div>
      <label for="id">ID:</label>
      <input type="text" id="id" name="id" required>
    </div>
    <div>
      <label for="username">Username:</label>
      <input type="text" id="username" name="username" required>
    </div>
    <div>
      <label for="password">Password:</label>
      <input type="password" id="password" name="password" required>
    </div>
    <div>
      <label for="fullname">Họ và tên:</label>
      <input type="text" id="fullname" name="fullname" required>
    </div>
    <div>
      <label for="email">Email:</label>
      <input type="email" id="email" name="email" required>
    </div>
    <button type="submit">Đăng ký</button>
  </form>
</body>
</html>