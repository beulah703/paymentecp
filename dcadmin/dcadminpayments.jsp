<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, spring.orm.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<jsp:include page="scripts.jsp" />
<body>
	<jsp:include page="dcreportspage.jsp" />

<div class="col-md-9">
    <h3 align="center">Payments View</h3>
    <div class="row mt-4">
        <div class="col-md-6 offset-md-3">
            <div class="text-center">
                <h5>Patient Type Wise</h5>
                <select class="form-control" id="typeFilter" onchange="applyFilters()">
                    <option value="main">Patient Type</option>
                   <option value="out">OUT</option>
                   <option value ="appn">APPN</option>
                    <!-- Add more test options as needed -->
                </select>
            </div>
        </div>
    </div>
   
    <div class="row mt-4">
        <div class="col-md-6 offset-md-3">
            <h5>Between Dates</h5>
            <div class="row">
                <div class="col-md-6">
                    <input type="date" class="form-control" id="startDateFilter" onchange="applyFilters()">
                </div>
                <div class="col-md-6">
                    <input type="date" class="form-control" id="endDateFilter" onchange="applyFilters()">
                </div>
            </div>
        </div>
    </div>
    <div class="row mt-4">
        <div class="col-md-12">
            <table class="table table-bordered" id="patientTable" style="display: none;">
                <thead>
                    <tr>
                        <th>Patient Id</th>
                        <th>Payment Type</th>
                        <th>Payment Amount</th>
                    </tr>
                </thead>
                <tbody id="patientTableBody">
                    <!-- Table rows will be populated dynamically -->
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="js/dcadminpayments.js">
</script>
 
</body>
</html>