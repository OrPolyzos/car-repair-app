<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>Parts</title>
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
<body>

<!-- NavBar -->
<#include "navbar.ftl">
    <div class="p5container">
        <img src="/../Images/eXtremeRed.png">
    </div>

    <h1 class="errorRed">${errorMessage!""}</h1>
    <h2>Create Part</h2>

    <br><br>

    <div class="container-fluid">
        <div class="row">
            <form class="form-horizontal" action="/admin/parts/create" method="POST" id="partForm" name="partForm">
                <legend>Parts Details</legend>
                    <div class="col-md-6">
                        <fieldset>
                            <#--bind this field with the registration form fields-->
                            <@spring.bind "partForm.partName"/>
                            <label for="partName">Part Name</label>
                                <input type="text" class="form-control "name="partName" id="partName" placeholder="turbocharger" value="${partForm.partName!""}"/>
                            <#list spring.status.errorMessages as error>
                                <span class="errorRed">${error}</span>
                            </#list>
                        </fieldset>
                    </div>
                    <div class="col-md-6">
                        <fieldset>
                            <label for="partPrice">Price</label>
                            <@spring.bind "partForm.partPrice"/>
                                <input type="number" class="form-control" name="partPrice" id="partPrice" placeholder="100" value="${partForm.partPrice!""}"/>
                            <#list spring.status.errorMessages as error>
                                <span class="errorRed">${error}</span>
                            </#list>
                        </fieldset>
                    </div>
                    <br><br>
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

    <hr></hr>

    <div class="container-fluid">
        <div class="row">
            <h2>Search Part</h2>

            <br><br>

            <form class="Search" class="form-horizontal" action="/admin/parts/search" method="GET" id="partSearchForm" name="partSearchForm">
                <fieldset class="Norm">
                    <legend>Fill in Part's Fields</legend>
                    <div class="col-md-4">
                        <label for=partID>Part ID</label>
                        <@spring.bind "partSearchForm.partID"/>
                        <input type="number" class="form-control" name="partID" id="partID" value="${partSearchForm.partID!""}"/>
                        <#list spring.status.errorMessages as error>
                            <span class="errorRed">${error}</span>
                        </#list>
                    </div>
                    <div class="col-md-4">
                        <label for=partPriceStart>Start Price</label>
                        <@spring.bind "partSearchForm.partPriceStart"/>
                        <input type="number" class="form-control" name="partPriceStart" id="partPriceStart" value="${partSearchForm.partPriceStart!""}"/>
                        <#list spring.status.errorMessages as error>
                            <span class="errorRed">${error}</span>
                        </#list>
                    </div>
                    <div class="col-md-4">
                        <label for=partPriceEnd>End Price</label>
                        <@spring.bind "partSearchForm.partPriceEnd"/>
                        <input type="number" class="form-control" name="partPriceEnd" id="partPriceEnd" value="${partSearchForm.partPriceEnd!""}"/>
                        <#list spring.status.errorMessages as error>
                            <span class="errorRed">${error}</span>
                        </#list>
                    </div>
                    <div class="col-md-6 col-md-offset-3">
                        <label for="filterInput">Filter</label>
                        <input type="text" class="form-control" name="filterInput" id="filterInput" placeholder="john"/>
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

    <h2>${searchNotFoundMessage!""}</h2>
    <#if partList??>
        <h3><u>Retrieved Parts</u></h3>
        <div class="table-responsive">
            <table id="resultsTable" class="table" class="table-hover">
                <thead>
                <tr>
                    <th>Part ID</th>
                    <th>Part Name</th>
                    <th>Part Price</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                <#list partList as part>
                        <span>
                    <tr>
                        <td>${part.partID!"Could not retrieve value!"}</td>
                        <td>${part.partName!"Could not retrieve value!"}</td>
                        <td>${part.partPrice!"Could not retrieve value!"}</td>
                        <form action="/admin/parts/edit/${part.partID}" method="GET">
                        <td>
                            <button type="submit" class="btn btn-success">
                                <span class="glyphicon glyphicon-cog"></span>
                            </button>
                        </td>
                        <td>
                            <button type="submit" class="btn btn-danger" formaction="/admin/parts/delete/${part.partID}" formmethod="POST" onclick="return confirm('Are you sure you want to delete this part?')">
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