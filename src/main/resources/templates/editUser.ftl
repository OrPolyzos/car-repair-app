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
    <!---- Trying to make some cool stuff with p5.js
    <script async src=https://CDN.JSDelivr.net/g/p5.js(p5.min.js+addons/p5.dom.js+addons/p5.sound.js)></script>
    <script defer src=/../sketch.js></script>
    <script defer src=/../particle.js></script> ------>

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
        <img src="/../Images/eXtremeRed.png">
    </div>
    <h1 class="errorRed">${errorMessage!""}</h1>
    <div>
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <h1 align="center">Edit User</h1>
                    <form  class="form-horizontal" action="/admin/users/editUser" method="POST" id="userForm" name="userForm">
                        <fieldset class="Hor">
                            <@spring.bind "userForm.userID"/>
                            <input type="hidden" id="userID" name="userID" value="${userForm.userID!""}"/>
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
                                <input type="text" class="form-control" id="password" name="password" placeholder="p4$$w0rd" value="${userForm.password!""}"/>
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
                                    <span "class="errorRed">${error}</span>
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
                            <button type="submit" id="btn-submit" class="btn btn-success btn-md">Edit</button>
                            <button type="reset" id="btn-clear" class="btn btn-danger btn-md">Clear</button>
                        </span>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>