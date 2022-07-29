<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import = "spring.hibernate.util.TriUtil" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des clients</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/ressources/css/style.css" />
</head>
<body>

	<!-- créer un lien pour un prénom -->
	<c:url var ="lienTriPrenom" value="/client/liste">
		<c:param name ="tri" value ="<%=Integer.toString(TriUtil.PRENOM)%>"/>
	</c:url>
	<c:url var ="lienTriNom" value="/client/liste">
		<c:param name ="tri" value ="<%=Integer.toString(TriUtil.NOM)%>"/>
	</c:url>
	<c:url var ="lienTriEmail" value="/client/liste">
		<c:param name ="tri" value ="<%=Integer.toString(TriUtil.EMAIL)%>"/>
	</c:url>

	<div id="wrapper">
			<div id="header">
				<h2>CRM - Customer Relationship Manager</h2>
			</div>
			<div id="container">
			<div id="content">
			<!-- ajout bouton -->
			
			<input class="add-button" type="button" value="Ajouter client" 
			onclick="window.location.href='montrerFormulaireAjout'; return false;"/>
			
			<!--  add a search box -->
            	<form:form action="recherche" method="GET">
                	Rechercher : <input type="text" name="rechercheNom" />
                
                	<input type="submit" value="Recherche" class="add-button" />
            	</form:form>
			
				<table>
					<tr>
						<th><a href="${lienTriPrenom }">Prénom</a></th>
						<th><a href="${lienTriNom }">Nom</a></th>
						<th><a href="${lienTriEmail }">Email</a></th>
						<th>Action</th>
					</tr>
					
					<c:forEach var="clientTemp" items="${clients}">
					
				<!-- construct an "update" link with customer id -->
					<c:url var="updateLink" value="/client/afficherFormulaire">
						<c:param name="idClient" value="${clientTemp.id}"/>
					</c:url>
					
					<!-- construct an "delete" link with customer id -->
					<c:url var="deleteLink" value="/client/supprimerClient">
						<c:param name="idClient" value="${clientTemp.id}"/>
					</c:url>
					
					<tr>
						<td>${clientTemp.firstName}</td>
						<td>${clientTemp.lastName}</td>
						<td>${clientTemp.email}</td>
						
						<td><!-- display the update link -->
							<a href="${updateLink}">Mettre a jour</a>
								|
							<a href="${deleteLink}"
							onclick="if (!(confirm('Êtes-vous sûr de vouloir supprimer ce client?'))) return false">Supprimer</a>
						</td>
						
					</tr>
					</c:forEach>			
				</table>
				
			</div>
		</div>
		</div>
		
</body>
</html>