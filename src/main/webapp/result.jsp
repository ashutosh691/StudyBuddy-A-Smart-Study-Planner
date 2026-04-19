<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.examprepplanner.model.Task" %>
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
            padding: 15px;
            margin-bottom: 10px;
            border-radius: 8px;
            border-left: 5px solid #38bdf8;
        }

        .task {
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        .left {
            display: flex;
            align-items: center;
        }

        .left input {
            margin-right: 10px;
        }

        .status {
            font-size: 12px;
            padding: 4px 8px;
            border-radius: 5px;
        }

        .completed {
            background: #22c55e;
            color: white;
        }

        .pending {
            background: #f59e0b;
            color: white;
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

    <!-- FORM START -->
    <form action="updateTask" method="post">

        <c:forEach var="t" items="${tasks}">
            <div class="card">
                <div class="task">

                    <div class="left">
                        <!-- ✅ FIXED: use ID -->
                        <input type="checkbox" name="task"
                               value="${t.id}"
                               <c:if test="${t.status == 'COMPLETED'}">checked disabled</c:if>>

                        <div>
                            <strong>${t.date}</strong> :
                            ${t.subject} → ${t.topic}
                        </div>
                    </div>

                    <!-- ✅ Status badge -->
                    <div class="status 
                        <c:choose>
                            <c:when test="${t.status == 'COMPLETED'}">completed</c:when>
                            <c:otherwise>pending</c:otherwise>
                        </c:choose>">
                        ${t.status}
                    </div>

                </div>
            </div>
        </c:forEach>

        <button type="submit" class="btn">Mark Completed</button>

    </form>
    <!-- FORM END -->

    <div class="links">
        <a href="index.html">Create New Plan</a>
        <a href="viewPlans">View Saved Plans</a>
    </div>

</div>

</body>
</html>