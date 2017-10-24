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
    <link rel="stylesheet" href="/../styles.css">
    <!---- Trying to make some cool stuff with p5.js
    <script async src=https://CDN.JSDelivr.net/g/p5.js(p5.min.js+addons/p5.dom.js+addons/p5.sound.js)></script>
    <script defer src=/../sketch.js></script>
    <script defer src=/../particle.js></script> ------>

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
    <div class="p5container">
        <img src="/../Images/eXtremeRed.png">
    </div>
    <h1 class="errorRed">${errorMessage!""}</h1>
    <div>
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <h1 align="center">Edit Repair</h1>
                    <form  class="form-horizontal" action="/admin/repairs/editRepair" method="POST" id="repairForm" name="repairForm">
                             <fieldset class="Hor">
                             <@spring.bind "repairForm.repairID"/>
                             <input type="hidden" id="repairID" name="repairID" value="${repairForm.repairID!""}"/>
                             <legend>Repair's Details</legend>
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
                              <legend>Schedule Details</legend>

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

                             <!--Needs better alignment-->
                             <fieldset class="Hor">
                             <div class="form-group">
                             <label for="repairTasks">Tasks</label>
                             <@spring.bind "repairForm.repairTasks"/>
                             <textarea  class="form-control"  rows=4 cols=50 id="repairTasks" name="repairTasks" placeholder="Engine oil change, Oil filter replacement, Spark plugs Replacement">${repairForm.repairTasks!""}</textarea>
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
</body>
</html>

