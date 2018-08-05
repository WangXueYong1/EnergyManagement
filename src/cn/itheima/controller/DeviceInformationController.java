package cn.itheima.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import cn.itheima.dao.DeviceInformationMapper;
import cn.itheima.dao.DeviceStatusMapper;
import cn.itheima.pojo.DeviceInformation;
import cn.itheima.pojo.DeviceStatus;
import cn.itheima.pojo.selectObj_BuildFloorRoomDateType;
import cn.itheima.service.DeviceInformationService;
import cn.itheima.vo.EchartData;

@Controller
@RequestMapping("/DeviceInformation")
public class DeviceInformationController {
	@Autowired
	private DeviceInformationService server;

	@Autowired
	private DeviceInformationMapper deviceInformationMapper;

	@Autowired
	private DeviceStatusMapper deviceStatusMapper;

	Gson gson = new Gson();

	@RequestMapping("/findById")
	@ResponseBody
	public ModelAndView itemsList(int id) throws Exception {
		DeviceInformation DeviceInformation = server.findDeviceInformationById(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("DeviceInformation", DeviceInformation);
		modelAndView.setViewName("itemList");

		return modelAndView;
	}

	@RequestMapping("/findDeviceInformationByType")
	@ResponseBody
	public ModelAndView findDeviceInformationByType(String type) throws Exception {
		List<DeviceInformation> DeviceInformation = server.findDeviceInformationByType(type);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("DeviceInformation", DeviceInformation);
		modelAndView.setViewName("itemList");
		return modelAndView;
	}

	@RequestMapping("/findRoomByBuildingFloor")
	@ResponseBody
	public String findRoomByBuildingFloor(DeviceInformation type) throws Exception {
		List<Integer> room = deviceInformationMapper.findRoomByBuildingFloor(type);
		String json = gson.toJson(room);
		return json;
	}

	@RequestMapping("/findFloorByBuilding")
	@ResponseBody
	public String findFloorByBuilding(DeviceInformation type) throws Exception {

		List<Integer> floor = deviceInformationMapper.findFloorByBuilding(type);
		String json = gson.toJson(floor);
		return json;
	}

	@RequestMapping("/findDeviceByDeviceInformation")
	@ResponseBody
	public String findDeviceByDeviceInformation(selectObj_BuildFloorRoomDateType type) throws Exception {
		List<DeviceInformation> d = deviceInformationMapper.findDeviceIdByDeviceInformation(type);
		String json = gson.toJson(d);
		return json;
	}

	

}
