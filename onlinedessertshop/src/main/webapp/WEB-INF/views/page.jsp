<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />


<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">


<title>Online Dessert - ${title}</title>

<script>
	window.menu = '${title}';

	window.contextRoot = '${contextRoot}'
</script>

<!-- <!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!--  jQuery library 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!--  Latest compiled JavaScript
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->

<!-- Bootstrap Core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap Readable Theme CSS -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">

<!-- Bootstrap Datatable -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

<link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>

	<div class="wrapper">

		<%@include file="./shared/navbar.jsp"%>

		<!-- Page Content -->
		<div class="content">

			<!-- Title will be shown when clicked home -->
			<c:if test="${userClickHome == true}">
				<%@include file="home.jsp"%>
			</c:if>

			<c:if test="${userClickAbout == true}">
				<%@include file="About.jsp"%>
			</c:if>


			<c:if test="${userClickContact == true}">
				<%@include file="Contact.jsp"%>
			</c:if>
			
			<c:if test="${userClickAllProducts == true or userClickCategoryProducts == true}">
				<%@include file="listProducts.jsp"%>
			</c:if>

			<c:if test="${userClickShowProduct == true}">
				<%@include file="singleProduct.jsp"%>
			</c:if>

			<c:if test="${userClickManageProduct == true}">
				<%@include file="manageProducts.jsp"%>
			</c:if>
			
			<c:if test="${userClickShowCart == true}">
				<%@include file="cart.jsp"%>
			</c:if>
			

		</div>

		<%@include file="./shared/footer.jsp"%>

		<!-- JQuery -->
		<script src="${js}/jquery.js"></script>
	
		<!-- Bootstrap core JavaScript -->
		<script src="${js}/bootstrap.min.js"></script>

		<!-- Datatables Plugin -->
		<script src="${js}/jquery.dataTables.js"></script>

		<!-- Datatables Bootstrap -->
		<script src="${js}/dataTables.bootstrap.js"></script>

		<!-- Self Coded JavaScript -->
		<script src="${js}/myapp.js"></script>
		
		<!-- jQuery Validator -->
		<script src="${js}/jquery.validate.js"></script>



<!-- BOOTBOX -->
		<script src="${js}/bootbox.min.js"></script>
	</div>

</body>

</html>