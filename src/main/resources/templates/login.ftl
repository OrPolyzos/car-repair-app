<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- Custom Css -->
    <link rel="stylesheet" type="text/css" media="screen" href="/../styles.css">
    <!---- Trying to make some cool stuff with p5.js ----->
    <script async src=https://CDN.JSDelivr.net/g/p5.js(p5.min.js+addons/p5.dom.js+addons/p5.sound.js)></script>
    <script defer src=sketch.js></script>
    <script defer src=particle.js></script>
</head>

<body>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">eXtreme Performance</a>
            </div>
        </div>
    </nav>

    <h2>${message!""}</h2>
    <h2 class="errorRed">${errorMessage!""}</h2>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <!--h1>Sign In</h1-->
                    <form action="/login" method="post" id="loginForm" name="loginForm" style="text-align:center">
                        <div class="form-group">
                            <label for="username">Email or AFM</label>
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="glyphicon glyphicon-user"></i>
                                    </span>
                                    <input type="text" class="form-control" id="username" name="username">
                                </div>
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="glyphicon glyphicon-lock"></i>
                                    </span>
                                    <input type="password" class="form-control" id="password" name="password">
                                </div>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-success btn-lg btn-block">Login!</button>
                        </div>
                    </form>
            </div>
        </div>
    </div>

    <#include "footer.ftl">

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> -->
</body>
</html>
