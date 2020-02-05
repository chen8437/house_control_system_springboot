//package com.yechrom.cloud.util;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.URISyntaxException;
//import java.util.Map;
//
//import org.apache.commons.codec.Charsets;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpHost;
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.ParseException;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.ResponseHandler;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.utils.URIBuilder;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.BasicResponseHandler;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//import org.omg.CORBA.portable.UnknownException;
//
//public class HttpRequestUtil {
//
////	private static CloseableHttpClient httpClient;
//	/**
//	 * 发送http post请求
//	 *
//	 * @param url
//	 * @param json
//	 * @return
//	 * @throws IOException
//	 * @throws ParseException
//	 */
//	public static String httpPostWithJson(String url, String json) {
//		String returnValue = "这是默认返回值，接口调用失败";
//		CloseableHttpClient httpClient = HttpClients.createDefault();
//		ResponseHandler<String> responseHandler = new BasicResponseHandler();
//		try {
//			// 第一步：创建HttpClient对象
//			httpClient = HttpClients.createDefault();
//			// 第二步：创建httpPost对象
//			HttpGet httpPost = new HttpGet(url);
////			HttpPost httpPost = new HttpPost(url);
//			// 第三步：给httpPost设置JSON格式的参数
//
//			StringEntity requestEntity = new StringEntity(json, "utf-8");
//
//			requestEntity.setContentEncoding("UTF-8");
//			httpPost.addHeader("Content-type", "application/json");
////			httpPost.addHeader("Host", "ops.picccdn.cn");
////			httpPost.addHeader("Authorization", "a1s2d3f4");
//
////			httpPost.setEntity(requestEntity);
//
//			// 第四步：发送HttpPost请求，获取返回值
//			returnValue = httpClient.execute(httpPost, responseHandler);
//			// 调接口获取返回值时，必须用此方法
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				httpClient.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		// 第五步：处理返回值
//		return returnValue;
////		return null;
//	}
//
//
//
//
//
//
//	public static String httpGet(String url) throws ParseException, IOException {
//
//
//
//		CloseableHttpResponse response = null;
//        BufferedReader in = null;
//        String result = "";
//        try {
//        	CloseableHttpClient httpClient =HttpClients.createDefault();
//
//        	//设置代理
////        	HttpHost proxy = new HttpHost("proxy.piccnet.com.cn", 3128, "http");
//
//            HttpGet httpGet = new HttpGet(url);
//            RequestConfig requestConfig = RequestConfig.custom()/*.setProxy(proxy)*/.setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(30000).build();
//            httpGet.setConfig(requestConfig);
//            httpGet.setConfig(requestConfig);
//            httpGet.addHeader("Content-type", "application/json; charset=utf-8");
//            httpGet.setHeader("Accept", "application/json");
//            response = httpClient.execute(httpGet);
//            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//            StringBuffer sb = new StringBuffer("");
//            String line = "";
//            String NL = System.getProperty("line.separator");
//            while ((line = in.readLine()) != null) {
//                sb.append(line + NL);
//            }
//            in.close();
//            result = sb.toString();
//        } catch (IOException e) {
//            return result="发送请求过程中出现异常!";
//        } catch (UnknownException e) {
//            return result="发送请求过程中出现未知异常! 初次估计 获取请求参数不包含权限 或权限过期!";
//        } finally {
//            try {
//                if (null != response) {
//                    response.close();
//                }
//            } catch (IOException e) {
//            	return result="response.close方法调用失败!";
//            }
//        }
//        return result;
//	}
//
//}
