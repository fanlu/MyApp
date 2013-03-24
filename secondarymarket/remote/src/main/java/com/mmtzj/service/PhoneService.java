package com.mmtzj.service;

import com.mmtzj.util.MapAdapter;

import javax.jws.WebMethod;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: wangxin
 * Date: 13-3-22
 * Time: 下午9:35
 * To change this template use File | Settings | File Templates.
 */
public interface PhoneService {

    @WebMethod
    @XmlJavaTypeAdapter(MapAdapter.class)
    public ArrayList<HashMap<String, Object>> getCDRRecord400(HashMap<String, Object> param);

    @WebMethod
    @XmlJavaTypeAdapter(MapAdapter.class)
    public ArrayList<HashMap<String, Object>> getMemberByPhoneInfo(String phone400, String extension);
}
