package net.saisimon.icloud.http;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

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
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.client.BasicCookieStore;
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
	
	public void setCookieStore(String path, String md5) throws ClassNotFoundException, IOException {
		context = HttpClientContext.create();
		Registry<CookieSpecProvider> registry = RegistryBuilder  
                .<CookieSpecProvider> create()  
                .register(CookieSpecs.DEFAULT, new DefaultCookieSpecProvider()).build();
		context.setCookieSpecRegistry(registry);
		
		context.setCookieStore(loadCookie(path, md5));
	}
	
	public CookieStore getCookieStore() {
		CookieStore cookieStore = null;
		if (context != null) {
			cookieStore = context.getCookieStore();
		} else {
			cookieStore = new BasicCookieStore();
		}
		return cookieStore;
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
			LOG.debug("Post: " + url + ", Body: " + getBody(entity) + ", Result: " + result);
		}
		httpPost.releaseConnection();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public CookieStore loadCookie(String path, String md5) {
		File file = new File(path + File.separatorChar + md5.charAt(0) + File.separatorChar + md5.substring(1));
		CookieStore cs = new BasicCookieStore();
		if (file.exists()) {
			try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
				Object obj = in.readObject();
				if (obj instanceof List && null != obj) {
					for (Cookie cookie : (List<Cookie>) obj) {
						cs.addCookie(cookie);
						if (LOG.isDebugEnabled()) {
							LOG.debug(cookie.toString());
						}
					}
				}
			} catch (Exception e) {
				LOG.error("Load Cookie Fail, Reason: " + e.getMessage(), e);
				return cs;
			}
		} else {
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
			} catch (IOException e) {
				LOG.error("Create Cookie File Fail, Reason: " + e.getMessage(), e);
				return cs;
			}
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Load Cookie in " + file.getAbsolutePath());
		}
		return cs;
	}
	
	public void saveCookie(String path, String md5, CookieStore cookieStore) {
		File file = new File(path + File.separatorChar + md5.charAt(0) + File.separatorChar + md5.substring(1));
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
			} catch (IOException e) {
				LOG.error("Create Cookie File Fail, Reason: " + e.getMessage(), e);
				return;
			}
		}
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
			List<Cookie> cookies = cookieStore.getCookies();
			out.writeObject(cookies);
		} catch (IOException e) {
			LOG.error("Save Cookie Fail, Reason: " + e.getMessage(), e);
			return;
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Save Cookie in " + file.getAbsolutePath());
		}
	}
	
	@Override
	public void close() throws IOException {
		if (null != httpClient) {
			httpClient.close();
			httpClient = null;
		}
	}

	public static String getBody(HttpEntity entity) throws UnsupportedOperationException, IOException {
		if (null == entity) {
			return "";
		}
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
