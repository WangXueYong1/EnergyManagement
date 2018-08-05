package cn.itheima.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itheima.dao.RoomInformationMapper;
import cn.itheima.pojo.RoomInformation;
import cn.itheima.pojo.roomInformationSelectObj;
@Service
public class RoomInformationService {
	
	@Autowired
	private RoomInformationMapper roomInformationMapper;
	
	public List<RoomInformation> findRoomInformationById(roomInformationSelectObj example){
		List<RoomInformation> findDeviceInformationById = roomInformationMapper.findRoomInformationByRoomInformationSelectObj(example);
		return findDeviceInformationById;
	};

}
