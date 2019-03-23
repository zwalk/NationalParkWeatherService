<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

	<section class = "main-content" id="home-page">
		<c:forEach items="${parkList}" var="park">
		<c:set value="${park.code}" var="parkCode"/>
			<c:url value="/img/parks/${parkCode.toLowerCase()}.jpg" var="parkImgLink"/>
			<c:url var="parkDetailUrl" value="/parkDetail">
				<c:param name="parkCode" value="${park.code}"/>
			</c:url>
			<div class="park-row-container">
				<a href="${parkDetailUrl}"><img src="${parkImgLink}"></a>		
				<div class="park-info-container">
					<h3 class="park-name"><c:out value="${park.name}"/></h3>
					<p class ="park-description"><c:out value="${park.parkDescription}"/></p>
				</div>
			</div>
		</c:forEach>
	</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />