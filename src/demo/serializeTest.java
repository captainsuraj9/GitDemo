package demo;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import Pojo.AddPlace;
import Pojo.Location;
import io.restassured.RestAssured;
import io.restassured.response.Response;


public class serializeTest 
{
	public static void main(String args[])
	{
	AddPlace p=new AddPlace();
	p.setAccuracy(50);
	p.setAddress("Phullanwal,Ludhiana");
	p.setLanguage("Punjabi-IN");
	p.setPhone_number("1234567890");
	p.setWebsite("www.google.com");
	p.setName("Suraj pahwa");
	List<String> myList=new ArrayList<>();
	myList.add("a");
	myList.add("b");
	p.setTypes(myList);
	
	Location l=new Location();
	l.setLat(12.123);
	l.setLng(123.321);
	p.setLocation(l);
	
	RestAssured.baseURI="https://rahulshettyacademy.com";
	Response res=given().log().all().queryParams("key","qaclick123")
			.body(p)
			.when().post("/maps/api/place/add/json/")
			.then().assertThat().statusCode(200).extract().response();
	  String response=res.asString();
	  System.out.println(response);
	
	}
}
