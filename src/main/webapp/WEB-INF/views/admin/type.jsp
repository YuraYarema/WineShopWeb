<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
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
					<li class="active"><a href="/admin/type">Type</a></li>
					<li><a href="/admin/capacity">Capacity</a></li>
					<li><a href="/admin/alcoholContent">Alcohol Content</a></li>
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
		<form:form class="form-inline" action="/admin/type" method="GET"
			modelAttribute="filter">
			<custom:hiddenInputs excludeParams="search" />
			<div class="form-group">
				<form:input path="search" class="form-control" placeholder="Enter text" />
			</div>
			<button type="submit" class="btn btn-danger">Search</button>
		</form:form>
	</div>
	<div class="col-md-7 col-xs-12">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/admin/type"
					method="POST" modelAttribute="type">
					<div class="form-group">
						<label for="alcoholContent" class="col-sm-2 control-label">Type</label>
						<div class="col-sm-10">
							<form:input class="form-control" path="type" id="type" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-10 col-sm-offset-2 control-label" for="type"
							style="color: red; text-align: left;"><form:errors
								path="type" /></label>
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
				<h3>Type</h3>
			</div>
			<div class="col-md-4 col-xs-4">
				<h3>Update</h3>
			</div>
			<div class="col-md-4 col-xs-4">
				<h3>Delete</h3>
			</div>
		</div>
		<c:forEach items="${page.content}" var="type">
			<div class="row">
				<div class="col-md-4 col-xs-4">${type.type}</div>
				<div class="col-md-4 col-xs-4">
					<a class="btn btn-success" href="/admin/type/update/${type.id}<custom:allParams/>">update</a>
				</div>
				<div class="col-md-4 col-xs-4">
					<a class="btn btn-danger" href="/admin/type/delete/${type.id}<custom:allParams/>">delete</a>
				</div>
			</div>
		</c:forEach>
	</div>
<div class="col-md-2 col-xs-12">
		<div class="row">
					<div class="col-md-5 col-xs-6 text-center">
						<div class="dropdown">
							<button class="btn btn-danger dropdown-toggle" type="button"
								data-toggle="dropdown">
								Sort <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<custom:sort innerHtml="Type asc" paramValue="type" />
								<custom:sort innerHtml="Type desc" paramValue="type,desc" />
							</ul>
						</div>
					</div>
					<div class="col-md-7 col-xs-6 text-center">
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