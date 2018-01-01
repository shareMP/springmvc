package com.mrl.springmvc_01_helloworld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorld {

	/**
	 * @return 使用@RequestMapping注解来映射请求的url
	 * 返回值会通过视图解析器解析成具体的物理视图
	 * 对于InternalResourceViewResolver
	 * 通过  prefix+returnVal+后缀 得到物理视图，做转发操作
	 * 
	 * WEB-INF/views/success.jsp
	 * 
	 */
	@RequestMapping("/helloworld")
	public String hello() {
		System.out.println("hello,world");
		System.out.println("kkkkkkkkkkkkkk");
		System.out.println(111);
		return "success";
	}
}
