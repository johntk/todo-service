<html>

<head>
    <title>Login</title>
</head>
<body>

<link href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" rel="stylesheet">

<div class="container">
    <form name='f' action="perform_login" method='POST'>

        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3>Login</h3>
            </div>
        </div>

        <div class="panel-body">
            <table>
                <tr>
                    <td>User:</td>
                    <td><input type='text' name='username' value=''></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type='password' name='password'/></td>
                </tr>
                <tr>
                    <td><input name="submit" type="submit" value="submit" class="btn btn-primary btn-md"/></td>
                </tr>
            </table>
        </div>

    </form>
</div>
</body>
</html>

