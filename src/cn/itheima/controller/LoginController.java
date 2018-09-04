package cn.itheima.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itheima.dao.UserMapper;
import cn.itheima.pojo.User;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private UserMapper usermapper;
	//跳转到登录页面/login/submit.jsp
	@RequestMapping("/login")
	public String login() throws Exception{
		return "login";
	}
	
	@RequestMapping("/submit")
	public String submit(String username, String password ,HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		//判断用户名密码的正确性,如果正确则将登录信息放入session中
		//这里简写,真正项目中需要去数据库中校验用户名和密码
		if(username != null){
			User user=new User();
			user.setUsername(username);
			user.setPassword(password);
			User checkUser = usermapper.checkUser(user);
			if (checkUser!=null) {
				session.setAttribute("username", username);
				return "success";
			}			
		}
		
		//跳转到列表页
		return "error";
	}
}
