<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="spring.orm.model.output.*,java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hospital Test Information</title>
    <link rel="stylesheet" type="text/css" href="./css/TestProfitData.css">
</head>
<body>

<%

List<OutputTestNameProfit> d1 = (List<OutputTestNameProfit>)request.getAttribute("tndata");
List<OutputTestMethodProfit> d2 = (List<OutputTestMethodProfit>)request.getAttribute("tmdata");
List<OutputTestCategoryProfit> d3 = (List<OutputTestCategoryProfit>)request.getAttribute("tcdata");
%>
    

    <div class="container">
        <table id="t1">
            
            <tr>
                <th>Test Name</th>
                <th>Profit</th>
            </tr>
            <%for(int i=0;i<d1.size();i++){ %>
            <tr>
             <td><%=d1.get(i).getTestname()%></td>
            <td><%=d1.get(i).getProfits()%></td>
            </tr>
            <%} %>
       </table>

        <table>
            
            <tr>
                <th>Test Category</th>
                <th>Profit</th>
            </tr>
            <%for(int i=0;i<d3.size();i++){ %>
            <tr>
            <td><%=d3.get(i).getTest_category()%></td>
            <td><%=d3.get(i).getProfits()%></td>
            </tr>
            <%} %>
            
        </table>

        <table>
           
            <tr>
                <th>Test Method</th>
                <th>Profit</th>
            </tr>
            <%for(int i=0;i<d2.size();i++){ %>
            <tr>
            <td><%=d2.get(i).getTestmethod()%></td>
            <td><%=d2.get(i).getProfit()%></td>
            </tr>
            <%} %>
       </table>
    </div>
</body>
</html>
