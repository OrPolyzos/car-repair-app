<h2 class="subtitle">Search Part</h2>
<form class="search form-horizontal" action="/admin/parts/search" method="POST" id="partSearchForm" name="partSearchForm">
    <fieldset class="horizontal-1">
        <legend>Fill in Part's Fields</legend>
        <div class="col-md-4">
            <label for=partID>Part ID</label>
            <@spring.bind "partSearchForm.partID"/>
            <input type="number" class="form-control" name="partID" id="partID" value="${partSearchForm.partID!""}"/>
            <#list spring.status.errorMessages as error>
                <span class="errorMessage">${error}</span>
            </#list>
        </div>
        <div class="col-md-4">
            <label for=partPriceStart>Start Price</label>
            <@spring.bind "partSearchForm.partPriceStart"/>
            <input type="number" class="form-control" name="partPriceStart" id="partPriceStart" value="${partSearchForm.partPriceStart!""}"/>
            <#list spring.status.errorMessages as error>
                <span class="errorMessage">${error}</span>
            </#list>
        </div>
        <div class="col-md-4">
            <label for=partPriceEnd>End Price</label>
            <@spring.bind "partSearchForm.partPriceEnd"/>
            <input type="number" class="form-control" name="partPriceEnd" id="partPriceEnd" value="${partSearchForm.partPriceEnd!""}"/>
            <#list spring.status.errorMessages as error>
                <span class="errorMessage">${error}</span>
            </#list>
        </div>
        <div class="col-md-6 col-md-offset-3">
            <label for="filterInput">Filter</label>
            <input type="text" class="form-control" name="filterInput" id="filterInput" placeholder="john"/>
        </div>
    </fieldset>
    <div class="col-md-12 controls">
        <span>
            <button type="submit" id="btn-submit" class="btn btn-primary">Search</button>
            <button type="reset" id="btn-clear" class="btn btn-danger">Clear</button>
        </span>
    </div>
</form>