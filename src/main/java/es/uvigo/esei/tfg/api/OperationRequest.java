package es.uvigo.esei.tfg.api;

import java.io.IOException;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import es.uvigo.esei.tfg.util.Settings;
import es.uvigo.esei.tfg.util.SettingsSingleton;

public class OperationRequest extends AbstractRequest {
	public static String sendRequest(String operation, String params, String ssoToken, String url) throws IOException {
		Settings settings = SettingsSingleton.getInstance();

		HttpPost post = new HttpPost(settings.getProperty(url) + operation + "/");

		post.setHeader(HTTP_HEADER_ACCEPT, settings.getProperty("APPLICATION_JSON"));
		post.setHeader(HTTP_HEADER_CONTENT_TYPE, settings.getProperty("APPLICATION_JSON"));
		post.setHeader(HTTP_HEADER_X_APPLICATION, settings.getProperty("APP_KEY"));
		post.setHeader(HTTP_HEADER_X_AUTHENTICATION, ssoToken);

		post.setEntity(new StringEntity(params, settings.getProperty("ENCODING_UTF8")));

		String responseString = executeRequest(post, settings);

		return responseString;
	}
}