<#if allParts?has_content>
    <h2 class="subtitle">Available parts</h2>
    <div class="container-fluid">
        <div class="row">
            <form class="form-horizontal">
                <fieldset class="horizontal-1">
                    <div class="col-md-6 col-md-offset-3">
                        <label for="filterInput">Filter</label>
                        <input type="text" class="form-control" name="filterInput" id="filterInput" placeholder="abc-123..."/>
                    </div>
                </fieldset>
            </form>
        </div>
        <div class="row">
            <div class="table-responsive">
                <table id="resultsTable" class="table table-hover">
                    <thead>
                    <tr>
                        <th>Part ID</th>
                        <th>Part Name</th>
                        <th>Part Price</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list allParts as part>
                        <tr>
                            <td>${part.partID}</td>
                            <td>${part.partName}</td>
                            <td>${part.partPrice}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
<#else>
    <h2 class="errorMessage">No parts available</h2>
</#if>