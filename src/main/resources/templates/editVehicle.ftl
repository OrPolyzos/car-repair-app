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

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="../../styles.css">

    <style>
        fieldset.Hor {
            float: left;
            width: 33.3%;
            padding: 20;
        }
        fieldset.Norm {
            padding: 20;
        }
    </style>
</head>
<body>
    <#include "navbar.ftl">
    <div>
        <img src="/../../Images/eXtremeRed.png">
    </div>
    <h1 class="errorRed">${errorMessage!""}</h1>
    <div>
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <h1>Add vehicle</h1>
                    <form  class="form-horizontal" /admin/vehicles/editUser" method="POST" id="vehicleForm" name="vehicleForm">
                        <fieldset class="Hor">
                            <div class="form-group">
                                <label for="afm">Owner's AFM</label>
                                <@spring.bind "vehicleForm.afm"/>
                                <input type="text" class="form-control" id="afm" name="afm" placeholder="123456789" value="${vehicleForm.afm!""}"/>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                            </div>

                            <@spring.bind "vehicleForm.userID" />
                            <input type="hidden" name="userID" value="${vehicleForm.userID!""}"/>
                            <@spring.bind "vehicleForm.vehicleID" />
                            <input type="hidden" name="vehicleID" value="${vehicleForm.vehicleID!""}"/>
                        </fieldset>
                        <fieldset class="Hor">
                            <div class="form-group">
                                <label for="brand">Brand</label>
                                <@spring.bind "vehicleForm.brand"/>
                                <input type="text" class="form-control" id="brand" name="brand" placeholder="Ford" value="${vehicleForm.brand!""}"/>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                            </div>
                            <div class="form-group">
                                <label for="model">Model</label>
                                <@spring.bind "vehicleForm.model"/>
                                <input type="text" class="form-control" id="model" name="model" placeholder="Focus" value="${vehicleForm.model!""}"/>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                            </div>
                            <div class="form-group">
                                <label for="year">Year</label>
                                <@spring.bind "vehicleForm.year"/>
                                <input type="number" class="form-control" type="number" min=1950 max=2017 step=1 name="year" id="year" placeholder="2001" value="${vehicleForm.year!""}"/>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                            </div>
                        </fieldset>
                        <fieldset class="Hor">
                            <div class="form-group">
                                <label for="fuel">Fuel</label>
                                <@spring.bind "vehicleForm.fuelType"/>
                                <select class="form-control" id="fuelType" name="fuelType">
                                    <option value="Petrol">Petrol</option>
                                    <option value="Diesel">Diesel</option>
                                </select>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                            </div>
                            <div class="form-group">
                                <label for="color">Color</label>
                                <@spring.bind "vehicleForm.color"/>
                                <input type="text" class="form-control" name="color" id="color" placeholder="Red" value="${vehicleForm.color!""}"/>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                            </div>
                        </fieldset>
                        <div class="col-sm-12 controls">
                        <span>
                            <button type="submit" id="btn-submit" class="btn btn-success btn-md">Edit</button>
                            <button type="reset" id="btn-clear" class="btn btn-danger btn-md">Clear</button>
                        </span>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <hr></hr>
</body>
</html>