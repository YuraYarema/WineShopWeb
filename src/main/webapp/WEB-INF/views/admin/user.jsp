<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
					<li class="active"><a href="/admin/user">User</a></li>
					<li><a href="/admin/order">Order</a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row">
	<div class="col-md-3 col-xs-12"></div>
	<div class="col-md-7 col-xs-12">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/admin/user"
					method="POST" modelAttribute="user">
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">User</label>
						<div class="col-sm-10">
							<form:input class="form-control" path="name" id="name" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-10 col-sm-offset-2 control-label"
							for="name" style="color: red; text-align: left;"><form:errors
								path="name" /></label>
					</div>
						<div class="form-group">
						<label for="surname" class="col-sm-2 control-label">Surname</label>
						<div class="col-sm-10">
							<form:input class="form-control" path="surname" id="surname" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-10 col-sm-offset-2 control-label"
							for="surname" style="color: red; text-align: left;"><form:errors
								path="surname" /></label>
					</div>
						<div class="form-group">
						<label for="email" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-10">
							<form:input class="form-control" path="email" id="email" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-10 col-sm-offset-2 control-label"
							for="email" style="color: red; text-align: left;"><form:errors
								path="email" /></label>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-primary">Create</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-2 col-xs-2">
				<h3>Name</h3>
			</div>
			<div class="col-md-2 col-xs-2">
				<h3>Surname</h3>
			</div>
			<div class="col-md-3 col-xs-3">
				<h3>Email</h3>
			</div>
			<div class="col-md-3 col-xs-3">
				<h3>Update</h3>
			</div>
			<div class="col-md-2 col-xs-2">
				<h3>Delete</h3>
			</div>
		</div>
		<c:forEach items="${users}" var="user">
			<div class="row">
				<div class="col-md-2 col-xs-2">${user.name}</div>
				<div class="col-md-2 col-xs-2">${user.surname}</div>
				<div class="col-md-3 col-xs-2">${user.email}</div>
				<div class="col-md-3 col-xs-2">
					<a class="btn btn-success"
						href="/admin/user/update/${user.id}">update</a>
				</div>
				<div class="col-md-2 col-xs-2">
					<a class="btn btn-danger"
						href="/admin/user/delete/${user.id}">delete</a>
				</div>
			</div>
		</c:forEach>
	</div>
</div>