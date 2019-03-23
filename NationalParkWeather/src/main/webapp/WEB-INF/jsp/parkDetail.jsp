<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<script>


// window.onload=function(){
// var weatherImg = document.getElementById("today-weather-img-container");    
// weatherImg.addEventListener("mouseover", createRain())
// }

//number of drops created.
var nbDrop = 850;

// function to generate a random number range.
function randRange( minNum, maxNum) {
  return (Math.floor(Math.random() * (maxNum - minNum + 1)) + minNum);
}

	 

// function to generate drops
function createRain() {
	const mainContent = document.getElementById("park-detail-page");
    for( i=1;i<nbDrop;i++) {
    var dropLeft = randRange(0,2000);
    var dropTop = randRange(-1000,1400);
   
    mainContent.innerHTML += '<div class="drop" id="drop'+i+'"></div>';
    $('#drop'+i).css('left',dropLeft);
    $('#drop'+i).css('top',dropTop);
    }
    
    mainContent.classList.add("drop-main");
    
    const rainPlayer = document.querySelector('audio');
  
   	rainPlayer.play();
   	rainPlayer.loop = true;
    
}

function removeRain() {
	let allDrops = document.querySelectorAll('.drop');
	let mainContent = document.getElementById('park-detail-page')
	mainContent.classList.remove('drop-main')
	
	allDrops.forEach((drop) => {
		drop.classList.remove('drop')
		drop.parentNode.removeChild(drop);
		
	})
	const rainPlayer = document.querySelector('audio');

	rainPlayer.pause();
	rainPlayer.currentTime = 0;
}

</script>
<section class="main-content" id="park-detail-page">
	<img id="park-detail-banner"
		src="img/parks/${park.code.toLowerCase()}.jpg">
	<h3 class="park-name">${park.name}</h3>
	<div class="table-container">
		<table>
			<tr>
				<td class="park-detail-info-header">State:</td>
				<td>${park.state}</td>
			</tr>
			<tr>
				<td class="park-detail-info-header">Year Founded:</td>
				<td>${park.yearFounded}</td>
			</tr>
			<tr>
				<td class="park-detail-info-header">Size:</td>
				<fmt:formatNumber  var="acres" type = "number" maxFractionDigits = "0" value = "${park.acreage}" />
				<td>${acres} acres</td>
			</tr>
			<tr>
				<td class="park-detail-info-header">Elevation:</td>
				<fmt:formatNumber  var="elevation" type = "number" maxFractionDigits = "0" value = "${park.elevationInFeet}" />
				<td>${elevation} feet</td>
			</tr>
			<tr>
				<td class="park-detail-info-header">Climate:</td>
				<td>${park.climate}</td>
			</tr>
		</table>
		<table>
			<tr>
				<td class="park-detail-info-header">Total Trail Length:</td>
				<td>${park.milesOfTrail} miles</td>
			</tr>
			<tr>
				<td class="park-detail-info-header">Campsites:</td>
				<td>${park.numberOfCampsites}</td>
			</tr>
			<tr>
				<td class="park-detail-info-header">Fee:</td>
				<td>$${park.entryFee}</td>
			</tr>
			<tr>
				<td class="park-detail-info-header">Annual Visitors:</td>
				<fmt:formatNumber  var="visitors" type = "number" maxFractionDigits = "0" value = "${park.annualVisitorCount}" />
				<td>${visitors}</td>
			</tr>
			<tr>
				<td class="park-detail-info-header">Animal Species:</td>
				<td>${park.numberOfAnimalSpecies}</td>
			</tr>
		</table>
	</div>
	<p>${park.parkDescription}</p>
	<p id="inspirational-quote">"${park.inspirationalQuote}" -
		${park.inspirationalQuoteSource}</p>
	<div class="weather-forecast-container">
		<c:forEach var="dailyWeather" items="${forecast}">
			<c:if test="${dailyWeather.dayNumber == 1}">
				<div class="today-weather-container">
					<h3>Today</h3>
					<div ondblclick="removeRain()" onclick="createRain()" class="today-weather-img-container" id="today-weather-img-container">
						<img src="img/weather/${dailyWeather.forecast}.png">
					</div>
					<div class="high-low-container">
						<c:choose>
							<c:when test="${isFahrenheit}">
								<p>
									High:
									<c:out value="${dailyWeather.highTempInFahrenheit}º" />
								</p>
								<p>
									Low:
									<c:out value="${dailyWeather.lowTempInFahrenheit}º" />
								</p>
							</c:when>
							<c:otherwise>
								<p>
									High:
									<c:out value="${dailyWeather.highTempInCelsius}º" />
								</p>
								<p>
									Low:
									<c:out value="${dailyWeather.lowTempInCelsius}º" />
								</p>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="weather-advisory-container">
						<p id="weather-advisory-message">
							<c:out value="${dailyWeather.advisory}" />
						</p>
					</div>
				</div>
			</c:if>
			<c:if test="${dailyWeather.dayNumber > 1}">
				<div class="week-weather-container">
					<div class="week-weather-img-container">
						<img src="img/weather/${dailyWeather.forecast}.png">
					</div>
					<c:choose>
						<c:when test="${isFahrenheit}">
							<p>
								High:
								<c:out value="${dailyWeather.highTempInFahrenheit}º" />
							</p>
							<p>
								Low:
								<c:out value="${dailyWeather.lowTempInFahrenheit}º" />
							</p>
						</c:when>
						<c:otherwise>
							<p>
								High:
								<c:out value="${dailyWeather.highTempInCelsius}º" />
							</p>
							<p>
								Low:
								<c:out value="${dailyWeather.lowTempInCelsius}º" />
							</p>
						</c:otherwise>
					</c:choose>
				</div>
			</c:if>
		</c:forEach>
	</div>
	<c:url value="/parkDetail" var="parkDetailRedirectLink" />
	<form action="${parkDetailRedirectLink}" method="post">
		<input type="hidden" name="parkCode" value="${park.code}" />
		<div class="toggle-switch-container">
			<c:choose>
				<c:when test="${isFahrenheit}">
					<div>Fº</div>
					<input onChange="this.form.submit()" type="checkbox" id="switch" name="isFahrenheit"
						value="false" />
					<label class="toggle-label" for="switch">Toggle</label>
					<div>Cº</div>
					<input type="hidden" id="switch-hidden" name="isFahrenheit"
						value="true" >
				</c:when>
				<c:otherwise>
					<div>Fº</div>
					<input onChange="this.form.submit()" type="checkbox" id="switch" name="isFahrenheit"
						value="false" checked />
					<label class="toggle-label" for="switch">Toggle</label>
					<div>Cº</div>
					<input type="hidden" id="switch-hidden" name="isFahrenheit"
						value="true" >
				</c:otherwise>
			</c:choose>
		</div>
	</form>
</section>

<audio>
	<source src="http://localhost:8080/m3-java-capstone/audio/rain-06.mp3" type="audio/mpeg">
</audio>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />