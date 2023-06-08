<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!-- c:out ; c:forEach etc. --> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) --> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Category Page</title>
</head>
<body>
	<div class="container border mt-5 text-center  col-7">
		<h1 class="mt-3 mb-2">${ category.name }</h1>
		<div class="container mb-5">
			<br /> <a href="/products/new">New Product</a>
			<div class="d-flex justify-content-between">
				<div class="col-5">
					<table class="table table-hover border">
						<thead>
							<tr>
								<th>Products:</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${ category.products }" var="product">
								<tr>
									<td>${ product.name }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="col-5 border p-4">
					<form action="/categories/add/products" method="post">
						<div class="form-group">
							<input name="product_id" type="hidden" value="${ category.id }">
							<div class="input-group">
								<div class="input-group-prepend">
									<label class="input-group-text">Products:</label>
								</div>
								<select name="category_id" class="form-control">
									<c:forEach items="${ products }" var="product">
										<option value="${ product.id }">${ product.name }</option>
									</c:forEach>
								</select>
							</div>
						</div><br>
						<input type="submit" value="Add" />
					</form>
				</div>			
			</div>
		</div>
	</div>

</body>
</html>