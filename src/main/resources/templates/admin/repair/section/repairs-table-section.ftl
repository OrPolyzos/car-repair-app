<#if repairList?has_content>
    <div class="table-responsive">
        <table id="resultsTable" class="table table-hover">
            <thead>
            <tr>
                <th>Repair ID</th>
                <th>Scheduled DateTime</th>
                <th>Status</th>
                <th>Type</th>
                <th>Total Cost</th>
                <th>Tasks</th>
                <th>Vehicle ID</th>
                <th>Edit Repair</th>
                <th>Delete Repair</th>
                <th>Parts</th>
            </tr>
            </thead>
            <tbody>
            <#list repairList as repair>
                <tr>
                    <td>${repair.id}</td>
                    <td>${repair.repairDateTime}</td>
                    <td>${repair.repairStatus}</td>
                    <td>${repair.repairType.repairTypeDescription}</td>
                    <td>${repair.repairTotalCost}</td>
                    <td>${repair.repairTasks}</td>
                    <td>${repair.vehicleID}</td>
                    <td>
                        <form action="/admin/repairs/${repair.id}/edit" method="GET">
                            <button type="submit" class="btn btn-success btn-md">
                                <span class="glyphicon glyphicon-cog"></span>
                            </button>
                        </form>
                    </td>
                    <td>
                        <form action="/admin/repairs/${repair.id}/delete" method="POST">
                            <button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure?')">
                                <span class="glyphicon glyphicon-remove"></span>
                            </button>
                        </form>
                    </td>
                    <td>
                        <form action="/admin/repairs/${repair.id}/parts" method="GET">
                            <button type="submit" class="btn btn-success">
                                <span class="glyphicon glyphicon-plus"></span>
                            </button>
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</#if>