<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>Vehicles</title>
    <#include "../common/head-section.ftl"/>
</head>
<body>
<#include "../common/navbar.ftl">
<div class="container-fluid">
    <div class="row">
        <#include "section/create-vehicle-section.ftl"/>
    </div>
    <hr/>
    <div class="row">
        <#include "section/search-vehicle-section.ftl"/>
    </div>
</div>
<#include "section/vehicles-table-section.ftl"/>
<#include "../common/scripts-section.ftl"/>
</body>
</html>
