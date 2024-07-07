<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<style>
body{
background-image:url("https://thumbs.dreamstime.com/b/corporate-data-management-system-document-employee-privacy-confidentiality-software-security-searching-managing-files-256158517.jpg")
}
.transparent-container {
    opacity: 0.8; /* Adjust the opacity level as needed (0 to 1) */
    border: 1px solid #ccc; /* Optional: add a border if you want */
    padding: 20px; /* Optional: adjust padding for better layout */
    border-radius: 10px; /* Optional: rounded corners */
    background-color: transparent; /* Ensure a background color for contrast */
}


.transparent-card-background{
      background-color: rgba(255, 255, 255, 0.3);/* Adjust the transparency level as needed */
    border: none; /* Optional: remove border */
}

.transparent-card-body {
    background-color: rgba(255, 255, 255, 0.1); /* Adjust the transparency level as needed */
}

</style>
<title>Product Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
   
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="https://www.xadmin.net" class="navbar-brand"> Product Management Application </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Products</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5 transparent-container">
    <div class="card transparent-card">
        <div class="card-body transparent-card-body">
				<c:if test="${product != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${product == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${product != null}">
            			Update Product
            		</c:if>
						<c:if test="${product == null}">
            			Add New Product
            		</c:if>
					</h2>
				</caption>

				<c:if test="${product != null}">
					<input type="hidden" name="id" value="<c:out value='${product.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Product Name</label> <input type="text"
						value="<c:out value='${product.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Product Country</label> <input type="text"
						value="<c:out value='${product.country}' />" class="form-control"
						name="country">
				</fieldset>
				<fieldset class="form-group">
					<label>Product Price</label> <input type="text"
						value="<c:out value='${product.price}' />" class="form-control"
						name="price">
				</fieldset>
				

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>