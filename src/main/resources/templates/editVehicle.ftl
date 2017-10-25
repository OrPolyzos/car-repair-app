<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>Vehicles</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <!-- Font Awesome Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <!-- Custom CSS -->
    <link rel="stylesheet" type="text/css" media="screen" href="/../styles.css">
</head>
<body>

    <#include "navbar.ftl">
        <div>
            <img src="/../../Images/eXtremeRed.png">
        </div>

    <h1 class="errorRed">${errorMessage!""}</h1>
    <h2>Edit Vehicle</h2>

    <div class="container">
        <div class="row">
            <div class="col-md-12">
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
                        <label for="vehicleID">Plate Number</label>
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class=""></i>
                                    </span>
                                    <input type="text" class="form-control" name="vehicleID" id="vehicleID" placeholder="ABC-1234" value="${vehicleForm.vehicleID!""}"/>
                                </div>
                        <#list spring.status.errorMessages as error>
                            <span class="errorRed">${error}</span>
                        </#list>
                    </div>

                    <div class="form-group">
                        <@spring.bind "vehicleForm.brand"/>
                        <label for="brand">Brand</label>
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="glyphicons glyphicons-car"></i>
                                    </span>
                                    <input type="text" class="form-control" name="brand" id="brand" placeholder="Ford" value="${vehicleForm.brand!""}"/>
                                </div>
                        <#list spring.status.errorMessages as error>
                            <span class="errorRed">${error}</span>
                        </#list>
                    </div>

                    <div class="form-group">
                        <@spring.bind "vehicleForm.model"/>
                        <label for="model">Model</label>
                                <div class="input-group">
                                    <span class="input-group-addon"></span>
                                    <input type="text" class="form-control" name="model" id="model" placeholder="Focus" value="${vehicleForm.model!""}"/>
                                </div>
                        <#list spring.status.errorMessages as error>
                            <span class="errorRed">${error}</span>
                        </#list>
                    </div>

                    <div class="form-group">
                        <@spring.bind "vehicleForm.year"/>
                        <label for="year">Year</label>
                                <div class="input-group">
                                    <span class="input-group-addon"></span>
                                    <input type="text" class="form-control" min=1950 max=2017 step=1 name="year" id="year" placeholder="2001" value="${vehicleForm.year!""}"/>
                                </div>
                        <#list spring.status.errorMessages as error>
                            <span class="errorRed">${error}</span>
                        </#list>
                    </div>

                    <div class="form-group">
                        <@spring.bind "vehicleForm.fuelType"/>
                        <label for="fuelType">Fuel</label>
                                <div class="input-group">
                                    <span class="input-group-addon"></span>
                                        <select class="form-control" id="fuelType" name="fuelType">
                                            <option value="Petrol">Petrol</option>
                                            <option value="Diesel">Diesel</option>
                                        </select>
                                </div>
                        <#list spring.status.errorMessages as error>
                            <span class="errorRed">${error}</span>
                        </#list>
                    </div>

                    <div class="form-group">
                        <@spring.bind "vehicleForm.color"/>
                        <label for="color">Color</label>
                                <div class="input-group">
                                    <span class="input-group-addon"></span>
                                        <input type="String" class="form-control" name="color" id="color" placeholder="Red" value="${vehicleForm.color!""}"/>
                                </div>
                        <#list spring.status.errorMessages as error>
                            <span class="errorRed">${error}</span>
                        </#list>
                    </div>

        <br><br>

                        <button type="submit" value="Edit" class="btn btn-success">Edit</button>
                        <button type="submit" value="Back" class="btn btn-danger" formaction="/admin/vehicles/" formmethod="GET">Back</button>
                </form>
            </div>
        </div>
    </div>

    <#include "footer.ftl">
</body>
</html>