package cn.itheima.dao;

import cn.itheima.pojo.User;

public interface UserMapper {
	User findUserById(int id);

	User findUserByUserName(String name);
	User checkUser(User user);
    void insertUser(User user); 
}