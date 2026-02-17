<%-- <%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Study Plan Result</title>
</head>
<body>

<h2>Exam Preparation Plan</h2>

<p><b>Name:</b> ${name}</p>
<p><b>Subject:</b> ${subject}</p>
<p><b>Days Left:</b> ${daysLeft}</p>

<h3>Your Personalized Study Plan</h3>

<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>Study Schedule</th>
    </tr>

    <c:forEach var="item" items="${planList}">
        <tr>
            <td>${item}</td>
        </tr>
    </c:forEach>

</table>

</body>
</html> --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Study Plan Result</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #2c3e50, #4ca1af);
            margin: 0;
            padding: 0;
        }

        .container {
            width: 70%;
            margin: 50px auto;
            background: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 4px 15px rgba(0,0,0,0.3);
        }

        h2 {
            text-align: center;
            color: #2c3e50;
        }

        .info {
            font-size: 18px;
            margin-bottom: 10px;
        }

        .info span {
            font-weight: bold;
            color: #34495e;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th {
            background-color: #34495e;
            color: white;
            padding: 12px;
            text-align: center;
        }

        td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .btn {
            display: block;
            width: 200px;
            margin: 25px auto 0;
            padding: 10px;
            text-align: center;
            background-color: #2c3e50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .btn:hover {
            background-color: #1a252f;
        }
    </style>
</head>

<body>

<div class="container">

    <h2>Exam Preparation Plan</h2>

    <div class="info"><span>Name:</span> ${name}</div>
    <div class="info"><span>Subject:</span> ${subject}</div>
    <div class="info"><span>Days Left:</span> ${daysLeft}</div>

    <h3>Your Personalized Study Plan</h3>

    <table>
        <tr>
            <th>Study Schedule</th>
        </tr>

        <c:forEach var="item" items="${planList}">
            <tr>
                <td>${item}</td>
            </tr>
        </c:forEach>

    </table>

    <a href="index.html" class="btn">Create Another Plan</a>

</div>

</body>
</html>	