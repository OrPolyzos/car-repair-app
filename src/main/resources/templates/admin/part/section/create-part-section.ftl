<h2 class="subtitle">Create</h2>
<form class="form-horizontal" action="/admin/parts" method="POST" id="partForm" name="partForm">
    <legend>Parts Details</legend>
    <div class="col-md-6">
        <fieldset>
            <#--bind this field with the registration form fields-->
            <@spring.bind "partForm.partName"/>
            <label for="partName">Part Name</label>
            <input type="text" class="form-control " name="partName" id="partName" placeholder="turbocharger" value="${partForm.partName!""}"/>
            <#list spring.status.errorMessages as error>
                <span class="errorMessage">${error}</span>
            </#list>
        </fieldset>
    </div>
    <div class="col-md-6">
        <fieldset>
            <label for="partPrice">Price</label>
            <@spring.bind "partForm.partPrice"/>
            <input type="number" class="form-control" name="partPrice" id="partPrice" placeholder="100" value="${partForm.partPrice!""}"/>
            <#list spring.status.errorMessages as error>
                <span class="errorMessage">${error}</span>
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