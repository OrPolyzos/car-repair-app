<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>Edit Vehicle</title>
    <#include "../common/head-section.ftl"/>
</head>
<body>
<#include "../common/navbar.ftl">
<h3>Vehicle's Details</h3>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <form action="/admin/vehicles/${vehicleForm.vehicleID}/edit" method="POST" id="vehicleForm" name="vehicleForm">
                <@spring.bind "vehicleForm.userID" />
                <input type="hidden" name="userID" value="${vehicleForm.userID}">
                <@spring.bind "vehicleForm.afm" />
                <input type="hidden" name="afm" value="${vehicleForm.afm}">
                <@spring.bind "vehicleForm.vehicleID" />
                <input type="hidden" name="vehicleID" value="${vehicleForm.vehicleID}">
                <h4>Plate Number: ${vehicleForm.vehicleID}</h4>
                <h4>Owner's AFM: ${vehicleForm.afm}</h4>
                <div class="form-group">
                    <@spring.bind "vehicleForm.brand"/>
                    <label for="brand">Brand</label>
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-apple"></i>
                        </span>
                        <input type="text" class="form-control" name="brand" id="brand" placeholder="Ford" value="${vehicleForm.brand!""}" required/>
                    </div>
                    <#list spring.status.errorMessages as error>
                        <span class="errorMessage">${error}</span>
                    </#list>
                </div>
                <div class="form-group">
                    <@spring.bind "vehicleForm.model"/>
                    <label for="model">Model</label>
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-dashboard"></i>
                        </span>
                        <input type="text" class="form-control" name="model" id="model" placeholder="Focus" value="${vehicleForm.model!""}" required/>
                    </div>
                    <#list spring.status.errorMessages as error>
                        <span class="errorMessage">${error}</span>
                    </#list>
                </div>
                <div class="form-group">
                    <@spring.bind "vehicleForm.year"/>
                    <label for="year">Year</label>
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-calendar"></i>
                        </span>
                        <input type="number" class="form-control" min=1950 max=2017 step=1 name="year" id="year" placeholder="2001" value="${vehicleForm.year!""}" required/>
                    </div>
                    <#list spring.status.errorMessages as error>
                        <span class="errorMessage">${error}</span>
                    </#list>
                </div>
                <div class="form-group">
                    <@spring.bind "vehicleForm.fuelType"/>
                    <label for="fuelType">Fuel</label>
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-tint"></i>
                        </span>
                        <select class="form-control" id="fuelType" name="fuelType">
                            <option value="Petrol">Petrol</option>
                            <option value="Diesel">Diesel</option>
                        </select>
                    </div>
                    <#list spring.status.errorMessages as error>
                        <span class="errorMessage">${error}</span>
                    </#list>
                </div>
                <div class="form-group">
                    <@spring.bind "vehicleForm.color"/>
                    <label for="color">Color</label>
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-adjust"></i>
                        </span>
                        <input type="String" class="form-control" name="color" id="color" placeholder="Red" value="${vehicleForm.color!""}" required/>
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