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
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(to right, #0f172a, #1e293b);
            color: #e2e8f0;
        }

        .container {
            width: 85%;
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
            transition: 0.3s;
        }

        .plan:hover {
            transform: translateY(-3px);
        }

        .title {
            font-size: 18px;
            color: #38bdf8;
            margin-bottom: 15px;
            border-bottom: 1px solid #334155;
            padding-bottom: 8px;
        }

        .day {
            margin: 12px 0;
            padding: 12px;
            background: #0b1220;
            border-left: 4px solid #38bdf8;
            border-radius: 8px;
        }

        .day-title {
            font-weight: 600;
            margin-bottom: 8px;
            color: #7dd3fc;
        }

        .chips {
            display: flex;
            flex-wrap: wrap;
            gap: 8px;
        }

        .chip {
            background: #1e40af;
            padding: 6px 10px;
            border-radius: 20px;
            font-size: 13px;
            color: #e0f2fe;
        }

        .btn {
            display: block;
            width: 240px;
            margin: 40px auto;
            padding: 12px;
            text-align: center;
            background: #2563eb;
            color: white;
            text-decoration: none;
            border-radius: 8px;
            font-weight: 500;
            transition: 0.3s;
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

    <h2>📘 Your Saved Study Plans</h2>

    <!-- No plans -->
    <c:if test="${empty plans}">
        <div class="empty">No saved plans found.</div>
    </c:if>

    <!-- Plans -->
    <c:forEach var="entry" items="${plans}">
        <div class="plan">

            <div class="title">
                Plan ID: ${entry.key}
            </div>

            <!-- Day-wise display -->
            <c:forEach var="item" items="${entry.value}" varStatus="status">

                <div class="day">
                    <div class="day-title">
                        Day ${status.index + 1}
                    </div>

                    <!-- Split by | -->
                    <div class="chips">
                        <c:forTokens items="${item}" delims="|" var="task">
                            <div class="chip">
                                ${task}
                            </div>
                        </c:forTokens>
                    </div>

                </div>

            </c:forEach>

        </div>
    </c:forEach>

    <a href="index.html" class="btn">+ Create New Plan</a>

</div>

</body>
</html>