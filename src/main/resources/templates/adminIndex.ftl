<!DOCTYPE html>
<html>
<head>
    <title>Admin Home</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

   <nav class="navbar navbar-inverse">
        <div class="container-fluid">

           <!-- Logo -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#mainNavBar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="#" class="navbar-brand">Auto Repair</a>
            </div>

           <!-- Menu Items -->
            <div class="collapse navbar-collapse" id="mainNavBar">
                <ul class="nav navbar-nav navbar-right">
                    <li class="active">
                        <a href="/admin/users">Users</a>
                    </li>
                    <li>
                        <a href="/admin/repairs">Repairs</a>
                    </li>
                    <li>
                        <a href="/admin/vehicles">Vehicles</a>
                    </li>
                    <li>
                        <a href="/admin/parts">Parts</a>
                    </li>
                    <li>
                        <a href="/logout">Logout</a>
                    </li>
                </ul>

               <!-- right align
                <ul class="nav navbar-nav navbar-right">
                    <a href="">Logout</a>
                </ul> -->

           </div>
        </div>
    </nav>
</body>
</html>