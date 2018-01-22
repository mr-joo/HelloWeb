<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Insert title here</title>
</head>
<body>
	<h3>KBO 선수목록</h3>
	<a href="<c:url value="/kbo/create"/>">선수 추가</a>
	<ul>
		<c:forEach var="p" items="${playerlist }">
			<li><a href="<c:url value="/kbo/view/${p.id}"/>">${p.name}</a></li>
		</c:forEach>
	</ul>
</body>
</html>