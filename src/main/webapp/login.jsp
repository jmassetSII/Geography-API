<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <title>Log in</title>

    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap-theme.css" rel="stylesheet">

</head>

<body>

<div class="container">

    <form method="POST" action="${contextPath}/login" class="form-signin">
        <h2 class="form-heading">Connexion</h2>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <input name="username" type="text" class="form-control" placeholder="Login" autofocus="true"/>
            <input name="password" type="password" class="form-control" placeholder="Mot de passe"/>
            <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
            <h4 class="text-center"><a href="${contextPath}/registration">Créer un compte</a></h4>
            <h4 class="text-center"><a href="${contextPath}/resetPassword">Mot de passe perdu?</a></h4>
        </div>

    </form>

</div>
<script src="${contextPath}/resources/js/jquery-3.1.0.js"></script>
<script src="${contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>
