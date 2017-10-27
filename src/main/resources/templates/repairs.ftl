<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>Repairs</title>
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
    <script type="text/javascript" src="../utilities.js"></script>


    <style>
        fieldset.Hor {
            float: left;
            width: 50%;
            padding: 20;
        }
        fieldset.Norm {
            padding: 20;
            width: 100%;
        }
    </style>
</head>
<body>

    <#include "navbar.ftl">

        <div class="p5container">
            <img src="../Images/eXtremeRed.png">
        </div>

    <h1 class="errorRed">${errorMessage!""}</h1>
    <h2>Add Repair</h2>
    <br><br>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <form class="form-horizontal" action="/admin/repairs/create" method="POST" id="repairForm" name="repairForm">
                    <legend>Repair's Details</legend>

                    <fieldset class="Hor">
                        <#--bind this form with the repair form fields-->
                            <div class="form-group">
                                <@spring.bind "repairForm.repairVehicleID"/>
                                <label for="repairVehicleID">Vehicle's Plate Number</label>
                                    <input type="String" class="form-control "name="repairVehicleID" id="repairVehicleID" placeholder="ABE-1234" value="${repairForm.repairVehicleID!""}"/>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                            </div>
                            <div class="form-group">
                                <@spring.bind "repairForm.repairTypeID"/>
                                <label for="repairTypeID">Type Service</label>
                                <select class="form-control" id="repairTypeID" name="repairTypeID">
                                    <option value="1">Small Service</option>
                                    <option value="2">Great Service</option>
                                </select>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                            </div>
                            <div class="form-group">
                                <@spring.bind "repairForm.repairStatus"/>
                                <label for="repairStatus">Status</label>
                                <select class="form-control" id="repairStatus" name="repairStatus">
                                    <option value="Pending">Pending</option>
                                    <option value="In progress">In progress</option>
                                    <option value="Completed">Completed</option>
                                </select>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                            </div>
                    </fieldset>
                    <fieldset class="Hor">
                        <div class="form-group">
                            <label for="repairDateTime">Scheduled Date & Time</label>
                            <@spring.bind "repairForm.repairDateTime"/>
                                <input class="form-control" type="datetime-local" name="repairDateTime" id="repairDateTime" value="${repairForm.repairDateTime!""}"/>
                            <#list spring.status.errorMessages as error>
                                <span class="errorRed">${error}</span>
                            </#list>
                        </div>

                        <div class="form-group">
                            <@spring.bind "repairForm.repairTasks"/>
                            <label for="repairTasks">Tasks</label>
                                <textarea rows=4 cols=50 id="repairTasks" class="form-control" name="repairTasks" placeholder="Engine oil change, Oil filter replacement, Spark plugs Replacement" value="${repairForm.repairTasks!""}">${repairForm.repairTasks!""}</textarea>
                            <#list spring.status.errorMessages as error>
                                <span class="errorRed">${error}</span>
                            </#list>
                        </div>
                    </fieldset>


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
            <h2>Search Repair</h2>

            <br><br>

                <form class="Search" class="form-horizontal" action="/admin/repairs/search" method="GET" id="repairSearchForm" name="repairSearchForm">
                    <fieldset class="Norm">
                        <legend>Please provide the Repair ID, the Plate Number of the Vehicle or the Date</legend>

                            <div class="col-md-6">
                                <#--bind this field with the registration form fields-->
                                <@spring.bind "repairSearchForm.repairID"/>
                                <label for="repairID">Repair ID</label>
                                    <input type="number" class="form-control" name="repairID" id="repairID" placeholder="1" value="${repairSearchForm.repairID!""}"/>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                            </div>
                            <div class="col-md-6">
                                 <@spring.bind "repairSearchForm.repairVehicleID"/>
                                 <label for="repairVehicleID">Vehicle's Plate Number</label>
                                    <input type="text" class="form-control" name="repairVehicleID" id="repairVehicleID" placeholder="ABC-1234" value="${repairSearchForm.repairVehicleID!""}"/>
                                 <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                 </#list>
                            </div>

                            <div class="col-md-6">
                                <@spring.bind "repairSearchForm.repairDateTimeStart"/>
                                <label for="repairDateTimeStart">Scheduled DateTime Start</label>
                                    <input type="datetime-local" class="form-control" name="repairDateTimeStart" id="repairDateTimeStart" value="${repairSearchForm.repairDateTimeStart!""}"/>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                            </div>
                            <div class="col-md-6">
                                <@spring.bind "repairSearchForm.repairDateTimeEnd"/>
                                <label for="repairDateTimeEnd">Scheduled DateTime End</label>
                                    <input type="datetime-local" class="form-control" name="repairDateTimeEnd" id="repairDateTimeEnd" value="${repairSearchForm.repairDateTimeEnd!""}"/>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                            </div>

                            <div class="col-md-6 col-md-offset-3">
                                <label for="filterInput">Filter</label>
                                    <input type="text" class="form-control" name="filterInput" id="filterInput" placeholder="abc-123..."/>
                            </div>
                    </fieldset>
        <br><br>
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
    <#if repairsList??>
        <h3><u>Retrieved Repairs</u></h3>
        <div class="table-responsive">
            <table id="resultsTable" class="table">
                <thead>
                    <tr>
                        <th>Repair ID</th>
                        <th>Scheduled DateTime</th>
                        <th>Status</th>
                        <th>Type</th>
                        <th>Total Cost</th>
                        <th>Tasks</th>
                        <th>Vehicle ID</th>
                        <th>Edit Repair</th>
                        <th>Delete Repair</th>
                        <th>Add Parts</th>
                    </tr>
                </thead>
                <tbody>
                    <#list repairsList as repair>
                    <span>
                    <tr>
                        <td>${repair.repairID!}</td>
                        <td>
                            <input type="datetime-local" name="repairDateTime" id="repairDateTime" value="${repair.repairDateTime!""}" disabled/>
                        </td>
                        <td>${repair.repairStatus!"Could not retrieve value!"}</td>
                        <td>${repair.repairType.repairTypeDescription!"Could not retrieve value!"}</td>
                        <td>${repair.repairTotalCost!"Could not retrieve value!"}</td>
                        <td>${repair.repairTasks!"Could not retrieve value!"}</td>
                        <td>${repair.vehicleID!"Could not retrieve value!"}</td>

                        <form action="/admin/repairs/edit/${repair.repairID}" method="GET">
                        <td>
                            <button type="submit" class="btn btn-success btn-md">
                                <span class="glyphicon glyphicon-cog"></span>
                            </button>
                        </td>
                        <td>
                            <button type="submit" class="btn btn-danger" formaction="/admin/repairs/delete/${repair.repairID}" formmethod="POST" onclick="return confirm('Are you sure?')">
                                <span class="glyphicon glyphicon-remove"></span>
                            </button>
                        </td>
                        <td>
                            <button type="submit" class="btn btn-success" formaction="/admin/repairs/parts/${repair.repairID}" formmethod="GET" >
                                <span class="glyphicon glyphicon-plus"></span>
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
