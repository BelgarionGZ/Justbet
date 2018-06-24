package es.uvigo.esei.tfg.api;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import es.uvigo.esei.tfg.util.Settings;

public abstract class AbstractRequest {
	private final static Logger logger = Logger.getLogger(AbstractRequest.class);

	protected final static String HTTP_HEADER_ACCEPT = "Accept";
	protected final static String HTTP_HEADER_CONTENT_TYPE = "Content-Type";
	protected final static String HTTP_HEADER_X_APPLICATION = "X-Application";
	protected final static String HTTP_HEADER_X_AUTHENTICATION = "X-Authentication";

	protected static String executeRequest(HttpPost post, Settings settings) throws IOException {
		Builder requestConfigBuilder = RequestConfig.custom();
		requestConfigBuilder.setConnectionRequestTimeout(Integer.parseInt(settings.getProperty("TIMEOUT")));
		requestConfigBuilder.setSocketTimeout(Integer.parseInt(settings.getProperty("TIMEOUT")));

		post.setConfig(requestConfigBuilder.build());

		HttpClient httpClient = HttpClientBuilder.create().build();

		HttpResponse response = httpClient.execute(post);

		HttpEntity entity = response.getEntity();

		String responseString = EntityUtils.toString(entity);

		logger.debug("Response: " + responseString);

		return responseString;
	}
}