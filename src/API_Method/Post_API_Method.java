package API_Method;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;

public class Post_API_Method {
	public static int StatusCode(String BaseURI,String Resource, String RequestBody) {
		RestAssured.baseURI = BaseURI;
		int StatusCode = given().header("Content-Type", "application/json").body(RequestBody).when().post(Resource).getStatusCode();
		return StatusCode;
		
	}
	public static String ResponseBody(String BaseURI,String Resource, String RequestBody) {
		RestAssured.baseURI = BaseURI;
		String ResponseBody = given().header("Content-Type", "application/json").body(RequestBody).when().post(Resource).then().extract().response().asString();
		return ResponseBody;
	}

}
