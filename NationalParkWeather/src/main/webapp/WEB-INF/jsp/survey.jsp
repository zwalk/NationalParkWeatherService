<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

	<section class="main-content" class="survey-page">
		<c:url value="/survey" var="surveyRedirectLink"/>
		
		<h2 class="survey-header">Today's Survey</h2>
		<p class="survey-header">What is your favorite park?</p>
	
		<form:form action="${surveyRedirectLink}" method="POST" modelAttribute="survey">
			
			<div class="form-group">
				<label for="favoritePark">Favorite National Park</label>
				<form:select class="input" id="parkCode" path="parkCode">
					<form:option value="">Please Select a Favorite Park</form:option>
					<c:forEach items="${parkList}" var="park">
						<form:option value="${park.code}"><c:out value="${park.name}"/></form:option>
					</c:forEach>
				</form:select>
				<form:errors path="parkCode" cssClass="error"/>
			</div>
			
			<div class="form-group">
				<label for="emailAddress">Your Email</label>
				<form:input class="input" type="email" path="emailAddress" placeholder="Enter Your Email"/>
				<form:errors path="emailAddress" cssClass="error"/>
			</div>
			
			<div class="form-group">
				<label for="state">State of Residence</label>
				<form:select class="input" id="state" path="state">
					<form:option value="">Choose State</form:option>
					<form:option value="AL">AL</form:option>
					<form:option value="AK">AK</form:option>
					<form:option value="AR">AR</form:option>	
					<form:option value="AZ">AZ</form:option>
					<form:option value="CA">CA</form:option>
					<form:option value="CO">CO</form:option>
					<form:option value="CT">CT</form:option>
					<form:option value="DC">DC</form:option>
					<form:option value="DE">DE</form:option>
					<form:option value="FL">FL</form:option>
					<form:option value="GA">GA</form:option>
					<form:option value="HI">HI</form:option>
					<form:option value="IA">IA</form:option>	
					<form:option value="ID">ID</form:option>
					<form:option value="IL">IL</form:option>
					<form:option value="IN">IN</form:option>
					<form:option value="KS">KS</form:option>
					<form:option value="KY">KY</form:option>
					<form:option value="LA">LA</form:option>
					<form:option value="MA">MA</form:option>
					<form:option value="MD">MD</form:option>
					<form:option value="ME">ME</form:option>
					<form:option value="MI">MI</form:option>
					<form:option value="MN">MN</form:option>
					<form:option value="MO">MO</form:option>	
					<form:option value="MS">MS</form:option>
					<form:option value="MT">MT</form:option>
					<form:option value="NC">NC</form:option>	
					<form:option value="NE">NE</form:option>
					<form:option value="NH">NH</form:option>
					<form:option value="NJ">NJ</form:option>
					<form:option value="NM">NM</form:option>			
					<form:option value="NV">NV</form:option>
					<form:option value="NY">NY</form:option>
					<form:option value="ND">ND</form:option>
					<form:option value="OH">OH</form:option>
					<form:option value="OK">OK</form:option>
					<form:option value="OR">OR</form:option>
					<form:option value="PA">PA</form:option>
					<form:option value="RI">RI</form:option>
					<form:option value="SC">SC</form:option>
					<form:option value="SD">SD</form:option>
					<form:option value="TN">TN</form:option>
					<form:option value="TX">TX</form:option>
					<form:option value="UT">UT</form:option>
					<form:option value="VT">VT</form:option>
					<form:option value="VA">VA</form:option>
					<form:option value="WA">WA</form:option>
					<form:option value="WI">WI</form:option>	
					<form:option value="WV">WV</form:option>
					<form:option value="WY">WY</form:option>
				</form:select>
				<form:errors path="state" cssClass="error"/>
			</div>
			
			<div class="form-group">
				<label for="activityLevel">Your Activity Level</label>
				<div class="input">
					<form:radiobutton path="activityLevel" value="Inactive"/>
					<label for="activityLevel">Inactive</label>
					
					<form:radiobutton path="activityLevel" value="Sedentary" />
					<label for="activityLevel">Sedentary</label>
					
					<form:radiobutton path="activityLevel" value="Active" />
					<label for="activityLevel">Active</label>
					
					<form:radiobutton path="activityLevel" value="Extremely Active" />
					<label for="activityLevel">Extremely Active</label>
					<form:errors path="activityLevel" cssClass="error"/>
				</div>
			</div>
			
			<input class="submit" type="submit" value="Submit"/>
			
		</form:form>
	</section>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />