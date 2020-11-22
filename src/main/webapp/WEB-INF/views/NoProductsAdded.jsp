<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="<c:url value="/resources/css/sideMenu.css" />" rel="stylesheet">
	<title>hw3_6_ProductsAddedJSP</title>
</head>



<body>

<div class="sidenav">
  <a href="main.shop?view=books">Books</a>
  <a href="main.shop?view=music">Music</a>
  <a href="main.shop?view=computers">Computers</a>
  <div class="logout">
    <a href="main.shop?view=viewCart">View Cart</a>
  	<a href="main.shop?view=logout">Sign Out</a>
  </div>
  <div class="vl"></div>

</div>

<div class="main">
	<h4 style="color:red">Error! No Items were selected.</h4>
	<br><br>
  <a href="main.shop?view=viewCart">[View Cart]</a>
  <a href="main.shop?view=books">[Go to Books Page]</a>
  <a href="main.shop?view=music">[Go to Music Page]</a>
  <a href="main.shop?view=computers">[Go to Computers Page]</a>
</div>

</body>
</html>