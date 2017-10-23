<!DOCTYPE html>
<html>
    <head>
        <title>Dashboard</title>
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