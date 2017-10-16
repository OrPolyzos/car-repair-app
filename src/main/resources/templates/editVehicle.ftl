<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>Vehicles</title>
</head>
<body>
    <h1>${errorMessage!""}</h1>
    <h2>Add Vehicle</h2>

    <form action="/admin/vehicles/edit" method="POST" id="vehicleForm" name="vehicleForm">
        <h4><i>Vehicle's Details</i></h4>
        <#--bind this field with the registration form fields-->

        <@spring.bind "vehicleForm.afm" />
        <input type="hidden" name="userID" value="${vehicleForm.afm!""}">

        <@spring.bind "vehicleForm.vehicleID" />
        Plate Number: <input type="text" name="vehicleID" id="vehicleID" placeholder="ABC-1234" value="${vehicleForm.vehicleID!""}"/>
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
        <input type="submit" value="Edit">
    </form>
    <form action="/admin/vehicles" method="GET">
        <input type="submit" value="Back">
    </form>
</body>
</html>