<!DOCTYPE html>
<html>
<head>
    <title>eXtreme Performance</title>
    <#include "common/head-section.ftl"/>
</head>
<body>
<#include "common/navbar.ftl">
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
        <#list repairList as repair>
            <tr>
                <td>${repair.id!}</td>
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
<#include "common/scripts-section.ftl"/>
</body>
</html>