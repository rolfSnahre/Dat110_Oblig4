package no.hvl.dat110.aciotdevice.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class RestClient {
	Gson gson = new Gson();
	
	public RestClient() {
		// TODO Auto-generated constructor stub
	}

	private static String logpath = "/accessdevice/log";

	public void doPostAccessEntry(String message) {

		// TODO: implement a HTTP POST on the service to post the message
		try {
			URL url = new URL("http://localhost:8080" + logpath );
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);
			
			String accessMessage = "{\"message\": \""+ message + "\"}";
			
			byte[] input = accessMessage.getBytes();
			
			OutputStream os = conn.getOutputStream();
			os.write(input);
			os.flush();
			os.close();

			conn.getInputStream();

	
		}catch(IOException e) {
		
		}
		
		return;
		
	}
	
	private static String codepath = "/accessdevice/code";
	
	public AccessCode doGetAccessCode() {

		try {
			URL url = new URL("http://localhost:8080" + codepath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			InputStreamReader is = new InputStreamReader(conn.getInputStream());
			
			
			return gson.fromJson(is, AccessCode.class);
	
		}catch(IOException e) {
		
		}

		return null;
	}
}
