<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<nav class="navbar-navbar-default navbar-fixed-top">
	<header>
		<nav class="navbar navbar-inverse ">
			<div class="container-fuild">
				<div class="col-md-12 offset-md-0">
					<div class="collapse navbar-collapse">
						<a href="/" class="navbar-brand"
							style="padding-top: 1%; color: white; background-color: #b30000; font-variant: small-caps;">
							<strong>Wine Shop</strong>
						</a>
						<sec:authorize access="isAuthenticated()">
							<ul class="nav navbar-nav navbar-left">
								<li><a href="" style="color: white" class="dropdown-toggle"
									data-toggle="dropdown" role="button" aria-haspopup="true"
									aria-expanded="false"><span
										class="glyphicon glyphicon-glass" aria-hidden="true"></span>Maker
										<span class="caret"></span></a>
									<ul class="dropdown-menu">
										<c:forEach items="${makers}" var="maker">
											<li><a href="/maker/${maker.id}">${maker.nameCompany}</a></li>
										</c:forEach>
									</ul></li>
								<li><a href="" style="color: white" class="dropdown-toggle"
									data-toggle="dropdown" role="button" aria-haspopup="true"
									aria-expanded="false"><span
										class="glyphicon glyphicon-globe" aria-hidden="true"></span>Country
										<span class="caret"></span></a>
									<ul class="dropdown-menu">
										<c:forEach items="${countrys}" var="country">
											<li><a href="/country/${country.id}">${country.country}</a></li>
										</c:forEach>
									</ul></li>
								<li><a href="" style="color: white" class="dropdown-toggle"
									data-toggle="dropdown" role="button" aria-haspopup="true"
									aria-expanded="false"><span
										class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>Type
										<span class="caret"></span></a>
									<ul class="dropdown-menu">
										<c:forEach items="${types}" var="type">
											<li><a href="/type/${type.id}">${type.type}</a></li>
										</c:forEach>
									</ul></li>
							</ul>
						</sec:authorize>

						<ul class="nav navbar-nav navbar-right">
							<sec:authorize access="isAuthenticated()" >
								<sec:authorize access="hasRole('ROLE_USER')">
								  <sec:authentication var="principal" property="principal" />
									<li class="dropdown"><a href="/" style="color: white"
										class="dropdown-toggle" role="button" aria-haspopup="true"
										aria-expanded="false"><span
											class="glyphicon glyphicon-user" aria-hidden="true"></span>
											Hello, ${principal.name}
											 </a></li>
								</sec:authorize>
							</sec:authorize>
							<sec:authorize access="!isAuthenticated()">
								<li class="dropdown"><a href="/registration"
									style="color: white" class="dropdown-toggle" role="button"
									aria-haspopup="true" aria-expanded="false"><span
										class="glyphicon glyphicon-check" aria-hidden="true"></span>
										Sign up</a></li>

								<li class="dropdown"><a href="/login" style="color: white"
									class="dropdown-toggle" role="button" aria-haspopup="true"
									aria-expanded="false"><span
										class="glyphicon glyphicon-log-in" aria-hidden="true"></span>
										Login</a></li>
							</sec:authorize>

							<sec:authorize access="isAuthenticated()">
								<sec:authorize access="hasRole('ROLE_ADMIN')">
									<li><a href="/admin" style="color: white"
										class="dropdown-toggle" role="button" aria-haspopup="true"
										aria-expanded="false"><span
											class="glyphicon glyphicon-user" aria-hidden="true"></span>
											ADMIN</a></li>
								</sec:authorize>
							</sec:authorize>


							<sec:authorize access="isAuthenticated()">
								<sec:authorize access="hasRole('ROLE_USER')">
									<li><a href="/shoppingcart" style="color: white"
										class="dropdown-toggle" role="button" aria-haspopup="true"
										aria-expanded="false"> <span
											class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
											Shopping Cart ${fn:length(order.wines)}
									</a></li>
								</sec:authorize>
								<li><form:form action="/logout" method="POST">
										<button type="submit" class="btn btn-danger navbar-btn">
											Logout</button>
									</form:form></li>
							</sec:authorize>
						</ul>
					</div>
				</div>
			</div>
		</nav>
	</header>
</nav>