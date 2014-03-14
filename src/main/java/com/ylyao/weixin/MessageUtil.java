package com.ylyao.weixin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class MessageUtil {

	public static final String RESP_MESSAGE_TYPE_TEXT = null;
	public static final Object REQ_MESSAGE_TYPE_TEXT = null;
	public static final Object REQ_MESSAGE_TYPE_IMAGE = null;
	public static final Object REQ_MESSAGE_TYPE_LOCATION = null;
	public static final Object REQ_MESSAGE_TYPE_LINK = null;
	public static final Object REQ_MESSAGE_TYPE_VOICE = null;
	public static final Object REQ_MESSAGE_TYPE_EVENT = null;
	public static final Object EVENT_TYPE_SUBSCRIBE = null;
	public static final Object EVENT_TYPE_UNSUBSCRIBE = null;
	public static final Object EVENT_TYPE_CLICK = null;

	public static Map<String, String> parseXml(HttpServletRequest request) {
		// TODO Auto-generated method stub
        try {
			String wxMsgXml = IOUtils.toString(request.getInputStream(),"utf-8");
			return readStringXmlOut(wxMsgXml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}
	
	@SuppressWarnings("rawtypes")
	public static Map<String,String> readStringXmlOut(String xml) {
        Map<String,String> map = new HashMap<String,String>();
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML

            Element rootElt = doc.getRootElement(); // 获取根节点

            String iter = rootElt.elementTextTrim("head"); // 获取根节点下的子节点head

            map.put("result", iter);
                   
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

	public static String textMessageToXml(TextMessage textMessage) {
		// TODO Auto-generated method stub
		return null;
	}

}
