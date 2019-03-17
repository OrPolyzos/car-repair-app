<h2 class="subtitle">Search</h2>
<form class="search form-horizontal" action="/admin/repairs/search" method="POST" id="repairSearchForm" name="repairSearchForm">
    <fieldset class="horizontal-1">
        <legend>Please provide the Repair ID, the Plate Number of the Vehicle or the Date</legend>
        <div class="col-md-6">
            <@spring.bind "repairSearchForm.repairID"/>
            <label for="repairID">Repair ID</label>
            <input type="number" class="form-control" name="repairID" id="repairID" placeholder="1" value="${repairSearchForm.repairID!""}"/>
            <#list spring.status.errorMessages as error>
                <span class="errorMessage">${error}</span>
            </#list>
        </div>
        <div class="col-md-6">
            <@spring.bind "repairSearchForm.repairVehicleID"/>
            <label for="repairVehicleID">Vehicle's Plate Number</label>
            <input type="text" class="form-control" name="repairVehicleID" id="repairVehicleID" placeholder="ABC-1234" value="${repairSearchForm.repairVehicleID!""}"/>
            <#list spring.status.errorMessages as error>
                <span class="errorMessage">${error}</span>
            </#list>
        </div>
        <div class="col-md-6">
            <@spring.bind "repairSearchForm.repairDateTimeStart"/>
            <label for="repairDateTimeStart">Scheduled DateTime Start</label>
            <input type="datetime-local" class="form-control" name="repairDateTimeStart" id="repairDateTimeStart" value="${repairSearchForm.repairDateTimeStart!""}"/>
            <#list spring.status.errorMessages as error>
                <span class="errorMessage">${error}</span>
            </#list>
        </div>
        <div class="col-md-6">
            <@spring.bind "repairSearchForm.repairDateTimeEnd"/>
            <label for="repairDateTimeEnd">Scheduled DateTime End</label>
            <input type="datetime-local" class="form-control" name="repairDateTimeEnd" id="repairDateTimeEnd" value="${repairSearchForm.repairDateTimeEnd!""}"/>
            <#list spring.status.errorMessages as error>
                <span class="errorMessage">${error}</span>
            </#list>
        </div>
        <div class="col-md-6 col-md-offset-3">
            <label for="filterInput">Filter</label>
            <input type="text" class="form-control" name="filterInput" id="filterInput" placeholder="abc-123..."/>
        </div>
    </fieldset>
    <div class="col-md-12 controls">
        <span>
            <button type="submit" id="btn-submit" class="btn btn-primary">Search</button>
            <button type="reset" id="btn-clear" class="btn btn-danger">Clear</button>
        </span>
    </div>
</form>