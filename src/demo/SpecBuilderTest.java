package demo;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import Pojo.AddPlace;
import Pojo.Location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderTest {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		AddPlace p=new AddPlace();
		p.setAccuracy(51);
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
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		Response res=given().spec(reqSpec)
				.body(p)
				.when().post("/maps/api/place/add/json/")
				.then().spec(resSpec).extract().response();
		String response=res.asString();
		System.out.println(response);

	}

}
