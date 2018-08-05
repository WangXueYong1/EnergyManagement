package cn.itheima.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itheima.dao.DeviceInformationMapper;
import cn.itheima.dao.DeviceStatusMapper;
import cn.itheima.pojo.DeviceInformation;
import cn.itheima.pojo.DeviceStatus;
@Service
public class DeviceStatusService {
	
	@Autowired
	private DeviceStatusMapper deviceStatusMapper;
	
	public List<DeviceStatus> findDeviceStatusById(int example){
		List<DeviceStatus> findDeviceInformationById = deviceStatusMapper.findDeviceStatusById(example);
		return findDeviceInformationById;
	};

	public List<DeviceStatus> findDeviceStatusByDate(Date example){
		List<DeviceStatus> findDeviceInformationByType = deviceStatusMapper.findDeviceStatusByDate(example);
		return findDeviceInformationByType;
	};
	public void insertDeviceStatus(DeviceStatus id){
		deviceStatusMapper.insertDeviceStatus(id);
	};
}
