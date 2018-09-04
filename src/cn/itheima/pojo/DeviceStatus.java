package cn.itheima.pojo;

import java.util.Date;

public class DeviceStatus {
	int deviceId;
	Date date;
	Double power;
	Double predictPower;
	public Double getPredictPower() {
		return predictPower;
	}
	public void setPredictPower(Double predictPower) {
		this.predictPower = predictPower;
	}
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getPower() {
		return power;
	}
	public void setPower(Double power) {
		this.power = power;
	}
	
}
