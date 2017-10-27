<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>Edit Repair</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/../utilities.js"></script>
    <link rel="stylesheet" href="/../styles.css">

</head>
<body>

    <#include "navbar.ftl">

    <div class="p5container">
        <img src="/../Images/eXtremeRed.png">
    </div>

    <h1 class="errorRed">${errorMessage!""}</h1>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <form class="form-horizontal" action="/admin/repairs/editRepair" method="POST" id="repairForm" name="repairForm">
                    <legend>Repair's Details</legend>
                        <@spring.bind "repairForm.repairID"/>
                        <input type="hidden" name="repairID" value="${repairForm.repairID}"/>
                        <#--bind this form with the repair form fields-->
                            <div class="form-group">
                                <@spring.bind "repairForm.repairVehicleID"/>
                                <label for="repairVehicleID">Vehicle's Plate Number</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"></span>
                                        <input type="String" class="form-control" name="repairVehicleID" id="repairVehicleID" placeholder="ABE-1234" value="${repairForm.repairVehicleID!""}"/>
                                    </div>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                            </div>
                            <div class="form-group">
                                <@spring.bind "repairForm.repairTypeID"/>
                                <label for="repairTypeID">Type Service</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"></span>
                                        <select class="form-control" id="repairTypeID" name="repairTypeID">
                                            <option value="1">Small Service</option>
                                            <option value="2">Great Service</option>
                                        </select>
                                    </div>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                            </div>
                            <div class="form-group">
                                <@spring.bind "repairForm.repairStatus"/>
                                <label for="repairStatus">Status</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"></span>
                                        <select class="form-control" id="repairStatus" name="repairStatus">
                                            <option value="Pending">Pending</option>
                                            <option value="In progress">In progress</option>
                                            <option value="Completed">Completed</option>
                                        </select>
                                    </div>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                            </div>
                            <div class="form-group">
                                <label for="repairDateTime">Scheduled Date & Time</label>
                                <@spring.bind "repairForm.repairDateTime"/>
                                    <div class="input-group">
                                        <span class="input-group-addon"></span>
                                        <input class="form-control" type="datetime-local" name="repairDateTime" id="repairDateTime" value="${repairForm.repairDateTime!""}"/>
                                    </div>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                            </div>
                            <div class="form-group">
                                <@spring.bind "repairForm.repairTasks"/>
                                <label for="repairTasks">Tasks</label>
                                <div class="input-group">
                                    <span class="input-group-addon"></span>
                                    <textarea rows=4 cols=50 id="repairTasks" class="form-control" name="repairTasks" placeholder="Engine oil change, Oil filter replacement, Spark plugs Replacement" value="${repairForm.repairTasks!""}">${repairForm.repairTasks!""}</textarea>
                                </div>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                            </div>
                                <br><br>
                                <button type="submit" class="btn btn-success">Save</button>
                                <button type="reset" id="btn-clear" class="btn btn-danger">Clear</button>

                    </form>
                </div>
            </div>
        </div>

    <#include "footer.ftl">

</body>
</html>

