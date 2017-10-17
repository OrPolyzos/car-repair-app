<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>Users</title>
</head>
<body align="center">
    <h1>${errorMessage!""}</h1>
    <h2>Edit User</h2>

    <form action="/admin/users/editUser" method="POST" id="userForm" name="userForm">
        <h4><i>User's Personal Details</i></h4>
        <@spring.bind "userForm.userID" />
        <input type="hidden" name="userID" value="${userForm.userID!""}">
        <@spring.bind "userForm.userID" />
        <input type="hidden" name="addressID" value="${userForm.addressID!""}">
        <#--bind this field with the registration form fields-->
        <@spring.bind "userForm.firstName" />
        First Name: <input type="text" name="firstName" id="firstName" placeholder="John" value="${userForm.firstName!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>

        <@spring.bind "userForm.afm"/>
        AFM: <input type="text" name="afm" id="afm" placeholder="123456789" value="${userForm.afm!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>

        <br><br>

        <@spring.bind "userForm.lastName"/>
        Last Name: <input type="text" name="lastName" id="lastName" placeholder="Doe" value="${userForm.lastName!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>

        <@spring.bind "userForm.email"/>
        Email: <input type="text" name="email" id="email" placeholder="john@doe.com" value="${userForm.email!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>

        <br><br>

        <@spring.bind "userForm.type"/>
        Type:
        <select id="type" name="type">
            <option value="User">User</option>
            <option value="Admin">Admin</option>
        </select>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>

        <@spring.bind "userForm.password"/>
        Password: <input type="password" name="password" id="password" placeholder="p4$$w0rd" value="${userForm.password!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>

        <h4><i>User's Address Details</i></h4>
        <@spring.bind "userForm.addressStreet"/>
        Street: <input type="text" name="addressStreet" id="addressStreet" placeholder="Bakers" value="${userForm.addressStreet!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>

        <@spring.bind "userForm.addressNumber"/>
        Number: <input type="number"  min=1 max=999 step=1 name="addressNumber" id="addressNumber" placeholder="26" value="${userForm.addressNumber!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>

        <@spring.bind "userForm.addressZipCode"/>
        Zip Code: <input type="number"  min=00001 max=99999 step=1 name="addressZipCode" id="addressZipCode" placeholder="15772" value="${userForm.addressZipCode!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>
        <br><br>
        <input type="submit" value="Edit">
    </form>

    <form action="/admin/users" method="GET">
        <input type="submit" value="Back">
    </form>

</body>
</html>