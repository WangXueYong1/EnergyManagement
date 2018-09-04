package cn.itheima.dao;

import cn.itheima.pojo.DeviceBPN;

public interface DeviceBPNMapper {
	DeviceBPN findDeviceBPNById(int id);
	
    void insertDeviceBPN(DeviceBPN deviceBPN); 
   void updateDeviceBPN(DeviceBPN deviceBPN); 
}