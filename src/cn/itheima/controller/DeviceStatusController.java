package cn.itheima.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import cn.itheima.dao.DeviceStatusMapper;
import cn.itheima.pojo.DeviceStatus;
import cn.itheima.pojo.selectObj_BuildFloorRoomDateType;
import cn.itheima.service.DeviceStatusService;
import cn.itheima.vo.EchartData;
import util.DataUtil;
import util.GreetingServer;

@Controller
@RequestMapping("/deviceStatus")
public class DeviceStatusController {
	@Autowired
	private DeviceStatusService server;

	@Autowired
	private DeviceStatusMapper deviceStatusMapper;

	Gson gson = new Gson();
	int[] layer = { 10, 10, 1 };

	@RequestMapping("/insertJsp")
	public ModelAndView insertJsp(DeviceStatus d) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("insertDeviceStatus");
		return modelAndView;
	}

	@RequestMapping("/receiveJsp")
	public void receiveJsp() throws Exception {
		String[] args;

		args = new String[1];
		args[0] = "6066";

		int port = Integer.parseInt(args[0]);
		try {
			Thread t = new GreetingServer(port);
			t.run();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Date date1 = new Date();
		int[] yearMonthDayHourMinuteSencondWeek = DataUtil.getYearMonthDayHourMinuteSencondWeek(date1);// 年月日时分秒星期几一年中第几个星期
		System.out.println(yearMonthDayHourMinuteSencondWeek.toString());

	}

	@RequestMapping("/train")
	public void train() throws Exception {
		double[][] data = new double[][] { { 2018, 8, 27, 0, 40, 3, 1, 35, 25, 35 },
				{ 2018, 8, 27, 1, 40, 3, 1, 35, 25, 35 }, { 2018, 8, 12, 2, 40, 3, 1, 35, 25, 35 },
				{ 2018, 8, 27, 3, 40, 3, 1, 35, 25, 35 }, { 2018, 8, 27, 4, 40, 3, 1, 35, 25, 35 },
				{ 2018, 8, 27, 5, 40, 3, 1, 35, 25, 35 }, { 2018, 8, 12, 6, 40, 3, 1, 35, 25, 35 },
				{ 2018, 8, 27, 7, 40, 3, 1, 35, 25, 35 }, { 2018, 8, 27, 8, 40, 3, 1, 35, 25, 35 },
				{ 2018, 8, 27, 9, 40, 3, 1, 35, 25, 35 }, { 2018, 8, 12, 10, 40, 3, 1, 35, 25, 35 },
				{ 2018, 8, 27, 11, 40, 3, 1, 35, 25, 35 }, { 2018, 8, 27, 12, 40, 3, 1, 35, 25, 35 },
				{ 2018, 8, 27, 13, 40, 3, 1, 35, 25, 35 }, { 2018, 8, 12, 14, 40, 3, 1, 35, 25, 35 },
				{ 2018, 8, 27, 15, 40, 3, 1, 35, 25, 35 }, { 2018, 8, 27, 16, 40, 3, 1, 35, 25, 35 },
				{ 2018, 8, 27, 17, 40, 3, 1, 35, 25, 35 }, { 2018, 8, 12, 18, 40, 3, 1, 35, 25, 35 },
				{ 2018, 8, 27, 19, 40, 3, 1, 35, 25, 35 }, { 2018, 8, 27, 20, 40, 3, 1, 35, 25, 35 },
				{ 2018, 8, 27, 21, 40, 3, 1, 35, 25, 35 }, { 2018, 8, 12, 22, 40, 3, 1, 35, 25, 35 },
				{ 2018, 8, 27, 23, 40, 3, 1, 35, 25, 35 } };
		// 设置目标数据，对应4个坐标数据的分类
		double[][] target = new double[][] { { 0 }, { 0 }, { 0 }, { 0 }, { 0 }, { 0 }, { 0 }, { 0 }, { 1 }, { 1 },
				{ 1 }, { 1 }, { 1 }, { 1 }, { 1 }, { 1 }, { 1 }, { 1 }, { 1 }, { 1 }, { 1 }, { 1 }, { 0.1 }, { 0 } };
		int id = 1;
		server.train1(layer, data, target, id);

		double[] data0 = data[0];
		double[] bpResult = server.getBPResult(layer, id, data0);
		data0 = data[1];
		server.getBPResult(layer, id, data0);
	}

	@RequestMapping("/insertDeviceStatus")
	@ResponseBody
	public String insertDeviceStatus(DeviceStatus d) throws Exception {
		Date date = d.getDate();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date2 = df.format(date);
		date = df.parse(date2);
		d.setDate(date);
		d.setPredictPower(d.getDeviceId() + 0.9);
		deviceStatusMapper.insertDeviceStatus(d);

		for (int i = 0; i < 23; i++) {
			date = new Date(date.getTime() + 60 * 60 * 1000L);
			d.setDate(date);
			double power = 0;
			if (i > 6 && i < 21) {
				power += 20.5 + Math.random() * 3;
			}
			power = new BigDecimal(power).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			double predict= power+Math.random();
			if (i > 6 && i < 21) {
				 predict= power+Math.random() * 5;
			}
			predict=new BigDecimal(predict).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			d.setPower(power);
			d.setPredictPower(predict);
			deviceStatusMapper.insertDeviceStatus(d);

		}
		String json = gson.toJson(d);
		return json;
	}

	@RequestMapping("/findById")
	@ResponseBody
	public ModelAndView itemsList(int id) throws Exception {
		List<DeviceStatus> DeviceInformation = server.findDeviceStatusById(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("DeviceInformation", DeviceInformation);
		modelAndView.setViewName("itemList");
		return modelAndView;
	}

	@RequestMapping("/findDeviceStatusByDate")
	@ResponseBody
	public ModelAndView findDeviceInformationByType(Date type) throws Exception {
		List<DeviceStatus> DeviceInformation = server.findDeviceStatusByDate(type);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("DeviceInformation", DeviceInformation);
		modelAndView.setViewName("itemList");
		return modelAndView;
	}

	@RequestMapping("/findDeviceStatusByBuildFloorDateType")
	@ResponseBody
	public String findDeviceStatusByBuildFloorDateType(selectObj_BuildFloorRoomDateType type) throws Exception {
		List<DeviceStatus> DeviceInformation = deviceStatusMapper.findDeviceStatusByBuildFloorDateType(type);
		String json = gson.toJson(DeviceInformation);
		return json;
	}

	@RequestMapping("/findDeviceStatusByDeviceInformation")
	@ResponseBody
	public String findDeviceIdByDeviceInformation(selectObj_BuildFloorRoomDateType deviceInformation) throws Exception {
		Date date = deviceInformation.getStartTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
		String date2 = df.format(date);
		date = df.parse(date2);
		deviceInformation.setStartTime(date);
		date = new Date(date.getTime() + 24 * 60 * 60 * 1000L);
		deviceInformation.setEndTime(date);
		EchartData echartData = new EchartData();
		List<DeviceStatus> deviceStatus = deviceStatusMapper.findDeviceStatusByDeviceStatus(deviceInformation);
		for (int j = 0; j < deviceStatus.size(); j++) {
			echartData.data.add(deviceStatus.get(j).getPower());
			echartData.xAxisData.add(dft.format(deviceStatus.get(j).getDate()));
		}
		String json = gson.toJson(echartData);
		return json;
	}

	@RequestMapping("/findDeviceStatusByDeviceInformation2")
	@ResponseBody
	public String findDeviceIdByDeviceInformation2(selectObj_BuildFloorRoomDateType deviceInformation)
			throws Exception {
		Date date = deviceInformation.getStartTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat dft = new SimpleDateFormat("MM-dd");
		String dateString = df.format(date);
		Date startDate = df.parse(dateString);
		deviceInformation.setStartTime(startDate);

		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(startDate);// 使用给定的 Date 设置此 Calendar 的时间。
		rightNow.add(Calendar.MONTH, 1);// 日期加1个月
		Date endDate = rightNow.getTime();

		EchartData echartData = new EchartData();
		for (int i = 0; i < 32; i++) {
			deviceInformation.setEndTime(new Date(deviceInformation.getStartTime().getTime() + 24 * 60 * 60 * 1000L));
			if (deviceInformation.getEndTime().after(endDate)) {
				break;
			}
			List<DeviceStatus> deviceStatus = deviceStatusMapper.findDeviceStatusByDeviceStatus(deviceInformation);
			double poewr = 0.0;
			for (int j = 0; j < deviceStatus.size(); j++) {
				poewr += deviceStatus.get(j).getPower();
			}
			echartData.data.add(poewr);
			echartData.xAxisData.add(dft.format(deviceInformation.getStartTime()));

			deviceInformation.setStartTime(deviceInformation.getEndTime());
		}
		String json = gson.toJson(echartData);
		return json;
	}

	@RequestMapping("/findDeviceStatusByDeviceInformation3")
	@ResponseBody
	public String findDeviceIdByDeviceInformation3(selectObj_BuildFloorRoomDateType deviceInformation)
			throws Exception {
		Date date = deviceInformation.getStartTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
		String dateString = df.format(date);
		Date startDate = df.parse(dateString);
		deviceInformation.setStartTime(startDate);

		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(startDate);// 使用给定的 Date 设置此 Calendar 的时间。
		rightNow.add(Calendar.YEAR, 1);// 日期加1年
		Date endDate = rightNow.getTime();

		EchartData echartData = new EchartData();
		for (int i = 0; i < 32; i++) {
			rightNow.setTime(deviceInformation.getStartTime());// 使用给定的 Date 设置此
																// Calendar 的时间。
			rightNow.add(Calendar.MONTH, 1);// 日期加1个月
			deviceInformation.setEndTime(rightNow.getTime());
			if (deviceInformation.getEndTime().after(endDate)) {
				break;
			}
			List<DeviceStatus> deviceStatus = deviceStatusMapper.findDeviceStatusByDeviceStatus(deviceInformation);
			double poewr = 0.0;
			for (int j = 0; j < deviceStatus.size(); j++) {
				poewr += deviceStatus.get(j).getPower();
			}
			echartData.data.add(poewr);
			echartData.xAxisData.add(dft.format(deviceInformation.getStartTime()));

			deviceInformation.setStartTime(deviceInformation.getEndTime());
		}
		String json = gson.toJson(echartData);

		return json;
	}

	@RequestMapping("/findDeviceStatusByDeviceInformation4")
	@ResponseBody
	public String findDeviceIdByDeviceInformation4(selectObj_BuildFloorRoomDateType deviceInformation)
			throws Exception {
		Date date = deviceInformation.getStartTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
		String date2 = df.format(date);
		date = df.parse(date2);
		deviceInformation.setStartTime(date);
		date = new Date(date.getTime() + 24 * 60 * 60 * 1000L);
		deviceInformation.setEndTime(date);
		EchartData echartData = new EchartData();
		List<DeviceStatus> deviceStatus = deviceStatusMapper.findDeviceStatusByDeviceStatus(deviceInformation);
		for (int j = 0; j < deviceStatus.size(); j++) {
			echartData.data.add(deviceStatus.get(j).getPower());
			echartData.data2.add(deviceStatus.get(j).getPredictPower());
			echartData.xAxisData.add(dft.format(deviceStatus.get(j).getDate()));
		}
		String json = gson.toJson(echartData);
		return json;
	}

}
