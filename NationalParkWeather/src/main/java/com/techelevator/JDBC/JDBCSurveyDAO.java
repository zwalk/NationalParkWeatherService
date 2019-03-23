package com.techelevator.JDBC;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.ParkSurveyResult;
import com.techelevator.npgeek.model.Survey;

@Component
public class JDBCSurveyDAO implements SurveyDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCSurveyDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<ParkSurveyResult> getSurveyResults() {
		List<ParkSurveyResult> surveyResults = new ArrayList<ParkSurveyResult>();
		String sqlSurveyResults = "SELECT parkcode, COUNT(*) AS votecount " + 
								  "FROM survey_result " + 
								  "GROUP BY parkcode " + 
								  "ORDER BY votecount DESC, parkcode ASC";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSurveyResults);
		while (results.next()) {
			Park park = getParkByCode(results.getString("parkcode"));
			ParkSurveyResult parkSurveyResult = new ParkSurveyResult();
			parkSurveyResult.setPark(park);
			parkSurveyResult.setVoteCount(results.getInt("votecount"));
			surveyResults.add(parkSurveyResult);
		}
		
		return surveyResults;
	}

	@Override
	public void save(Survey survey) {
		Long id = getNextId();
		survey.setSurveyId(id);
		String sqlInsertSurvey = "INSERT INTO survey_result(surveyid, parkcode, emailaddress, state, activitylevel) VALUES (?,?,?,?,?)";
		jdbcTemplate.update(sqlInsertSurvey, survey.getSurveyId(), survey.getParkCode(), survey.getEmailAddress(), survey.getState(), survey.getActivityLevel());
		survey.getActivityLevel();
	}

	private Long getNextId() {
		String sqlSelectNextId = "SELECT NEXTVAL('seq_surveyid')";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectNextId);
		Long id = null;
		if(results.next()) {
			id = results.getLong(1);
		} else {
			throw new RuntimeException("Something strange happened, unable to select next forum post id from sequence");
		}
		return id;
	}

	private Park getParkByCode(String parkCode) {
		
		String sqlGetParkByCode = "SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, "
				+ "numberofcampsites, climate, yearfounded, annualvisitorcount, " + 
				"inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies FROM park WHERE parkcode = ?";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetParkByCode, parkCode);
		
		result.next();
		Park park = mapRowToPark(result);
		
		
		return park;
	}
	
	private Park mapRowToPark(SqlRowSet result) {
		Park park = new Park();
		
		park.setAcreage(result.getInt("acreage"));
		park.setAnnualVisitorCount(result.getInt("annualvisitorcount"));
		park.setClimate(result.getString("climate"));
		park.setCode(result.getString("parkcode"));
		park.setElevationInFeet(result.getInt("elevationinfeet"));
		park.setEntryFee(result.getInt("entryfee"));
		park.setInspirationalQuote(result.getString("inspirationalquote"));
		park.setInspirationalQuoteSource(result.getString("inspirationalquotesource"));
		park.setMilesOfTrail(result.getDouble("milesoftrail"));
		park.setName(result.getString("parkname"));
		park.setNumberOfAnimalSpecies(result.getInt("numberofanimalspecies"));
		park.setNumberOfCampsites(result.getInt("numberofcampsites"));
		park.setParkDescription(result.getString("parkdescription"));
		park.setState(result.getString("state"));
		park.setYearFounded(result.getInt("yearfounded"));
		
		return park;
	}
}
