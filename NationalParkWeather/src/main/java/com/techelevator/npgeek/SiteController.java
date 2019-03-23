package com.techelevator.npgeek;

import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.JDBC.DailyWeatherDAO;
import com.techelevator.JDBC.JDBCDailyWeatherDAO;
import com.techelevator.JDBC.JDBCParkDAO;
import com.techelevator.JDBC.JDBCSurveyDAO;
import com.techelevator.JDBC.ParkDAO;
import com.techelevator.JDBC.SurveyDAO;
import com.techelevator.npgeek.model.Survey;

@Controller
@SessionAttributes("isFahrenheit")
public class SiteController {
	
	ParkDAO parkDao;
	DailyWeatherDAO dailyWeatherDao;
	SurveyDAO surveyDao;
	Boolean isFahrenheit;
	
	@Autowired
	public SiteController(DataSource dataSource) {
		parkDao = new JDBCParkDAO(dataSource);
		dailyWeatherDao = new JDBCDailyWeatherDAO(dataSource);
		surveyDao = new JDBCSurveyDAO(dataSource);
	}
	
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	public String displayHomePage(ModelMap map) {
		
		map.addAttribute("parkList", parkDao.getAllParks());
		
		return "homePage";
	}
	
	@RequestMapping(path="/parkDetail", method=RequestMethod.GET)
	public String displayParkDetail(@RequestParam String parkCode, ModelMap map) {
		
		if(isFahrenheit == null) {
			isFahrenheit = true;
		}
		
		map.addAttribute("isFahrenheit", isFahrenheit);
		
		map.addAttribute("park", parkDao.getParkByCode(parkCode));
		map.addAttribute("forecast", dailyWeatherDao.getFiveDayForecast(parkCode));
		
		return "parkDetail";
		
	}
	
	@RequestMapping(path="/parkDetail", method=RequestMethod.POST)
	public String displayParkDetail(@RequestParam Boolean isFahrenheit, @RequestParam String parkCode, ModelMap map, RedirectAttributes attr) {
		
		this.isFahrenheit = isFahrenheit;
		map.addAttribute("isFahrenheit", isFahrenheit);
		attr.addAttribute("parkCode", parkCode);
//		
//		attr.addFlashAttribute("park", parkDao.getParkByCode(parkCode));
//		attr.addFlashAttribute("forecast", dailyWeatherDao.getFiveDayForecast(parkCode));
		
		return "redirect:/parkDetail";
		
	}
	
	@RequestMapping(path="/survey", method=RequestMethod.GET)
	public String displaySurveyPage(ModelMap map) {
		
		if (! map.containsAttribute("survey")) {
			map.addAttribute("survey", new Survey());
		}
		
		map.addAttribute("parkList", parkDao.getAllParks());
		
		return "survey";
	}
	
	@RequestMapping(path="/survey", method=RequestMethod.POST)
	public String redirectToFavoriteParksPage(@Valid @ModelAttribute("survey") Survey survey, 
			BindingResult result, RedirectAttributes attr, ModelMap map) {
		
		if (result.hasErrors()) {
			map.addAttribute("parkList", parkDao.getAllParks());
			return "/survey";
		}
		
		surveyDao.save(survey);
		attr.addFlashAttribute("favoriteParkList", surveyDao.getSurveyResults());
		
		return "redirect:/favoriteParks";
		
	}
	
	@RequestMapping(path="/favoriteParks", method=RequestMethod.GET)
	public String displayFavoriteParksPage(ModelMap map) {
		
		map.addAttribute("favoriteParkList", surveyDao.getSurveyResults());
		
		return "favoriteParks";
	}
	

}
