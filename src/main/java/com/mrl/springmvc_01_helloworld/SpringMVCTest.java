package com.mrl.springmvc_01_helloworld;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lwqMR
 * 
 * @SessionAttributes 除了可以通过属性名指定需要放到会话中的属性外(value属性值)
	还可以通过模型属性的对象类型指定哪些模型属性需要放到会话中(types属性值) 
	注解只能放在类的上面
 *
 */
/*@SessionAttributes(value={"user"},types={String.class})*/
@Controller
@RequestMapping("/springMvc")
public class SpringMVCTest {

	private static final String SUCCESS = "success";

	
	/**
	 * @param id
	 *由@ModelAttribute  标记的方法会在每个目标方法执行之前被springmvc调用
	 *
	 *1.调用@ModelAttribute注解修饰的方法，实际上把@ModelAttribute方法中Map中的数据放在了implicitModel中。
	  2.解析请求处理器的目标参数，实际上改目标参数来自于WebDataBinder的target属性
	  		a。创建WebDataBinder对象 
	  		           确定objectname属性 ：若传入的attrName属性值为空串，则objectname为类名第一个字母小写，若目标方法 的pojo属性使用了@ModelAttribute修饰，则attrName即为@ModelAttribute的value属性值
	  		           确定target属性：在implicitModel中查找attrName对应的属性值，若存在，返回。若不存在，则验证当前Handdler是否使用了@SessionAttributes进行修饰，若使用了，则尝试从session获取attrName对应的属性值，若session中没有对应的属性值，则抛出异常。
	  						  若handler没有使用SessionAttributes进行修饰，或者SessionAttributes没有使用value值指定的key和attrName相匹配，则通过反射创建POJO对象
	  		b.springmvc把表单 的请求参数赋给了WebDataBinder的target对应的属性
	  		c.springmvc会把webdatabinder的name和target给到implicitModel
	  		d。把webdatabinder的target作为参数传递给目标方法的入参
	  3.
	 *
	 */
	
	@ModelAttribute
	public void getUser(@RequestParam(value="id",required=false) Integer id,Map<String,Object> map){
		if(id != null){
			//模拟从数据库中获取对象
			User user = new User(1, "tom","123","tom@gmail.com",20);
			map.put("user", user);
			System.out.println("从数据库中获取一个对象");
		}
	}
	
	/**
	 * @param user
	 * @return
	 * 
	 * map.put("user", user); 键要和目标方法传入对象的User第一个字母小写
	 * springmvc帮我们做的是在调用目标方法之前，调用@ModelAttribute修饰的方法，把表单数据填充进去，然后将对象传入目标方法
	 */
	@RequestMapping("/testModelAttribute")
	public String testModelAttribute(User user){
		System.out.println("修改："+user);
		return SUCCESS;
	}
	
	@RequestMapping("/testSessionAttributes")
	public String testSessionAttributes(Map<String,Object> map){
		User user = new User("lwq","111","123@qq.com",20);
		map.put("user", user);
		map.put("school", "YuCui");
		return SUCCESS;
	}
	
	/**
	 * 目标方法可以添加Map类型的参数
	 * 也也可以是Model类型 或者ModelMap类型
	 * @param map
	 * @return
	 */
	@RequestMapping("/testMap")
	public String testMap(Map<String,Object> map){
		System.out.println(map.getClass().getName());
		map.put("names", Arrays.asList("Tom","Jerry","Mike"));
		return SUCCESS;
	}
	
	
	/**
	 * 目标方法的返回值ModelAndView类型
	 * 可以包含视图和模型信息
	 * 把model中的数据放在request的域对象中
	 * @return
	 */
	@RequestMapping("/testModelAndView")
	public ModelAndView testModelAndView(){
		String viewname = SUCCESS;
		ModelAndView modelAndView = new ModelAndView(viewname);
		
		//添加模型数据到ModelAndView
		modelAndView.addObject("time",new Date());
		
		
		return modelAndView;
	}
	
	/**
	 * 可以使用 Servlet原生的API作为目标方法的参数 具体有 HttpServletRequest • HttpServletResponse
	 * • HttpSession 
	 * • java.security.Principal
	 *  • Locale
	 *  • InputStream
	 *  •OutputStream •
	 *  Reader 
	 *  • Writer
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/testServletAPI")
	public String testServletAPI(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("testServletAPI:" + request + "," + response);
		return SUCCESS;

	}

	@RequestMapping(value = "/testPojo")
	public String testPojo(User user) {

		System.out.println("testPojo:" + user);
		return SUCCESS;

	}

	/**
	 * @CookieValue 映射一个cookie值 属性同@RequestParam
	 * @param sessionId
	 * @return
	 */
	@RequestMapping(value = "/testCookieValue")
	public String testCookieValue(@CookieValue("JSESSIONID") String sessionId) {
		System.out.println("testCookieValue:" + sessionId);
		return SUCCESS;
	}

