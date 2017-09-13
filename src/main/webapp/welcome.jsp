<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <title>Bienvenue</title>

    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap-theme.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css"/>
    

</head>
<body>
<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Bienvenue <strong>${pageContext.request.userPrincipal.name}</strong>
        <c:if test="${pageContext.request.userPrincipal.name == 'DarthVador'}">, que voulez vous détruire aujourd'hui?</c:if> | <a onclick="document.forms['logoutForm'].submit()"><i class="glyphicon glyphicon-off"></i></a></h2>
        <br><br>
        
        <form id="departementSelect">
	        <div class="form-group">
	        	<label for="departement">Sélectionner un département</label><br>
	        		<form:select path="departementList" class="form-control" onchange="villeSelect()">
	        			<form:option value="0" label="--- Choisir ---" />
	        			<form:options items="${departementList}" itemValue="code" itemLabel="nomUppercase"/>
	        		</form:select>
			</div>
		</form>
		<br>
		<table id="villes" class="table table-striped">
			<thead id ="villesHeader" class="hidden">
				<tr>
					<th>Nom</th>
					<th>Code postal</th>
					<th>Pop.1999</th>
					<th>Pop.2010</th>
					<th>Pop.2012</th>
					<th>PDF</th>
				</tr>
			</thead>
			<tbody>
			
			</tbody>
			
		
		</table>
    </c:if>

</div>
<script src="${contextPath}/resources/js/jquery-3.1.0.js"></script>
<script src="${contextPath}/resources/js/bootstrap.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>

<script src="${contextPath}/resources/js/app.js"></script>
</body>
</html>
