<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Saved Study Plans</title>

    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #0f172a, #1e293b);
            color: #e2e8f0;
        }

        .container {
            width: 80%;
            margin: 40px auto;
        }

        h2 {
            text-align: center;
            margin-bottom: 30px;
            color: #38bdf8;
        }

        .plan {
            background: #1e293b;
            padding: 20px;
            margin-bottom: 25px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.4);
        }

        .title {
            font-size: 18px;
            color: #38bdf8;
            margin-bottom: 15px;
            border-bottom: 1px solid #334155;
            padding-bottom: 8px;
        }

        .item {
            padding: 8px 12px;
            margin: 6px 0;
            background: #0f172a;
            border-radius: 6px;
            border-left: 4px solid #38bdf8;
        }

        .btn {
            display: block;
            width: 220px;
            margin: 30px auto;
            padding: 12px;
            text-align: center;
            background: #2563eb;
            color: white;
            text-decoration: none;
            border-radius: 6px;
            font-weight: 500;
        }

        .btn:hover {
            background: #1d4ed8;
        }

        .empty {
            text-align: center;
            margin-top: 50px;
            font-size: 18px;
            color: #94a3b8;
        }
    </style>
</head>

<body>

<div class="container">

    <h2>Your Saved Study Plans</h2>

    <!-- If no plans -->
    <c:if test="${empty plans}">
        <div class="empty">No saved plans found.</div>
    </c:if>

    <!-- Plans display -->
    <c:forEach var="entry" items="${plans}">
        <div class="plan">

            <div class="title">
                Plan ID: ${entry.key}
            </div>

            <c:forEach var="item" items="${entry.value}">
                <div class="item">
                    ${item}
                </div>
            </c:forEach>

        </div>
    </c:forEach>

    <a href="index.html" class="btn">Create New Plan</a>

</div>

</body>
</html>