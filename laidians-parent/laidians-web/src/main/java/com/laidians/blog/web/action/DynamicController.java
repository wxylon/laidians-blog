package com.laidians.blog.web.action;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.laidians.framework.web.action.annotation.BaseActionController;

/**
 * 动态控制器入口
 * @author wangx
 */
@Controller
public class DynamicController extends BaseActionController {
	
	/**
	 * 根地址访问
	 * @author wangx
	 */
	@RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
	public String index(HttpServletRequest request, ModelMap model) {
		model.put("stylesheet", "12121.css");
		model.put("title", "12121.css");
		return "pages/hello";
	}
	
	/**
	 * /index.html 地址访问
	 * @author wangx
	 */
	@RequestMapping(value = "/index.html", method = {RequestMethod.GET, RequestMethod.POST})
	public String indexForWeblogic(HttpServletRequest request, ModelMap model) {
		return index(request, model);
	}
}
