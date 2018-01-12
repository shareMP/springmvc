<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- <script type="text/javascript" src="scripts/jquery-3.2.1.js"></script> -->
</head>
<body>


	<!-- 模拟修改操作
		原始数据：1，tom，123,tom@gmail.com,22
		密码不能被修改
		表达回显，模拟操作直接在表单填写对应的属性值
	 -->
	<form action="springMvc/testModelAttribute" method="post">
		<input type="hidden" name="id" value="1">
		username:<input type="text" name="username" value="tom">
		<br>
		<!-- passowrd:<input type="password" name="password"> -->
		<br>
		email:<input type="text" name="email" value="tom@gmail.com">
	<br>
		age:<input type="text" name="age" value="22">
		<br>
		<input type="submit" value="submit">
	</form>
<br>
	<br>

<a href="springMvc/testSessionAttributes">testSessionAttributes</a>
	<br>
	<br>

<a href="springMvc/testMap">testMap</a>
	<br>
	<br>

<a href="springMvc/testModelAndView">testModelAndView</a>
	<br>
	<br>

<a href="springMvc/testServletAPI">testServletAPI</a>
	<br>
	<br>


	<form action="springMvc/testPojo">
		username:<input type="text" name="username">
		<br>
		passowrd:<input type="password" name="password">
		<br>
		email:<input type="text" name="email">
	<br>
		age:<input type="text" name="age">
		<br>
		city:<input type="text" name="address.city">
			<br>
			province:<input type="text" name="address.province">
			<br>
		<input type="submit" value="submit">
	</form>
	<br><br>
<a href="springMvc/testCookieValue">testCookieValue</a>
	<br>
	<br>

<a href="springMvc/testRequestHeader">testRequestHeader</a>
	<br>
	<br>

<a href="springMvc/testRequestParam?username=lwq&age=20">testRequestParam</a>
	<br>
	<br>

	
	<a href="springMvc/testRest/1">testRest Get</a>
	<br>
	<br>
	
	<form action="springMvc/testRest/1" method="post">
		<input type="hidden" name="_method" value="DELETE">
		<input type="submit" value="testRest DELETE">
	</form>
	
	<form action="springMvc/testRest/1" method="post">
		<input type="hidden" name="_method" value="PUT">
		<input type="submit" value="testRest Put">
	</form>
	<br>
	<br>
	
	<form action="springMvc/testRest/1" method="post">
		<input type="submit" value="testRest Post">
	</form>
	<br>
	<br>
	
	
	<!-- <a href="springMvc/testRest">testRes POST</a> -->
	<br>
	<br>
	


<a href="springMvc/testPathVariable/1">testPathVariable</a>
	<br>
	<br>

	
<a href="springMvc/testAntPath/a/s/cs/sad/abc">Test AntPath</a>
	<br>
	<br>

	<a href="springMvc/testMethod">Test Method</a>
	<br>
	<br>

	<a href="springMvc/testParamAndHeaders?username=ceshi&age=10">testParamAndHeaders</a>
	<br>
	<br>

	<form action="springMvc/testMethod" method="post">
		<input type="submit" value="submit">
	</form>


	<a href="springMvc/testRequestMapping">testRequestMapping</a>
	<br>
	<br>
	<a href="helloworld">Hello,World</a>
	<script type="text/javascript">
		function testMethod() {
			$.ajax({
				type : "POST",
				url : "springMvc/testMethod",
				data : "name=John&location=Boston",
				success : function(msg) {
					alert("testMethod");
				}
			});
		}
	</script>
</body>
</html>