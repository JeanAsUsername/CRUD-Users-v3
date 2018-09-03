<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Search users</title>
	
	<!-- main style -->
	<link href="../css/user-search.css" rel="stylesheet">
	
	<!-- components styles -->
	<link href="../css/components/header.css" rel="stylesheet">
	<link href="../css/components/footer.css" rel="stylesheet">
	
	<!-- utilities style -->
	<link href="../css/util.css" rel="stylesheet">
	
</head>
<body>

	<!-- Header component -->
	<jsp:include page="../components/header.jsp" />
	
	<main>
	
		<section class="content">
		
			<c:if test="${pageLoadException != null}"> 	
				<%
					response.sendRedirect("http://localhost:8080/users?error=1");
				%>
			</c:if>
		
			<div class="content__search">
				
				<c:if test="${languageModel != null}">
				
					<h2 class="search__caption">Search users by language</h2>
			
					<form:form class="search__form" action="/users/searchUsers" method="POST" commandName="languageModel">
					
						<form:select class="form__select" path="id">
							<c:forEach var="language" items="${languages}">
								<form:option value="${language.getId()}">
									<c:out value="${language.getName()}" />
								</form:option>
							</c:forEach>
							
							<c:if test="${searchedLanguage != null}">
								<form:option selected="true" value="${searchedLanguage.getId()}">
									<c:out value="${searchedLanguage.getName()}" />
								</form:option>
							</c:if>
	
							
						</form:select>
						
						<input type="submit" class="form__submit" value="Search">
						
					</form:form>
				
				</c:if>
			
			</div>
			
			<div class="content__users">
			
				<c:if test="${users != null}">
				
					<h2 class="users__caption">Users</h2>
					
					<ul class="users__list">
						<c:forEach var="user" items="${users}">
							<li class="user__item">
								<c:out value="${user.getUsername()}" />
							</li>
						</c:forEach>
					</ul>
					
				</c:if>
			</div>
		
		</section>
	
	</main>
	
	<!-- Footer component -->
	<jsp:include page="../components/footer.jsp" />

</body>
</html>