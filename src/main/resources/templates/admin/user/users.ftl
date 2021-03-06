<#import "/spring.ftl" as spring/>
<html>

<head>
    <title>Users</title>
    <#include "../common/head-section.ftl"/>
</head>
<body>
<#include "../common/navbar.ftl">
<div class="container-fluid">
    <div class="row">
        <#include "section/create-user-section.ftl"/>
    </div>
    <hr/>
    <div class="row">
        <#include "section/search-user-section.ftl"/>
    </div>
</div>
<#include "section/users-table-section.ftl"/>
<#include "../common/scripts-section.ftl"/>
</body>
</html>
