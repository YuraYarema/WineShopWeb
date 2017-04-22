<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty winess}">
<h2> <b>Type: ${type.type} </b></h2>

<table class="table table-hover">
		<tr>
			<th>Name</th>
			<th>Price</th>
			<th>More</th>
		</tr>
		<c:forEach items="${winess}" var="wine">
			<tr>
				<td>${wine.name}</td>
				<td>${wine.price} UAN</td>
				<td><a class="btn btn-danger" href="/wine/${wine.id}">Specifications</a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>
<c:if test="${empty winess}">
	<h2 style="text-align: center; margin-top: 15%; font-variant: small-caps;
	 color: #b30000;">Wines not found</h2>
	 <h4 style="text-align: center;"><a href="/">Back to shopping</a></h4>
</c:if>