package cn.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.springmvc.model.User;
import cn.springmvc.service.UserService;

@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("index")
	public String index(){
		System.out.println(111);
		return "index";
	}
	
	@RequestMapping("add")
	@ResponseBody
	public User add(){
		User u = new User();
		u.setNickname("吾册那");
		u.setState(2);
		
		userService.insertUser(u);
		
		return u;
	}
	
	@RequestMapping("getAll")
	@ResponseBody
	public List<User> getUsers(){
		return userService.getAll();
	} 
}
