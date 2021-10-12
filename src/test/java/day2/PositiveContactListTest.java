package day2;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class PositiveContactListTest {
	String id=null;
	  @Test(enabled = false,description = "GetAll contactList")
	  public void getContactListInfo() {
		 Response res= given()
		  .when()
		  		.get("http://3.13.86.142:3000/contacts");
		 System.out.println("Total response time: "+res.getTime());
		  res.then()
		  	.log()
		  	.body()
		  	.statusCode(404);	
		  
	  }
	  
	  
	  @Test(enabled = false,description = "Getting specific contact")
	  public void getSpecificContact() {
		  given()
		  .when()
		  		.get("http://3.13.86.142:3000/contacts/60eba23e170734047659ad54")
		  .then()
		  	.log()
		  	.body()
		  	.statusCode(200);	
		  
	  }
	  
	  
	  @Test(enabled = false,description = "Getting specific contact")
	  public void getSpecificContact2() {
		 Response res= given()
		  .when()
		  		.get("http://3.13.86.142:3000/contacts/60eba23e170734047659ad54");
		 
		 System.out.println("Response time: "+res.getTime());
		  res.then()
		  	.log()
		  	.body()
		  	.statusCode(200);	
		  
	  }
	  
	  
	  @Test(enabled = true,description = "adding specific contact")
	  public void addingContact() {
		  JSONObject details=new JSONObject();
		  JSONObject loc=new JSONObject();
		  JSONObject emp=new JSONObject();
		  
		  loc.put("city", "Hubli");
		  loc.put("country", "India");
		  emp.put("jobTitle", "QA");
		  emp.put("company", "LTI");
		  details.put("firstName", "Rahul");
		  details.put("lastName", "Nayathani");
		  details.put("email", "mkarthik2299@gmail.com");
		  details.put("location", loc);
		  details.put("employer", emp);
		  
		  
		 ExtractableResponse<Response> res=given()
		  		.header("Content-Type","application/json")
		  		.body(details.toJSONString())
		  	.when()
		  		.post("http://3.13.86.142:3000/contacts")
		  	.then()
		  		.log()
		  		.body()
		  		.statusCode(200)
		  		.extract();
		  		//.path("_id");
		  		
		  
		id=res.path("_id");
		System.out.println("id: "+res.path("_id"));
		System.out.println("Fname: "+res.path("firstName"));
		System.out.println("Lname: "+res.path("lastName"));
		System.out.println("Lname: "+res.path("employer.jobTitle"));
	  
	  }
	  
	  @Test(enabled = true,dependsOnMethods = "addingContact",description = "updating specific contact")
	  public void updatingContact() {
		  JSONObject details=new JSONObject();
		  JSONObject loc=new JSONObject();
		  JSONObject emp=new JSONObject();
		  
		  loc.put("city", "Hubli");
		  loc.put("country", "India");
		  emp.put("jobTitle", "QA");
		  emp.put("company", "LTI");
		  details.put("firstName", "Kasturi");
		  details.put("lastName", "Morab");
		  details.put("email", "katya@gmail.com");
		  details.put("location", loc);
		  details.put("employer", emp);
		  
		  
		  given()
		  		.header("Content-Type","application/json")
		  		.body(details.toJSONString())
		  	.when()
		  		.put("http://3.13.86.142:3000/contacts/"+id)
		  	.then()
		  		.log()
		  		.body()
		  		.statusCode(204);//for update status code is 204
		 
	}
	  
	  
	  
	  @Test(enabled = true,dependsOnMethods = "updatingContact",description = "check after updating specific contact")
	  public void getSpecificContact3() {
		
		  given()
		  	.when()
		  		.get("http://3.13.86.142:3000/contacts/"+id)
		  	.then()
		  		.log()
		  		.body()
		  		.statusCode(200);
		  		
	}
	  
	  @Test(enabled = true,dependsOnMethods = "getSpecificContact3",description = "deleting a specific contact")
	  public void deletingContact() {
		
		  given()
		  	.when()
		  		.delete("http://3.13.86.142:3000/contacts/"+id)
		  	.then()
		  		.log()
		  		.body()
		  		.statusCode(404);
		  		
		  System.out.println(id+ "Deleted successfully");
		  
	}
	  
	  
	  
	}