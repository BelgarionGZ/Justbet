package es.uvigo.esei.tfg.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;

import es.uvigo.esei.tfg.util.Settings;
import es.uvigo.esei.tfg.util.SettingsSingleton;

public class LoginRequest extends AbstractRequest {
	private static String getParams(Settings settings) {
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("username", settings.getProperty("USERNAME")));
		params.add(new BasicNameValuePair("password", settings.getProperty("PASSWORD")));

		return URLEncodedUtils.format(params, settings.getProperty("ENCODING"));
	}

	public static String sendRequest() throws IOException {
		Settings settings = SettingsSingleton.getInstance();

		HttpPost post = new HttpPost(settings.getProperty("LOGIN_URL"));

		post.setHeader(HTTP_HEADER_ACCEPT, settings.getProperty("APPLICATION_JSON"));
		post.setHeader(HTTP_HEADER_CONTENT_TYPE, settings.getProperty("APPLICATION_URLENCODED"));
		post.setHeader(HTTP_HEADER_X_APPLICATION, settings.getProperty("APP_KEY"));

		post.setEntity(new StringEntity(getParams(settings), settings.getProperty("ENCODING_UTF8")));

		String responseString = executeRequest(post, settings);

		return responseString;
	}
}