<!DOCTYPE html>
<html>
<head>
    <title>Admin Home</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!---- Our Custom CSS ----->
    <link rel="stylesheet" href="../styles.css">
</head>
<body>

    <#include "navbar.ftl">

    <div class="p5container">
        <img src="/../Images/eXtremeRed.png">
    </div>

        <h1 class="errorRed">${errorMessage!""}</h1>
        <#if user??>
            <h1>Welcome to eXtreme Performance dear ${user.firstName!"admin"}!</h1>
                <#if repairsList??>
                    <h3><u>Repairs By Repair Date</u></h3>
                    <div class="table-responsive">
                        <table id="resultsTable" class="table">
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
                            <span>
                                <tr>
                                    <td>${repair.repairID!}</td>
                                    <td>
                                        <input type="datetime-local" name="repairDateTime" id="repairDateTime" value="${repair.repairDateTime!""}" disabled/>
                                    </td>
                                    <td>${repair.repairStatus!"Could not retrieve value!"}</td>
                                    <td>${repair.repairType.repairTypeDescription!"Could not retrieve value!"}</td>
                                    <td>${repair.repairTasks!"Could not retrieve value!"}</td>
                                    <td>${repair.repairTotalCost!"Could not retrieve value!"}</td>
                                    <td>${repair.vehicleID!"Could not retrieve value!"}</td>
                                </tr>
                            </span>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                <#else>
                    <h3><u>No Repairs found!</u></h3>
                </#if>
            </#if>
    <#include "footer.ftl">
</body>
</html>