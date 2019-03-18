<!DOCTYPE html>
<html>
<head>
    <title>eXtreme Performance</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/styles.css">
</head>
<body>

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
        <div class="navbar-collapse collapse" id="mainNavBar">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="/logout">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<h1>Welcome to eXtreme Performance, ${user.firstName}!</h1>
<h3><b>My Vehicles</b></h3>
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
        <#list user.vehicles as vehicle>
            <tr>
                <td>${vehicle.id}</td>
                <td>${vehicle.brand}</td>
                <td>${vehicle.model}</td>
                <td>${vehicle.year}</td>
                <td>${vehicle.color}</td>
                <td>${vehicle.fuelType}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<hr/>
<h3><b>My Repairs</b></h3>
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
        <#list user.vehicles as vehicle>
            <#list vehicle.repairs as repair>
                <tr>
                    <td>${repair.id}</td>
                    <td>
                        <!--TODO Fix the display format -->
                        ${repair.repairDateTime}
                    </td>
                    <td>${repair.repairStatus}</td>
                    <td>${repair.repairType.repairTypeDescription}</td>
                    <td>${repair.repairTasks}</td>
                    <td>${repair.vehicleID}</td>
                </tr>
            </#list>
        </#list>
        </tbody>
    </table>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>