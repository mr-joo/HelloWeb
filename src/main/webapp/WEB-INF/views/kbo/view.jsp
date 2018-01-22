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
	<h3>KBO 선수 상세</h3>
		이름 : ${ player.name }<br>
		나이 : ${ player.age }<br>
		연봉 : <fmt:formatNumber value="${player.salary}" type="currency"></fmt:formatNumber><br>
		<a href="<c:url value="/kbo/update/${player.id}"/>">수정</a>
		<a href="<c:url value="/kbo/delete/${player.id}"/>">삭제</a>
</body>
</html>