<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Study Plan</title>

    <style>
        body {
            margin: 0;
            font-family: Arial;
            background: linear-gradient(to right, #0f172a, #1e293b);
            color: #e2e8f0;
        }

        .container {
            width: 75%;
            margin: 40px auto;
        }

        h2 {
            text-align: center;
            color: #38bdf8;
            margin-bottom: 30px;
        }

        .card {
            background: #1e293b;
            padding: 20px;
            margin-bottom: 15px;
            border-radius: 8px;
            border-left: 5px solid #38bdf8;
        }

        .btn {
            display: block;
            width: 220px;
            margin: 30px auto;
            padding: 12px;
            text-align: center;
            background: #2563eb;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }

        .btn:hover {
            background: #1d4ed8;
        }

        .links {
            text-align: center;
            margin-top: 20px;
        }

        .links a {
            color: #38bdf8;
            text-decoration: none;
            margin: 0 10px;
        }
    </style>
</head>

<body>

<div class="container">

    <h2>Your Study Plan</h2>

    <c:forEach var="item" items="${planList}">
        <div class="card">
            ${item}
        </div>
    </c:forEach>

    <!-- SAVE BUTTON -->
    <form action="savePlan" method="post">
        <c:forEach var="item" items="${planList}">
            <input type="hidden" name="planItem" value="${item}" />
        </c:forEach>

        <button type="submit" class="btn">Save Plan</button>
    </form>

    <div class="links">
        <a href="index.html">Create New Plan</a>
        <a href="viewPlans">View Saved Plans</a>
    </div>

</div>

</body>
</html>