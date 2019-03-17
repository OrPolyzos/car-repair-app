<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>Edit Part</title>
    <#include "../common/head-section.ftl"/>
</head>
<body>
<#include "../common/navbar.ftl">
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <form class="form-horizontal" action="/admin/parts/${partForm.partID}/edit" method="POST" id="partForm" name="partForm">
                <h4>Part Details</h4>
                <@spring.bind "partForm.partID"/>
                <input type="hidden" id="partID" name="partID" value="${partForm.partID!""}">
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
                        <span class="errorMessage">${error}</span>
                    </#list>
                </div>
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
                        <span class="errorMessage">${error}</span>
                    </#list>
                </div>
                <div class="col-md-12 controls">
                    <span>
                        <button type="submit" class="btn btn-success">Save</button>
                        <button type="reset" id="btn-clear" class="btn btn-danger">Clear</button>
                    </span>
                </div>
            </form>
        </div>
    </div>
</div>
<#include "../common/scripts-section.ftl"/>
</body>
</html>
