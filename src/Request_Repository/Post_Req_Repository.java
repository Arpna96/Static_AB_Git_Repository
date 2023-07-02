package Request_Repository;

import java.io.IOException;
import java.util.ArrayList;

import API_Method.Common_Utility_Method;

public class Post_Req_Repository {
	public static String BaseURI() {
		String BaseUri = "https://reqres.in/";
		return BaseUri;
	}
	public static String Resource() {
		String Resource = "api/users";
		return Resource;
	}
	public static String Post_Cre_TC() throws IOException {
		ArrayList<String> Req_Data = Common_Utility_Method.DataReadExcel("Post_Api", "TC1");
		//System.out.println(Req_Data);
		String Req_name = Req_Data.get(1);
		String Req_job = Req_Data.get(2);
		String RequestBody = "{\r\n"
				+ "    \"name\": \""+Req_name+"\",\r\n"
				+ "    \"job\": \""+Req_job+"\"\r\n"
				+ "}";
		return RequestBody;
	}

}
