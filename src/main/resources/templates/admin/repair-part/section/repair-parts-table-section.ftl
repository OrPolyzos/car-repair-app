<#if repairPartList?has_content>
    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Part ID</th>
                <th>Quantity</th>
                <th>Delete Part</th>
            </tr>
            </thead>
            <tbody>
            <#list repairPartList as repairPart>
                <tr>
                    <td>${repairPart.partID}</td>
                    <td>${repairPart.quantity}</td>
                    <td>
                        <form action="/admin/repairs/${repairPart.repairID}/parts/${repairPart.partID}/delete" method="POST">
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
<#else>
    <h2 class="errorMessage">No parts assigned to this repair</h2>
</#if>