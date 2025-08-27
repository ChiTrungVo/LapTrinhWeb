<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Home</title>
</head>
<body>

  <c:choose>
    <c:when test="${sessionScope.account != null}">
      <p>Xin chào, ${sessionScope.account.fullName}</p>
      <p><a href="${pageContext.request.contextPath}/logout">Đăng Xuất</a></p>
    </c:when>
    <c:otherwise>
      <p>Bạn chưa đăng nhập.</p>
    </c:otherwise>
  </c:choose>

</body>
</html>
