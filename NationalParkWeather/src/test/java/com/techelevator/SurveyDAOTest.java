package com.techelevator;

import org.springframework.jdbc.core.JdbcTemplate;
import com.techelevator.JDBC.JDBCSurveyDAO;
import com.techelevator.JDBC.SurveyDAO;
import com.techelevator.npgeek.model.ParkSurveyResult;
import com.techelevator.npgeek.model.Survey;

import java.util.List;

import org.junit.*;

public class SurveyDAOTest extends DAOIntegrationTest {
	
	private JdbcTemplate jdbcTemplate;
	private SurveyDAO surveyDao;
	
	@Before
	public void setup() {
		jdbcTemplate = new JdbcTemplate(getDataSource());
		surveyDao = new JDBCSurveyDAO(getDataSource());
		clearSurveyTable();
	}
	
	@Test
	public void get_survey_results_returns_a_map_size_greater_than_0() {
		insertSurveyIntoDatabase();
		List<ParkSurveyResult> surveyResults = surveyDao.getSurveyResults();
		Assert.assertTrue(surveyResults.size() == 1);
	}
	
	@Test
	public void get_survey_results_displays_correct_order() {
		insertSurveyIntoDatabase();
		insertSecondSurveyIntoDatabase();
		insertThirdSurveyIntoDatabase();
		
		List<ParkSurveyResult> surveyResults = surveyDao.getSurveyResults();
		
		
		Assert.assertEquals(2, surveyResults.get(0).getVoteCount());
		Assert.assertEquals(1, surveyResults.get(1).getVoteCount());
		Assert.assertTrue(surveyResults.get(0).getVoteCount() > surveyResults.get(1).getVoteCount() );
	}
	
	@Test
	public void get_survey_results_returns_no_results_when_no_park_has_a_survey() {
		List<ParkSurveyResult> surveyResults = surveyDao.getSurveyResults();
		
		Assert.assertTrue(surveyResults.size() == 0);
	}
	
	@Test
	public void save_survey_correctly_saves_in_database() {
		Survey survey = createSurvey();
		surveyDao.save(survey);
		List<ParkSurveyResult> surveyResults = surveyDao.getSurveyResults();
		Assert.assertTrue(surveyResults.size() > 0);
	}
	
	private Survey createSurvey() {
		Survey survey = new Survey();
		survey.setParkCode("GCNP");
		survey.setEmailAddress("bush@whitehouse.gov");
		survey.setState("Utah");
		survey.setActivityLevel("Super-Active!!");
		return survey;
		
	}
	
	private void clearSurveyTable() {
		String truncateSurveyTableSql = "TRUNCATE survey_result CASCADE";
		jdbcTemplate.update(truncateSurveyTableSql);
	}
	
	private void insertSurveyIntoDatabase() {
		
		String sqlInsertSurvey = "INSERT INTO survey_result(surveyid, parkcode, emailaddress, state, activitylevel) " +
								"VALUES (NEXTVAL('seq_surveyid'), 'ENP', 'testtacopalace@trump.org', 'Ohio', 'PIZZA-ACTIVE')";
		jdbcTemplate.update(sqlInsertSurvey);
	}
	
	private void insertSecondSurveyIntoDatabase() {
		
		String sqlInsertSurvey = "INSERT INTO survey_result(surveyid, parkcode, emailaddress, state, activitylevel) " +
								"VALUES (NEXTVAL('seq_surveyid'), 'ENP', 'donald@trump.org', 'China', 'PIZZA-ACTIVE')";
		jdbcTemplate.update(sqlInsertSurvey);
	}
	
	private void insertThirdSurveyIntoDatabase() {
		
		String sqlInsertSurvey = "INSERT INTO survey_result(surveyid, parkcode, emailaddress, state, activitylevel) " +
								"VALUES (NEXTVAL('seq_surveyid'), 'GCNP', 'testtacopalace@trump.org', 'Ohio', 'PIZZA-ACTIVE')";
		jdbcTemplate.update(sqlInsertSurvey);
	}
}