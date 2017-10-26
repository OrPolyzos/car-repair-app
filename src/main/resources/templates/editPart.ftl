<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>Edit Part</title>
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
                <form class="form-horizontal" action="/admin/parts/editPart" method="POST" id="partForm" name="partForm">
                    <h4>Part Details</h4>
                    <@spring.bind "partForm.partID"/>
                    <input type="hidden" id="partID" name="partID" value="${partForm.partID!""}">

                    <#--bind this field with the registration form fields-->
                        <div class="form-group">
                            <@spring.bind "partForm.partName"/>
                            <label for="partName">Part Name</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class=""></i>
                                </span>
                                <input type="text" class="form-control" name="partName" id="partName" placeholder="Turbocharger" value="${partForm.partName!""}"/>
                            </div>
                            <#list spring.status.errorMessages as error>
                                <span class="errorRed">${error}</span>
                            </#list>
                        </div>

                        <br><br>

                        <div class="form-group">
                            <@spring.bind "partForm.partPrice"/>
                            <label for="partPrice">Cost</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class=""></i>
                                </span>
                                <input type="number" class="form-control" name="partPrice" id="partPrice" placeholder="100" value="${partForm.partPrice!""}"/>
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

    <#include  "footer.ftl">

</body>
</html>
