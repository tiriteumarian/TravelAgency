<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="<c:url value="/resources/styles/bootstrap.min.css" />" rel="stylesheet">
<script src="<c:url value="/resources/javascript/bootstrap.min.js" />"></script>
<link href="<c:url value="/resources/styles/style.css" />" rel="stylesheet">
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
<h2 class="text-center">Accommodations Details</h2><br/><br/> <br/><br/>
<table class="table table-striped table-bordered table-responsive">
<tr>
<td>ID</td><td><c:out value="${model.accommodation.id}"/></td>
</tr>
<tr>
<td>Name</td><td><c:out value="${model.accommodation.name}"/></td>
</tr>
<tr>
<td>Type</td><td><c:out value="${model.accommodation.type}"/></td>
</tr>
<tr>
<td>Price</td><td><c:out value="${model.accommodation.price}"/></td>
</tr>
<tr>
<td>Contact</td><td><c:out value="${model.accommodation.contact}"/></td>
</tr>
<tr>
<td>Details</td><td><c:out value="${model.accommodation.details}"/></td>
</tr>
</table>
<a href="<c:url value="/accommodations.htm" />"><input type=submit value="Back to list" class="btn btn-success"/></a>
</body>
</html>