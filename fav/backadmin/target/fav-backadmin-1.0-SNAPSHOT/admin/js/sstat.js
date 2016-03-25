/**
 * 
 * 
 * @file sstat.js
 * @since v0.1
 */

var zNodes0 = [ {
	name : "江苏省",
	open : true,
	children : [ {
		name : "南京",
		children : [ {
			name : "江宁"
		}, {
			name : "鼓楼"
		}, {
			name : "市区"
		} ]
	}, {
		name : "北京",
		children : [ {
			name : "叶子"
		}, {
			name : "叶子"
		}, {
			name : "叶子"
		}, {
			name : "叶子"
		} ]
	}, {
		name : "父节点13",
		isParent : true
	} ]
}, {
	name : "四川",
	children : [ {
		name : "成都",
		open : true,
		children : [ {
			name : "节点211"
		}, {
			name : "节点212"
		}, {
			name : "节点213"
		}, {
			name : "叶子节点214"
		} ]
	} ]
}, {
	name : "父节",
	isParent : true
}];

// （1）显示事件统计
function generateColumnFChartByDayMultiple() {
	//drawNextNodeEventStat("SChart");
	drawNextDeptEventStat("SChart");
}

// （2)事件类型统计
function generateColumnFChartByCategoryMultiple() {
	drawNextDeptEventTypeStat("SChart");
}

// 全范围安全状态趋势指势图
function generateSplineFChartByDeptMultiple() {
	drawNextDeptEventTrend("SChart");
}

// 安全状态风险系数驾驶盘
function generateGaugeChart() {
	var chart = new Highcharts.Chart(
			{
				chart : {
					renderTo : 'SChartGauge',
					defaultSeriesType : 'gauge',
					backgroundColor : 'rgba(0,0,0,0)',
					events : {
						click : function(e) {
							// alert(e.point.category);
					// 进入具体风险类别统计 如中危、高危及低危统计视图
					// generateColumnFChartByCategoryMultiple();

				}
			}
				},
				title : {
					text : ''
				},

				exporting : {
					// 是否允许导出
				enabled : false
			},

			credits : {
				enabled : false
			},

			pane : {
				startAngle : -100,
				endAngle : 100,
				background : null,
				// 极坐标图或角度测量仪的中心点，像数组[x,y]一样定位。位置可以是以像素为单位的整数或者是绘图区域的百分比
				center : [ '60%', '30%' ],
				size : 180
			},
			// the value axis
				yAxis : {
					min : 0,
					max : 100,
					minorTickInterval : 'auto',
					minorTickWidth : 1,
					minorTickLength : 10,
					minorTickPosition : 'inside',
					minorTickColor : '#666',
					tickPixelInterval : 30,
					tickWidth : 2,
					tickPosition : 'inside',
					tickLength : 10,
					tickColor : '#666',
					labels : {
						step : 2,
						rotation : 'auto'
					},
					title : {
						text : '安全状态指数U'
					},
					plotBands : [ {
						from : 0,
						to : 30,
						color : '#55BF3B'
					}, {
						from : 30,
						to : 70,
						color : '#DDDF0D' // yellow
					}, {
						from : 70,
						to : 100,
						color : '#DF5353' // red
					} ]
				},
				plotOptions : {
					series : {
						cursor : 'pointer',
						events : {
							click : function(e) {
								// alert(e.point.category);
						// 进入具体风险类别统计 如中危、高危及低危统计视图
						// generateColumnFChartByCategoryMultiple();

					}
				}
					}
				},
				series : [ {
					name : '指数',
					data : [ 80 ],
					tooltip : {
						valueSuffix : ' '
					}
				} ]

			},
			// Add some life
			function(chart) {
				if (!chart.renderer.forExport) {
					setInterval(
							function() {
								var point = chart.series[0].points[0], newVal, inc = Math
										.round((Math.random() - 0.5) * 20);

								newVal = point.y + inc;
								if (newVal < 0 || newVal > 100) {
									newVal = point.y - inc;
								}

								point.update(newVal);

							}, 3000);
				}
			});
}
