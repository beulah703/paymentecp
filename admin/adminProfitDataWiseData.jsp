<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="spring.orm.model.output.*,java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hospital Doctor Profit Information</title>
  <link rel="stylesheet" type="text/css" href="./css/adminProfitDataWiseData.css">
</head>
<body>

<%

List<OutputDoctorProfit> d1 = (List<OutputDoctorProfit>)request.getAttribute("tndata");
List<OutputSpecializationProfit> d2 = (List<OutputSpecializationProfit>)request.getAttribute("tmdata");

%>
    

    <div class="container">
        <table id="t1">
            
            <tr>
                <th>Doctor Name</th>
                <th>Profit</th>
            </tr>
            <%for(int i=0;i<d1.size();i++){ %>
            <tr>
             <td><%=d1.get(i).getDoctorName() %></td>
            <td><%=d1.get(i).getTotalProfit() %></td>
            </tr>
            <%} %>
       </table>

       

        <table>
           
            <tr>
                <th>Specialization</th>
                <th>Profit</th>
            </tr>
            <%for(int i=0;i<d2.size();i++){ %>
            <tr>
            <td><%=d2.get(i).getSpecializationTitle()%></td>
            <td><%=d2.get(i).getTotalProfit()%></td>
            </tr>
            <%} %>
       </table>
    </div>
</body>
</html>
