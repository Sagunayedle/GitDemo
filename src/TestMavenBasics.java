import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import file.playLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class TestMavenBasics {

	public static void main(String[] args) {
        // Base URI
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        // Sending the request
     String response=   given().log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(playLoad.AddPlace()).
        when().post("/maps/api/place/add/json").
        then().assertThat().statusCode(200).body("scope", equalTo("APP"))
        .header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
     System.out.println(response);
   JsonPath js=new JsonPath(response);
  String placeId= js.getString("place_id");
     
     System.out.println(placeId);
     
     //Update address
     String newAddress="70 winter walk, USA";
     given().log().all().queryParam("key", "quick123").header("Content-Type", "application/json")
     .body("{\r\n"
     		+ "\"place_id\":\""+placeId+"\",\r\n"
     		+ "\"address\":\""+newAddress+"\",\r\n"
     		+ "\"key\":\"qaclick123\"\r\n"
     		+ "}").
     when().put("/maps/api/place/update/json")
     .then().log().all().assertThat().statusCode(200)
     .body("msg", equalTo("Address successfully updated"));   
     
     System.out.println("Thank you");

////get update
//
//      String getplaceresponse=  given().log().all().queryParam("key","quaclick123").
//    		  queryParam("place_id",placeId )
//        .when().get("/maps/api/place/add/json")
//        .then().log().all().assertThat().statusCode(200).extract().response().asString();
//      
//      JsonPath js1=new JsonPath(getplaceresponse);
//      
//     String actualAddress= js1.getString("address");
//        System.out.println(actualAddress);
//        	
        
        
        
	}
	
}
