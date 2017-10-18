<#import "/spring.ftl" as spring/>

<head>
    <title>Login</title>
</head>

<body>
<h2>${message!""}</h2>
<h2 style="color: red">
${errorMessage!""}
</h2>
<div align="center">
    <form action="/login" method="post" id="loginForm" name="loginForm">
        <input type="text" name="username" id="username" placeholder="Email or AFM" autocomplete="off"/>
        <br><br>
        <input type="password" name="password" id="password" placeholder="password"/>
        <br><br>
        <input type="submit" value="Log In!"/>
    </form>
</div>

</body>
