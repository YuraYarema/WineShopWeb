<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<style>
.filter .control-label {
	text-align: left;
}

.filter span {
	display: block;
}
</style>
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
					<li class="active"><a href="/admin/alcoholContent">Alcohol
							Content</a></li>
					<li><a href="/admin/year">Year</a></li>
					<li><a href="/admin/user">User</a></li>
					<li><a href="/admin/order">Order</a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row">
	<div class="col-md-3 col-xs-12">
		<form:form class="form-horizontal filter" action="/admin/alcoholContent"
			method="GET" modelAttribute="filter">
			<custom:hiddenInputs excludeParams=" max, min" />
			<div class="form-group">
				<div class="col-sm-6">
					<form:input path="min" class="form-control" placeholder="Min" />
				</div>
				<div class="col-sm-6">
					<form:input path="max" class="form-control" placeholder="Max" />
				</div>
			</div>
			<button type="submit" class="btn btn-danger">Search</button>
		</form:form>
	</div>
	<div class="col-md-7 col-xs-12">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/admin/alcoholContent"
					method="POST" modelAttribute="alcoholContent">
					<div class="form-group">
						<label for="alcoholContent" class="col-sm-2 control-label">Percentage</label>
						<div class="col-sm-10">
							<form:input class="form-control" path="alcoholContent"
								id="alcoholContent" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-10 col-sm-offset-2 control-label"
							for="alcoholContent" style="color: red; text-align: left;"><form:errors
								path="alcoholContent" /></label>
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
			<div class="col-md-4 col-xs-4">
				<h3>Alcohol Content</h3>
			</div>
			<div class="col-md-4 col-xs-4">
				<h3>Update</h3>
			</div>
			<div class="col-md-4 col-xs-4">
				<h3>Delete</h3>
			</div>
		</div>
		<c:forEach items="${page.content}" var="alcoholContent">
			<div class="row">
				<div class="col-md-4 col-xs-4">${alcoholContent.alcoholContent}</div>
				<div class="col-md-4 col-xs-4">
					<a class="btn btn-success"
						href="/admin/alcoholContent/update/${alcoholContent.id}<custom:allParams/>">update</a>
				</div>
				<div class="col-md-4 col-xs-4">
					<a class="btn btn-danger"
						href="/admin/alcoholContent/delete/${alcoholContent.id}<custom:allParams/>">delete</a>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="col-md-2 col-xs-12">
		<div class="row">
					<div class="col-md-6 col-xs-6 text-center">
						<div class="dropdown">
							<button class="btn btn-danger dropdown-toggle" type="button"
								data-toggle="dropdown">
								Sort <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<custom:sort innerHtml="Alchol Content asc" paramValue="alcoholContent" />
								<custom:sort innerHtml="Alchol Content desc" paramValue="alcoholContent,desc" />
							</ul>
						</div>
					</div>
					<div class="col-md-6 col-xs-6 text-center">
						<custom:size posibleSizes="1,2,5,10" size="${page.size}" />
					</div>
				</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12 col-xs-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>"
			container="<ul class='pagination'></ul>" />
	</div>
</div>