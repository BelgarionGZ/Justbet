package es.uvigo.esei.tfg.api;

import java.io.IOException;

import org.apache.http.client.methods.HttpPost;

import es.uvigo.esei.tfg.util.Settings;
import es.uvigo.esei.tfg.util.SettingsSingleton;

public class KeepAliveRequest extends AbstractRequest {
	public static String sendRequest(String ssoToken) throws IOException {
		Settings settings = SettingsSingleton.getInstance();

		HttpPost post = new HttpPost(settings.getProperty("KEEP_ALIVE_URL"));

		post.setHeader(HTTP_HEADER_ACCEPT, settings.getProperty("APPLICATION_JSON"));
		post.setHeader(HTTP_HEADER_X_APPLICATION, settings.getProperty("APP_KEY"));
		post.setHeader(HTTP_HEADER_X_AUTHENTICATION, ssoToken);

		String responseString = executeRequest(post, settings);

		return responseString;
	}
}