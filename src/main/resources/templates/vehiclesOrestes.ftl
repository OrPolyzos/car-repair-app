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
    <script type="text/javascript" src="/utilities.js"></script>
    <link rel="stylesheet" href="/../styles.css">
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
        <img src="../Images/eXtremeRed.png">
    </div>
    <h1 class="errorRed">${errorMessage!""}</h1>
    <div>
        <div class="container">
         <!--img src="../Images/eXtreme.png"-->
            <div class="row">
                <div class="col-sm-12">
                    <h1>Add vehicle</h1>
                    <form  class="form-horizontal" action="/admin/vehicles/create" method="POST" id="vehicleForm" name="vehicleForm">
                        <fieldset class="Hor">
                            <div class="form-group">
                                <label for="afm">Owner's AFM</label>
                                <@spring.bind "vehicleForm.afm"/>
                                <input type="text" class="form-control" id="afm" name="afm" placeholder="123456789" value="${vehicleForm.afm!""}"/>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                            </div>
                            <div class="form-group">
                                <label for="afm">Plate Number</label>
                                <@spring.bind "vehicleForm.vehicleID"/>
                                <input type="text" class="form-control" id="vehicleID" name="vehicleID" placeholder="ABC-1234" value="${vehicleForm.vehicleID!""}"/>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                            </div>
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
                            <button type="submit" id="btn-submit" class="btn btn-success btn-md">Add</button>
                            <button type="reset" id="btn-clear" class="btn btn-danger btn-md">Clear</button>
                        </span>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <hr></hr>
        <div>
            <center>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-12">
                        <h1 align="center">Search</h1>
                        <form class="Search" class="form-horizontal" action="/admin/vehicles/search" method="GET" id="vehicleSearchForm" name="vehicleSearchForm">
                            <fieldset class="Norm">
                                <legend>Fill in User's AFM or Email</legend>
                                <div class="form-group">
                                    <label for="vehicleID">Plate Number</label>
                                    <@spring.bind "vehicleSearchForm.vehicleID"/>
                                    <input type="text" class="input-sm" type="text" name="vehicleID" id="vehicleID" placeholder="ABC-1234" value="${vehicleSearchForm.vehicleID!""}"/>
                                    <#list spring.status.errorMessages as error>
                                        <span class="errorRed">${error}</span>
                                    </#list>

                                    <label for="afm">AFM</label>
                                    <@spring.bind "vehicleSearchForm.afm"/>
                                    <input type="text" class="input-sm" name="afm" id="afm" placeholder="123456789" value="${vehicleSearchForm.afm!""}"/>
                                    <#list spring.status.errorMessages as error>
                                        <span class="errorRed">${error}</span>
                                    </#list>
                                    <br><br>
                                    <label for="filterInput">Filter</label>
                                    <input type="text" class="input-sm" name="filterInput" id="filterInput" placeholder="zzz-43..."/>
                                </div>
                            </fieldset>
                            <div class="col-sm-12 controls">
                                <span>
                                    <button type="submit" id="btn-submit" class="btn btn-success btn-md">Search</button>
                                    <button type="reset" id="btn-clear" class="btn btn-danger btn-md">Clear</button>
                                </span>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            </center>
        </div>
    <h2>${searchNotFoundMessage!""}</h2>
    <#if vehicleList??>
        <h3 class="text-center"><u>Retrieved Vehicles</u></h3>
        <div class="table-responsive">
        <table id="resultsTable" class="table" class="table-hover">
            <thead>
                <tr>
                    <th class="text-center">Plate Number</th>
                    <th class="text-center">Owner's AFM</th>
                    <th class="text-center">Brand</th>
                    <th class="text-center">Model</th>
                    <th class="text-center">Year</th>
                    <th class="text-center">Color</th>
                    <th class="text-center">Fuel</th>
                    <th class="text-center">Edit</th>
                    <th class="text-center">Delete</th>
                </tr>
            </thead>
            <tbody>
                <#list vehicleList as vehicle>
                    <span>
                <tr>
                    <td class="text-center">${vehicle.vehicleID}</td>
                    <td class="text-center">${vehicle.user.afm}</td>
                    <td class="text-center">${vehicle.brand!}</td>
                    <td class="text-center">${vehicle.model}</td>
                    <td class="text-center">${vehicle.year}</td>
                    <td class="text-center">${vehicle.color}</td>
                    <td class="text-center">${vehicle.fuelType}</td>
                    <form action="/admin/vehicles/edit/${vehicle.vehicleID}" method="GET">
                        <td class="text-center">
                            <button type="submit">
                                <span class="glyphicon glyphicon-cog"></span>
                            </button>
                        <td class="text-center">
                            <button type="submit" formaction="/admin/vehicles/delete/${vehicle.vehicleID}" formmethod="GET" onclick="return confirm('Are you sure?')">
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

    <script>
    function myFunction() {
        confirm("Are you sure to delete?");
    }
    </script>

    <div>
        <footer>
            <p>&copy; RGCS 2017 - The Auto Repair Group.</p>
        </footer>
    </div>

</body>
</html>


