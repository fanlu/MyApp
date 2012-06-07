package com.fl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.internal.util.WebUtils;
import com.taobao.api.request.UserGetRequest;
import com.taobao.api.response.UserGetResponse;

public class TaoBaoKeTest {

	@Test
	public void testUserGet() {
		TaobaoClient client = new DefaultTaobaoClient(
				TaobaoConstant.SANDBOX_URL, TaobaoConstant.APP_KEY,
				TaobaoConstant.APP_SERCET_SANDBOX);// 实例化TopClient类
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
