<%-- <%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add页面</title>
</head>
<body>


	<form action="${pageContext.request.contextPath }/testConversionServiceConvertor" method="post">
	
	
	<!-- 格式 
		
	-->
		Employee:<input type="text" name="employee">
		
		<input type="submit" value="submit">
	</form>
	<br><br>


	<!-- 使用form标签 快速开发表单页面，可以更方便的进行表单值的回显 -->
	<form:form action="${pageContext.request.contextPath }/crud/emp" method="POST" modelAttribute="employee">
		<!-- path 属性对应 html 表单标签的 name 属性值 
			可以通过 modelAttribute 属性指定绑定的模型属性，若
			没有指定该属性，则默认从 request 域对象中读取
			command 的表单 bean，如果该属性值也不存在，则会
			发生错误
		-->
		<c:if test="${employee.id == null }">
			LastName: <form:input path="lastName" />
		</c:if>
		<c:if test="${employee.id != null }">
			<form:hidden path="id"/>
			<input type="hidden" name="_method" value="PUT"/>
		</c:if>
			<br>
			Email: <form:input path="email" />
			<br>
			<%
				Map<String,String> genders = new HashMap<String,String>();
				genders.put("1", "Male");
				genders.put("0", "Female");
				request.setAttribute("genders", genders);
			%>
			Gender:<form:radiobuttons path="gender" items="${genders }"/>
			<br>
			Department:<form:select path="department.id" items="${departments }" itemLabel="departmentName" itemValue="id"></form:select>
		<br>
			<input type="submit" value="submit">
	</form:form>
</body>
</html> --%>