<#import "/spring.ftl" as spring/>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <title>Login</title>
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
            <a class="navbar-brand" href="/">Auto Repair</a>
        </div>
    </div>
</nav>
<h2>${message!""}</h2>
<h2>${errorMessage!""}</h2>
<div align="center">
  <div class="container-fluid">
    <div class="row">
      <div class="col-sm-8 col-sm-offset-2">
        <h1>Login</h1>
        <form action="/login" method="post" id="loginForm" name="loginForm">
        <div class="form-group">
            <label for="username">Email or AFM</label>
            <input type="text" class="form-control" id="username" name="username">
            <p class="help-block">You can use your Email or your AFM to log in!</p>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password">
        </div>
        <div class="col-sm-12 controls">
            <button type="submit" id="btn-login" class="btn btn-success btn-lg btn-block">Log In!</button>
        </div>
        </form>
      </div>
    </div>
  </div>
</div>
  <!-- Bootstrap core JavaScript
================================================== -->
  <!-- Placed at the end of the document so the pages load faster -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</body>
</html>