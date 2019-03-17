<#import "/spring.ftl" as spring/>
<html>

<head>
    <title>Repair Parts</title>
    <meta charset="utf-8" >
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/../utilities.js"></script>
    <link rel="stylesheet" href="/../styles.css">
    <style>
        fieldset.horizontal-3 {
            float: left;
            width: 50%;
            padding: 20;
        }
        fieldset.horizontal-1 {
            padding: 20;
            width: 100%;
        }
    </style>

</head>

<body class="IMAGE">
    <#include "navbar.ftl">

    <h1 class="errorMessage">${errorMessage!""}</h1>
    <div>
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <h2 align="center">Add Parts</h2>
                    <form  class="form-horizontal" action="/admin/repairs/parts/add" method="POST" id="repairPartsForm" name="repairPartsForm">
                            <@spring.bind "repairPartsForm.repairID"/>
                            <input type="hidden" name="repairID" value="${repairPartsForm.repairID!""}"/>
                             <fieldset class="Hor">

                             <div class="form-group">
                             <label for="partID">Part ID</label>
                             <@spring.bind "repairPartsForm.partID"/>
                             <input class="form-control" type="number" name="partID" id="partID" placeholder="1" value="${repairPartsForm.partID!""}"/>
                             <#list spring.status.errorMessages as error>
                                <span class="errorMessage">${error}</span>
                             </#list>
                             </div>
                             </fieldset>

                             <fieldset class="Hor">

                             <div class="form-group">
                             <label for="quantity">Quantity</label>
                             <@spring.bind "repairPartsForm.quantity"/>
                             <input class="form-control" type="number" min=1 max=999999 name="quantity" id="quantity" placeholder="1" value="${repairPartsForm.quantity!""}"/>
                             <#list spring.status.errorMessages as error>
                                <span class="errorMessage">${error}</span>
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

    <#if repairPartList??>
        <h3 class="text-center"><u>Retrieved Parts of this Repair</u></h3>
        <div class="table-responsive">
            <table class="table" class="table-hover">
                <thead>
                    <tr>
                        <th>Part ID</th>
                        <th>Part Price</th>
                        <th>Quantity</th>
                        <th>Delete Part</th>
                    </tr>
                </thead>
                <tbody>
                <#list repairPartList as repairPart>
                <span>
                <tr>
                    <td>${repairPart.part.partID}</td>
                    <td>${repairPart.part.partPrice}</td>
                    <td>${repairPart.quantity}</td>
                    <td>
                        <form action="/admin/repairs/parts/delete/${repairPart.repairID}/${repairPart.part.partID}" method="POST">
                        <button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure?')">
                            <span class="glyphicon glyphicon-remove"></span>
                        </button>
                        </form>
                    </td>
                </tr>
                </span>
                </#list>
            </tbody>
        </table>
    </div>
    <#else>
        <h2>No parts added to this repair yet!</h2>
    </#if>
    <hr></hr>




    <hr></hr>
    <#if wholePartList??>
        <h3 class="text-center"><u>All available parts</u></h3>
        <br><br>
            <div class="container-fluid">
                <div class="row">
                    <form class="form-horizontal">
                        <fieldset class="Norm">
                            <div class="col-md-6 col-md-offset-3">
                                <label  for="filterInput">Filter</label>
                                <input type="text" class="form-control" name="filterInput" id="filterInput" placeholder="abc-123..."/>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        <br><br>
        <div class="table-responsive">
            <table id="resultsTable" class="table" class="table-hover">
                <thead>
                    <tr>
                        <th>Part ID</th>
                        <th>Part Name</th>
                        <th>Part Price</th>
                    </tr>
                </thead>
                <tbody>
                    <#list wholePartList as part>
                <span>
                <tr>
                    <td>${part.partID}</td>
                    <td>${part.partName}</td>
                    <td>${part.partPrice}</td>
                </tr>
                </span>
                    </#list>
            </tbody>
        </table>
    </div>
    <#else>
    <h2>There are no parts at all!</h2>
    </#if>
    <#include "footer.ftl">
</body>
</html>

