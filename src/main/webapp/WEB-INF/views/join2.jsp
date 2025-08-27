<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 회원가입</title>
</head>
<body>
	<h3>학생 회원가입</h3>
	<form action="joinOk2" method="get">
		아이디 <input type="text" name="id"><br/>
		비밀번호 <input type="password" name="pw"><br/>
		이름 <input type="text" name="name"><br/>
		나이 <input type="text" name="age"><br/>
		<input type="submit" value="회원가입">
	</form>
	<hr/>
	<c:if test="${not empty error }">
	<span style="color: red;">${error }</span>
	</c:if>
</body>
</html>