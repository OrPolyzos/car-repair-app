<#if partList?has_content>
    <div class="table-responsive">
        <table id="resultsTable" class="table table-hover">
            <thead>
            <tr>
                <th>Part ID</th>
                <th>Part Name</th>
                <th>Part Price</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <#list partList as part>
                <tr>
                    <td>${part.id}</td>
                    <td>${part.partName}</td>
                    <td>${part.partPrice}</td>
                    <td>
                        <form action="/admin/parts/${part.id}/edit" method="GET">
                            <button type="submit" class="btn btn-success">
                                <span class="glyphicon glyphicon-cog"></span>
                            </button>
                        </form>
                    </td>
                    <td>
                        <form action="/admin/parts/${part.id}/delete" method="POST">
                            <button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this part?')">
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