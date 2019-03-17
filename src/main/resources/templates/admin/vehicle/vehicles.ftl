<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>Vehicles</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" media="screen" href="/../styles.css">
</head>
<body>
<#include "../../navbar.ftl">
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
<script type="text/javascript" src="/utilities.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
