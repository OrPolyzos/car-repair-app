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
    <script type="text/javascript" src="../utilities.js"></script>
    <link rel="stylesheet" href="../styles.css">
    <!---- Trying to make some cool stuff with p5.js
    <script async src=https://CDN.JSDelivr.net/g/p5.js(p5.min.js+addons/p5.dom.js+addons/p5.sound.js)></script>
    <script defer src=/../sketch.js></script>
    <script defer src=/../particle.js></script> ---->

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
    <div>
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <h1 align="center">Create New Repair</h1>
                    <form  class="form-horizontal" action="/admin/repairs/create" method="POST" id="repairForm" name="repairForm">
                    <legend>Repair's Details</legend>
                        <fieldset class="Hor">
                            <div class="form-group">
                            <label for="repairVehicleID">Vehicle's Plate Number</label>
                            <@spring.bind "repairForm.repairVehicleID"/>
                            <input class="form-control" type="text" name="repairVehicleID" id="repairVehicleID" placeholder="ABE-1234" value="${repairForm.repairVehicleID!""}"/>
                            <#list spring.status.errorMessages as error>
                                 <span class="errorRed">${error}</span>
                            </#list>
                            </div>
                            <div class="form-group">
                            <label for="repairTotalCost">Total Cost</label>
                            <@spring.bind "repairForm.repairTotalCost"/>
                            <input class="form-control" type="number" name="repairTotalCost" id="repairTotalCost" placeholder="344" value="${repairForm.repairTotalCost!""}"/>
                            <#list spring.status.errorMessages as error>
                                 <span class="errorRed">${error}</span>
                            </#list>
                            </div>
                            <div class="form-group">
                            <label for="repairTypeID">Types</label>
                            <@spring.bind "repairForm.repairTypeID"/>
                            <select class="form-control" id="repairTypeID" name="repairTypeID">
                                <option value="1">Small Service</option>
                                <option value="2">Great Service</option>
                                <option value="3">Custom Service</option>
                            </select>
                            <#list spring.status.errorMessages as error>
                                 <span class="errorRed">${error}</span>
                            </#list>
                            </div>
                            </fieldset>
                            <fieldset class="Hor">
                            <div class="form-group">
                            <label for="repairDate">Scheduled Date & Time</label>
                            <@spring.bind "repairForm.repairDateTime"/>
                            <input class="form-control" type="datetime-local" name="repairDateTime" id="repairDateTime" value="${repairForm.repairDateTime!""}"/>
                            <#list spring.status.errorMessages as error>
                                 <span class="errorRed">${error}</span>
                            </#list>
                            </div>
                            <div class="form-group">
                            <label for="repairStatus">Status</label>
                            <@spring.bind "repairForm.repairStatus"/>
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
                            <fieldset class="Norm">
                            <div class="form-group">
                            <label for="repairTasks">Tasks</label>
                            <@spring.bind "repairForm.repairTasks"/>
                            <textarea  class="form-control"  rows=4 cols=50 id="repairTasks" name="repairTasks" placeholder="Engine oil change, Oil filter replacement, Spark plugs Replacement"> ${repairForm.repairTasks!""}</textarea>
                            <#list spring.status.errorMessages as error>
                                 <span class="errorRed">${error}</span>
                            </#list>
                            </div>
                            </fieldset>
                            <div class="col-sm-12 controls">
                            <span>
                                <button type="submit" id="btn-submit" class="btn btn-success btn-md">Create</button>
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
                    <h1 align="center">Search Repair</h1>
                    <form class="Search" class="form-horizontal" action="/admin/repairs/search" method="GET" id="repairSearchForm" name="repairSearchForm">
                        <fieldset class="Norm">
                            <legend>Leave all the fields blank to get all the results</legend>
                            <div class="form-group">
                                <label for="afm">RepairID</label>
                                <@spring.bind "repairSearchForm.repairID"/>
                                <input type="number" class="input-sm" name="repairID" id="repairID" placeholder="43234" value="${repairSearchForm.repairID!""}"/>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                                <label for="vehicleID">Plate Number</label>
                                <@spring.bind "repairSearchForm.repairVehicleID"/>
                                <input type="text" class="input-sm" name="repairVehicleID" id="repairVehicleID" placeholder="ABC-1234" value="${repairSearchForm.repairVehicleID!""}"/>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                                <br><br>
                                <@spring.bind "repairSearchForm.repairDateTimeStart"/>
                                <label for="repairDateTimeStart">Scheduled DateTime Start</label>
                                <input type="datetime-local" name="repairDateTimeStart" id="repairDateTimeStart" value="${repairSearchForm.repairDateTimeStart!""}"/>
                                <#list spring.status.errorMessages as error>
                                     <span>${error}</span>
                                </#list>
                                <@spring.bind "repairSearchForm.repairDateTimeEnd"/>
                                <label for="repairDateTimeEnd">Scheduled DateTime End</label>
                                <input type="datetime-local" name="repairDateTimeEnd" id="repairDateTimeEnd" value="${repairSearchForm.repairDateTimeEnd!""}"/>
                                <#list spring.status.errorMessages as error>
                                     <span>${error}</span>
                                </#list>
                                <hr></hr>
                                <label for="filterInput">Filter</label>
                                <input type="text" class="input-sm" name="filterInput" id="filterInput" placeholder="abc-123..."/>
                            </fieldset>
                            </div>
                            <br><br>
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
    <#if repairsList??>
        <h3 class="text-center"><u>Retrieved Repairs</u></h3>
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
                        <th class="text-center">Edit Repair</th>
                        <th class="text-center">Delete Repair</th>
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
                    <form action="/admin/repairs/edit/${repair.repairID}" method="GET">
                      <td class="text-center">
                          <button type="submit">
                            <span class="glyphicon glyphicon-cog"></span>
                        </button>
                        </td>
                        <td class="text-center">
                            <button type="submit" formaction="/admin/users/delete/${repair.repairID}" formmethod="GET" onclick="return confirm('Are you sure you want to delete this user?')">
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
</body>
</html>






