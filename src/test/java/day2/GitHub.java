package day2;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;
public class GitHub {
	@BeforeTest
	public void beforetest() {
		baseURI="https://api.github.com/user/repos";
		authentication=oauth2("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");
	}
	
 
	 @Test
	  public void gettingAllRepositories() {
		  given()
		        .auth()
		        .oauth2("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")
		   .when()
		   .get("https://api.github.com/user/repos")
		   .then()
		   .log()
		   .body()
		   .statusCode(200);
	  }
	 @Test(enabled=true)
	  public void createRepositories() {
		 JSONObject data=new JSONObject();
		 data.put("name","RestAssuresCreationsNew");
		 data.put("description","I am created By RestAssured");
		 data.put("homepage", "https://github.com/Nayathani1");
		  given()
		        .auth()
		        .oauth2("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")
		        .header("Content-Type","application/json")
		        .body(data.toJSONString())
		   .when()
		   .post("https://api.github.com/user/repos")
		   .then()
		   .log()
		   .body()
		   .statusCode(201)
		   .time(Matchers.lessThan(7000L), TimeUnit.MILLISECONDS);
	  }
}
