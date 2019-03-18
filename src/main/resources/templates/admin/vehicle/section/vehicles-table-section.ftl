<#if vehicleList?has_content>
    <div class="table-responsive">
        <table id="resultsTable" class="table table-hover">
            <thead>
            <tr>
                <th>Plate Number</th>
                <th>Brand</th>
                <th>Model</th>
                <th>Year</th>
                <th>Color</th>
                <th>Fuel</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <#list vehicleList as vehicle>
                <tr>
                    <td>${vehicle.id}</td>
                    <td>${vehicle.brand}</td>
                    <td>${vehicle.model}</td>
                    <td>${vehicle.year}</td>
                    <td>${vehicle.color}</td>
                    <td>${vehicle.fuelType}</td>
                    <td>
                        <form action="/admin/vehicles/${vehicle.id}/edit" method="GET">
                            <button type="submit" value="Edit" class="btn btn-success">
                                <span class="glyphicon glyphicon-cog"></span>
                            </button>
                        </form>
                    <td>
                        <form action="/admin/vehicles/${vehicle.id}/delete" method="POST">
                            <button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure?')">
                                <span class="glyphicon glyphicon-remove"></span>
                            </button>
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</#if>