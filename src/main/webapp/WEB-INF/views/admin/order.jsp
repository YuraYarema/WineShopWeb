<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="row">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a href="/admin/wine">Wine</a></li>
					<li><a href="/admin/maker">Maker</a></li>
					<li><a href="/admin/country">Country</a></li>
					<li><a href="/admin/type">Type</a></li>
					<li><a href="/admin/capacity">Capacity</a></li>
					<li><a href="/admin/alcoholContent">Alcohol Content</a></li>
					<li><a href="/admin/year">Year</a></li>
					<li><a href="/admin/user">User</a></li>
					<li class="active"><a href="/admin/order">Order</a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<form class="form-horizontal" action="/admin/order" method="POST">
	<div class="form-group">
		<label for="address" class="col-md-2 control-label">Enter
			address:</label>
		<div class="col-md-10">
			<input class="form-control" type="text" name="address">
		</div>
	</div>
	<div class="form-group">
		<label for="status" class="col-md-2 control-label">Enter
			status:</label>
		<div class="col-md-10">
			<input class="form-control" type="text" name="status">
		</div>
	</div>
	<div class="form-group">
		<div class="col-md-offset-2 col-md-10">
			<button class="btn btn-primary" type="submit">Create</button>
		</div>
	</div>
</form>
<h3>Available orders:</h3>
<ul>
	<c:forEach items="${orders}" var="order">
		<li><h4>${order.user.name} ${order.user.surname} : ${order.user.email}
		<a href="/admin/order/delete/${order.id}"> <button
					type="button" class="btn btn-danger">Delete</button></a>
		</h4></li>
	</c:forEach>
</ul>