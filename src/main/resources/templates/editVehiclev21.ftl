<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>Vehicles</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Font Awesome Icons -->
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
    
	<!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

	
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

    <#include "navbar.ftl">

    <h1>${errorMessage!""}</h1>
    <h2>Edit Vehicle</h2>

    <div class="container">
        <div class="row">
            <div class="col-sm-12>
                <form action="/admin/vehicles/editVehicle" method="POST" id="vehicleForm" name="vehicleForm">
                    <h4><i>Vehicle's Details</i></h4>

                    <h5><u>Owner's AFM: ${vehicleForm.afm!""}</u></h5>
                    <#--bind this field with the registration form fields-->
                    <@spring.bind "vehicleForm.userID" />
                    <input type="hidden" name="userID" value="${vehicleForm.userID!""}">
                    <@spring.bind "vehicleForm.afm" />
                    <input type="hidden" name="afm" value="${vehicleForm.afm!""}">

                    <div class="form-group">
                        <@spring.bind "vehicleForm.vehicleID" />
                            <label>Plate Number</label>
                                <div class="input-prepend">
                                    <span class="add-on">
                                        <input type="text" class="form-control" name="vehicleID" id="vehicleID" placeholder="ABC-1234" value="${vehicleForm.vehicleID!""}"/>
                                    </span>
                                </div>
                        <#list spring.status.errorMessages as error>
                            <span>${error}</span>
                        </#list>
                    </div>

                    <div class="form-group">
                        <@spring.bind "vehicleForm.brand"/>
                            <label>Brand</label>
                                <div class="input-prepend">
                                    <span class="add-on">
                                        <span class="glyphicons glyphicons-search"></span>
                                        <input type="text" class="form-control" name="brand" id="brand" placeholder="Ford" value="${vehicleForm.brand!""}"/>
                                    </span>
                                </div>
                        <#list spring.status.errorMessages as error>
                            <span>${error}</span>
                        </#list>
                    </div>

                    <div class="form-group">
                        <@spring.bind "vehicleForm.model"/>
                            <label>Model</label>
                                <div class="input-prepend">
                                    <span class="add-on">
                                        <input type="text" class="form-control" name="model" id="model" placeholder="Focus" value="${vehicleForm.model!""}"/>
                                    </span>
                                </div>
                        <#list spring.status.errorMessages as error>
                            <span>${error}</span>
                        </#list>
                    </div>

                    <!--    <span class="glyphicons glyphicons-notes-2"></span> -->


                    <div class="form-group">
                        <@spring.bind "vehicleForm.year"/>
                            <label>Year</label>
                                <div class="input-prepend">
                                    <span class="add-on">
                                        <input type="text" class="form-control" min=1950 max=2017 step=1 name="year" id="year" placeholder="2001" value="${vehicleForm.year!""}"/>
                                    </span>
                                </div>
                        <#list spring.status.errorMessages as error>
                            <span>${error}</span>
                        </#list>
                    </div>

                    <div class="form-group">
                        <@spring.bind "vehicleForm.fuelType"/>
                            <label>Fuel Type</label>
                                <div class="input-prepend">
                                    <span class="add-on">
                                        <select id="fuelType" name="fuelType">
                                            <option value="Petrol">Petrol</option>
                                            <option value="Diesel">Diesel</option>
                                        </select>
                                    </span>
                                </div>
                        <#list spring.status.errorMessages as error>
                            <span>${error}</span>
                        </#list>
                    </div>

                    <div class="form-group">
                        <@spring.bind "vehicleForm.color"/>
                            <label>Color</label>
                                <div class="input-prepend">
                                    <span class="add-on">
                                        <input type="String" class="form-control" name="color" id="color" placeholder="Red" value="${vehicleForm.color!""}"/>
                                    </span>
                                </div>
                        <#list spring.status.errorMessages as error>
                            <span>${error}</span>
                        </#list>
                    </div>

        <br><br>

                        <button type="submit" value="Edit" class="btn btn-primary">Edit</button>
                        <button type="submit" value="Back" formaction="/admin/vehicles/" formmethod="GET">Back</button>
                </form>
            </div>
        </div>
    </div>

    <!--#include "footer.ftl"-->
</body>
</html>