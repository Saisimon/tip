package net.saisimon.icloud;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.DefaultCookieSpecProvider;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpsClient implements Closeable {
	
	private static final Logger LOG = LoggerFactory.getLogger(HttpsClient.class);
	
	private CloseableHttpClient httpClient;
	private HttpClientContext context;
	
	public HttpsClient() throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException {
		httpClient = createHttpsClient();
	}
	
	public void setContext(CookieStore cookieStore) {
		context = HttpClientContext.create();
		Registry<CookieSpecProvider> registry = RegistryBuilder  
                .<CookieSpecProvider> create()  
                .register(CookieSpecs.DEFAULT, new DefaultCookieSpecProvider()).build();
		context.setCookieSpecRegistry(registry);
		context.setCookieStore(cookieStore);
	}
	
	public HttpClientContext getContext() {
		return context;
	}
	
	public String get(String url) throws IOException {
		return get(url, null);
	}
	
	public String get(String url, Header[] headers) throws IOException {
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeaders(headers);
		HttpResponse httpResponse = httpClient.execute(httpGet, context);
		String result = getBody(httpResponse.getEntity());
		if (LOG.isDebugEnabled()) {
			LOG.debug("Get: " + url + ", Result: " + result);
		}
		httpGet.releaseConnection();
		return result;
	}
	
	public String post(String url) throws IOException {
		return post(url, null, null);
	}

	public String post(String url, Header[] headers) throws IOException {
		return post(url, headers, null);
	}

	public String post(String url, HttpEntity entity) throws IOException {
		return post(url, null, entity);
	}
	
	public String post(String url, Header[] headers, HttpEntity entity) throws IOException {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeaders(headers);
		httpPost.setEntity(entity);
		HttpResponse httpResponse = httpClient.execute(httpPost, context);
		String result = getBody(httpResponse.getEntity());
		if (LOG.isDebugEnabled()) {
			LOG.debug("Post: " + url + ", Result: " + result);
		}
		httpPost.releaseConnection();
		return result;
	}
	
	@Override
	public void close() throws IOException {
		if (null != httpClient) {
			httpClient.close();
		}
	}

	public static String getBody(HttpEntity entity) throws UnsupportedOperationException, IOException {
		StringBuilder body = new StringBuilder();
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				body.append(line);
			}
		}
		return body.toString();
	}
	
	private static CloseableHttpClient createHttpsClient()
			throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (chain, authType) -> true).build();
		SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext);
		return HttpClients.custom()
				.setUserAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36")
				.setSSLSocketFactory(sslConnectionSocketFactory).build();
	}
}
