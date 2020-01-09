<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="<c:url value="/resources/styles/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/styles/style.css" />" rel="stylesheet">
<script src="<c:url value="/resources/javascript/bootstrap.min.js" />"></script>
<title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light ">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <a class="navbar-brand" href="home.htm">Home</a>

  <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
      <li class="nav-item active">
        <a class="nav-link" href="/Travel/accommodations.htm">Accommodations <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/Travel/clients.htm">Clients</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/Travel/destinations.htm">Destinations</a>
      </li>
     <li class="nav-item">
        <a class="nav-link" href="/Travel/reservations.htm">Reservations</a>
      </li>     
      <li class="nav-item active">
        <a class="nav-link" href="/Travel/transportations.htm">Transportations<span class="sr-only">(current)</span></a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>
<h2 class="text-center">All transportations</h2><br/><br/>
	<table class="table table-striped table-bordered table-responsive">
	
<tr>
<td>Id</td>
<td>Type</td>
<td>Company</td>
<td>Contact</td>
<td>Details</td>
<td>From Destination ID</td>
<td>To Destination ID</td>
<td>Price</td>
</tr>

<c:forEach items="${model.transportations}" var = "tr">
<tr>
<td><c:out value = "${tr.id}"/></td>
<td><c:out value = "${tr.type}"/><br/></td>
<td><c:out value = "${tr.company}"/><br/></td>
<td><c:out value = "${tr.contact}"/><br/></td>
<td><c:out value = "${tr.details}"/></td>
<td><c:out value = "${model.fromDestinationMap.get(tr.id)}"/><br/></td>
<td><c:out value = "${model.toDestinationMap.get(tr.id)}"/><br/></td>
<td><c:out value = "${tr.price}"/><br/></td>
	<td><a href="./transportationDetails/${tr.id}">Details</a></td>
	<td><a href="./editTransportation/${tr.id}">Edit</a></td>
	<td><a href="./deleteTransportation/${tr.id}">Delete</a></td>
</tr>
</c:forEach>
</table>
<a href="<c:url value="/transportationadd.htm" />"><input type=submit value="Add new transporation" class="btn btn-success"/></a>
</body>
</html>