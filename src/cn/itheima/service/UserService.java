package cn.itheima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itheima.dao.UserMapper;
import cn.itheima.pojo.User;

@Service
public class UserService {
	@Autowired
	private UserMapper usermapper;
	
	public User findUserById(int id){
		User findUserById = usermapper.findUserById(id);
		return findUserById;
	}
	public User findUserByUserName(String name){
		User findUserById = usermapper.findUserByUserName(name);
		return findUserById;
	}
	public void insertUser(User user){
		 usermapper.insertUser(user);		
	}
}
