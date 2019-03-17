<h2 class="subtitle">Search</h2>
<form class="form-horizontal search" action="/admin/users/search" method="POST" id="userSearchForm" name="userSearchForm">
    <fieldset class="horizontal-1">
        <legend>Fill in User's AFM or Email</legend>
        <div class="col-md-4">
            <label for="afm">AFM</label>
            <@spring.bind "userSearchForm.afm"/>
            <input type="number" class="form-control" name="afm" id="afm" placeholder="123456789" value="${userSearchForm.afm!""}"/>
            <#list spring.status.errorMessages as error>
                <span class="errorMessage">${error}</span>
            </#list>
        </div>
        <div class="col-md-4">
            <label for="email">Email</label>
            <@spring.bind "userSearchForm.email"/>
            <input type="email" class="form-control" name="email" id="email" placeholder="john@doe.com" value="${userSearchForm.email!""}"/>
            <#list spring.status.errorMessages as error>
                <span class="errorMessage">${error}</span>
            </#list>
        </div>
        <div class="col-md-4">
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