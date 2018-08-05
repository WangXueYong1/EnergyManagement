package cn.itheima.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.itheima.pojo.User;
import cn.itheima.service.UserService;
import cn.itheima.vo.EchartData;
import cn.itheima.vo.Series;
import com.google.gson.Gson;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/findUserByid")
	public User findUserById(int id) {
		User findUserById = userService.findUserById(id);
		return findUserById;
	}

	@RequestMapping("/findUserByName")
	public User findUserByUserName(String name) {
		User findUserById = userService.findUserByUserName(name);
		return findUserById;
	}

	@RequestMapping("/addUser")
	public void insertUser(User user) {
		userService.insertUser(user);
	}
	@RequestMapping("/echart")
	public void echart() {
		ModelAndView modelAndView = new ModelAndView();

		
		modelAndView.setViewName("echart");
	}
	
	
	/*@RequestMapping("/getEchartData")
	@ResponseBody
	public String getEchartData(HttpServletRequest request,HttpServletResponse response) throws IOException {
		List<String> legendList = new ArrayList<String>();

		List<String> categoryList = new ArrayList<String>();
		categoryList.add("20170301-20170308");
		categoryList.add("20170309-20170316");
		categoryList.add("20170317-20170324");
		categoryList.add("20170325-20170401");
		categoryList.add("20170402-20170409");
		categoryList.add("20170410-20170417");
		categoryList.add("20170418-20170425");
		List<Series> seriesList = new ArrayList<>();
		List<Integer> data1 = new ArrayList<>();
		data1.add(111);
		data1.add(272);
		data1.add(313);
		data1.add(474);
		data1.add(515);
		data1.add(474);
		data1.add(515);
		seriesList.add(new Series("消费金额(元)", "line", data1));
		List<Integer> data2 = new ArrayList<>();
		data2.add(111);
		data2.add(222);
		data2.add(333);
		data2.add(444);
		data2.add(555);
		data2.add(444);
		data2.add(555);
		seriesList.add(new Series("周环比(100%)", "line", data2));
		EchartData echartData = new EchartData(legendList, categoryList, seriesList);
		 return echartData; 
		System.out.println("success");
		System.out.println("success");
		System.out.println("success");
		System.out.println("success");
		System.out.println("success");
		System.out.println("success");
		System.out.println("success");
		System.out.println("success");
//		String s = "[{ 'name': 'cxh', 'sex': '1','website':'http://www.bejson.com' },{ 'name': 'ss', 'sex': '1','website':'http://www.ij2ee.com' }]";
		response.setContentType("text/html;charset=utf-8");
		String s = JSON.toJSONString(echartData);
		response.getWriter().println(s);
		Gson gson = new Gson();
		String json = gson.toJson(echartData);
		return json;
	}*/
	
	
//	@RequestMapping(value="/detail",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	
	@RequestMapping(value="/detail", produces="text/html;charset=utf-8")
	@ResponseBody
	public String detail(Long id) throws Exception{
		User customer = new User();
		Gson gson = new Gson();
		gson.toJson(customer);
		String s="{\"result\":\"success\",\"msg\":\"成功\"}";
		return s;
	}
	
	@RequestMapping("/monitor")
	public ModelAndView monitor(){
		ModelAndView modelAndView = new ModelAndView();		
		modelAndView.setViewName("monitor");
		return modelAndView;
	}
	
}
