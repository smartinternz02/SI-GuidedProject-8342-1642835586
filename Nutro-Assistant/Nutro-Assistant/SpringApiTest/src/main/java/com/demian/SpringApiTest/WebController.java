package com.demian.SpringApiTest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javassist.bytecode.Descriptor.Iterator;

@Controller
public class WebController {
	
	@GetMapping("/")
	public String output() {
		return "index";
	}
	
	@GetMapping("/quickanswer")
	public String qetquery() {
		return "quickAnswer";
		
	}
	
	@PostMapping("/quickanswer")
	public ModelAndView Test(Input input) throws IOException, InterruptedException {
		ModelAndView mv1 = new ModelAndView("quickAnswer");
		mv1.addObject("input",input);
		String query = input.getQues();
		System.out.println(query);																
		String encoded=URLEncoder.encode(query,"UTF-8").replace("+", "%20");
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/quickAnswer?q="+encoded))
				.header("x-rapidapi-key", "f2179b0ee2msh46dd220682815e1p1e6122jsnaea9bb30dd96")
				.header("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		String temp = response.body();
		ModelAndView mav = new ModelAndView("result");
		//System.out.println(response.body());
		 ObjectMapper mapper = new ObjectMapper();
	     Map<String, Object> restMap=mapper.readValue(temp, Map.class);
	     String Answer=restMap.get("answer").toString();
	     String URL=restMap.get("image").toString();
	     System.out.println("String is:"+(restMap.toString()));
	     mv1.addObject("answer",Answer);
	     mv1.addObject("URL",URL);
		return mv1;
	
	}
	
	@GetMapping("/nutbydish")
	public String getdish() {
		return "NutByDish";
	}
	
	@RequestMapping(value="/nutbydish", method=RequestMethod.POST)
	public ModelAndView res(Input input) throws IOException, InterruptedException {
		ModelAndView mv1 = new ModelAndView("NutByDish");
		mv1.addObject("input",input);
		String name = input.getDishName();
		System.out.println(name);
		String encoded=URLEncoder.encode(name,"UTF-8").replace("+", "%20");
		String url="https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/guessNutrition?title="+encoded;
		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(url))
			.header("x-rapidapi-key", "f2179b0ee2msh46dd220682815e1p1e6122jsnaea9bb30dd96")
			.header("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
			.method("GET", HttpRequest.BodyPublishers.noBody())
			.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		String temp = response.body();
		System.out.println(response.body());
		ObjectMapper mapper = new ObjectMapper();
	    Map<String, Object> restMap=mapper.readValue(temp, Map.class);
	    String cals=restMap.get("calories").toString();
	    String a = cals;
	    a = a.replace("{", "").replace("=", ",");
	    String[] arrOfStr = a.split(",");
	    int valueofCals = (int) Float.parseFloat(arrOfStr[1]);
	    String prots=restMap.get("protein").toString();
	    String b = prots;
	    b = b.replace("{", "").replace("=", ",");
	    String[] arrOfStr1 = b.split(",");
	    int valueofProts = (int) Float.parseFloat(arrOfStr1[1]);
	    String fat=restMap.get("fat").toString();
	    String c = fat;
	    c = c.replace("{", "").replace("=", ",");
	    String[] arrOfStr2 = c.split(",");
	    int valueOfFat = (int) Float.parseFloat(arrOfStr2[1]);
	    String carbs=restMap.get("carbs").toString();
	    String d = prots;
	    d = d.replace("{", "").replace("=", ",");
	    String[] arrOfStr3 = d.split(",");
	    int valueOfCarbs = (int) Float.parseFloat(arrOfStr3[1]);
	    mv1.addObject("cals",valueofCals);
	    mv1.addObject("prots",valueofProts);
	    mv1.addObject("fat",valueOfFat);
	    mv1.addObject("carbs",valueOfCarbs);
	    return mv1;
	}
	
	@GetMapping("/recipebyCal")
	public String recipebyCalorie() {
		return "recipe";
	}
	@RequestMapping(value="/recipebyCal", method=RequestMethod.POST)
	public ModelAndView recipeByCal(Input input) throws IOException, InterruptedException {
		ModelAndView mv1 = new ModelAndView("recipe");
		mv1.addObject("input",input);
		String count = input.getCalorieCount();
		String encoded=URLEncoder.encode(count,"UTF-8").replace("+", "%20");
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/mealplans/generate?targetCalories="+encoded+"&timeFrame=day"))
				.header("x-rapidapi-key", "f2179b0ee2msh46dd220682815e1p1e6122jsnaea9bb30dd96")
				.header("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		//System.out.println(response.body());
		String map = response.body();
		ObjectMapper mapper = new ObjectMapper();
	    Map<String, Object> RecMap=mapper.readValue(map, Map.class);
	    System.out.println(RecMap);
	    System.out.println("space \n");
	    ArrayList<String> meals=(ArrayList) RecMap.get("meals");
	    System.out.println(Arrays.toString(meals.toArray()));		// printing arraylist-------
	    System.out.println("Testing image url id");					//						same				
	    System.out.println(meals.getClass());						//	to find the type 		|
	    System.out.println("Meals:" +meals);						//printing arraylist -------
	    System.out.println("\n\nff");
	    mv1.addObject("list",meals);
	    return mv1;
	}
	
	@GetMapping("/convertAmt")
	public String convertAmount(){
		return "convert";
	} 
	
	@PostMapping("/convertAmt")
	public ModelAndView convert(Input input) throws IOException, InterruptedException {
		ModelAndView mv1 = new ModelAndView("convert");
		mv1.addObject("input", input);
		String iname,wunit,sunit;
		String amt;
		iname = input.getItemName();
		wunit = input.getWeightUnit();
		sunit = input.getsUnit();
		amt = input.getSAmount();
		String e1=URLEncoder.encode(iname,"UTF-8").replace("+", "%20");
		String e2=URLEncoder.encode(wunit,"UTF-8").replace("+", "%20");
		String e3=URLEncoder.encode(sunit,"UTF-8").replace("+", "%20");
		String e4 =URLEncoder.encode(amt,"UTF-8").replace("+", "%20");
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/convert?ingredientName="+e1+"&targetUnit="+e2+"&sourceUnit="+e3+"&sourceAmount="+e4))
				.header("x-rapidapi-key", "f2179b0ee2msh46dd220682815e1p1e6122jsnaea9bb30dd96")
				.header("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		String tmp = response.body();
		ObjectMapper mapper = new ObjectMapper();
	    Map<String, Object> restMap=mapper.readValue(tmp, Map.class);
	    String Answer=restMap.get("answer").toString();
	    mv1.addObject("answer",Answer);
	    return mv1;
	}
}
