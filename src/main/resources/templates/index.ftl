<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <title>eXtreme Performance</title>
        <!---- Trying to make some cool stuff with p5.js ----->
        <script async src=https://CDN.JSDelivr.net/g/p5.js(p5.min.js+addons/p5.dom.js+addons/p5.sound.js)></script>
        <script defer src=sketch.js></script>
        <script defer src=particle.js></script>
        <!---- Our Custom CSS ----->
        <link rel="stylesheet" type="text/css" media="screen" href="/../styles.css">
    </head>
<body>

    <!-- NAVBAR -->
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
            <div class="navbar-right">
                <a class="navbar-brand" href="/login">Login</a>
            </div>
        </div>
    </nav>

    <div class="p5container">
        <img src="Images/eXtreme.png">
    </div>
    <div id="canvasParent">
    </div>
</body>
</html>