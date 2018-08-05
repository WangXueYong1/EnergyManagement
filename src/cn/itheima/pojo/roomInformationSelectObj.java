package cn.itheima.pojo;

import java.util.Date;

public class roomInformationSelectObj {
	Integer roomId;	
	Double temperature;
	Double illumination;	
	Date time;
	Date startTime;
	Date endTime;
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
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	

	
}
