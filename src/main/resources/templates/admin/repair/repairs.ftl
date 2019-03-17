<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>Repairs</title>
    <#include "../common/head-section.ftl"/>
</head>
<body>
<#include "../common/navbar.ftl">
<div class="container-fluid">
    <div class="row">
        <#include "section/create-repair-section.ftl"/>
    </div>
    <hr/>
    <div class="row">
        <#include "section/search-repair-section.ftl"/>
    </div>
</div>
<#include "section/repairs-table-section.ftl"/>
<#include "../common/scripts-section.ftl"/>
</body>
</html>
