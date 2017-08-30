<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap-theme.css" rel="stylesheet">
</head>
	<body>
	<div class="container">
		<div class="span12">
			<h1>
			    Réinitialiser votre mot de passe
			</h1>
		<br>
		<form method="POST" action="${contextPath}/resetPassword" class="form-inline">
		  <div class="form-group">
		    
		    <div class="input-group">
		      <div class="input-group-addon">@</div>
		      <input name="email" type="text" class="form-control" id="email" placeholder="Entrer votre email">
		      <span>${error}</span>
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		    </div>
		  </div>
		  <button type="submit" class="btn btn-primary">Réinitialiser</button>
		</form>
		 
		<br> 
<%-- 		<a href="<c:url value="/user/registration" />"> --%>
<%-- 		   <spring:message code="label.form.loginSignUp"></spring:message> --%>
<!-- 		</a> -->
<!-- 		<br> -->
<%-- 		<a href="<c:url value="login.html"/>"> --%>
<%-- 		   <spring:message code="label.form.loginLink"></spring:message> --%>
<!-- 		</a> -->
	 
		</div>
	</div>
	

	
	<script src="${contextPath}/resources/js/jquery-3.1.0.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.js"></script>
	<script src="${contextPath}/resources/js/app.js"></script>
	 
	</body>
 
</html>