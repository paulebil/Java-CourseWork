<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/jstl/core" prefix="c" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Products</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container mt-5">
    <h1>Product List</h1>

    <!-- Table to display products -->
    <table class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Category</th>
                <th>Price</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.category}</td>
                    <td>${product.price}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Form to add a new product -->
    <h2>Add New Product</h2>
    <form id="addProductForm">
        <div class="mb-3">
            <label for="name" class="form-label">Product Name</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="mb-3">
            <label for="category" class="form-label">Category</label>
            <input type="text" class="form-control" id="category" name="category" required>
        </div>
        <div class="mb-3">
            <label for="price" class="form-label">Price</label>
            <input type="number" class="form-control" id="price" name="price" required>
        </div>
        <button type="submit" class="btn btn-primary">Add Product</button>
    </form>
</div>

<script>
    $(document).ready(function() {
        $('#addProductForm').submit(function(event) {
            event.preventDefault(); // Prevent the default form submission

            // Get form data
            const formData = {
                name: $('#name').val(),
                category: $('#category').val(),
                price: $('#price').val()
            };

            // Make an AJAX POST request to add a new product
            $.ajax({
                type: 'POST',
                url: '/product/add',
                contentType: 'application/json',
                data: JSON.stringify(formData),
                success: function(response) {
                    alert('Product added successfully!');
                    location.reload(); // Reload the page to see the new product
                },
                error: function() {
                    alert('Error adding product. Please try again.');
                }
            });
        });
    });
</script>

</body>
</html>
