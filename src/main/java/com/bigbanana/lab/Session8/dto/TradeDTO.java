package com.bigbanana.lab.Session8.dto;

import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public class TradeDTO extends BaseDTO{
	private List<Pair<Long,Integer>> items;
	private PayStatus payStatus;
	private List<String> PayOrders;
	private TradeStatus tradeStatus;
	private DeliverStatus deliverStatus;
	private List<String> deliverOrders;
}