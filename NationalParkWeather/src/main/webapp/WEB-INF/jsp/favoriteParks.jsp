<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

	<section class="main-content">
		<h1 id="survey-header">Today's Survey Results!</h1>
		<c:forEach items="${favoriteParkList}" var="parkResult">
			<c:set value="${parkResult.park}" var="park"/>
					<c:set value="${park.code}" var="parkCode"/>
			<c:url value="/img/parks/${parkCode.toLowerCase()}.jpg" var="parkImgLink"/>
			<c:url var="parkDetailUrl" value="/parkDetail">
				<c:param name="parkCode" value="${park.code}"/>
			</c:url>
			<div class="favorite-park-row-container">
				<a href="${parkDetailUrl}"><img src="${parkImgLink}"></a>		
				<div class="favorite-park-info-container">
					<h3><c:out value="${park.name}"/></h3>
				</div>
				<div class="vote-count-container">
					<p><span class="vote-number"><c:out value="${parkResult.voteCount}"/></span> vote(s)</p>
				</div>
			</div> 
		</c:forEach>
	</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />