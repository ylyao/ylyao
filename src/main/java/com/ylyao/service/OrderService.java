package com.ylyao.service;

import com.ylyao.model.OrderBean;

public interface OrderService {

	public Long doOrder(OrderBean order, String webPath);

}
