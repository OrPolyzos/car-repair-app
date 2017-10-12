<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>Users</title>
</head>
<body>
    <h1>${errorMessage!""}</h1>
    <h2>${actionText!"Create new User"}</h2>
    <form action="/admin/users" method="post" id="userForm" name="userForm">
        <h4><i>User's Personal Details</i></h4>
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
        Password: <input type="password" name="password" id="password" placeholder="p4$$w0rd"/>
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
        Number: <input type="number" name="addressNumber" id="addressNumber" placeholder="26" value="${userForm.addressNumber!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>

        <@spring.bind "userForm.addressZipCode"/>
        Zip Code: <input type="number" name="addressZipCode" id="addressZipCode" placeholder="15772" value="${userForm.addressZipCode!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>
        <br><br>
        <input type="submit" value="Create">
    </form>
    <hr></hr>
    <h2>Search User</h2>
    <form action="/registerUserForm" method="post" id="searchForm" name="searchForm">
        <h4><i>Fill in User's AFM and/or Email</i></h4>
        <#--bind this field with the registration form fields-->
        <@spring.bind "userForm.afm" />
        AFM: <input type="text" name="afm" id="firstName" placeholder="123456789"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>

        <@spring.bind "userForm.email"/>
        Email: <input type="text" name="email" id="email" placeholder="john@doe.com"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>

        <br><br>
        <input type="submit" value="Search">
    </form>
<hr></hr>
    <h2>${searchErrorMessage!""}</h2>

    <#if userList??>
        <h3>Retrieved Users:</h3>
        <#list userList as user>
        <span>
        <form action="/admin/users/delete/${user.afm}" method="POST">
            <a href="smth">${user.firstName} ${user.lastName} with AFM: ${user.afm} and Email: ${user.email}</a>
        <input type="submit" onclick="return confirm('Are you sure?')" value="Delete" />
        </span>
        </#list>
    </#if>
    <script>
    function myFunction() {
        confirm("Are you sure to delete?");
    }
    </script>

</body>
</html>


