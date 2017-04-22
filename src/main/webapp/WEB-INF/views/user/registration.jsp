<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
h1 {
	font-weight: 800;
	color: #b30000;
	font-variant: small-caps;
}

.a {
	color: red;
}
#word {
	color: #b30000;
	font-variant: small-caps;
}
</style>
<h1>WineShop Registration</h1>
<p>
	<i>Register now while wines are available!</i>
</p>
<hr />
<div class="row">
	<div class="col-md-10 col-xs-10">
		<div class="col-sm-12 col-xs-12">
			<form:form class="form-horizontal" action="/registration"
				method="POST" modelAttribute="user">
				<div class="form-group">
					<label for="name" class="col-sm-2 control-label" id="word">Name<a
						class="a">*</a></label>
					<div class="col-sm-10">
						<form:input class="form-control" path="name" id="name" />
					</div>
				</div>
				<div class="form-group">
					<label for="surname" class="col-sm-2 control-label" id="word">Surname<a
						class="a">*</a></label>
					<div class="col-sm-10">
						<form:input class="form-control" path="surname" id="surname" />
					</div>
				</div>
				<div class="form-group"></div>
				<div class="form-group">
					<label for="email" class="col-sm-2 control-label" id="word">Email<a
						class="a">*</a></label>
					<div class="col-sm-10">
						<form:input class="form-control" path="email" id="email" />
					</div>
					<label class="col-sm-10 col-sm-offset-2 control-label" for="email"
						style="color: red; text-align: left;"><form:errors
							path="email" /></label>
				</div>
				<div class="form-group">
					<label for="email" class="col-sm-offset-2 col-sm-10"><form:errors
							path="password" /></label>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-2 control-label" id="word">Password<a
						class="a">*</a></label>
					<div class="col-sm-10">
						<form:password class="form-control" path="password" id="password" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-danger">Register</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>