<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
   
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-image: url('https://images.shiksha.com/mediadata/images/articles/1567401387phpphiMNK.jpeg');
             background-repeat: no-repeat;
            background-size: cover;
            background-position: center center;
            height: 100vh;
        }

        .container {
            max-width: 500px;
            margin: auto;
            padding: 20px;
            background-color: white;
            border-radius: 5px;
            margin-top: 100px;
        }
        h1 {
            font-family: Arial, sans-serif;
            font-size: 32px;
            font-weight: bold;
            text-align: center;
            color: #333;
        }
    </style>
</head>
<body>
    <div class="container">
        <form action="savespec" method="post">
         <center>
  
    <h1>Specializations</h1></center>
            <div class="form-group">
                <label for="spec_name" class="form-label">Specialization Name</label>
                <input type="text" name="sname" id="sname" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="spec_desc" class="form-label">Description</label>
                <input type="text" name="sdesc" id="sdesc" class="form-control" required>
            </div>

           

            <button type="submit" class="btn btn-primary btn-block">Add</button>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
