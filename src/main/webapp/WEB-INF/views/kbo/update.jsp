<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h3>KBO 선수 수정</h3>
	<form:form modelAttribute="player">
		이름 : <form:input path="name" /><br>
		나이 : <form:input path="age" /><br>
		연봉 : <form:input path="salary" /><br>
		사진 : <input type="file" name="file" /><br>
		<input type="submit" value="수정"/>
	</form:form>
</body>
</html>