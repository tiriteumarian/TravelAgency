<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="<c:url value="/resources/styles/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/styles/style.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/javascript/bootstrap.min.js" />"></script>
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light ">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarTogglerDemo03"
			aria-controls="navbarTogglerDemo03" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<a class="navbar-brand" href="home.htm">Home</a>

		<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<li class="nav-item active"><a class="nav-link"
					href="/Travel/accommodations.htm">Accommodations <span
						class="sr-only">(current)</span></a></li>
				<li class="nav-item"><a class="nav-link"
					href="/Travel/clients.htm">Clients</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/Travel/destinations.htm">Destinations</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/Travel/reservations.htm">Reservations</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="/Travel/transportations.htm">Transportations<span
						class="sr-only">(current)</span></a></li>
			</ul>
			<form:form
				action="${pageContext.request.contextPath}/clientSearch.htm"
				commandName="clientForm" method="post"
				class="form-inline my-2 my-lg-0">

				<input class="form-control mr-sm-2" type="search"
					placeholder="Search" name="searchQuery" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form:form>
		</div>
	</nav>
	<h2 class="text-center">All clients</h2>
	<br />
	<br />
	<table class="table table-striped table-bordered table-responsive">

		<tr>
			<td>Id</td>
			<td>First Name</td>
			<td>Last Name</td>
			<td>Phone</td>
			<td>Email</td>
		</tr>

		<c:forEach items="${model.clients}" var="cl">
			<tr>
				<td><c:out value="${cl.id}" /></td>
				<td><c:out value="${cl.firstName}" /><br /></td>
				<td><c:out value="${cl.lastName}" /><br /></td>
				<td><c:out value="${cl.phone}" /><br /></td>
				<td><c:out value="${cl.email}" /><br /></td>
				<td><a href="./clientDetails/${cl.id}">Details</a></td>
				<td><a href="./editClient/${cl.id}">Edit</a></td>
				<td><a href="./deleteClient/${cl.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="<c:url value="/clientadd.htm" />"><input type=submit
		value="Add new client" class="btn btn-success" /></a>
</body>
</html>