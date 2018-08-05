package cn.itheima.dao;

import java.util.List;

import cn.itheima.pojo.DeviceInformation;
import cn.itheima.pojo.ItemsExample;
import cn.itheima.pojo.selectObj_BuildFloorRoomDateType;

public interface DeviceInformationMapper {
	/*
	 * <select id="findDeviceInformationById" parameterType="int"
	 * resultType="cn.itheima.pojo.DeviceInformation"> select * from
	 * deviceInformation where deviceId=#{id} </select>
	 * 
	 * <select id="findDeviceInformationByUserType" parameterType="string"
	 * resultType="cn.itheima.pojo.DeviceInformation"> select * from
	 * deviceInformation where type like '%${value}%' </select>
	 * 
	 * <insert id="insertDeviceInformation"
	 * parameterType="cn.itheima.pojo.DeviceInformation" > <selectKey
	 * keyProperty="id" order="AFTER" resultType="java.lang.Integer"> select
	 * LAST_INSERT_ID() </selectKey> insert into deviceInformation
	 * (deviceId,type,buildingId,floor,room,name)
	 * values(#{deviceId},#{type},#{buildingId},#{floor},#{room},#{name})
	 * </insert>
	 */
	DeviceInformation findDeviceInformationById(int example);

	List<DeviceInformation> findDeviceInformationByType(String example);

	List<Integer> findFloorByBuilding(DeviceInformation d);

	List<Integer> findRoomByBuildingFloor(DeviceInformation d);
	List<DeviceInformation> findDeviceIdByDeviceInformation(selectObj_BuildFloorRoomDateType d);
	void insertDeviceInformation(DeviceInformation id);
}
