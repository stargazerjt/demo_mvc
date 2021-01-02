<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sample UI Dynamic Form</title>
</head>
<body>
	<h1>Sample UI Dynamic Form</h1>

	<form:form method="POST" action="/form" modelAttribute="moduleTbl">
		<table>
			<tr>
				<td><form:label path="module">Module</form:label></td>
				<td>
					<form:select path="module" onchange="this.form.submit()">
						<form:option value="1">Module 1</form:option>
						<form:option value="2">Module 2</form:option>						
					</form:select>
				</td>
			</tr>
			<tr>
				<td><form:label path="majorType">Major Type</form:label></td>
				<td>
					<form:select path="majorType" onchange="this.form.submit()">
						<form:option value="1">Major Type 1</form:option>
						<form:option value="2">Major Type 2</form:option>						
					</form:select>
				</td>
			</tr>
			<tr>
				<td><form:label path="mainType">Main Type</form:label></td>
				<td>
					<form:select path="mainType" onchange="this.form.submit()">
						<form:option value="1">Main Type 1</form:option>
						<form:option value="2">Main Type 2</form:option>						
					</form:select>
				</td>
			</tr>
			<tr>
				<td colspan="2"><hr></td>
			</tr>
			<c:forEach items="${textBoxes}" var="textBox" varStatus="loop">
			<tr>
				<td colspan="2">${textBox} <input type="text" id="${textBox}" name="${textBox}"/></form></td>
			</tr>
			</c:forEach>
		</table>
	</form:form>
</body>
</html>