package files;
import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class DynamicJson 
{
	@Test(dataProvider="booksData")
	public void addBook(String isbn,String aisle)
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().header("Content-Type", "application/json").body(payload.AddBook(isbn,aisle))
		.when()
		.post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		JsonPath js=ReusableMethods.rawToJson(response);
		String id=js.get("ID");
		System.out.println(id);
		//git branch testing
	}


@DataProvider(name="booksData")
public Object[][] getData()
{
	return new Object[][] {{"abcd","2331"},{"dfgh","4443"},{"qwer","3245"}};
}

}