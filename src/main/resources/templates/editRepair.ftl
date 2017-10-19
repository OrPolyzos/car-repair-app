<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>Repairs</title>
</head>
<body align="center">
    <h1>${errorMessage!""}</h1>
    <h2>Edit Repair</h2>

    <form action="/admin/repairs/editRepair" method="POST" id="repairForm" name="repairForm">
        <h4><i>Repair's Details</i></h4>
        <@spring.bind "repairForm.repairID" />
        <input type="hidden" name="repairID" value="${repairForm.repairID!""}">
        <@spring.bind "repairForm.repairID" />
        <input type="hidden" name="addressID" value="${repairForm.addressID!""}">
        <#--bind this field with the registration form fields-->

        <@spring.bind "repairForm.repairDate"/>
        Scheduled Date: <input type="text" name="repairDate" id="repairDate" placeholder="2017/10/17" value="${repairForm.repairDate!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>

        <@spring.bind "repairForm.repairTime"/>
        Scheduled Time: <input type="text" name="repairTime" id="repairTime" placeholder="10:00" value="${repairForm.repairTime!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>
<br><br>
        <@spring.bind "repairForm.status"/>
        Repair Status: <input type="text" name="status" id="status" placeholder="Completed" value="${repairForm.status!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>

        <@spring.bind "repairForm.repairTypeID"/>
        Type of Repair: <input type="number" min=1 max=4 step=1 name="type" id="repairTypeID" placeholder="1" value="${repairForm.repairTypeID!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>

        <br><br>
        <@spring.bind "repairForm.tasks"/>
        Tasks:
        <input type="text" id="tasks" name="tasks" placeholder="Please write your Notes" value="${repairForm.tasks!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>

        <@spring.bind "repairForm.vehicleID"/>
        Vehicle to be Repaired: <input type="String" name="vehicleID" id="vehicleID" placeholder="ABE-1234" value="${repairForm.vehicleID!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>

 <br><br>
        <input type="submit" value="Edit">
    </form>

    <form action="/admin/repairs" method="GET">
        <input type="submit" value="Back">
    </form>

</body>
</html>