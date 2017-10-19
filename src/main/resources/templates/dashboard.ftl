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
                <h3 align="center"><u>Retrieved Vehicles</u></h3>
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
            <#else>
                <h3 align="center"><u>No Vehicles Found!</u></h3>
            </#if>
        </#if>
    </body
</html>