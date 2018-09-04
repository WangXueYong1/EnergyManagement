package cn.itheima.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itheima.dao.DeviceBPNMapper;
import cn.itheima.dao.DeviceInformationMapper;
import cn.itheima.dao.DeviceStatusMapper;
import cn.itheima.pojo.DeviceBPN;
import cn.itheima.pojo.DeviceInformation;
import cn.itheima.pojo.DeviceStatus;
import util.BpDeep;

@Service
public class DeviceStatusService {

	@Autowired
	private DeviceStatusMapper deviceStatusMapper;
	@Autowired
	private  DeviceBPNMapper devicebpnMapper;

	public List<DeviceStatus> findDeviceStatusById(int example) {
		List<DeviceStatus> findDeviceInformationById = deviceStatusMapper.findDeviceStatusById(example);
		return findDeviceInformationById;
	};

	public List<DeviceStatus> findDeviceStatusByDate(Date example) {
		List<DeviceStatus> findDeviceInformationByType = deviceStatusMapper.findDeviceStatusByDate(example);
		return findDeviceInformationByType;
	};

	public void insertDeviceStatus(DeviceStatus id) {
		deviceStatusMapper.insertDeviceStatus(id);
	};

	public void train(BpDeep bp, double[][] data, double[][] target) throws Exception {
		// 初始化神经网络的基本配置
		// 第一个参数是一个整型数组，表示神经网络的层数和每层节点数，比如{3,10,10,10,10,2}表示输入层是3个节点，输出层是2个节点，中间有4层隐含层，每层10个节点
		// 第二个参数是学习步长，第三个参数是动量系数
		// BpDeep bp = new BpDeep(new int[]{2,10,1}, 0.15, 0.8);

		// 设置样本数据，对应上面的4个二维坐标数据
		// double[][] data = new double[][]{{1,2},{2,2},{1,1},{2,1}};
		// 设置目标数据，对应4个坐标数据的分类
		// double[][] target = new double[][]{{0.1},{0.2},{0.05},{0.1}};

		// 迭代训练5000次
		for (int n = 0; n < 500000; n++)
			for (int i = 0; i < data.length; i++)
				bp.train(data[i], target[i]);

		// 根据训练结果来检验样本数据
		for (int j = 0; j < data.length; j++) {
			double[] result = bp.computeOut(data[j]);
			System.out.println(Arrays.toString(data[j]) + ":" + Arrays.toString(result));
		}

		// 根据训练结果来预测一条新数据的分类
		double[] x = new double[] { 3, 1 };
		double[] result = bp.computeOut(x);
		System.out.println(Arrays.toString(x) + ":" + Arrays.toString(result));

	}

	public double[] getBPResult(int[] layer,int id ,double[] data) throws Exception {
		//预测结果
		BpDeep bp = getBP(id, layer);
		double[] result = bp.computeOut(data);
		System.out.println(Arrays.toString(data) + ":" + Arrays.toString(result));
		return result;
	}
	public  BpDeep getBP(int id,int[] layer){
		DeviceBPN findDeviceBPNById = devicebpnMapper.findDeviceBPNById(id);
		double[][][] weight=new double[layer.length][][];
		for (int i = 0; i < weight.length-1; i++) {
			weight[i]=new double[layer[i]+1][layer[i+1]];
		}
		if (findDeviceBPNById==null) {
			for (int i = 0; i < weight.length-1; i++) {
				for (int j = 0; j < weight[i].length; j++) {
					for (int k = 0; k < weight[i][j].length; k++) {
						weight[i][j][k]=Math.random();
					}
				}
			}
		}else{
			String bpn_weight = findDeviceBPNById.getBPN_weight();
			deserialization(weight, bpn_weight);
		}	
		BpDeep bp = new BpDeep(layer, 0.15, 0.8,weight);
		return bp;
	}
	public  void train1(int[] layer,double[][] data,double[][] target,int id){	
		// 数据：年、月、日、时、分、秒、星期几、一年中第几个星期、温度、光照
		//[2018, 8, 27, 16, 40, 3, 1,35,25,35]
		// 迭代训练5000次
		BpDeep bp = getBP(id, layer);
		for (int n = 0; n < 5000; n++)
			for (int i = 0; i < data.length; i++)
				bp.train(data[i], target[i]);

		// 根据训练结果来检验样本数据
		for (int j = 0; j < data.length; j++) {
			double[] result = bp.computeOut(data[j]);
			System.out.println(Arrays.toString(data[j]) + ":" + Arrays.toString(result));
		}
		//保存训练的BP权重数据到数据库
		String str = serialization(bp.layer_weight);		
		DeviceBPN devicebpn=new DeviceBPN();
		devicebpn.setId(id);
		devicebpn.setBPN_weight(str);
		
		DeviceBPN findDeviceBPNById = devicebpnMapper.findDeviceBPNById(id);		
		if (findDeviceBPNById==null) {
			devicebpnMapper.insertDeviceBPN(devicebpn);	
		}else{
			devicebpnMapper.updateDeviceBPN(devicebpn);
		}	
	}
	
	
	private static void deserialization(double[][][] weight, String str) {
		String[] weightStr = str.split(",");	
		int index=0;
		for (int i = 0; i < weight.length-1; i++) {
			for (int j = 0; j < weight[i].length; j++) {
				for (int e = 0; e < weight[i][j].length; e++) {
					weight[i][j][e]=Double.valueOf(weightStr[index]); 
					index++;
				}
			}
		}						
	}

	private static String serialization(double[][][] weight) {
		StringBuffer sb = new StringBuffer(20000);  				 
		for (int i = 0; i < weight.length-1; i++) {
			for (int j = 0; j < weight[i].length; j++) {
				for (int e = 0; e < weight[i][j].length; e++) {
					sb.append(weight[i][j][e]).append(","); 
				}
			}
		}		
		String str=sb.toString();
		return str;
	}
}
