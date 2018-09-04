package util;

import java.util.Arrays;
import java.util.Random;

public class BpDeepTest {
	public static void main(String[] args) {
		// 初始化神经网络的基本配置
		// 第一个参数是一个整型数组，表示神经网络的层数和每层节点数，比如{3,10,10,10,10,2}表示输入层是3个节点，输出层是2个节点，中间有4层隐含层，每层10个节点
		// 第二个参数是学习步长，第三个参数是动量系数
		// 数据：年、月、日、时、分、秒、星期几、一年中第几个星期、温度、光照
		//[2018, 8, 27, 16, 40, 3, 1,35,25,35]
		 Random random = new Random();
		int[] layer={ 10, 10, 1 };
		double[][][] weight=new double[layer.length][][];
		for (int i = 0; i < weight.length-1; i++) {
			weight[i]=new double[layer[i]+1][layer[i+1]];
		}
		for (int i = 0; i < weight.length-1; i++) {
			for (int j = 0; j < weight[i].length; j++) {
				for (int k = 0; k < weight[i][j].length; k++) {
					weight[i][j][k]=Math.random();
				}
			}
		}
		BpDeep bp = new BpDeep(layer, 0.15, 0.8,weight);

		// 设置样本数据，对应上面的4个二维坐标数据
		double[][] data = new double[][] { 
			{ 2018, 8, 27, 0, 40, 3, 1, 35, 25, 35 },
			{ 2018, 8, 27, 1, 40, 3, 1, 35, 25, 35 },
			{ 2018, 8, 12, 2, 40, 3, 1, 35, 25, 35 },
			{ 2018, 8, 27, 3, 40, 3, 1, 35, 25, 35 },
			{ 2018, 8, 27, 4, 40, 3, 1, 35, 25, 35 },
			{ 2018, 8, 27, 5, 40, 3, 1, 35, 25, 35 },
			{ 2018, 8, 12, 6, 40, 3, 1, 35, 25, 35 },
			{ 2018, 8, 27, 7, 40, 3, 1, 35, 25, 35 },
			{ 2018, 8, 27, 8, 40, 3, 1, 35, 25, 35 },
			{ 2018, 8, 27, 9, 40, 3, 1, 35, 25, 35 },
			{ 2018, 8, 12, 10, 40, 3, 1, 35, 25, 35 },
			{ 2018, 8, 27, 11, 40, 3, 1, 35, 25, 35 },
			{ 2018, 8, 27, 12, 40, 3, 1, 35, 25, 35 },
			{ 2018, 8, 27, 13, 40, 3, 1, 35, 25, 35 },
			{ 2018, 8, 12, 14, 40, 3, 1, 35, 25, 35 },
			{ 2018, 8, 27, 15, 40, 3, 1, 35, 25, 35 },
			{ 2018, 8, 27, 16, 40, 3, 1, 35, 25, 35 },
			{ 2018, 8, 27, 17, 40, 3, 1, 35, 25, 35 },
			{ 2018, 8, 12, 18, 40, 3, 1, 35, 25, 35 },
			{ 2018, 8, 27, 19, 40, 3, 1, 35, 25, 35 },
			{ 2018, 8, 27, 20, 40, 3, 1, 35, 25, 35 },
			{ 2018, 8, 27, 21, 40, 3, 1, 35, 25, 35 },
			{ 2018, 8, 12, 22, 40, 3, 1, 35, 25, 35 },
			{ 2018, 8, 27, 23, 40, 3, 1, 35, 25, 35 }
		};
		// 设置目标数据，对应4个坐标数据的分类
		double[][] target = new double[][] { 
			{ 0 }, { 0 }, { 0 }, { 0 }, { 0 }, { 0 }, { 0 }, { 0 },
			{ 1 }, {1}, {1 }, {1}, {1}, { 1 }, {1 }, { 1 },
			{ 1}, { 1 }, { 1 }, { 1}, { 1 }, { 1 }, { 0.1 }, { 0 } };

		// 迭代训练5000次
		for (int n = 0; n < 5000; n++)
			for (int i = 0; i < data.length; i++)
				bp.train(data[i], target[i]);

		// 根据训练结果来检验样本数据
		for (int j = 0; j < data.length; j++) {
			double[] result = bp.computeOut(data[j]);
			System.out.println(Arrays.toString(data[j]) + ":" + Arrays.toString(result));
		}

		// 根据训练结果来预测一条新数据的分类
		double[] x = new double[] {18, 8, 27, 23, 40, 3, 1, 35, 25, 35 };
		double[] result = bp.computeOut(x);
		System.out.println(Arrays.toString(x) + ":" + Arrays.toString(result));
		
		String str = serialization(weight);
		deserialization(weight, str);
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
		
		
		System.out.println(weight);
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