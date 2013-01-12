package com.mmtzj;
public class TaobaoConstant {

	//AppKey和AppSercet
	public final static String APP_KEY = "12675864";
	public final static String APP_SERCET_SANDBOX = "sandboxb0af08ac01b4815ccc8a7c8f2";
	public final static String APP_SERCET = "ae8a2cdb0af08ac01b4815ccc8a7c8f2";
	
	//沙箱环境地址
	public final static String SANDBOX_URL = "http://gw.api.tbsandbox.com/router/rest";
	//正式环境需要设置为
	public final static String APP_URL = "http://gw.api.taobao.com/router/rest";      
	
	public final static String APP_HTTPS_URL = "https://eco.taobao.com/router/rest";
	//获取授权码的地址
	public final static String GET_SESSION_SANDBOX_URL = "http://container.api.tbsandbox.com/container";
	
	public final static String GET_SESSION_URL = "http://container.open.taobao.com/container";
	
	public final static String GET_SESSION_CLIENT_URL = "http://my.open.taobao.com/auth/authorize.htm";
}