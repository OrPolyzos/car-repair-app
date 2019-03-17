<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>Repair Parts</title>
    <#include "../common/head-section.ftl"/>
</head>
<body>
<#include "../common/navbar.ftl">
<div>
    <div class="container-fluid">
        <div class="row">
            <#include "section/create-repair-part-section.ftl"/>
        </div>
        <div class="row">
            <#include "section/repair-parts-table-section.ftl"/>
        </div>
        <div class="row">
            <#include "section/all-parts-table-section.ftl"/>
        </div>
    </div>
</div>
<#include "../common/scripts-section.ftl"/>
</body>
</html>

