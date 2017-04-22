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
					<li class="active"><a href="/admin/wine">Wine</a></li>
					<li><a href="/admin/maker">Maker</a></li>
					<li><a href="/admin/country">Country</a></li>
					<li><a href="/admin/type">Type</a></li>
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
		<form:form class="form-horizontal filter" action="/admin/wine"
			method="GET" modelAttribute="filter">
			<custom:hiddenInputs
				excludeParams="_makerIds, _typeIds, typeIds,
			 makerIds, _capacityIds, capacityIds,_yearIds, yearIds, _countryIds, countryIds,_alcoholContentIds, alcoholContentIds, max, min, search, _search" />
			<div class="form-group">
			<label class="control-label col-sm-12">Enter name:</label>
				<div class="col-sm-6">
				<form:input path="search" class="form-control" placeholder="Enter text" />
				</div>
				<div class="col-sm-6"></div>
			</div>
			<div class="form-group">
			<label class="control-label col-sm-12">Price:</label>
				<div class="col-sm-6">
					<form:input path="min" class="form-control" placeholder="Min" />
				</div>
				<div class="col-sm-6">
					<form:input path="max" class="form-control" placeholder="Max" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-12">Maker</label>
				<div class="col-sm-12">
					<form:checkboxes items="${makers}" path="makerIds"
						itemLabel="nameCompany" itemValue="id" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-12">Country</label>
				<div class="col-sm-12">
					<form:checkboxes items="${countrys}" path="countryIds" itemLabel="country"
						itemValue="id" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-12">Type</label>
				<div class="col-sm-12">
					<form:checkboxes items="${types}" path="typeIds" itemLabel="type"
						itemValue="id" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-12">Capacity</label>
				<div class="col-sm-12">
					<form:checkboxes items="${capacitys}" path="capacityIds"
						itemLabel="capacity" itemValue="id" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-12">Year</label>
				<div class="col-sm-12">
					<form:checkboxes items="${years}" path="yearIds" itemLabel="year"
						itemValue="id" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-12">Alcohol Content</label>
				<div class="col-sm-12">
					<form:checkboxes items="${ACS}" path="alcoholContentIds"
						itemLabel="alcoholContent" itemValue="id" />
				</div>
			</div>
			<button type="submit" class="btn btn-danger">Search</button>
		</form:form>
	</div>
	<div class="col-md-7 col-xs-12">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/admin/wine"
					method="POST" modelAttribute="wine" enctype="multipart/form-data">
					<custom:hiddenInputs excludeParams="name, price, file"/>
					<form:hidden path="id"/>
					<form:hidden path="version"/>
					<div class="form-group">
						<label class="col-sm-11 col-sm-offset-1 control-label" for="name"
							style="color: red; text-align: left;"><form:errors
								path="name" /></label>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-1 control-label">Name</label>
						<div class="col-sm-11">
							<form:input type="text" class="form-control" path="name"
								id="name" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-11 col-sm-offset-1 control-label" for="price"
							style="color: red; text-align: left;"><form:errors
								path="price" /></label>
					</div>
					<div class="form-group">
						<label for="price" class="col-sm-1 control-label">Price</label>
						<div class="col-sm-11">
							<form:input type="text" class="form-control" path="price"
								id="price" />
						</div>
					</div>
					<div class="form-group">
						<label for="maker" class="col-sm-1 control-label">Maker</label>
						<div class="col-sm-11">
							<form:select class="form-control" path="maker" id="maker"
								items="${makers}" itemValue="id" itemLabel="nameCompany" />
						</div>
					</div>
					<div class="form-group">
						<label for="maker" class="col-sm-1 control-label">Country</label>
						<div class="col-sm-11">
							<form:select class="form-control" path="country" id="country"
								items="${countrys}" itemValue="id" itemLabel="country" />
						</div>
					</div>
					<div class="form-group">
						<label for="type" class="col-sm-1 control-label">Type</label>
						<div class="col-sm-11">
							<form:select class="form-control" path="type" id="type"
								items="${types}" itemValue="id" itemLabel="type" />
						</div>
					</div>
					<div class="form-group">
						<label for="capacity" class="col-sm-1 control-label">Capacity</label>
						<div class="col-sm-11">
							<form:select class="form-control" path="capacity" id="capacity"
								items="${capacitys}" itemValue="id" itemLabel="capacity" />
						</div>
					</div>
					<div class="form-group">
						<label for="year" class="col-sm-1 control-label">Year</label>
						<div class="col-sm-11">
							<form:select class="form-control" path="year" id="year"
								items="${years}" itemValue="id" itemLabel="year" />
						</div>
					</div>
					<div class="form-group">
						<label for="alcoholContent" class="col-sm-1 control-label">Alcohol
							Content</label>
						<div class="col-sm-11">
							<form:select class="form-control" path="alcoholContent"
								id="alcoholContent" items="${ACS}" itemValue="id"
								itemLabel="alcoholContent" />
						</div>
					</div>
					<div class="form-group">
				<label for="file" class="col-sm-1 control-label">Image</label>
				<div class="col-sm-10">
					<input type="file" name="file" id="file">
				</div>
			</div>
					<div class="form-group">
						<div class="col-sm-offset-1 col-sm-10">
							<button type="submit" class="btn btn-primary">Create</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
		<ul></ul><div class="row">

			<div class="col-md-2 col-xs-2">
				<h3>Image</h3>
			</div>
			<div class="col-md-2 col-xs-2">
				<h3>Name</h3>
			</div>
			<div class="col-md-2 col-xs-2">
				<h3>Price</h3>
			</div>
			<div class="col-md-2 col-xs-3">
				<h3>Maker</h3>
			</div>
			<div class="col-md-2 col-xs-2">
				<h3>Update</h3>
			</div>
			<div class="col-md-2 col-xs-2">
				<h3>Delete</h3>
			</div>
		</div>
		<c:forEach items="${page.content}" var="wine">
			<ul></ul><div class="row">
				<div class="col-md-2 col-xs-2"><img src="/images/wine/${wine.id}.jpg?version=${wine.version}" width="100%"></div>
				<div class="col-md-2 col-xs-2">${wine.name}</div>
				<div class="col-md-2 col-xs-2">${wine.price}</div>
				<div class="col-md-2 col-xs-2">${wine.maker.nameCompany}</div>
				<div class="col-md-2 col-xs-2">
					<a class="btn btn-success"
						href="/admin/wine/update/${wine.id}<custom:allParams/>">update</a>
				</div>
				<div class="col-md-2 col-xs-2">
					<a class="btn btn-danger"
						href="/admin/wine/delete/${wine.id}<custom:allParams/>">delete</a>
				</div>
			</div>
		</c:forEach>
		<div class="row">
	<div class="col-md-12 col-xs-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>"
			container="<ul class='pagination'></ul>" />
	</div>
</div>
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
						<custom:sort innerHtml="Wine asc" paramValue="name" />
						<custom:sort innerHtml="Wine desc" paramValue="name,desc" />
						<custom:sort innerHtml="Price asc" paramValue="price" />
						<custom:sort innerHtml="Price desc" paramValue="price,desc" />
					</ul>
				</div>
			</div>
			<div class="col-md-6 col-xs-6 text-center">
				<custom:size posibleSizes="1,2,5,10" size="${page.size}" />
			</div>
		</div>
	</div>
</div>