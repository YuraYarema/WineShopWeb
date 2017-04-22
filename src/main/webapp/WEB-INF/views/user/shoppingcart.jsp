<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<style>
body{
	padding-bottom:70px;
}
.price {
	color: red;
}
.pricee {
	font-variant: small-caps;
	background-color: #b30000;
	color:white;
	text-align: center;
	border: 1px solid red;
    padding: 10px;
     border-radius: 5px;
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

<div class="col-md-2"></div>
<div class="col-md-8">
<c:if test="${not empty qwer}">
<h2
	style="text-align: center; font-variant: small-caps; color: #b30000;">Shopping
	Cart:</h2>
</c:if>
	<div class="row">
		<ul>
			<c:forEach items="${qwer}" var="wine">
				<div class="row">
					<div class="col-md-2 col-xs-4">
						<img src="/images/wine/${wine.id}.jpg?version=${wine.version}"
							width="100%">
					</div>
					<div class="col-md-9 col-xs-4">
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
						</table>
					</div>
					<div class="col-md-1 col-xs-4">
						<form:form style="display:inline-block;margin-top:8px;"
							action="/deletefromcart/${wine.id}" method="POST">
							<button type="submit" class="btn btn-danger">Remove <span class="glyphicon glyphicon-remove"
					aria-hidden="true"></span></button>
						</form:form>
					</div>
				</div>
				<hr/>
			</c:forEach>
		</ul>
</div>
<div class="col-md-2"></div>
<c:if test="${empty qwer}">
	<h2 style="text-align: center; margin-top: 15%; font-variant: small-caps;
	 color: #b30000;">Shopping cart is empty</h2>
	 <h4 style="text-align: center;"><a href="/">Back to shopping</a></h4>
</c:if>
<c:if test="${not empty qwer}">
			<div class="col-md-12">
				<div class="col-md-8"></div>
				<div class="col-md-4">
					<c:set var="total" value="${0}" /> 
					<c:forEach items="${qwer}" var="wine"> 
						<c:set var="total" value="${total+wine.price}" /> 
					</c:forEach> 
					<h5 class="pricee"><strong>Total price: ${total} UAN</strong></h5>
				</div>
			</div>
			<div class="col-md-12">
				<div class="col-md-8"></div>
				<div class="col-md-4">
					<a href="/makeorder"><button type="button" class="btn btn-primary btn-lg btn-block">Confirm order <span class="glyphicon glyphicon-ok"
					aria-hidden="true"></span>	</button></a>
				</div>
			</div>
</c:if>