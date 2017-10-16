<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>Vehicles</title>
</head>
<body>
    <h1>${errorMessage!""}</h1>
    <h2>Add Vehicle</h2>

    <form action="/admin/vehicles/create" method="POST" id="vehicleForm" name="vehicleForm">
        <h4><i>Vehicle's Details</i></h4>
        <#--bind this field with the registration form fields-->
        <@spring.bind "vehicleForm.vehicleID" />
        Plate Number: <input type="text" name="vehicleID" id="vehicleID" placeholder="ABC-1234" value="${vehicleForm.vehicleID!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>

        <@spring.bind "vehicleForm.afm"/>
        Owner's AFM: <input type="text" name="afm" id="afm" placeholder="123456789" value="${vehicleForm.afm!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>

        <br><br>

        <@spring.bind "vehicleForm.brand"/>
        Brand: <input type="text" name="brand" id="brand" placeholder="Ford" value="${vehicleForm.brand!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>

        <@spring.bind "vehicleForm.model"/>
        Model: <input type="text" name="model" id="model" placeholder="Focus" value="${vehicleForm.model!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>

        <@spring.bind "vehicleForm.year"/>
        Year: <input type="number" min=1950 max=2017 step=1 name="year" id="year" placeholder="2001" value="${vehicleForm.year!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>


        <br><br>
        <@spring.bind "vehicleForm.fuelType"/>
        Fuel Type:
        <select id="fuelType" name="fuelType">
            <option value="Petrol">Petrol</option>
            <option value="Diesel">Diesel</option>
            <option value="Gas">Gas</option>
        </select>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>

        <@spring.bind "vehicleForm.color"/>
        Color: <input type="String" name="color" id="color" placeholder="Red" value="${vehicleForm.color!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>

        <br><br>
        <input type="submit" value="Create">
    </form>
    <hr></hr>
    <h2>Search Vehicle</h2>
    <form action="/admin/vehicles/search" method="GET" id="vehicleSearchForm" name="vehicleSearchForm">
        <h4><i>Fill in Vehicle's Plate number or Owner's AFM</i></h4>
        <#--bind this field with the registration form fields-->
        <@spring.bind "vehicleSearchForm.afm" />
        AFM: <input type="text" name="afm" id="afm" placeholder="123456789" value="${vehicleSearchForm.afm!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>

        <@spring.bind "vehicleSearchForm.vehicleID"/>
        Plate Number: <input type="text" name="vehicleID" id="vehicleID" placeholder="ABC-1234" value="${vehicleSearchForm.vehicleID!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>
        <br><br>
        <input type="submit" value="Search">
    </form>
<hr></hr>
    <h2>${searchNotFoundMessage!""}</h2>
    <#if vehicleList??>
        <h3>Retrieved Vehicles:</h3>
        <table>
            <tr>
                <th>Plate Number</th>
                <th>Brand</th>
                <th>Model</th>
                <th>Year</th>
                <th>Color</th>
                <th>Fuel Type</th>
                <th>Edit Vehicle</th>
                <th>Delete Vehicle</th>
            </tr>
        <#list vehicleList as vehicle>
        <span>
        <tr>
            <td>${vehicle.vehicleID}</td>
            <td>${vehicle.brand!}</td>
            <td>${vehicle.model}</td>
            <td>${vehicle.year}</td>
            <td>${vehicle.color}</td>
            <td>${vehicle.fuelType}</td>

            <form action="/admin/vehicles/edit/${vehicle.vehicleID}" method="GET">
            <td> <input type="submit" value="Edit"> </td>
            <td>
                <button type="submit" formaction="/admin/vehicles/delete/${vehicle.vehicleID}" formmethod="GET" onclick="return confirm('Are you sure?')">Delete</button>
            </td>
            <!--td>
                <button type="submit" formaction="/admin/users/vehicles/repairs/${vehicle.userID}" formmethod="GET">Repairs</button>
            </td-->
            </form>
        </tr>
        </span>
        </#list>
    </table>
    </#if>
    <script>
    function myFunction() {
        confirm("Are you sure to delete?");
    }
    </script>
</body>
</html>