	/**
	 * 用法同RequestParam 映射请求头信息
	 * 
	 * @param al
	 * @return
	 */
	@RequestMapping(value = "/testRequestHeader")
	public String testRequestHeader(@RequestHeader(value = "Accept-Language") String al) {
		System.out.println("testRequestHeader,Accept-Language:" + al);
		return SUCCESS;
	}

	/**
	 * value:映射参数值
	 * 
	 * @RequestParam 映射请求参数 value：请求参数的参数名 required：参数是否必须，默认为true
	 *               defaultValue:请求参数的默认值
	 * @param un
	 * @param age
	 * @return
	 */
	@RequestMapping(value = "/testRequestParam")
	public String testRequestParam(@RequestParam(value = "username") String un,
			@RequestParam(value = "age", required = false) Integer age) {
		System.out.println("testRequestParam,username:" + un + "age:" + age);
		return SUCCESS;
	}

	/**
	 * @return 1.除了修饰方法还可以修饰类 2.– 类定义处：提供初步的请求映射信息。相对于 WEB 应用的根目录 –
	 *         方法处：提供进一步的细分映射信息。相对于类定义处的 URL 若类定义处未标注 @RequestMapping，则方法处标记的
	 *         URL相对于WEB应用的根目录
	 * 
	 */
	@RequestMapping("/testRequestMapping")
	public String testRequestMapping() {
		System.out.println("testRequestMapping");
		return SUCCESS;
	}

	/**
	 * @return 1.使用method属性指定请求方式,常用 映射更加精细化
	 */
	@RequestMapping(value = "/testMethod", method = RequestMethod.POST)
	public String testMethod() {
		System.out.println("testMethod");
		return SUCCESS;
	}

	/**
	 * @return 可以使用params和headers来更加精确的映射请求,支持简单的表达式
	 */
	@RequestMapping(value = "/testParamAndHeaders", params = { "username", "age!=10" }, headers = {
			"Accept-Language=zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3" })
	public String testParamAndHeaders() {
		System.out.println("testParamAndHeaders");
		return SUCCESS;
	}

	@RequestMapping("/testAntPath/*/abc")
	public String testAntPath() {
		System.out.println("testAntPath");
		return SUCCESS;
	}

	/**
	 * @PathVariable 可以来映射url中的占位符到目标方法的参数列表中
	 * @param id
	 * @return
	 */
	@RequestMapping("/testPathVariable/{id}")
	public String testPathVariable(@PathVariable("id") Integer id) {
		System.out.println("testPathVariable :" + id);
		return SUCCESS;
	}

	@RequestMapping(value = "/testRest/{id}", method = RequestMethod.GET)
	public String testRest(@PathVariable Integer id) {
		System.out.println("test Rest GET" + id);
		return SUCCESS;
	}

	/**
	 * Rest风格的url 以CRUD为例 – /order/1 HTTP GET ：得到 id = 1 的 order – /order/1 HTTP
	 * DELETE：删除 id = 1的 order – /order/1 HTTP PUT：更新id = 1的 order – /order HTTP
	 * POST：新增 order
	 * 
	 * 如何发送PUT和DELETE请求： 1.配置HiddenHttpMethodFilter 2.发送POST请求
	 * 3.再发送POST请求时携带一个隐藏域，name=_method的隐藏域,值为DELETE或者PUT
	 * 
	 * 在SpringMvc的目标方法中，如何得到id，使用@PathVariable注解
	 * 
	 * @return
	 */
	@RequestMapping(value = "/testRest/{id}", method = RequestMethod.POST)
	public String testRest() {
		System.out.println("test Rest POST");
		return SUCCESS;
	}

	@RequestMapping(value = "/testRest/{id}", method = RequestMethod.DELETE)
	public String testRestDelete(@PathVariable Integer id) {
		System.out.println("test RestDelete" + id);
		return SUCCESS;
	}

	@RequestMapping(value = "/testRest/{id}", method = RequestMethod.PUT)
	public String testRestPut(@PathVariable Integer id) {
		System.out.println("test RestPut" + id);
		return SUCCESS;
	}

}
