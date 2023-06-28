<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Success</title>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  
    <script>
    $(document).ready(function() {
      // Delay in milliseconds
      var delay = 5000;

      setTimeout(function() {
        // Relative URL to redirect to
        var url = "./getpatient";

        // Redirect
        window.location.href = url;
      }, delay);
    });
  </script>
</head>
<body>
  <h1>Updated Successfully</h1>
  <p>You will be redirected back in a few seconds...</p>
</body>
</html>
