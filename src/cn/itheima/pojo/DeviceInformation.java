package cn.itheima.pojo;

public class DeviceInformation {
	int deviceId;
	String type;
	int buildingId;
	int floor;
	int room;
	String name;
	boolean is_working;
	String energy_consumption_status;
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public boolean isIs_working() {
		return is_working;
	}
	public void setIs_working(boolean is_working) {
		this.is_working = is_working;
	}
	public String getEnergy_consumption_status() {
		return energy_consumption_status;
	}
	public void setEnergy_consumption_status(String energy_consumption_status) {
		this.energy_consumption_status = energy_consumption_status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
	
}
