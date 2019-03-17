<h2 class="subtitle">Create</h2>
<div class="col-md-12">
    <form class="form-horizontal" action="/admin/repairs/create" method="POST" id="repairForm" name="repairForm">
        <legend>Repair's Details</legend>
        <fieldset class="horizontal-2">
            <div class="form-group">
                <@spring.bind "repairForm.repairVehicleID"/>
                <label for="repairVehicleID">Vehicle's Plate Number</label>
                <input type="text" class="form-control"name="repairVehicleID" id="repairVehicleID" placeholder="ABE-1234" value="${repairForm.repairVehicleID!""}"/>
                <#list spring.status.errorMessages as error>
                    <span class="errorMessage">${error}</span>
                </#list>
            </div>
            <div class="form-group">
                <@spring.bind "repairForm.repairTypeID"/>
                <label for="repairTypeID">Type Service</label>
                <select class="form-control" id="repairTypeID" name="repairTypeID">
                    <option value="1">Small Service</option>
                    <option value="2">Great Service</option>
                </select>
                <#list spring.status.errorMessages as error>
                    <span class="errorMessage">${error}</span>
                </#list>
            </div>
            <div class="form-group">
                <@spring.bind "repairForm.repairStatus"/>
                <label for="repairStatus">Status</label>
                <select class="form-control" id="repairStatus" name="repairStatus">
                    <option value="Pending">Pending</option>
                    <option value="In progress">In progress</option>
                    <option value="Completed">Completed</option>
                </select>
                <#list spring.status.errorMessages as error>
                    <span class="errorMessage">${error}</span>
                </#list>
            </div>
        </fieldset>
        <fieldset class="horizontal-2">
            <div class="form-group">
                <label for="repairDateTime">Scheduled Date & Time</label>
                <@spring.bind "repairForm.repairDateTime"/>
                <input class="form-control" type="datetime-local" name="repairDateTime" id="repairDateTime" value="${repairForm.repairDateTime!""}"/>
                <#list spring.status.errorMessages as error>
                    <span class="errorMessage">${error}</span>
                </#list>
            </div>
            <div class="form-group">
                <@spring.bind "repairForm.repairTasks"/>
                <label for="repairTasks">Tasks</label>
                <textarea rows=4 cols=50 id="repairTasks" class="form-control" name="repairTasks" placeholder="Engine oil change, Oil filter replacement, Spark plugs Replacement" value="${repairForm.repairTasks!""}">${repairForm.repairTasks!""}</textarea>
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