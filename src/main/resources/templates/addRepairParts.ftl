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
                    <h1 align="center">Add Repair Parts</h1>
                    <form  class="form-horizontal" action="/admin/repairs/addRepairParts" method="POST" id="addRepairPartsForm" name="addRepairPartsForm">
                             <fieldset class="Hor">
                            <!-- <@spring.bind "addRepairPartsForm.repairID"/>
                             <input type="hidden" id="repairID" name="repairID" value="${repairForm.repairID!""}"/>
                             <legend>Repair's Details</legend>
                             <div class="form-group"> -->
                             </fieldset>

                             <fieldset class="Hor">

                             <label for="repairId">Repair ID</label>
                             <@spring.bind "addRepairPartsForm.repairId"/>
                             <input type="disabled" type="number" name="repairId" id="repairId" placeholder="1" value="${addRepairPartsForm.repairId!""}"/>
                             <#list spring.status.errorMessages as error>
                                <span class="errorRed">${error}</span>
                             </#list>
                             </div>
                             </fieldset>

                             <fieldset class="Hor">

                             <div class="form-group">
                             <label for="partId">Part ID</label>
                             <@spring.bind "addRepairPartsForm.partId"/>
                             <input class="form-control" type="number" name="partId" id="partId" placeholder="1" value="${addRepairPartsForm.partId!""}"/>
                             <#list spring.status.errorMessages as error>
                                <span class="errorRed">${error}</span>
                             </#list>
                             </div>
                             </fieldset>

                             <fieldset class="Hor">

                             <div class="form-group">
                             <label for="quantity">Quantity</label>
                             <@spring.bind "addRepairPartsForm.quantity"/>
                             <input class="form-control" type="number" name="quantity" id="quantity" placeholder="1" value="${addRepairPartsForm.quantity!""}"/>
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

    <h2>${searchNotFoundMessage!""}</h2>
    <#if repairPartsList??>
        <h3 class="text-center"><u>Retrieved Repair Parts</u></h3>
        <div class="table-responsive">
            <table id="resultsTable" class="table" class="table-hover">
                <thead>
                    <tr>
                        <th class="text-center">Repair ID</th>
                        <th class="text-center">Part ID</th>
                        <th class="text-center">Quantity</th>
                        <th class="text-center">Edit Repair</th>
                        <th class="text-center">Delete Repair</th>
                    </tr>
                </thead>
                <tbody>
                    <#list repairPartsList as repairPart>
                        <span>
                <tr>
                    <td class="text-center">${repairPart.repairId}</td>
                    <td class="text-center">${repairPart.partId}</td
                    <td class="text-center">${repairPart.Quantity}</td
                    <form action="/admin/repairs/addRepairParts/${repairPart.repairId}" method="GET">
                      <td class="text-center">
                          <button type="submit">
                            <span class="glyphicon glyphicon-cog"></span>
                        </button>
                        </td>
                        <td class="text-center">
                            <button type="submit" formaction="/admin/repairs/deleteRepairParts/${repairPart.repairID}" formmethod="GET" onclick="return confirm('Are you sure you want to delete this part from this repair?')">
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

   <hr></hr>
       <div>
           <center>
           <div class="container-fluid">
               <div class="row">
                   <div class="col-sm-12">
                       <h1 align="center">Available Parts</h1>
                       <form class="Search" class="form-horizontal" action="/admin/parts/search" method="GET" id="partSearchForm" name="partSearchForm">
                           <fieldset class="Norm">
                               <div class="form-group">
                                   <hr></hr>
                        <!--           <label for="filterInput">Filter</label> -->
                                   <input type="text" class="input-sm" name="filterInput" id="filterInput" placeholder="abc-123..."/>
                               </fieldset>
                               </div>
                               <br><br>
                           <div class="col-sm-12 controls">
                               <span>
                                   <button type="submit" id="btn-submit" class="btn btn-success btn-md">Filter</button>
                                   <button type="reset" id="btn-clear" class="btn btn-danger btn-md">Clear</button>
                               </span>
                           </div>
                       </form>
                   </div>
               </div>
           </div>
           </center>
       </div>

    <#if partsList??>
        <div class="table-responsive">
            <table id="resultsTable" class="table" class="table-hover">
                <thead>
                    <tr>
                        <th class="text-center">Part ID</th>
                        <th class="text-center">Part Name</th>
                        <th class="text-center">Part Cost</th>
                        <th class="text-center">Part Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <#list partsList as part>
                        <span>
                <tr>
                    <td class="text-center">${part.partId}</td>
                    <td class="text-center">${part.partName}</td
                    <td class="text-center">${part.partCost}</td
                    <td class="text-center">${part.quantity}</td
                    <form action="/admin/parts/searchParts/${part.partId}" method="GET">
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

