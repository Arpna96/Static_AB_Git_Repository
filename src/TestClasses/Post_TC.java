package TestClasses;

import java.io.IOException;
import java.time.LocalDateTime;

import org.testng.Assert;

import API_Method.Common_Utility_Method;
import Request_Repository.Post_Req_Repository;
import io.restassured.path.json.JsonPath;

public class Post_TC {
	public static void execute() throws IOException {
		int StatusCode = API_Method.Post_API_Method.StatusCode(Post_Req_Repository.BaseURI(), Post_Req_Repository.Resource(),Post_Req_Repository.Post_Cre_TC());
		System.out.println(StatusCode);
		
		String ResponseBody = API_Method.Post_API_Method.ResponseBody(Post_Req_Repository.BaseURI(), Post_Req_Repository.Resource(),Post_Req_Repository.Post_Cre_TC());
		System.out.println(ResponseBody);
		
		String RequestBody = Post_Req_Repository.Post_Cre_TC();
		Common_Utility_Method.EvidenceCreator("Post_TC", RequestBody, ResponseBody, StatusCode);
		
		JsonPath Jspreq = new JsonPath(RequestBody);
		String Req_Name = Jspreq.getString("name");
		String Req_job = Jspreq.getString("job");
		LocalDateTime currenttime = LocalDateTime.now();
		String Expecteddate = currenttime.toString().substring(0, 11);
		
		JsonPath Jspres = new JsonPath(ResponseBody);
		String Res_Name = Jspres.getString("name");
		String Res_job = Jspres.getString("job");
		String Res_createdAt = Jspres.getString("createdAt");
		Res_createdAt = Res_createdAt.toString().substring(0, 11);
		
		Assert.assertEquals(Res_Name, Req_Name);
		Assert.assertEquals(Res_job, Req_job);
		Assert.assertEquals(Res_createdAt, Expecteddate);
	}

}
