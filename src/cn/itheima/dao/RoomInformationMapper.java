package cn.itheima.dao;

import java.util.List;

import cn.itheima.pojo.RoomInformation;
import cn.itheima.pojo.roomInformationSelectObj;

public interface RoomInformationMapper {
	List<RoomInformation> findRoomInformationByRoomInformationSelectObj(roomInformationSelectObj room);
	void insertRoomInformation(RoomInformation room);

}
