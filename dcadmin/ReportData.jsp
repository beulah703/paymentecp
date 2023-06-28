<%@ page import="java.util.List,spring.orm.model.output.OutputReportData,java.lang.*" %>
<!DOCTYPE html>
<html>
<head>
<title>Patients</title>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.18/dist/sweetalert2.all.min.js"></script>
 <link rel="stylesheet" type="text/css" href="./css/ReportData.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body id="con">
    <%
        List<OutputReportData> data = (List<OutputReportData>) request.getAttribute("ord");
    %>
    <table border="1" id="patientTable">
        <tr>
            <th>Patient ID</th>
            <th>DGBL ID</th>
            <th>Patient Name</th>
            <th>Patient Gender</th>
            <th>Amount</th>
            <th align="center">Action</th>
        </tr>
        <%
            for (int i = 0; i < data.size(); i++) {
        %>
        <tr>
            <td><%=data.get(i).getPatn_id()%></td>
            <td><%=data.get(i).getDgbl_id()%></td>
            <td><%=data.get(i).getPatn_name()%></td>
            <td><%=data.get(i).getPatn_gender()%></td>
            <td><%=data.get(i).getDgbl_amount()%></td>
            <td>
                <form class="uploadForm" enctype="multipart/form-data">
                    <input type="hidden" name="dgblId" value="<%=data.get(i).getDgbl_id()%>">
                    <input type="file" name="file" accept="image/*">
                    <input type="submit" value="Upload">
                </form>
            </td>
        </tr>
        <%
            }
        %>
    </table>

<script src="js/ReportData.js"></script>
 
 
</body>
</html>