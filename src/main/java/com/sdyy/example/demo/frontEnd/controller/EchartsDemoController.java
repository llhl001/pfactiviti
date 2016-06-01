package com.sdyy.example.demo.frontEnd.controller;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdyy.common.echarts.EchartData;
import com.sdyy.common.echarts.Series;

@Controller
@RequestMapping("/echartsDemo")
public class EchartsDemoController {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());

	/**
	 * 
	 * @Title: forExample
	 * @author：liuyx 
	 * @date：2016年1月4日下午2:48:24
	 * @Description: 简单柱状图示例
	 * http://localhost:8080/platform/echartsDemo/forExample.do
	 * @param request
	 * @return
	 */
	@RequestMapping("forExample")
	public String forExample(HttpServletRequest request){
		
		return "example/frontEnd/echarts/frontEnd_echarts_forExample";
	}
	
	/**
	 * 
	 * @Title: forAjaxExample
	 * @author：liuyx 
	 * @date：2016年1月4日下午2:48:24
	 * @Description: 柱状图获取后台数据示例
	 * http://localhost:8080/platform/echartsDemo/forExample.do
	 * @param request
	 * @return
	 */
	@RequestMapping("forAjaxExample")
	public String forAjaxExample(HttpServletRequest request){
		
		return "example/frontEnd/echarts/frontEnd_echarts_forAjaxExample";
	}
	
	@RequestMapping("/ajaxExampleData")  
    @ResponseBody  
    public EchartData ajaxExampleData() {  
        logger.info("lineData....");  
          
        List<String> legend = new ArrayList<String>(Arrays.asList(new String[]{"最高气温"}));//数据分组  
        List<String> category = new ArrayList<String>(Arrays.asList(new String []{"周一","周二","周三","周四","周五","周六","周日"}));//横坐标  
        List<Series> series = new ArrayList<Series>();//纵坐标  
          
        series.add(new Series("最高气温", "line",   
                        new ArrayList<Integer>(Arrays.asList(  
                                21,23,28,26,21,33,44))));  
          
        EchartData data=new EchartData(legend, category, series);  
        return data;  
    }  
      
}
