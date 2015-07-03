package com.dc.weixin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.zip.GZIPInputStream;

class Sign {
	public static void main(String[] args) {
		String tmpAccessToken  = "Nc7XUyg8r6DsdvkocTOFbBRuvaEMclKuABDo6PjXHa83Iga7Z6g7mGmNzPAkD8ywiGPszAmvGSI82WrPaG-T8Smjnr5WwGI6WXXiwwDg5YA";
		String tmpJsapi  = "sM4AOVdWfPE4DxkXGEs8VEH8x_f50tH9IZBiekJNsslpJlFKJpRTVDE5V39QkJRHbnkSiZSsUjZ36m3-baZMAg";
		
		
		String appID = "wxbcbcde2f59739276";
		String secret = "016a2b656a742207fa2752b9e8455584";
		String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appID+"&secret="+secret;
		String jsapiUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+tmpAccessToken+"&type=jsapi";
		Map<String, String> ret;

//		String access_token = getDataFromUrl(accessTokenUrl);
//		System.out.println(access_token);
//		String jsapi = getDataFromUrl(jsapiUrl);
//		System.out.println(jsapi);
		
		//{"responsedata":{"timestamp":"1432867543","appid":"wxbcbcde2f59739276","RTN_CODE":"000000","nonce_str":"34f45d84-7bdd-4eb9-9e76-f2629454d501","RTN_MSG":"获取签名成功","signature":"b5f591cdf38ab8f5f7f4635e2945894c20d4d9cf","url":"http://fuzhoutest.scity.cn/wapsite/testScanDebug.jsp"}}
		
//		String jsapi_ticket = tmpJsapi;
//
//		// 注意 URL 一定要动态获取，不能 hardcode
//		String url = "http://fuzhoutest.scity.cn/csp-wapsite/testScan.jsp";
//		ret = sign(jsapi_ticket, url);
//		for (Map.Entry entry : ret.entrySet()) {
//			System.out.println(entry.getKey() + ", " + entry.getValue());
//		}
//		url = "http://fuzhoutest.scity.cn/csp-wapsite/wxJsSdkDemo/wxJsSdk.html";
//		ret = sign(jsapi_ticket, url);
//		for (Map.Entry entry : ret.entrySet()) {
//			System.out.println(entry.getKey() + ", " + entry.getValue());
//		}
		ret = signed();
		for (Map.Entry entry : ret.entrySet()) {
			System.out.println(entry.getKey() + ", " + entry.getValue());
		}
		System.out.println("10e4b41453dbfcd6e3b359e50e270c5e404795a1".equals("10e4b41453dbfcd6e3b359e50e270c5e404795a1"));
	};

	public static Map<String, String> sign(String jsapi_ticket, String url) {
		Map<String, String> ret = new HashMap<String, String>();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String string1;
		String signature = "";

		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str
				+ "&timestamp=" + timestamp + "&url=" + url;

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);

		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private static String getDataFromUrl(String sUrl){
		String sResult = "";
		try {
			URL url = null;
			url = new URL(sUrl);
			HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
			// 因为这个是post请求,设立需要设置为true
			urlConn.setDoOutput(true);
			urlConn.setDoInput(true);
			// 设置以POST方式
			urlConn.setRequestMethod("GET");
			// Post 请求不能使用缓存
			urlConn.setUseCaches(false);
			urlConn.setInstanceFollowRedirects(false);

//			urlConn.setRequestProperty("Host", sUrl);
			urlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:5.0) Gecko/20100101 Firefox/5.0");
			urlConn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			urlConn.setRequestProperty("Accept-Language", "zh-cn,zh;q=0.5");
			urlConn.setRequestProperty("Accept-Encoding", "gzip, deflate");
			urlConn.setRequestProperty("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
			urlConn.setRequestProperty("Connection", "keep-alive");

			urlConn.connect();

			// DataOutputStream流
			OutputStream out = urlConn.getOutputStream();
			// 刷新、关闭
			out.flush();
			out.close();

			InputStream is = null;
			String encoding = urlConn.getContentEncoding();

			InputStreamReader inputReader;
			BufferedReader bufReader;

			if (encoding != null && encoding.toLowerCase().equalsIgnoreCase("gzip")) {
				is = new GZIPInputStream(urlConn.getInputStream());
			} else {
				is = urlConn.getInputStream();
			}

			inputReader = new InputStreamReader(is, "GBK");
			bufReader = new BufferedReader(inputReader);
			String lineTmp = "";
			StringBuffer lineBuff = new StringBuffer();
			while (((lineTmp = bufReader.readLine()) != null)) {
				lineBuff.append(lineTmp).append("\n");
			}
			sResult = lineBuff.toString();
			bufReader.close();
			inputReader.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sResult;
	}
	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
	
	public static Map<String, String> signed() {

		Map<String, String> ret = new HashMap<String, String>();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String string1;
		String signature = "";

		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=sM4AOVdWfPE4DxkXGEs8VEH8x_f50tH9IZBiekJNsslJ65HMfS1bRQmJuKcrwExXz3eaxiK7jHFcBhlkxZfgHA" +
				"&noncestr=92b92b76-df6e-47cc-b2d4-4809c5f70685&timestamp=1432869595" +
				"&url=http://192.168.1.130:8080/csp-wapsite/testScanDebug.jsp";

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		ret.put("url", "http://192.168.1.130:8080/csp-wapsite/testScanDebug.jsp");
		ret.put("jsapi_ticket", "sM4AOVdWfPE4DxkXGEs8VEH8x_f50tH9IZBiekJNsslJ65HMfS1bRQmJuKcrwExXz3eaxiK7jHFcBhlkxZfgHA");
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);
		
		return ret;
	}
}
