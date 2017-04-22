<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<style>
.filter .control-label {
	text-align: left;
}

.filter span {
	display: block;
}

#wines {
	background-color: #b30000;
	color: white;
}

h1, h2, h3, h4 {
	font-variant: small-caps;
}

.price {
	color: red;
}

#filt {
	color: #b30000;
	font-weight: 700;
	font-variant: small-caps;
}

.footer {
	background: black;
	border-top: #900022 5px solid;
	overflow: hidden;
	color: white;
	text-align:center;
}

.copyright {
	font-family: 'proxima_nova_rgregular', Arial, sans-serif;
	font-size: 12px;
	color: #fff;
	margin: 35px 0 40px 0;
}

.layout-main {
	margin: 0 auto;
	padding: 5px 2.5%;
}

#def {
	color: black;
}

hr {
	height: 30px;
	border-style: solid;
	border-color: #b30000;
	border-width: 1px 0 0 0;
	border-radius: 20px;
}

hr:before {
	display: block;
	content: "";
	height: 30px;
	margin-top: -31px;
	border-style: solid;
	border-color: #b30000;
	border-width: 0 0 1px 0;
	border-radius: 20px;
}
</style>
	<div class="row">
		<div class="col-md-3 col-xs-12">
			<form:form class="form-horizontal filter" action="/" method="GET"
				modelAttribute="filter">
				<custom:hiddenInputs
					excludeParams="_makerIds, _typeIds, typeIds,
			 makerIds, _capacityIds, capacityIds, _countryIds, countryIds, max, min, search, _search" />
				<div class="form-group">
					<label class="control-label col-sm-12" id="filt">Enter
						name:</label>
					<div class="col-sm-6">
						<form:input path="search" class="form-control"
							placeholder="Enter wine" />
					</div>
					<div class="col-sm-6"></div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-12" id="filt">Price:</label>
					<div class="col-sm-4">
						<form:input path="min" class="form-control" placeholder="Min" />
					</div>
					<div class="col-sm-4">
						<form:input path="max" class="form-control" placeholder="Max" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-12" id="filt">Maker :</label>
					<div class="col-sm-10">
						<form:select class="form-control chosen-select"
							data-placeholder="Choose a maker" items="${makers}"
							path="makerIds" itemLabel="nameCompany" itemValue="id" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-12" id="filt">Country :</label>
					<div class="col-sm-10">
						<form:select class="form-control chosen-select"
							data-placeholder="Choose a country" items="${countrys}"
							path="countryIds" itemLabel="country" itemValue="id" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-12" id="filt">Type :</label>
					<div class="col-sm-10">
						<form:select class="form-control chosen-select"
							data-placeholder="Choose a type" items="${types}" path="typeIds"
							itemLabel="type" itemValue="id" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-12" id="filt">Capacity
						(L) :</label>
					<div class="col-sm-10">
						<form:select class="form-control chosen-select"
							data-placeholder="Choose a capacity" items="${capacitys}"
							path="capacityIds" itemLabel="capacity" itemValue="id" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-12" id="filt">Year</label>
					<div class="col-sm-10">
						<form:select class="form-control chosen-select"
							data-placeholder="Choose a year" items="${years}" path="yearIds"
							itemLabel="year" itemValue="id" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-12" id="filt">Alcohol
						Content</label>
					<div class="col-sm-10">
						<form:select class="form-control chosen-select"
							data-placeholder="Choose a percentage" items="${ACS}"
							path="alcoholContentIds" itemLabel="alcoholContent"
							itemValue="id" />
					</div>
				</div>
				<button type="submit" class="btn btn-danger">Search</button>
			</form:form>
		</div>
		<div class="col-md-7 col-xs-12 ">
			<div class="row">
				<nav class="navbar navbar-default" id="wines">
					<div class="container-fluid">
						<div class="collapse navbar-collapse" id="myNavbar">
							<ul class="nav navbar-nav">
								<li><h2>
										<b>Wines</b>
									</h2></li>
							</ul>
						</div>
					</div>
				</nav>
			</div>
			<c:forEach items="${page.content}" var="wine">
				<ul></ul>
				<div class="row">
					<div class="col-md-3 col-xs-4">
						<a href="/wine/${wine.id}"><img
							src="/images/wine/${wine.id}.jpg?version=${wine.version}"
							width="100%"></a>
					</div>
					<div class="col-md-7 col-xs-4">
						<h4>
							<b><a id="def" href="/wine/${wine.id}">${wine.name}</a> - <span
								class="price">${wine.price}</span> UAN</b>
						</h4>
						<table class="table table-hover">
							<tr>
								<td>Maker:</td>
								<td><i>${wine.maker.nameCompany}</i></td>

							</tr>
							<tr>
								<td>Country:</td>
								<td><i>${wine.country.country}</i></td>

							</tr>
							<tr>
								<td>Type:</td>
								<td><i>${wine.type.type}</i></td>

							</tr>

						</table>
					</div>
					<div class="col-md-1 col-xs-2">
						<a class="btn btn-danger" href="/wine/${wine.id}">Review</a>
					</div>
				</div>
				<hr />
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
		<div class="row">
			<div class="footer">
				<div class="layout-main">
					<div class="copyright"> 
						<h3 style="text-align: center">© Yura Yarema 2017, Wine Shop.</h3>
						<p style="font-variant: small-caps;">Please add a reference when using our publications, and do
							not copy or reprint publications using content provided by Wine
							Shop.</p>
					</div>
				</div>
			</div>
		</div>
