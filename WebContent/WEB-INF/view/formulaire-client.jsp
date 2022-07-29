<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Client formulaire</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/ressources/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/ressources/css/add-customer-style.css">
</head>
<body>
	<div id="wrapper">
		<div id=header">
			<h2>MRC - Manager relation client</h2>
		</div>
		<div id="container">
			<h3>Sauvegarder client</h3>
			<form:form action="sauvegarderClient" modelAttribute="client" method="POST">
			<form:hidden path="id" />
			<table>
				<tbody>
					<tr>
						<td><label>Prénom :</label></td>
						<td><form:input path="firstName"/></td>
					</tr>
					<tr>
						<td><label>Nom :</label></td>
						<td><form:input path="LastName"/></td>
					</tr>
					<tr>
						<td><label>Email :</label></td>
						<td><form:input path="email"/></td>
					</tr>
					<tr>
						<td><label></label></td>		
						<td><input type="submit" value="Sauvegarder" class="save"/></td>
					</tr>
				</tbody>
			</table>
			</form:form>
			<div style="clear; both;"></div>
			<p>
				<a href="${pageContext.request.contextPath}/client/liste">Back to list</a>
			</p>
	</div>
		</div>
	</div>
</body>
</html>