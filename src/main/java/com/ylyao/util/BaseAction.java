package com.ylyao.util;
/**
 * 版权所有：恒生聚源
 * 项目名称:researchreport
 * 创建者: loubin
 * 创建日期: 2010-6-10
 * 文件说明: 常用对象的封装
 * 最近修改者：loubin
 * 最近修改日期：2010-6-10
 */

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.opensymphony.xwork2.ActionSupport;
import com.ylyao.action.MatrixToImageWriter;

public class BaseAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 677438071040615160L;
	
	public static final String JSONACTION = "jsonAction";
	
	private String detail;//页面数据展示
	
	private String operatorState;//页面保存信息展示
	
	public  Log log = LogFactory.getLog(getClass());
	
	private Map<String, Object> session;
	
	private WebApplicationContext ctx;
	
	protected final Object getBean(String beanName) {
		 ctx = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
	     return ctx.getBean(beanName);
	}

    protected HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();
    }

    protected HttpServletResponse getResponse() {
        return ServletActionContext.getResponse();
    }

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public String getOperatorState() {
		return operatorState;
	}

	public void setOperatorState(String operatorState) {
		this.operatorState = operatorState;
	}

	public Map<String, Object> getSession(){
		return session;
	}
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	/**
	* @Description: 向页面输出json格式的字符串
	* @param jsonString json字符串
	* @return null
	* @throws exception
	*/
	public void writeJson(String jsonString) throws Exception{
		this.getResponse().setContentType("text/html;charset=utf-8");
		this.getResponse().getWriter().write(jsonString);
	}
    
	/**
	 * 输出想要的属性，JsonConfig
	 * @author ylyao
	 * @param obj
	 * @param props
	 * @param cfg
	 * @throws IOException
	 */
	protected void writeJson(Object obj, String[] props,
			JsonConfig cfg) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		final Map<String, String> propMap = map;
		if (cfg == null){
			cfg = new JsonConfig();
		}
		if (props != null){
			for (String str : props) {
				map.put(str, str);
			}
			cfg.setJsonPropertyFilter(new PropertyFilter() {
				public boolean apply(Object source, String prop, Object value) {
					if (propMap.containsKey(prop)) {
						return false;
					}
					return true;
				}
			});
		}
		//日期处理
		cfg.registerJsonValueProcessor(Date.class , new JsonValueProcessor(){

			@Override
			public Object processArrayValue(Object arg0, JsonConfig arg1) {
				// TODO Auto-generated method stub
				return process(arg0);
			}

			@Override
			public Object processObjectValue(String arg0, Object arg1,
					JsonConfig arg2) {
				// TODO Auto-generated method stub
				return process(arg0);
			}
			
			private Object process(Object value) {
		        try {
		            if (value instanceof Date) {
		               return DateUtil.formatDate((Date)value, "yyyy-MM-dd HH:mm"); 
		            }  
		            return value == null ? "" : value.toString();  
		        } catch (Exception e) {  
		            return "";  
		        }
		    }  
			
		});
		String value = JSONArray.fromObject(obj, cfg).toString();
		OutputStream os = null;
		try {
			this.getResponse().setContentType("application/json;charset=UTF-8");
			os = getResponse().getOutputStream();
			os.write(value.getBytes("UTF-8"));
		} catch (Exception e) {
			getResponse().sendError(
					HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统错误");
			return;
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}
	
	public String getWebPath(){
		return getRequest().getSession().getServletContext().getRealPath("/");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void tdcDeal(String url,String fileName){
		String text = url;
		int width = 100;
		int height = 100;
		// 二维码的图片格式
		String format = "gif";
		String filePath = getWebPath()+"pictures\\tdc\\"+fileName+"."+format;
		File file = new File(filePath);
		if (file.exists()){
			return ;
		}
		
		Hashtable hints = new Hashtable();
		// 内容所使用编码
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		BitMatrix bitMatrix;
		try {
			bitMatrix = new MultiFormatWriter().encode(text,
					BarcodeFormat.QR_CODE, width, height, hints);
			// 生成二维码
			File outputFile = new File(filePath);
			MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
