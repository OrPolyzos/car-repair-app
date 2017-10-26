<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/styles.css">
    <link rel="stylesheet" href="/p5styles.css">
    <!---- Trying to make some cool stuff with p5.js ----->
    <script async src=https://CDN.JSDelivr.net/g/p5.js(p5.min.js+addons/p5.dom.js+addons/p5.sound.js)></script>
    <script defer src=sketch.js></script>
    <script defer src=particle.js></script>
</head>
<body>

    <!-- NavBar -->
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Logo -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#mainNavBar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="#" class="navbar-brand">eXtreme Performance</a>
            </div>
            <!-- Menu Items -->
            <div class="collapse navbar-collapse" id="mainNavBar">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="/logout">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="p5container">
        <img src="../Images/eXtremeRed.png">
    </div>

    <h1 class="errorRed">${errorMessage!""}</h1>
        <#if user??>
            <h1>Welcome to eXtreme Performance dear ${user.firstName!"owner"}!</h1>
            <#if user.userVehicles??>
                <h3><u>Retrieved Vehicles</u></h3>
                <div class="table-responsive">
                    <table id="resultsTable" class="table">
                        <thead>
                            <tr>
                                <th>Plate Number</th>
                                <th>Brand</th>
                                <th>Model</th>
                                <th>Year</th>
                                <th>Color</th>
                                <th>Fuel</th>
                            </tr>
                        </thead>
                        <tbody>
                            <#list user.userVehicles as vehicle>
                                <span>
                                    <tr>
                                        <td>${vehicle.vehicleID!"Could not retrieve value!"}</td>
                                        <td>${vehicle.brand!"Could not retrieve value!"}</td>
                                        <td>${vehicle.model!"Could not retrieve value!"}</td>
                                        <td>${vehicle.year!"Could not retrieve value!"}</td>
                                        <td>${vehicle.color!"Could not retrieve value!"}</td>
                                        <td>${vehicle.fuelType!"Could not retrieve value!"}</td>
                                    </tr>
                                </span>
                            </#list>
                        </tbody>
                    </table>
                </div>

            <hr></hr>

                <#if repairsList??>
                    <h3><u>Retrieved Repairs</u></h3>
                    <div class="table-responsive">
                        <table id="resultsTable" class="table">
                            <thead>
                                <tr>
                                    <th>Repair ID</th>
                                    <th>Scheduled DateTime</th>
                                    <th>Status</th>
                                    <th>Type</th>
                                    <th>Tasks</th>
                                    <th>Vehicle ID</th>
                                </tr>
                            </thead>
                            <tbody>
                                <#list repairsList as repair>
                                    <span>
                                        <tr>
                                            <td>${repair.repairID!"Could not retrieve value!"}</td>
                                            <td>
                                                <input type="datetime-local" name="repairDateTime" id="repairDateTime" value="${repair.repairDateTime!""}" disabled/>
                                            </td>
                                            <td>${repair.repairStatus!"Could not retrieve value!"}</td>
                                            <td>${repair.repairType.repairTypeDescription!"Could not retrieve value!"}</td>
                                            <td>${repair.repairTasks!"Could not retrieve value!"}</td>
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
            <#else>
                <h3><u>No Vehicles Found</u></h3>
        </#if>
    </#if>

    <#include "footer.ftl">

</body>
</html>