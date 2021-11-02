package OAuth;
import static io.restassured.RestAssured.given;

import java.util.List;

import Pojo.Api;
import Pojo.GetCourses;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class oAuthTest {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		String tokenUrl="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWgrY3K3v8DyK86zYomkDKNovRRcz14HN2u5WPVsa5kXlxR50dj3WFojdHtvYF8VvQ&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
		
		String partialCode=tokenUrl.split("code=")[1];
		String code=partialCode.split("&scope")[0];
		System.out.println(code);
		String accessTokenResponse=given().urlEncodingEnabled(false)
		.queryParams("code",code)
		.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code")
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token").asString();
		System.out.println(accessTokenResponse);
		JsonPath js=new JsonPath(accessTokenResponse);
		String accessToken=js.getString("access_token");
		System.out.println(accessToken);
		
		GetCourses gc=given().queryParam("access_token",accessToken).expect().defaultParser(Parser.JSON)
		.when()
		.get("https://rahulshettyacademy.com/getCourse.php").as(GetCourses.class);
		//System.out.println(response);
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		List<Api> apiCourses=gc.getCourses().getApi();
		
		for(int i=0;i<apiCourses.size();i++)
		{
			
			if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI WebServices testing"))
			{
				System.out.println(apiCourses.get(i).getPrice());
			}
		}

	}

}
