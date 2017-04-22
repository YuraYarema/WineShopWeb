<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<style>
.price {
	color: red;
}

h3 {
	font-variant: small-caps;
}

#lala {
	margin-top: 15%;
}

.center {
	font-variant: small-caps;
	text-align: center;
	border: 1px solid red;
	padding: 10px;
	border-radius: 5px;
	color:#b30000;
	
}
#dep{
	color:#b30000;
}
</style>
<h2
	style="text-align: center; font-variant: small-caps; color: #b30000;">Specifications:</h2>
<div class="row" style="margin-top: 5%;">
	<div class="col-md-1 col-xs-12 "></div>
	<div class="col-md-10 col-xs-12 ">
		<c:forEach items="${qwer}" var="wine">
			<div class="row">
				<div class="col-md-3 col-xs-4">
					<img src="/images/wine/${wine.id}.jpg?version=${wine.version}"
						width="100%">
				</div>
				<div class="col-md-8 col-xs-4">
					<h3>
						<b>${wine.name} - <span class="price">${wine.price}</span> UAN
						</b>
					</h3>
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
						<tr>
							<td>Capacity:</td>
							<td><i>${wine.capacity.capacity}</i></td>

						</tr>
						<tr>
							<td>Alcohol Content:</td>
							<td><i>${wine.alcoholContent.alcoholContent}</i></td>

						</tr>
						<tr>
							<td>Year:</td>
							<td><i>${wine.year.year}</i></td>
						</tr>
					</table>
				</div>
				<sec:authorize access="isAuthenticated()">
					<sec:authorize access="hasRole('ROLE_USER')">
						<div class="col-md-1 col-xs-2">
							<form:form style="display:inline-block;margin-top:8px;"
								action="/addtocart/${wine.id}" method="POST" id="addToCart">
								<button type="submit" class="btn btn-danger">Add to
									cart</button>
							</form:form>
						</div>
					</sec:authorize>
				</sec:authorize>
			</div>
		</c:forEach>
	</div>
</div>
<sec:authorize access="!isAuthenticated()">
	<h3 class="center">To buy wine please <a href="/login" id="dep">login</a> or <a href="/registration" id="dep">sign up</a></h3>
</sec:authorize>

<script type="text/javascript">
	document.getElementById('addToCart').addEventListener('click', function() {
		alert("Selected wine was added to your shopping cart.")
	});
</script>