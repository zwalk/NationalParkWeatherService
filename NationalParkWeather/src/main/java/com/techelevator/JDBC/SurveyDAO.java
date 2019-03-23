package com.techelevator.JDBC;

import java.util.List;
import java.util.Map;

import com.techelevator.npgeek.model.ParkSurveyResult;
import com.techelevator.npgeek.model.Survey;

public interface SurveyDAO {

	public List<ParkSurveyResult> getSurveyResults();
	public void save(Survey survey);
}
