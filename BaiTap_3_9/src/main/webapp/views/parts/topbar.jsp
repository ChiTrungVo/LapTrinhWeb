<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Topbar</title>
</head>
<body>

<c:choose>
  <c:when test="${sessionScope.account == null}">
    <div class="col-sm-6">
      <ul class="list-inline right-topbar float-end">
        <li class="list-inline-item">
          <a href="${pageContext.request.contextPath}/login">Đăng nhập</a> |
          <a href="${pageContext.request.contextPath}/register">Đăng ký</a>
        </li>
        <li class="list-inline-item">
          <i class="search fa fa-search search-button" aria-hidden="true"></i>
        </li>
      </ul>
    </div>
  </c:when>

  <c:otherwise>
    <div class="col-sm-6">
      <ul class="list-inline right-topbar float-end">
        <li class="list-inline-item">
          <%-- Đảm bảo bạn sử dụng đúng tên thuộc tính: fullName --%>
          <a href="${pageContext.request.contextPath}/member/myaccount">
            ${sessionScope.account.fullName}
          </a> |
          <a href="${pageContext.request.contextPath}/logout">Đăng Xuất</a>
        </li>
        <li class="list-inline-item">
          <i class="search fa fa-search search-button" aria-hidden="true"></i>
        </li>
      </ul>
    </div>
  </c:otherwise>
</c:choose>

</body>
</html>