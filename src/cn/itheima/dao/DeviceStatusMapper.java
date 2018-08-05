package cn.itheima.dao;

import java.util.Date;
import java.util.List;

import cn.itheima.pojo.DeviceStatus;
import cn.itheima.pojo.selectObj_BuildFloorRoomDateType;

public interface DeviceStatusMapper {
	/*<select id="findDeviceStatusById" parameterType="int" resultType="cn.itheima.pojo.DeviceStatusMapper">
	select * from deviceStatus where deviceId=#{id}
</select>

<select id="findDeviceStatusByUserType" parameterType="string" resultType="cn.itheima.pojo.DeviceStatusMapper">
	select * from deviceStatus where type like '%${value}%'
</select>

<insert id="insertDeviceStatus" parameterType="cn.itheima.pojo.DeviceStatusMapper" >
	<selectKey keyProperty="id" order="AFTER" resultType="java.lang.Integer">
		select LAST_INSERT_ID()
	</selectKey>
	insert into deviceStatus (deviceId,date,power) values(#{deviceId},#{date},#{power})
</insert>*/
	
	List<DeviceStatus> findDeviceStatusById(int id);
	List<DeviceStatus> findDeviceStatusByDate(Date date);
	void insertDeviceStatus(DeviceStatus state);
	List<DeviceStatus> findDeviceStatusByBuildFloorDateType(selectObj_BuildFloorRoomDateType type);
	List<DeviceStatus> findDeviceStatusByDeviceStatus(selectObj_BuildFloorRoomDateType d);
}
