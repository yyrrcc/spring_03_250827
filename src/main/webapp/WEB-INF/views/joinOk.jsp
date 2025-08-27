<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 회원가입 확인</title>
</head>
<body>
	<h3>회원가입 성공 내역</h3>
	<h3>아이디 : ${studentDto.id }</h3>
	<h3>비밀번호 : ${studentDto.pw }</h3>
	<h3>이름 : ${studentDto.name }</h3>
	<h3>나이 : ${studentDto.age }</h3>
</body>
</html>