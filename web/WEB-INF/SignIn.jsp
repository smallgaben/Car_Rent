
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>

<div id="container">
  <form method="post" action="${pageContext.request.contextPath}/signin" id="register_from">
    <input type="text" name="username" placeholder="Enter username" class="input">
    <input type="password" name="password" placeholder="Enter password" class="input">
    <button id="submit_button" type="submit" name="login" >Sign In</button>
  </form>

</div>

</body>
</html>
