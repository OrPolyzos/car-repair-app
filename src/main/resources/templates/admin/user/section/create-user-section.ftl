<h2 class="subtitle">Create</h2>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <form class="form-horizontal" action="/admin/users" method="POST" id="userForm" name="userForm">
                <fieldset class="horizontal-3">
                    <legend>Personal Details</legend>
                    <div class="form-group">
                        <label for="afm">AFM</label>
                        <@spring.bind "userForm.afm"/>
                        <input type="number" class="form-control" id="afm" name="afm" placeholder="123456789" value="${userForm.afm!""}" required/>
                        <#list spring.status.errorMessages as error>
                            <span class="errorMessage">${error}</span>
                        </#list>
                    </div>
                    <div class="form-group">
                        <label for="firstName">Firstname</label>
                        <@spring.bind "userForm.firstName"/>
                        <input type="text" class="form-control" id="firstName" name="firstName" placeholder="John" value="${userForm.firstName!""}" required/>
                        <#list spring.status.errorMessages as error>
                            <span class="errorMessage">${error}</span>
                        </#list>
                    </div>
                    <div class="form-group">
                        <label for="lastName">Lastname</label>
                        <@spring.bind "userForm.lastName"/>
                        <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Doe" value="${userForm.lastName!""}" required/>
                        <#list spring.status.errorMessages as error>
                            <span class="errorMessage">${error}</span>
                        </#list>
                    </div>
                </fieldset>
                <fieldset class="horizontal-3">
                    <legend>Credentials</legend>
                    <div class="form-group">
                        <label for="type">Type</label>
                        <@spring.bind "userForm.type"/>
                        <select class="form-control" id="type" name="type">
                            <option value="User">User</option>
                            <option value="Admin">Admin</option>
                        </select>
                        <#list spring.status.errorMessages as error>
                            <span class="errorMessage">${error}</span>
                        </#list>
                    </div>

                    <div class="form-group">
                        <label for="email">Email</label>
                        <@spring.bind "userForm.email"/>
                        <input type="email" class="form-control" id="email" name="email" placeholder="john@doe.com" value="${userForm.email!""}" required/>
                        <#list spring.status.errorMessages as error>
                            <span class="errorMessage">${error}</span>
                        </#list>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <@spring.bind "userForm.password"/>
                        <input type="password" class="form-control" id="password" name="password" placeholder="p4$$w0rd" value="${userForm.password!""}" required/>
                        <#list spring.status.errorMessages as error>
                            <span class="errorMessage">${error}</span>
                        </#list>
                    </div>
                </fieldset>
                <fieldset class="horizontal-3">
                    <legend>Address Details</legend>
                    <div class="form-group">
                        <label for="addressStreet">Street</label>
                        <@spring.bind "userForm.addressStreet"/>
                        <input type="text" class="form-control" id="addressStreet" name="addressStreet" placeholder="Broadway" value="${userForm.addressStreet!""}" required/>
                        <#list spring.status.errorMessages as error>
                            <span class="errorMessage">${error}</span>
                        </#list>
                    </div>
                    <div class="form-group">
                        <label for="addressNumber">Number</label>
                        <@spring.bind "userForm.addressNumber"/>
                        <input class="form-control" type="number" min=1 max=999 step=1 name="addressNumber" id="addressNumber" placeholder="28" value="${userForm.addressNumber!""}" required/>
                        <#list spring.status.errorMessages as error>
                            <span class="errorMessage">${error}</span>
                        </#list>
                    </div>
                    <div class="form-group">
                        <label for="addressZipCode">Zip</label>
                        <@spring.bind "userForm.addressZipCode"/>
                        <input class="form-control" type="number" min=00001 max=99999 step=1 name="addressZipCode" id="addressZipCode" placeholder="15772" value="${userForm.addressZipCode!""}" required/>
                        <#list spring.status.errorMessages as error>
                            <span class="errorMessage">${error}</span>
                        </#list>
                    </div>
                </fieldset>
                <div class="col-md-12 controls">
                    <span>
                        <button type="submit" id="btn-submit" class="btn btn-success btn-md">Create</button>
                        <button type="reset" id="btn-clear" class="btn btn-danger btn-md">Clear</button>
                    </span>
                </div>
            </form>
        </div>
    </div>
</div>
