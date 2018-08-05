package cn.itheima.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itheima.dao.DeviceInformationMapper;
import cn.itheima.dao.DeviceStatusMapper;
import cn.itheima.pojo.DeviceInformation;
@Service
public class DeviceInformationService {
	@Autowired
	private DeviceInformationMapper deviceInformationMapper;
	
	public DeviceInformation findDeviceInformationById(int example){
		DeviceInformation findDeviceInformationById = deviceInformationMapper.findDeviceInformationById(example);
		return findDeviceInformationById;
	};

	public List<DeviceInformation> findDeviceInformationByType(String example){
		List<DeviceInformation> findDeviceInformationByType = deviceInformationMapper.findDeviceInformationByType(example);
		return findDeviceInformationByType;
	};
	public void insertDeviceInformation(DeviceInformation id){
		deviceInformationMapper.insertDeviceInformation(id);
	};
	
}
