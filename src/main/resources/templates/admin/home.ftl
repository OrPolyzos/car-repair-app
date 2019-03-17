<!DOCTYPE html>
<html>
<head>
    <title>eXtreme Performance</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="../styles.css">
</head>
<body>
<#include "../navbar.ftl">
<h1>Welcome to eXtreme Performance, ${user.firstName}!</h1>
<h3><b>Repairs By Repair Date</b></h3>
<div class="table-responsive">
    <table id="resultsTable" class="table table-hover">
        <thead>
        <tr>
            <th>Repair ID</th>
            <th>Scheduled DateTime</th>
            <th>Status</th>
            <th>Type</th>
            <th>Tasks</th>
            <th>Total Cost</th>
            <th>Vehicle ID</th>
        </tr>
        </thead>
        <tbody>
        <#list repairsList as repair>
            <tr>
                <td>${repair.repairID!}</td>
                <td>
                    <!-- TODO FIX the display format -->
                    ${repair.repairDateTime}
                </td>
                <td>${repair.repairStatus}</td>
                <td>${repair.repairType.repairTypeDescription}</td>
                <td>${repair.repairTasks}</td>
                <td>${repair.repairTotalCost}</td>
                <td>${repair.vehicleID}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>