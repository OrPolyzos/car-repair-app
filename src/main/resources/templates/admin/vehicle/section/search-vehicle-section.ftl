<h2 class="subtitle">Search</h2>
<form class="search form-horizontal" action="/admin/vehicles/search" method="POST" id="vehicleSearchForm" name="vehicleSearchForm">
    <fieldset class="horizontal-1">
        <legend>Fill in Vehicle's Plate number or Owner's AFM</legend>
        <div class="col-md-4">
            <@spring.bind "vehicleSearchForm.vehicleID"/>
            <label for="vehicleID">Plate Number</label>
            <input type="text" class="form-control" name="vehicleID" id="vehicleID" placeholder="ABC-1234" value="${vehicleSearchForm.vehicleID!""}"/>
            <#list spring.status.errorMessages as error>
                <span class="errorMessage">${error}</span>
            </#list>
        </div>
        <div class="col-md-4">
            <@spring.bind "vehicleSearchForm.afm" />
            <label for="afm">AFM</label>
            <input type="number" class="form-control" name="afm" id="afm" placeholder="123456789" value="${vehicleSearchForm.afm!""}"/>
            <#list spring.status.errorMessages as error>
                <span class="errorMessage">${error}</span>
            </#list>
        </div>
        <div class="col-md-4">
            <label for="filterInput">Filter</label>
            <input type="text" class="form-control" name="filterInput" id="filterInput" placeholder="zzz-43..."/>
        </div>
    </fieldset>
    <div class="col-md-12 controls">
        <span>
            <button type="submit" id="btn-submit" class="btn btn-primary">Search</button>
            <button type="reset" id="btn-clear" class="btn btn-danger">Clear</button>
        </span>
    </div>
</form>
