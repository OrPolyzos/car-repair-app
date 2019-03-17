<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>Parts</title>
    <#include "../common/head-section.ftl"/>
</head>
<body>
<#include "../common/navbar.ftl">
<div class="container-fluid">
    <div class="row">
        <#include "section/create-part-section.ftl"/>
    </div>
    <hr/>
    <div class="row">
        <#include "section/search-part-section.ftl"/>
    </div>
</div>
<#include "section/parts-table-section.ftl"/>
<#include "../common/scripts-section.ftl"/>
</body>
</html>