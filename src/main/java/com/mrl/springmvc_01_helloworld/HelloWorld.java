package com.mrl.springmvc_01_helloworld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorld {

	/**
	 * @return ʹ��@RequestMappingע����ӳ�������url
	 * ����ֵ��ͨ����ͼ�����������ɾ����������ͼ
	 * ����InternalResourceViewResolver
	 * ͨ��  prefix+returnVal+��׺ �õ�������ͼ����ת������
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
