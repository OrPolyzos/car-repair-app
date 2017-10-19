<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>Repairs</title>
</head>
<body>
    <h1>${errorMessage!""}</h1>
    <h2>Add Repair</h2>

    <form action="/admin/repairs/create" method="POST" id="repairForm" name="repairForm">
        <h4><i>Repair's Details</i></h4>
        <#--bind this field with the registration form fields-->
        <@spring.bind "repairForm.repairID" />
        Repair ID: <input type="text" name="repairID" id="repairID" placeholder="1" value="${repairForm.repairID!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>

        <@spring.bind "repairForm.repairDate"/>
        Scheduled Date: <input type="text" name="repairDate" id="repairDate" placeholder="2017/10/17" value="${repairForm.repairDate!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>

        <br><br>

        <@spring.bind "repairForm.repairTime"/>
        Scheduled Time: <input type="text" name="repairTime" id="repairTime" placeholder="10:00" value="${repairForm.repairTime!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>

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
        <input type="submit" value="Create">
    </form>
    <hr></hr>
    <h2>Search Repair</h2>
    <form action="/admin/repairs/search" method="GET" id="repairSearchForm" name="repairSearchForm">
        <h4><i>Please provide the Repair ID, the Plate Number of the Vehicle or the Date</i></h4>
        <#--bind this field with the registration form fields-->
        <@spring.bind "repairSearchForm.repairID" />
        Repair ID: <input type="text" name="repairID" id="repairID" placeholder="1" value="${repairSearchForm.repairID!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>

        <@spring.bind "repairForm.repairDate"/>
        Scheduled Date: <input type="text" name="repairDate" id="repairDate" placeholder="2017/10/17" value="${repairForm.repairDate!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>

        <@spring.bind "repairSearchForm.vehicleID"/>
        Plate Number: <input type="text" name="vehicleID" id="vehicleID" placeholder="ABC-1234" value="${repairSearchForm.vehicleID!""}"/>
        <#list spring.status.errorMessages as error>
             <span>${error}</span>
        </#list>
        <br><br>
        <input type="submit" value="Search">
    </form>
<hr></hr>
    <h2>${searchNotFoundMessage!""}</h2>
    <#if repairsList??>
        <h3>Retrieved Repairs:</h3>
        <table>
            <tr>
                <th>Scheduled Date</th>
                <th>Scheduled Time</th>
                <th>Status</th>
                <th>Type</th>
                <th>Tasks</th>
                <th>Vehicle ID</th>
                <th>Edit Repair</th>
                <th>Delete Repair</th>
            </tr>
        <#list repairsList as repair>
        <span>
        <tr>
            <td>${repair.repairDate!}</td>
            <td>${repair.repairTime!}</td>
            <td>${repair.status!}</td>
            <td>${repair.repairTypeID!}</td>
            <td>${repair.tasks!}</td>
            <td>${repair.vehicleID!}</td>

            <form action="/admin/repairs/edit/${repair.repairID}" method="GET">
            <td> <input type="submit" value="Edit"> </td>
            <td>
                <button type="submit" formaction="/admin/repairs/delete/${repair.repairID}" formmethod="GET" onclick="return confirm('Are you sure?')">Delete</button>
            </td>

            </form>
        </tr>
        </span>
        </#list>
    </table>
    </#if>
    <script>
    function myFunction() {
        confirm("Are you sure to delete?");
    }
    </script>
</body>
</html>






