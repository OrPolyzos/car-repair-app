<#import "/spring.ftl" as spring/>
<html>

<head>
    <title>Users</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../utilities.js"></script>
    <link rel="stylesheet" href="/../styles.css">

    <style>
        fieldset.Hor {
            float: left;
            width: 33.3%;
            padding: 20;
        }
        fieldset.Norm {
            width: 100%;
            padding: 20;
        }
    </style>

</head>

<body>
<#include "navbar.ftl">

    <div class="p5container">
        <img src="../Images/eXtremeRed.png">
    </div>
    <h1 class="errorRed">${errorMessage!""}</h1>
    <h2>Create User</h2>

    <br><br>

    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">

                <form  class="form-horizontal" action="/admin/users/create" method="POST" id="userForm" name="userForm">
                    <fieldset class="Hor">
                        <legend>User's Personal Details</legend>
                        <div class="form-group">
                            <label for="afm">AFM</label>
                            <@spring.bind "userForm.afm"/>
                            <input type="number" class="form-control" id="afm" name="afm" placeholder="123456789" value="${userForm.afm!""}" required/>
                            <#list spring.status.errorMessages as error>
                                <span class="errorRed">${error}</span>
                            </#list>
                        </div>
                        <div class="form-group">
                            <label for="firstName">Firstname</label>
                            <@spring.bind "userForm.firstName"/>
                            <input type="text" class="form-control" id="firstName" name="firstName" placeholder="John" value="${userForm.firstName!""}" required/>
                            <#list spring.status.errorMessages as error>
                                <span class="errorRed">${error}</span>
                            </#list>
                        </div>
                        <div class="form-group">
                            <label for="lastName">Lastname</label>
                            <@spring.bind "userForm.lastName"/>
                            <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Doe" value="${userForm.lastName!""}" required/>
                            <#list spring.status.errorMessages as error>
                                <span class="errorRed">${error}</span>
                            </#list>
                        </div>
                    </fieldset>
                    <fieldset class="Hor">
                        <legend>User's Credentials</legend>
                        <div class="form-group">
                            <label for="type">Type</label>
                            <@spring.bind "userForm.type"/>
                            <select class="form-control" id="type" name="type">
                                <option value="User">User</option>
                                <option value="Admin">Admin</option>
                            </select>
                            <#list spring.status.errorMessages as error>
                                <span class="errorRed">${error}</span>
                            </#list>
                        </div>

                        <div class="form-group">
                            <label for="email">Email</label>
                            <@spring.bind "userForm.email"/>
                            <input type="email" class="form-control" id="email" name="email" placeholder="john@doe.com" value="${userForm.email!""}" required/>
                            <#list spring.status.errorMessages as error>
                                <span class="errorRed">${error}</span>
                            </#list>
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <@spring.bind "userForm.password"/>
                            <input type="password" class="form-control" id="password" name="password" placeholder="p4$$w0rd" value="${userForm.password!""}" required/>
                            <#list spring.status.errorMessages as error>
                                <span class="errorRed">${error}</span>
                            </#list>
                        </div>
                    </fieldset>
                    <fieldset class="Hor">
                        <legend>User's Address Details</legend>
                        <div class="form-group">
                            <label for="addressStreet">Street</label>
                            <@spring.bind "userForm.addressStreet"/>
                            <input type="text" class="form-control" id="addressStreet" name="addressStreet" placeholder="Broadway" value="${userForm.addressStreet!""}" required/>
                            <#list spring.status.errorMessages as error>
                                <span class="errorRed">${error}</span>
                            </#list>
                        </div>
                        <div class="form-group">
                            <label for="addressNumber">Number</label>
                            <@spring.bind "userForm.addressNumber"/>
                            <input class="form-control" type="number" min=1 max=999 step=1 name="addressNumber" id="addressNumber" placeholder="28" value="${userForm.addressNumber!""}" required/>
                            <#list spring.status.errorMessages as error>
                                <span class="errorRed">${error}</span>
                            </#list>
                        </div>
                        <div class="form-group">
                            <label for="addressZipCode">Zip</label>
                            <@spring.bind "userForm.addressZipCode"/>
                            <input class="form-control" type="number" min=00001 max=99999 step=1 name="addressZipCode" id="addressZipCode" placeholder="15772" value="${userForm.addressZipCode!""}" required/>
                            <#list spring.status.errorMessages as error>
                                <span class="errorRed">${error}</span>
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

    <hr></hr>

    <div class="container-fluid">
        <div class="row">
            <h2>Search User</h2>
            <br><br>
            <form class="Search" class="form-horizontal" action="/admin/users/search" method="GET" id="userSearchForm" name="userSearchForm">
                <fieldset class="Norm">
                    <legend>Fill in User's AFM or Email</legend>
                    <div class="col-md-4">
                        <label for="afm">AFM</label>
                        <@spring.bind "userSearchForm.afm"/>
                        <input type="number" class="form-control" name="afm" id="firstName" placeholder="123456789" value="${userSearchForm.afm!""}"/>
                        <#list spring.status.errorMessages as error>
                            <span class="errorRed">${error}</span>
                        </#list>
                    </div>
                    <div class="col-md-4">
                        <label for="email">Email</label>
                        <@spring.bind "userSearchForm.email"/>
                        <input type="email" class="form-control" name="email" id="email" placeholder="john@doe.com" value="${userSearchForm.email!""}"/>
                        <#list spring.status.errorMessages as error>
                            <span class="errorRed">${error}</span>
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
        </div>
    </div>

    <h2>${searchNotFoundMessage!""}</h2>
    <#if userList??>
        <h3><u>Retrieved Users</u></h3>
        <div class="table-responsive">
            <table id="resultsTable" class="table" class="table-hover">
                <thead>
                <tr>
                    <th>User ID</th>
                    <th>AFM</th>
                    <th>Email</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Type</th>
                    <th>Street</th>
                    <th>Number</th>
                    <th>Zip Code</th>
                    <th>Edit</th>
                    <th>Delete</th>
                    <th>Vehicles</th>
                </tr>
                </thead>
                <tbody>
                <#list userList as user>
                        <span>
                    <tr>
                        <td>${user.userID!"Could not retrieve value!"}</td>
                        <td>${user.afm!"Could not retrieve value!"}</td>
                        <td>${user.email!"Could not retrieve value!"}</td>
                        <td>${user.firstName!"Could not retrieve value!"}</td>
                        <td>${user.lastName!"Could not retrieve value!"}</td>
                        <td>${user.type!"Could not retrieve value!"}</td>
                        <td>${user.addressStreet!"Could not retrieve value!"}</td>
                        <td>${user.addressNumber!"Could not retrieve value!"}</td>
                        <td>${user.addressZipCode!"Could not retrieve value!"}</td>
                        <form action="/admin/users/edit/${user.userID}" method="GET">
                        <td>
                            <button type="submit" class="btn btn-success">
                                <span class="glyphicon glyphicon-cog"></span>
                            </button>
                        </td>
                        <td>
                            <button type="submit" class="btn btn-danger" formaction="/admin/users/delete/${user.userID}" formmethod="POST" onclick="return confirm('Are you sure?')">
                                <span class="glyphicon glyphicon-remove"></span>
                            </button>
                        </td>
                        <td>
                            <button type="submit" class="btn btn-info" formaction="/admin/vehicles/${user.userID}" formmethod="GET">
                                <span class="glyphicon glyphicon-dashboard"></span>
                            </button>
                        </td>
                        </form>
                    </tr>
                        </span>
                </#list>
                </tbody>
            </table>
        </div>
    </#if>

    <#include "footer.ftl">

</body>
</html>
