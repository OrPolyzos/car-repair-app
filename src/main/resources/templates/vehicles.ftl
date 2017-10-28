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
    <!-- Custom CSS -->
    <link rel="stylesheet" type="text/css" media="screen" href="/../styles.css">
    <script type="text/javascript" src="/utilities.js"></script>
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
<body class="IMAGE">

<!-- NavBar -->
<#include "navbar.ftl">


    <h1 class="errorRed">${errorMessage!""}</h1>
    <h2>Add Vehicle</h2>

    <br><br>

    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">

                <form class="form-horizontal" action="/admin/vehicles/create" method="POST" id="vehicleForm" name="vehicleForm">
                    <legend>Vehicle's Details</legend>
                    <fieldset class="Hor">


                        <#--bind this field with the registration form fields-->
                            <div class="form-group">
                                <label for="vehicleID">Plate Number</label>
                                <@spring.bind "vehicleForm.vehicleID"/>
                                <input type="text" class="form-control "name="vehicleID" id="vehicleID" placeholder="ABC-1234" value="${vehicleForm.vehicleID!""}"/>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                            </div>
                            <div class="form-group">
                                <label for="afm">Owner's AFM</label>
                                <@spring.bind "vehicleForm.afm"/>
                                <input type="number" class="form-control" name="afm" id="afm" placeholder="123456789" value="${vehicleForm.afm!""}"/>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                            </div>
                    </fieldset>
                    <fieldset class="Hor">
                        <div class="form-group">
                            <label for="brand">Brand</label>
                            <@spring.bind "vehicleForm.brand"/>
                            <input type="text" class="form-control" name="brand" id="brand" placeholder="Ford" value="${vehicleForm.brand!""}"/>
                            <#list spring.status.errorMessages as error>
                                <span class="errorRed">${error}</span>
                            </#list>
                        </div>
                        <div class="form-group">
                            <label for="model">Model</label>
                            <@spring.bind "vehicleForm.model"/>
                            <input type="text" class="form-control" name="model" id="model" placeholder="Focus" value="${vehicleForm.model!""}"/>
                            <#list spring.status.errorMessages as error>
                                <span class="errorRed">${error}</span>
                            </#list>
                        </div>
                        <div class="form-group">
                            <label for="year">Year</label>
                            <@spring.bind "vehicleForm.year"/>
                            <input type="number" class="form-control" min=1950 max=2017 step=1 name="year" id="year" placeholder="2001" value="${vehicleForm.year!""}"/>
                            <#list spring.status.errorMessages as error>
                                <span class="errorRed">${error}</span>
                            </#list>
                        </div>
                    </fieldset>
                    <fieldset class="Hor">
                        <div class="form-group">
                            <label for="fuelType">Fuel</label>
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
                            <input type="String" class="form-control" name="color" id="color" placeholder="Red" value="${vehicleForm.color!""}"/>
                            <#list spring.status.errorMessages as error>
                                <span class="errorRed">${error}</span>
                            </#list>
                        </div>
                    </fieldset>

                    <br><br>
                    <div class="col-md-12 controls">
                        <span>
                            <button type="submit" id="btn-submit" class="btn btn-success">Create</button>
                            <button type="reset" id="btn-clear" class="btn btn-danger">Clear</button>
                        </span>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <hr></hr>

    <div class="container-fluid">
        <div class="row">
            <h2>Search Vehicle</h2>

            <br><br>

            <form class="Search" class="form-horizontal" action="/admin/vehicles/search" method="GET" id="vehicleSearchForm" name="vehicleSearchForm">
                <fieldset class="Norm">
                    <legend>Fill in Vehicle's Plate number or Owner's AFM</legend>
                    <div class="col-md-4">

                        <#--bind this field with the registration form fields-->
                            <@spring.bind "vehicleSearchForm.afm" />
                            <label for="afm">AFM</label>
                            <input type="number" class="form-control" name="afm" id="afm" placeholder="123456789" value="${vehicleSearchForm.afm!""}"/>
                            <#list spring.status.errorMessages as error>
                                <span class="errorRed">${error}</span>
                            </#list>
                    </div>
                    <div class="col-md-4">
                        <@spring.bind "vehicleSearchForm.vehicleID"/>
                        <label for="vehicleID">Plate Number</label>
                        <input type="text" class="form-control" name="vehicleID" id="vehicleID" placeholder="ABC-1234" value="${vehicleSearchForm.vehicleID!""}"/>
                        <#list spring.status.errorMessages as error>
                            <span class="errorRed">${error}</span>
                        </#list>
                    </div>
                    <div class="col-md-4">
                        <label for="filterInput">Filter</label>
                        <input type="text" class="form-control" name="filterInput" id="filterInput" placeholder="zzz-43..."/>
                    </div>
                </fieldset>



                <div class="col-md-12 controls">
                    <span>
                        <button type="submit" id="btn-submit" class="btn btn-primary">Search</button>
                        <button type="reset" id="btn-clear" class="btn btn-danger">Clear</button>
                    </span>
                </div>
            </form>
        </div>
    </div>

    <hr></hr>

    <h2>${searchNotFoundMessage!""}</h2>
    <#if vehicleList??>
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
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                <#list vehicleList as vehicle>
                        <span>
                    <tr>
                        <td>${vehicle.vehicleID!"Could not retrieve value!"}</td>
                        <td>${vehicle.brand!"Could not retrieve value!"}</td>
                        <td>${vehicle.model!"Could not retrieve value!"}</td>
                        <td>${vehicle.year!"Could not retrieve value!"}</td>
                        <td>${vehicle.color!"Could not retrieve value!"}</td>
                        <td>${vehicle.fuelType!"Could not retrieve value!"}</td>
                    <form action="/admin/vehicles/edit/${vehicle.vehicleID}" method="GET">
                        <td>
                            <button type="submit" value="Edit" class="btn btn-success">
                                <span class="glyphicon glyphicon-cog"></span>
                            </button>
                        <td>
                            <button type="submit" formaction="/admin/vehicles/delete/${vehicle.vehicleID}" formmethod="POST" class="btn btn-danger" onclick="return confirm('Are you sure?')" >
                                <span class="glyphicon glyphicon-remove"></span>
                            </button>
                        </td>
                    </form>
                    </tr>
                        </span>
                </#list>
                </tbody>
            </table>
        </div>
    </#if>
    <#include "footer.ftl">

</body>
</html>
