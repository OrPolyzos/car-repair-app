<div class="col-12">
    <h2 class="subtitle">Add a part to this repair</h2>
    <form class="form-horizontal" action="/admin/repairs/${repairPartsForm.repairID!""}/parts" method="POST" id="repairPartsForm" name="repairPartsForm">
        <@spring.bind "repairPartsForm.repairID"/>
        <input type="hidden" name="repairID" value="${repairPartsForm.repairID!""}"/>
        <fieldset class="horizontal-1">
            <div class="form-group">
                <label for="partID">Part ID</label>
                <@spring.bind "repairPartsForm.partID"/>
                <input class="form-control" type="number" name="partID" id="partID" placeholder="1" value="${repairPartsForm.partID!""}"/>
                <#list spring.status.errorMessages as error>
                    <span class="errorMessage">${error}</span>
                </#list>
            </div>
        </fieldset>
        <fieldset class="horizontal-1">
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