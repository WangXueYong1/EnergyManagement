package cn.itheima.pojo;

import java.util.Date;

public class RoomInformation {
	Integer roomId;
	Double temperature;
	Double illumination;
	Date time;
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public Double getIllumination() {
		return illumination;
	}
	public void setIllumination(Double illumination) {
		this.illumination = illumination;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	
	
}
