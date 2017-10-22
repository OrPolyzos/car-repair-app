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
    <link rel="stylesheet" href="../styles.css">
    <!---- Trying to make some cool stuff with p5.js
    <script async src=https://CDN.JSDelivr.net/g/p5.js(p5.min.js+addons/p5.dom.js+addons/p5.sound.js)></script>
    <script defer src=/../sketch.js></script>
    <script defer src=/../particle.js></script> ---->

    <style>
        fieldset.Hor {
            float: left;
            width: 33.3%;
            padding: 20;
        }
        fieldset.Norm {
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
    <div>
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <h1 align="center">Create New User</h1>
                    <form  class="form-horizontal" action="/admin/users/create" method="POST" id="userForm" name="userForm">
                        <fieldset class="Hor">
                            <legend>User's Personal Details</legend>
                            <div class="form-group">
                                <label for="afm">AFM</label>
                                <@spring.bind "userForm.afm"/>
                                <input type="text" class="form-control" id="afm" name="afm" placeholder="123456789" value="${userForm.afm!""}"/>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                            </div>
                            <div class="form-group">
                                <label for="firstName">Firstname</label>
                                <@spring.bind "userForm.firstName"/>
                                <input type="text" class="form-control" id="firstName" name="firstName" placeholder="John" value="${userForm.firstName!""}"/>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                            </div>
                            <div class="form-group">
                                <label for="lastName">Lastname</label>
                                <@spring.bind "userForm.lastName"/>
                                <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Doe" value="${userForm.lastName!""}"/>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                            </div>
                        </fieldset>
                        <fieldset class="Hor">
                            <legend>User's Credentials</legend>
                            <div class="form-group">
                                <label for="email">Email</label>
                                <@spring.bind "userForm.email"/>
                                <input type="text" class="form-control" id="email" name="email" placeholder="john@doe.com" value="${userForm.email!""}"/>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                            </div>
                            <div class="form-group">
                                <label for="password">Password</label>
                                <@spring.bind "userForm.password"/>
                                <input type="text" class="form-control" id="password" name="password" placeholder="p4$$w0rd" value="${userForm.lastName!""}"/>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                            </div>
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
                        </fieldset>
                        <fieldset class="Hor">
                            <legend>User's Address Details</legend>
                            <div class="form-group">
                                <label for="addressStreet">Street</label>
                                <@spring.bind "userForm.addressStreet"/>
                                <input type="text" class="form-control" id="addressStreet" name="addressStreet" placeholder="Broadway" value="${userForm.addressStreet!""}"/>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                            </div>
                            <div class="form-group">
                                <label for="addressNumber">Number</label>
                                <@spring.bind "userForm.addressNumber"/>
                                <input class="form-control" type="number" min=1 max=999 step=1 name="addressNumber" id="addressNumber" placeholder="28" value="${userForm.addressNumber!""}"/>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed"class="errorRed">${error}</span>
                                </#list>
                            </div>
                            <div class="form-group">
                                <label for="addressZipCode">Zip</label>
                                <@spring.bind "userForm.addressZipCode"/>
                                <input class="form-control" type="number" min=00001 max=99999 step=1 name="addressZipCode" id="addressZipCode" placeholder="15772" value="${userForm.addressZipCode!""}"/>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                            </div>
                        </fieldset>
                        <div class="col-sm-12 controls">
                        <span>
                            <button type="submit" id="btn-submit" class="btn btn-success btn-md">Create</button>
                            <button type="reset" id="btn-clear" class="btn btn-danger btn-md">Clear</button>
                        </span>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <hr></hr>
    <div>
        <center>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-12">
                    <h1 align="center">Search</h1>
                    <form class="Search" class="form-horizontal" action="/admin/users/search" method="GET" id="userSearchForm" name="userSearchForm">
                        <fieldset class="Norm">
                            <legend>Fill in User's AFM or Email</legend>
                            <div class="form-group">
                                <label for="afm">AFM</label>
                                <@spring.bind "userSearchForm.afm"/>
                                <input type="text" class="input-sm" name="afm" id="firstName" placeholder="123456789" value="${userSearchForm.afm!""}"/>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                                <label for="email">Email</label>
                                <@spring.bind "userSearchForm.email"/>
                                <input type="email" class="input-sm" name="email" id="email" placeholder="john@doe.com" value="${userSearchForm.email!""}"/>
                                <#list spring.status.errorMessages as error>
                                    <span class="errorRed">${error}</span>
                                </#list>
                                <br><br>
                                <label for="filterInput">Filter</label>
                                <input type="text" class="input-sm" name="filterInput" id="filterInput" placeholder="john"/>
                            </div>
                        </fieldset>
                        <div class="col-sm-12 controls">
                            <span>
                                <button type="submit" id="btn-submit" class="btn btn-success btn-md">Search</button>
                                <button type="reset" id="btn-clear" class="btn btn-danger btn-md">Clear</button>
                            </span>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        </center>
    </div>
    <h2>${searchNotFoundMessage!""}</h2>
    <#if userList??>
        <h3 class="text-center"><u>Retrieved Users</u></h3>
        <div class="table-responsive">
            <table id="resultsTable" class="table" class="table-hover">
                <thead>
                    <tr>
                        <th class="text-center">User ID</th>
                        <th class="text-center">AFM</th>
                        <th class="text-center">Email</th>
                        <th class="text-center">First Name</th>
                        <th class="text-center">Last Name</th>
                        <th class="text-center">Type</th>
                        <th class="text-center">Street</th>
                        <th class="text-center">Number</th>
                        <th class="text-center">Zip Code</th>
                        <th class="text-center">Edit</th>
                        <th class="text-center">Delete</th>
                        <th class="text-center">Vehicles</th>
                    </tr>
                </thead>
                <tbody>
                    <#list userList as user>
                        <span>
                <tr>
                    <td class="text-center">${user.userID}</td>
                    <td class="text-center">${user.afm}</td>
                    <td class="text-center">${user.email}</td>
                    <td class="text-center">${user.firstName}</td>
                    <td class="text-center">${user.lastName}</td>
                    <td class="text-center">${user.type}</td>
                    <td class="text-center">${user.addressStreet}</td>
                    <td class="text-center">${user.addressNumber}</td>
                    <td class="text-center">${user.addressZipCode}</td>
                    <form action="/admin/users/edit/${user.userID}" method="GET">
                      <td class="text-center">
                          <button type="submit">
                            <span class="glyphicon glyphicon-cog"></span>
                        </button>
                        </td>
                        <td class="text-center">
                            <button type="submit" formaction="/admin/users/delete/${user.userID}" formmethod="GET" onclick="return confirm('Are you sure you want to delete this user?')">
                                <span class="glyphicon glyphicon-remove"></span>
                            </button>
                        </td>
                        <td class="text-center">
                            <button type="submit" formaction="/admin/vehicles/${user.userID}" formmethod="GET">
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
    <!---footer>
        <p>&copy; RGCS 2017 - The Auto Repair Group.</p>
    </footer--->
</body>
</html>

<!-- <article id="right_content" class="page_subsection">
< h2 > Χορηγοί < /h2> < iframe src = ""
scrolling = "no" > < /iframe> < hr >
< iframe src = ""
scrolling = "no" > < /iframe> < hr >
< iframe src = ""
scrolling = "no" > < /iframe> < /article> -->