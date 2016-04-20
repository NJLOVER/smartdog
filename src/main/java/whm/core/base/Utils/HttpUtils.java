package whm.core.base.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;

import javax.net.SocketFactory;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory;
import org.apache.log4j.Logger;

public class HttpUtils {
	private static Logger logger=Logger.getLogger(HttpUtils.class);
	private static int iTimeOut = 10000;//ConfigUtil.readIntValue("config", "time_out", 10000);

	public static String sendSSLPostRequest(String reqURL, Map<String, String> params) {  
        String result = null;  
        ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
        if(reqURL.startsWith("https")){
        	Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
        }
        HttpClient httpClient = new HttpClient();
        trustAllHosts();
        PostMethod method = new PostMethod(reqURL);  
  
        Part[] parts = new Part[params.size()];  
        Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();  
        int i = 0;  
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();  
            parts[i++] = new StringPart(entry.getKey(), entry.getValue(),"utf-8");  
        }
        method.setRequestEntity(new MultipartRequestEntity(parts, method.getParams()));  
        // 使用系统提供的默认的恢复策略  
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
        try {
        	//设置超时
        	httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(iTimeOut);
        	httpClient.getHttpConnectionManager().getParams().setSoTimeout(iTimeOut);
            // 执行method  
            int statusCode = httpClient.executeMethod(method);  
            if (statusCode != HttpStatus.SC_OK) {  
            	logger.error("Method failed: " + method.getStatusLine());  
            }
            StringBuffer sOutPkg = new StringBuffer();
			BufferedReader inbf;
			try {
				inbf = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), "UTF-8"));
				String line = "";
				while ((line = inbf.readLine()) != null) {	
					sOutPkg.append(line);
				}
//				logger.info("获取实体的操作数据："+sOutPkg.toString());
				result=sOutPkg.toString();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
               
        } catch (HttpException e) {  
            // 发生致命的异常，可能是协议不对或者返回的内容有问题  
        	logger.error(e.getMessage());  
            e.printStackTrace();  
        } catch (IOException e) {  
            // 发生网络异常  
        	logger.error(e.getMessage());  
            e.printStackTrace();  
        } finally {  
            // 释放连接  
            method.releaseConnection();  
        }  
        return result;  
    }  
	
	
	/**
	 * Trust every server - dont check for any certificate
	 */
	public static void trustAllHosts() {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[] {};
			}

			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}
		} };

		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static class MySecureProtocolSocketFactory implements
			SecureProtocolSocketFactory {
		private SSLContext sslcontext = null;

		private SSLContext createSSLContext() {
			SSLContext sslcontext = null;
			try {
				sslcontext = SSLContext.getInstance("SSL");
				sslcontext.init(null,
						new TrustManager[] { new TrustAnyTrustManager() },
						new java.security.SecureRandom());
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (KeyManagementException e) {
				e.printStackTrace();
			}
			return sslcontext;
		}

		private SSLContext getSSLContext() {
			if (this.sslcontext == null) {
				this.sslcontext = createSSLContext();
			}
			return this.sslcontext;
		}

		public Socket createSocket(Socket socket, String host, int port,
								   boolean autoClose) throws IOException, UnknownHostException {
			return getSSLContext().getSocketFactory().createSocket(socket, host,
					port, autoClose);
		}

		public Socket createSocket(String host, int port) throws IOException,
				UnknownHostException {
			return getSSLContext().getSocketFactory().createSocket(host, port);
		}

		public Socket createSocket(String host, int port, InetAddress clientHost,
								   int clientPort) throws IOException, UnknownHostException {
			return getSSLContext().getSocketFactory().createSocket(host, port,
					clientHost, clientPort);
		}

		public Socket createSocket(String host, int port, InetAddress localAddress,
								   int localPort, HttpConnectionParams params) throws IOException,
				UnknownHostException, ConnectTimeoutException {
			if (params == null) {
				throw new IllegalArgumentException("Parameters may not be null");
			}
			int timeout = params.getConnectionTimeout();
			SocketFactory socketfactory = getSSLContext().getSocketFactory();
			if (timeout == 0) {
				return socketfactory.createSocket(host, port, localAddress,
						localPort);
			} else {
				Socket socket = socketfactory.createSocket();
				SocketAddress localaddr = new InetSocketAddress(localAddress,
						localPort);
				SocketAddress remoteaddr = new InetSocketAddress(host, port);
				socket.bind(localaddr);
				socket.connect(remoteaddr, timeout);
				return socket;
			}
		}

		// 自定义私有类
		private class TrustAnyTrustManager implements X509TrustManager {

			public void checkClientTrusted(X509Certificate[] chain, String authType)
					throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType)
					throws CertificateException {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[] {};
			}
		}
	}

}
