<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Plans</title>

    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI', sans-serif;
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
            border-radius: 12px;
            box-shadow: 0 6px 16px rgba(0,0,0,0.5);
        }

        .title {
            font-size: 18px;
            color: #38bdf8;
            margin-bottom: 10px;
        }

        .progress-bar {
            width: 100%;
            background: #334155;
            border-radius: 10px;
            overflow: hidden;
            height: 20px;
            margin: 10px 0;
        }

        .progress-fill {
            height: 100%;
            background: #22c55e;
            text-align: center;
            color: white;
            font-size: 12px;
        }

        .stats {
            font-size: 14px;
            margin-top: 5px;
            color: #cbd5f5;
        }

        .btn-open {
            display: inline-block;
            margin-top: 10px;
            padding: 8px 14px;
            background: #2563eb;
            color: white;
            border-radius: 6px;
            text-decoration: none;
        }

        .btn-open:hover {
            background: #1d4ed8;
        }

        .empty {
            text-align: center;
            margin-top: 50px;
            font-size: 18px;
            color: #94a3b8;
        }

        .btn-create {
            display: block;
            width: 240px;
            margin: 40px auto;
            padding: 12px;
            text-align: center;
            background: #2563eb;
            color: white;
            text-decoration: none;
            border-radius: 8px;
        }

        .btn-create:hover {
            background: #1d4ed8;
        }
    </style>
</head>

<body>

<div class="container">

    <h2>📊 Your Study Plans</h2>

    <!-- No plans -->
    <c:if test="${empty plans}">
        <div class="empty">No plans found.</div>
    </c:if>

    <!-- Plans -->
    <c:forEach var="p" items="${plans}">
        <div class="plan">

            <div class="title">
                ${p.name}
            </div>

            <!-- Progress bar -->
            <div class="progress-bar">
                <div class="progress-fill" style="width:${p.percent}%;">
                    ${p.percent}%
                </div>
            </div>

            <div class="stats">
                Completed: ${p.completed} / ${p.total}
            </div>

            <!-- Open plan -->
            <a href="viewTasks?planId=${p.id}" class="btn-open">
                Open Plan
            </a>

        </div>
    </c:forEach>

    <a href="index.html" class="btn-create">+ Create New Plan</a>

</div>

</body>
</html>