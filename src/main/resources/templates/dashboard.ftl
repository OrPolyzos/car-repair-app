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
            <!---- Trying to make some cool stuff with p5.js
            <script async src=https://CDN.JSDelivr.net/g/p5.js(p5.min.js+addons/p5.dom.js+addons/p5.sound.js)></script>
            <script defer src=/../sketch.js></script>
            <script defer src=/../particle.js></script> ---->
    </head>
    <body align="center">
        <h1 color="red">${errorMessage!""}</h1>
        <#if user??>
            <h1>Welcome to AutoRepair dear ${user.firstName!"owner"}!</h1>
            <#if user.userVehicles??>
                <h3 align="center"><u>Your Vehicles</u></h3>
                <table align="center">
                    <tr>
                        <th align="center">Plate Number</th>
                        <th align="center">Brand</th>
                        <th align="center">Model</th>
                        <th align="center">Year</th>
                        <th align="center">Color</th>
                        <th align="center">Fuel</th>
                    </tr>
                    <#list user.userVehicles as vehicle>
                        <span>
                            <tr>
                                <td align="center">${vehicle.vehicleID}</td>
                                <td align="center">${vehicle.brand}</td>
                                <td align="center">${vehicle.model}</td>
                                <td align="center">${vehicle.year}</td>
                                <td align="center">${vehicle.color}</td>
                                <td align="center">${vehicle.fuelType}</td>
                            </tr>
                        </span>
                    </#list>
                </table>
                    <#if repairsList??>
                        <h3 class="text-center"><u>Your Repairs</u></h3>
                        <div class="table-responsive">
                            <table id="resultsTable" class="table" class="table-hover">
                                <thead>
                                    <tr>
                                        <th class="text-center">Repair ID</th>
                                        <th class="text-center">Scheduled DateTime</th>
                                        <th class="text-center">Status</th>
                                        <th class="text-center">Type</th>
                                        <th class="text-center">Tasks</th>
                                        <th class="text-center">Vehicle ID</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <#list repairsList as repair>
                                        <span>
                                <tr>
                                    <td class="text-center">${repair.repairID}</td>
                                    <!---td class="text-center">${repair.repairDateTime}</td--->
                                    <td class="text-center">
                                    <input type="datetime-local" name="repairDateTime" id="repairDateTime" value="${repair.repairDateTime!""}" disabled/>
                                    </td>
                                    <td class="text-center">${repair.repairStatus}</td>
                                    <td class="text-center">${repair.repairType.repairTypeDescription}</td>
                                    <td class="text-center">${repair.repairTasks}</td>
                                    <td class="text-center">${repair.vehicle.vehicleID}</td>
                                </tr>
                                    </span>
                                    </#list>
                            </tbody>
                        </table>
                    </div>
                <#else>
                    <h3 align="center"><u>No Repairs Found!</u></h3>
                </#if>
            <#else>
                <h3 align="center"><u>No Vehicles Found!</u></h3>
            </#if>
        </#if>
    </body
</html>