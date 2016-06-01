package com.sdyy.example.demo.pushlet.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdyy.common.RetObj;

import nl.justobjects.pushlet.core.Dispatcher;
import nl.justobjects.pushlet.core.Event;


@Controller
@RequestMapping("/pushletDemo")
public class PushletDemoController {
	/**
	 * 
	 * @Title: pushMessage
	 * @author：liuyx 
	 * @date：2016年1月13日下午1:41:20
	 * @Description: 推送消息给相应的用户
	 * @param userId 用户的operatorId
	 * @param message 推送的消息
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("pushMessage")
	@ResponseBody
	public RetObj pushMessage(String userId,String message) throws UnsupportedEncodingException{
		//以test/pushMessage主题创建事件
		Event event = Event.createDataEvent("/test/pushMessage");  
		message = URLEncoder.encode(message,"UTF-8");
		event.setField("message",message);
		Dispatcher.getInstance().unicast(event, userId);
		return new RetObj(true);
	}
	
	@RequestMapping("forDemo")
	public String forDemo(){
		return "example/pushlet/pushlet_forDemo";
	}
	
}
