<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
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
<h2 class="text-center">Edit transportations</h2><br/><br/>
<form:form action="${pageContext.request.contextPath}/transportationEdit.htm" commandName="transportationForm" 
                 method="post">
<table class="table table-striped table-bordered table-responsive">
<tr>
<td>Id</td>
<td><form:input path="id"/></td>
</tr>
<tr>
<td>Type</td>
<td><form:input path="type"/><br/></td>
</tr>
<tr>
<td>Company</td>
<td><form:input path="company"/><br/></td>
</tr>
<tr>
<td>Contact</td>
<td><form:input path="contact"/><br/></td>
</tr>
<tr>
<td>Details</td>
<td><form:input path="details"/><br/></td>
</tr>
<tr>
<td>From destination ID</td>
<td>
<form:select path="fromDestinationId" class="custom-select">
    <form:options items="${model.destinations}" itemValue="id" itemLabel="allDestination"/>
</form:select>
</td>
<tr>
<td>To destination ID</td>
<td>
<form:select path="toDestinationId" class="custom-select">
<form:options items="${model.destinations}" itemValue="id" itemLabel="allDestination"/>
</form:select>
</td>
<tr>
<td>Price</td>
<td><form:input path="price"/><br/></td>
</tr>
</table>

<input type=submit value="Save" class="btn btn-success"/>

</form:form>
</body>
</html>