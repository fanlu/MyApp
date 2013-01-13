package com.fl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.mmtzj.util.TaobaoConstant;
import org.junit.Before;
import org.junit.Test;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.internal.util.WebUtils;
import com.taobao.api.request.ItemsGetRequest;
import com.taobao.api.request.ItemsSearchRequest;
import com.taobao.api.request.UserGetRequest;
import com.taobao.api.response.ItemsGetResponse;
import com.taobao.api.response.ItemsSearchResponse;
import com.taobao.api.response.UserGetResponse;

public class TaoBaoKeTest {
	
	TaobaoClient client;
	
	@Before
	public void getclient(){
		client = new DefaultTaobaoClient(
				TaobaoConstant.SANDBOX_URL, TaobaoConstant.APP_KEY,
				TaobaoConstant.APP_SERCET_SANDBOX);// 实例化TopClient类
	}

	@Test
	public void testUserGet() {
		UserGetRequest req = new UserGetRequest();// 实例化具体API对应的Request类
		req.setFields("user_id,uid,nick,sex,buyer_credit,seller_credit,location,created,last_visit,birthday,type,status,alipay_no,alipay_account,alipay_account,email,consumer_protection,alipay_bind");
		req.setNick("fanlu7311");
		UserGetResponse response;
		try {
			response = client.execute(req); // 执行API请求并打印结果
			System.out.println("result:" + response.getBody());
			System.out.println("nick:" + response.getUser().getNick());
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testItemsSearch(){
		ItemsSearchRequest req=new ItemsSearchRequest();
		req.setFields("num_iid,title,nick,pic_url,cid,price,type,delist_time,post_fee,score,volume");
		req.setNicks("tbtest561");
		try {
			ItemsSearchResponse response = client.execute(req);
			System.out.println(response.getBody());
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}
	
	@Test 
	public void testItemsGet(){
		ItemsGetRequest req=new ItemsGetRequest();
		req.setFields("num_iid,title,nick,pic_url,cid,price,type,delist_time,post_fee,score,volume");
		req.setNicks("tbtest649");
		try {
			ItemsGetResponse response = client.execute(req);
			System.out.println(response.getBody());
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSessionKey(){
		Map<String, String> param = new HashMap<String, String>();
		param.put("appkey", TaobaoConstant.APP_KEY);
		param.put("encode", "utf-8");
		try {
			String s = WebUtils.doPost(TaobaoConstant.GET_SESSION_CLIENT_URL, param, 3000, 3000);
			System.out.println(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getaccesstoken(){
		String url = "https://oauth.taobao.com/token";
        Map<String, String> param = new HashMap<String, String>();

        param.put("grant_type", "authorization_code");

        param.put("code", "xMuBtklMeuxr0AujfjOZ3lht1");

        param.put("client_id", TaobaoConstant.APP_KEY);

        param.put("client_secret", TaobaoConstant.APP_SERCET_SANDBOX);
        param.put("redirect_uri", "http://localhost");

        param.put("scope", "item");

        param.put("view", "web");

        param.put("state", "");

        try {
			WebUtils.doPost(url, param, 3000, 3000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
