<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, spring.orm.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<jsp:include page="scripts.jsp" />
</head>
<body>
<jsp:include page="nav.jsp" />


    <div class="row mt-4">
        <div class="col-md-6 offset-md-3">
            <div class="text-center">
                <h5>Appointment Status</h5>
                <select class="form-control" id="statusFilter" onchange="applyFilters()">
                    <option value="main">Status</option>
                   <option value="com">Completed</option>
                   <option value ="sus">Suspended</option>
                    <option value ="resd">Rescheduled</option>
                    <!-- Add more test options as needed -->
                </select>
            </div>
        </div>
    </div>
    <div class="row mt-4">
        <div class="col-md-12">
            <table class="table table-bordered" id="patientTable" style="display: none;">
                <thead>
                    <tr>
                        <th>Patient Id</th>
                        <th>Payment Mode</th>
                        <th>Payment Reference</th>
                        <th>Payment Amount</th>
                        <th>Payment Status</th>
                    </tr>
                </thead>
                <tbody id="patientTableBody">
                    <!-- Table rows will be populated dynamically -->
                </tbody>
            </table>
        </div>
    </div>


       
<script type="text/javascript" src="./js/appointmentspage.js"></script>
 
</body>
</html>