package com.ynyes.tianya.util;

import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 * 统计图生成工具
 * 
 * @author gl
 *
 */
public class StatisticalUtil<T> {

	/**
	 * 饼图
	 */
	public String createSimplePieChart(Map<String, T> data, String title) {
		// 数据准备
		DefaultPieDataset dataset = new DefaultPieDataset();
		for (String key : data.keySet()) {
			dataset.setValue(key, (Number) data.get(key));
		}
		// 产生饼图
		JFreeChart chart = ChartFactory.createPieChart3D(title +"统计饼图", // 图表标题
				dataset, // 数据集
				true, // 是否显示图例(对于简单的柱状图必须是 false)
				false, // 是否生成工具
				false // 是否生成 URL 链接
				);

		// 中文乱码
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setLabelFont(new Font("宋体", Font.PLAIN, 20));
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("宋体", Font.PLAIN, 20));
		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));

		// 写图表对象到文件，参照柱状图生成源码
		FileOutputStream fos_jpg = null;

		String imagePath = SiteMagConstant.imagePath + "/"+ title + "统计饼图.jpg";
		try {
			fos_jpg = new FileOutputStream(imagePath);
			ChartUtilities.writeChartAsJPEG(fos_jpg, 1.0f, chart, 600, 500,
					null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos_jpg.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return imagePath;
	}

	/**
	 * 条形图
	 */

	public String createSimpleBarChart(Map<String, T> data, String title) {

		// 数据准备
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (String key : data.keySet()) {
			dataset.addValue((Number) data.get(key), "类型", key);
		}
		// 产生条形图
		JFreeChart chart = ChartFactory.createBarChart3D(title + "统计条形图", // 图表标题
				"订单类型", // 目录轴的显示标签
				"订单数", // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				true, // 是否显示图例(对于简单的柱状图必须是 false)
				false, // 是否生成工具
				false // 是否生成 URL 链接
				);
		//中文乱码
		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
		CategoryAxis domainAxis = categoryplot.getDomainAxis();
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("宋体", Font.PLAIN, 20));
		domainAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 11));
		domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
		numberaxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 12));
		numberaxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));

		FileOutputStream fos_jpg = null;

		String imagePath = SiteMagConstant.imagePath + "/"+ title +"统计条形图.jpg";
		try {
			try {
				fos_jpg = new FileOutputStream(imagePath);
				ChartUtilities.writeChartAsJPEG(fos_jpg, 1.0f, chart, 900, 600,
						null);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} finally {
			try {
				fos_jpg.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return imagePath;
	}
	
	public String transferStringToUTF8(String s){
		try {
			byte[] bytes = s.getBytes("utf-8");
			s = new String(bytes, "utf-8"); 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return s;
	}

}
