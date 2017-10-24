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
    <link rel="stylesheet" type="text/css" media="screen" href="/css/styles.css">
    <script src="static/js/scripts.js"></script>

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

<!-- NavBar -->
<#include "navbar.ftl">


    <h1>${errorMessage!""}</h1>
    <h2>Add Vehicle</h2>

    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-12">

                <form class="form-horizontal" action="/admin/parts/create" method="POST" id="vehicleForm" name="partForm">
                    <legend>Vehicle's Details</legend>
                    <fieldset class="Hor">


                        <#--bind this field with the registration form fields-->
                            <div class="form-group">
                                <label>Part ID</label>
                                <@spring.bind "partForm.partID"/>
                                <input type="number" class="form-control "name="partID" id="partID" placeholder="" value="${partForm.partID!""}"/>
                                <#list spring.status.errorMessages as error>
                                    <span>${error}</span>
                                </#list>
                            </div>
                            <div class="form-group">
                                <label>Part Name</label>
                                <@spring.bind "partForm.partName"/>
                                <input type="text" class="form-control "name="partName" id="partName" placeholder="" value="${partForm.partName!""}"/>
                                <#list spring.status.errorMessages as error>
                                    <span>${error}</span>
                                </#list>
                            </div>
                    </fieldset>
                    <fieldset class="Hor">
                        <div class="form-group">
                            <label>Cost</label>
                            <@spring.bind "partForm.partCost"/>
                            <input type="number" class="form-control" name="partCost" id="partCost" placeholder="" value="${partForm.partCost!""}"/>
                            <#list spring.status.errorMessages as error>
                                <span>${error}</span>
                            </#list>
                        </div>
                    </fieldset>

                    <br><br>
                    <div class="col-md-12 controls">
                        <span>
                            <button type="reset" id="btn-clear" class="btn btn-danger" style="float: right;"></button>
                            <button type="submit" id="btn-submit" class="btn btn-success" style="float: right;"></button>
                        </span>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <#include "footer.ftl">

</body>
</html>