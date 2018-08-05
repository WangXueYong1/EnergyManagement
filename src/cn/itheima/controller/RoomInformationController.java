package cn.itheima.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import cn.itheima.dao.RoomInformationMapper;
import cn.itheima.pojo.DeviceStatus;
import cn.itheima.pojo.RoomInformation;
import cn.itheima.pojo.roomInformationSelectObj;
import cn.itheima.service.RoomInformationService;
import cn.itheima.vo.EchartData;

@Controller
@RequestMapping("/roomInformation")
public class RoomInformationController {
	@Autowired
	private RoomInformationService server;
	
	@Autowired
	private RoomInformationMapper roomInformationMapper;

	Gson gson = new Gson();
	SimpleDateFormat dfd = new SimpleDateFormat("MM-dd HH:mm");
	SimpleDateFormat dfh = new SimpleDateFormat("HH:mm");
	
	@RequestMapping("/insertRoomInformation")
	@ResponseBody
	public String insertRoomInformation(RoomInformation room) throws ParseException{
		
		
		Date date = room.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date2 = df.format(date);
		date = df.parse(date2);
		room.setTime(date);
		room.setIllumination(2 + Math.random() + 0.5);
		room.setTemperature(2 + Math.random() + 2.5);
		roomInformationMapper.insertRoomInformation(room);
		for (int i = 0; i < 23; i++) {
			date = new Date(date.getTime() + 60 * 60 * 1000L);
			room.setTime(date);
			room.setIllumination(i + Math.random() + 0.5);
			room.setTemperature(i + Math.random() + 2.5);
			roomInformationMapper.insertRoomInformation(room);
		}
		String json = gson.toJson(room);
		return json;
		
	}
	
	
	@RequestMapping("/monitor")
	public ModelAndView monitor(){
		ModelAndView modelAndView = new ModelAndView();		
		modelAndView.setViewName("roomIformation");
		return modelAndView;
	}
	
	@RequestMapping("/findRoomInformationByRoomInformationSelectObj")
	@ResponseBody
	public String findRoomInformationByRoomInformationSelectObj(roomInformationSelectObj room) throws Exception {
		List<RoomInformation> DeviceInformation = roomInformationMapper.findRoomInformationByRoomInformationSelectObj(room);
		EchartData echartData = new EchartData();		
		for (int j = 0; j < DeviceInformation.size(); j++) {
			echartData.data.add(DeviceInformation.get(j).getTemperature());
			if (room.getEndTime().getTime()-room.getStartTime().getTime()< 70 * 60 * 60 * 1000L) {
				echartData.xAxisData.add(dfh.format(DeviceInformation.get(j).getTime()));
			} else {
				echartData.xAxisData.add(dfd.format(DeviceInformation.get(j).getTime()));
			}
		}
		
		String json = gson.toJson(echartData);
		return json;
	}

	@RequestMapping("/findRoomInformationByRoomInformationSelectObj2")
	@ResponseBody
	public String findRoomInformationByRoomInformationSelectObj2(roomInformationSelectObj room) throws Exception {
		List<RoomInformation> DeviceInformation = roomInformationMapper.findRoomInformationByRoomInformationSelectObj(room);
		EchartData echartData = new EchartData();
		for (int j = 0; j < DeviceInformation.size(); j++) {
			echartData.data.add(DeviceInformation.get(j).getIllumination());
			if (room.getEndTime().getTime()-room.getStartTime().getTime()< 70 * 60 * 60 * 1000L) {
				echartData.xAxisData.add(dfh.format(DeviceInformation.get(j).getTime()));
			} else {
				echartData.xAxisData.add(dfd.format(DeviceInformation.get(j).getTime()));
			}
		}
		
		String json = gson.toJson(echartData);
		return json;
	}

}
