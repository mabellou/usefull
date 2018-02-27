package com.mabellou.economy.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;

import org.apache.coyote.Request;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.javalite.http.Http;
import org.javalite.http.Post;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ConnectService {

	private String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.167 Safari/537.36";
	private String CSRF = "v9n8T9htaHW5yanBAyATVGKaeTKyuHi3TJdnKQsGdCHdy1KDZ9n7VDf6RrAzI2nbcEmvcABg34TS5zLcFlQaOrdJn2tmR9hOOLfOFeiAswitQF5P9duP4sbwIZUT28Fo";
	private String RESOURCE_URL
	  = "";
	
	public void connect() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("User-Agent", USER_AGENT);
		headers.set("Cookie", "Cookies");
		headers.set("CSRF", CSRF);
		
		String requestJson = "{\""}";
		HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

		ResponseEntity<String> response = restTemplate.exchange(RESOURCE_URL, HttpMethod.POST, entity, String.class);
				
		System.out.println(response);
	}
	
	public void connect2() {
		String requestJson = "{\""}";
		try {
			Element response = Jsoup.connect(RESOURCE_URL)
			 .requestBody(requestJson)
			 .header("Content-Type", "application/json")
			 .header("User-Agent", USER_AGENT)
			 .header("CSRF", CSRF)
			 .cookie("", "")
			 .ignoreContentType(true)
			 .post();
			System.out.println(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
public void connect3() {
		
		String requestJson = "{\""}";
		Post post = Http.post(RESOURCE_URL, requestJson)
				 .header("Content-Type", "application/json")
				 .header("User-Agent", USER_AGENT)
				 .header("CSRF", CSRF)
				 .header("Cookie", "");
		//System.out.println(post.text());
		System.out.println(post.headers());
		List<HttpCookie> cookies = new ArrayList<>();
		post.headers().get("Set-Cookie").forEach(setCookie -> cookies.addAll(HttpCookie.parse(setCookie)));
		System.out.println(cookies);
	}
	
	public void connect4() {
		try {
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost(RESOURCE_URL);
			String requestJson = "{\}";
			StringEntity entity;
			entity = new StringEntity(requestJson);
			request.addHeader("content-type", "application/x-www-form-urlencoded");
		    request.addHeader("Content-Type", "application/json");
		    request.addHeader("User-Agent", USER_AGENT);
		    request.addHeader("Cookie", "");
		    request.addHeader("CSRF", CSRF);
		    request.setEntity(entity);
		    HttpResponse response = httpClient.execute(request);
		    BufferedReader rd = new BufferedReader(
		            new InputStreamReader(response.getEntity().getContent()));
		    StringBuffer result = new StringBuffer();
		    String line = "";
		    while ((line = rd.readLine()) != null) {
		    	result.append(line);
		    }
		    System.out.println(result);
		} catch (Exception e1) {
			e1.printStackTrace();
		}    
	}
}
