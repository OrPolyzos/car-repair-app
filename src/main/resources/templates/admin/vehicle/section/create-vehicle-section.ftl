<h2 class="subtitle">Create</h2>
<div class="col-md-12">
    <form class="form-horizontal" action="/admin/vehicles" method="POST" id="vehicleForm" name="vehicleForm">
        <fieldset class="horizontal-3">
            <div class="form-group">
                <label for="vehicleID">Plate Number</label>
                <@spring.bind "vehicleForm.vehicleID"/>
                <input type="text" class="form-control " name="vehicleID" id="vehicleID" placeholder="ABC-1234" value="${vehicleForm.vehicleID!""}"/>
                <#list spring.status.errorMessages as error>
                    <span class="errorMessage">${error}</span>
                </#list>
            </div>
            <div class="form-group">
                <label for="afm">Owner's AFM</label>
                <@spring.bind "vehicleForm.afm"/>
                <input type="number" class="form-control" name="afm" id="afm" placeholder="123456789" value="${vehicleForm.afm!""}"/>
                <#list spring.status.errorMessages as error>
                    <span class="errorMessage">${error}</span>
                </#list>
            </div>
        </fieldset>
        <fieldset class="horizontal-3">
            <div class="form-group">
                <label for="brand">Brand</label>
                <@spring.bind "vehicleForm.brand"/>
                <input type="text" class="form-control" name="brand" id="brand" placeholder="Ford" value="${vehicleForm.brand!""}"/>
                <#list spring.status.errorMessages as error>
                    <span class="errorMessage">${error}</span>
                </#list>
            </div>
            <div class="form-group">
                <label for="model">Model</label>
                <@spring.bind "vehicleForm.model"/>
                <input type="text" class="form-control" name="model" id="model" placeholder="Focus" value="${vehicleForm.model!""}"/>
                <#list spring.status.errorMessages as error>
                    <span class="errorMessage">${error}</span>
                </#list>
            </div>
            <div class="form-group">
                <label for="year">Year</label>
                <@spring.bind "vehicleForm.year"/>
                <input type="number" class="form-control" min=1950 max=2100 step=1 name="year" id="year" placeholder="2001" value="${vehicleForm.year!""}"/>
                <#list spring.status.errorMessages as error>
                    <span class="errorMessage">${error}</span>
                </#list>
            </div>
        </fieldset>
        <fieldset class="horizontal-3">
            <div class="form-group">
                <label for="fuelType">Fuel</label>
                <@spring.bind "vehicleForm.fuelType"/>
                <select class="form-control" id="fuelType" name="fuelType">
                    <option value="Petrol">Petrol</option>
                    <option value="Diesel">Diesel</option>
                </select>
                <#list spring.status.errorMessages as error>
                    <span class="errorMessage">${error}</span>
                </#list>
            </div>
            <div class="form-group">
                <label for="color">Color</label>
                <@spring.bind "vehicleForm.color"/>
                <input type="text" class="form-control" name="color" id="color" placeholder="Red" value="${vehicleForm.color!""}"/>
                <#list spring.status.errorMessages as error>
                    <span class="errorMessage">${error}</span>
                </#list>
            </div>
        </fieldset>
        <div class="col-md-12 controls">
            <span>
                <button type="submit" id="btn-submit" class="btn btn-success">Create</button>
                <button type="reset" id="btn-clear" class="btn btn-danger">Clear</button>
            </span>
        </div>
    </form>
</div>
