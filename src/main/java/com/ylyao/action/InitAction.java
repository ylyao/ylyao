package com.ylyao.action;

import java.io.File;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ylyao.mail.JavaReciveMail;
import com.ylyao.model.MsgInfo;
import com.ylyao.model.OrderBean;
import com.ylyao.service.OrderService;
import com.ylyao.service.SystemService;
import com.ylyao.util.BaseAction;
import com.ylyao.util.ServiceLocator;

public class InitAction extends BaseAction{
	
	private static final long serialVersionUID = -3423363411757855848L;

	private Log log = LogFactory.getLog(InitAction.class);
	
    protected ServiceLocator service = null;

	private SystemService systemService = null;
	
	private OrderService orderService = null;
	
	public void init() {
		service = ServiceLocator.getInstance();
		systemService = (SystemService) ServiceLocator.getService("systemService");
		orderService = (OrderService) ServiceLocator.getService("orderService");

		new Thread() {
			@Override
			public void run() {
				try {

					log.info("启动自动删除缓存图片程序：");
					List<MsgInfo> msgInfos;
					while(true){
						Thread.sleep(6000);
						msgInfos = systemService.findMessageByDate("tempPic");
						for (MsgInfo msg : msgInfos){
							boolean success = (new File(msg.getMsgInfo())).delete();
							if (success){
								systemService.msgDealSuccess(msg.getId());
							}else{
								systemService.msgDealFail(msg.getId());
								log.error("删除文件【"+msg.getMsgInfo()+"】失败！");
							}
						}
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	public void reciveMail(){
		final JavaReciveMail javaReciveMail = JavaReciveMail.getInstance();
		final String webPath = this.getWebPath();
		
		new Thread() {
			@Override
			public void run() {
				try {
					log.info("启动邮箱指令接收程序：");
					List<OrderBean> orderBeans;
					Long id = null;
					while(true){
						Thread.sleep(6000);
						orderBeans = javaReciveMail.getOrder();
						for (OrderBean order : orderBeans){
							id = orderService.doOrder(order,webPath);
							log.info("处理指令【"+id+"-"+order.getType()+"】！");
						}
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	public void doInit(){
		reciveMail();
		try {
			writeJson("邮箱指令接收启动成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

}
