/**
 * 
 * 
 * @file fstat.js
 * @since v0.1
 */

//下级单位风险类型统计图
function drawRiskTypeStat(divId){//alert("drawRiskTypeStat")
	//console.debug("drawNextDeptEventTypeStat");
$.ajax({
	type: "post",
	url: "module/basedata/presentation_getRiskTypeStat.do?mode=ajaxMode",
	data:{"id": orgManager.currentOrg.id},
	dataType:"json",
	beforeSend: function(XMLHttpRequest){

	},
	success: function(data){
		generateRiskTypeStatChart(data, divId);

	},
	complete: function(XMLHttpRequest, textStatus){

	},
	error: function(){//请求出错处理
		 console.debug('获取统计信息出错，请稍后再试！');
		}
	});
}

// （1）显示某单位风险评估、基线核查、预警信息（IDS）、病毒预警分类统计柱状图，可以选择时间段
function generateRiskTypeStatChart(data, divId) {//alert("generateRiskTypeStatChart")

	
	var chart = new Highcharts.Chart( {
	chart : {
		renderTo : divId,
		defaultSeriesType : 'column',
		backgroundColor : 'rgba(0,0,0,0)'
	},
	title : {
		text : '风险类别分类统计',
		style : {
			// margin : '10px 100px 0 0', // center it,
		// color : '#000',
		font : 'bold 12px Verdana, sans-serif'

	}
	},
	exporting : {
		// 是否允许导出
		enabled : false
	},

	credits : {
		enabled : false
	},
	xAxis : {
		categories : data.nameList
	},
	legend : {
		enabled : false
	},
	plotOptions : {
		// 设置柱状图宽度
		column : {
			pointPadding : 0.2,
			borderWidth : 0,
			pointWidth : 40

		},
		series : {
			cursor : 'pointer',
			events : {
				click : function(e) {
					//alert(e.point.category);
					// 进入具体风险类别统计 如中危、高危及低危统计视图
					drawRiskLevelStatByRiskType(divId, e.point.category);

				}
			}
		}
	},

	yAxis : {
		min : 0,
		allowDecimals : true,
		title : {
			text : '总数'
		}
	// tickInterval : 5

		},
		series : [ {
			name : '风险类型',
			data : data.countList
		} ]

	});
}

//某一风险类型下风险等级统计 
function drawRiskLevelStatByRiskType(divId, kindId){
	//console.debug("drawNextDeptEventTypeStat");
$.ajax({
	type: "post",
	url: "module/basedata/presentation_getRiskLevelStatByRiskType.do?mode=ajaxMode",
	data:{"kind":kindId,"id": orgManager.currentOrg.id},
	dataType:"json",
	beforeSend: function(XMLHttpRequest){

	},
	success: function(data){
		generateRiskLevelStatChart(data, divId);

	},
	complete: function(XMLHttpRequest, textStatus){

	},
	error: function(){//请求出错处理
		 console.debug('获取统计信息出错，请稍后再试！');
		}
	});
}

// （2)点击（1）根据风险结果统计该风险结果下的风险类别（中危、高危等）统计
function generateRiskLevelStatChart(data, divId) {

	var chart = new Highcharts.Chart( {
		chart : {
			renderTo : divId,
			defaultSeriesType : 'column',
			backgroundColor : 'rgba(0,0,0,0)'
		},
		title : {
			text : '风险结果类别分类统计',
			style : {
				// margin : '10px 100px 0 0', // center it,
				color : '#000',
				font : 'bold 12px Verdana, sans-serif'

			}
		},
		exporting : {
			// 是否允许导出
		enabled : false
	},

	credits : {
		enabled : false
	},
	legend : {
		enabled : false
	},
	xAxis : {
		categories : data.nameList
	},

	plotOptions : {
		// 设置柱状图宽度
		column : {
			pointPadding : 0.2,
			borderWidth : 0,
			pointWidth : 40

		},
		series : {
			cursor : 'pointer',
			events : {
				click : function(e) {
					// alert(e.point.category);
					drawRiskTypeStat(divId);//返回下级单位风险类型统计图
				}
			}
		}
	},

	yAxis : {
		min : 0,
		allowDecimals : true,
		title : {
			text : '总数'
		}
	// tickInterval : 5
		},
		series : [ {
			name : '风险等级',
			data : data.countList
		} ]

	});

}

//（1）显示某单位风险评估、基线核查、预警信息（IDS）、病毒预警分类统计柱状图，可以选择时间段
function generateColumnFChartByDayMultiple() {
	var chart = new Highcharts.Chart( {
		chart : {
			renderTo : 'FChartpre',
			defaultSeriesType : 'column',
			backgroundColor : 'rgba(0,0,0,0)'
		},
		title : {
			text : '风险类别分类统计',
			style : {
				// margin : '10px 100px 0 0', // center it,
			// color : '#000',
			font : 'bold 12px Verdana, sans-serif'

		}
		},
		exporting : {
			// 是否允许导出
		enabled : false
	},

	credits : {
		enabled : false
	},
	xAxis : {
		categories : [ '风险评估', '基线核查', '预警信息（IDS）', '病毒预警' ]
	},
	legend : {
		enabled : false
	},
	plotOptions : {
		// 设置柱状图宽度
		column : {
			pointPadding : 0.2,
			borderWidth : 0,
			pointWidth : 40

		},
		series : {
			cursor : 'pointer',
			events : {
				click : function(e) {
					 //alert(e.point.category);
			// 进入具体风险类别统计 如中危、高危及低危统计视图
			generateColumnFChartByCategoryMultiple();

		}
	}
		}
	},

	yAxis : {
		min : 0,
		allowDecimals : true,
		title : {
			text : '总数'
		}
	// tickInterval : 5

		},
		series : [ {
			name : '风险',
			data : [ 15, 35, 4, 70 ]
		} ]

	});
}

// （2)点击（1）根据风险结果统计该风险结果下的风险类别（中危、高危等）统计
function generateColumnFChartByCategoryMultiple() {

	var chart = new Highcharts.Chart( {
		chart : {
			renderTo : 'FChartpre',
			defaultSeriesType : 'column',
			backgroundColor : 'rgba(0,0,0,0)'
		},
		title : {
			text : '风险结果类别分类统计',
			style : {
				// margin : '10px 100px 0 0', // center it,
				color : '#000',
				font : 'bold 12px Verdana, sans-serif'

			}
		},
		exporting : {
			// 是否允许导出
		enabled : false
	},

	credits : {
		enabled : false
	},
	legend : {
		enabled : false
	},
	xAxis : {
		categories : [ '低级', '中级', '高级' ]
	},

	plotOptions : {
		// 设置柱状图宽度
		column : {
			pointPadding : 0.2,
			borderWidth : 0,
			pointWidth : 40

		},
		series : {
			cursor : 'pointer',
			events : {
				click : function(e) {
					// alert(e.point.category);
			// 进入具体风险类别统计 如中危、高危及低危统计视图

		}
	}
		}
	},

	yAxis : {
		min : 0,
		allowDecimals : true,
		title : {
			text : '总数'
		}
	// tickInterval : 5
		},
		series : [ {
			name : '风险类别',
			data : [ 15, 35, 4 ]
		} ]

	});

}
// 全范围能力损失趋势指势图
function generateSplineFChartByDeptMultiple() {

	var data = [], time = (new Date()).getTime(), i, temp;
	// 获得当前时间
	for (i = 0; i <= 10; i++) {
		temp = Highcharts.dateFormat('%H:%M:%S', time + i * 100000);
		data.push(temp);
	}

	var chart = new Highcharts.Chart(
			{
				chart : {
					renderTo : 'FChartSpline',
					defaultSeriesType : 'spline',
					backgroundColor : 'rgba(0,0,0,0)'
				},
				title : {
					text : '安全状态趋势指势图',
					style : {
						// margin : '10px 100px 0 0', // center it,
					// color : '#000',
					font : 'bold 12px Verdana, sans-serif'

				}
				},
				exporting : {
					// 是否允许导出
					enabled : false
				},

				credits : {
					enabled : false
				},
				xAxis : {
					// categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
					// 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
					categories : data
				},
				yAxis : {
					title : {
						text : '损失指数U'
					},
					min : 0
				},
				tooltip : {
					formatter : function() {
						return '<b>风险评估20</b><br/><b>基线核查10</b><br/><b>预警信息10</b><br/><b>病毒风险20</b>';
						// return '<b>' + this.series.name + '</b><br/>'
						// + Highcharts.dateFormat('%e. %b', this.x) + ': '
						// + this.y + ' m';
					}
				},
				series : [ {
					name : '南京',
					data : [ 22, 5, 2, 3, 20, 11, 33, 2, 9, 19 ]
				}, {
					name : '北京',
					data : [ 12, 5, 21, 3, 120, 11, 133, 2, 9, 29 ]
				}, {
					name : '深圳',
					data : [ 32, 5, 32, 13, 20, 21, 33, 2, 19, 39 ]
				} ]

			});
}

// 当前能力损失驾驶盘
function generateGaugeChart() {
	var chart = new Highcharts.Chart(
			{
				chart : {
					renderTo : 'FChartGauge',
					defaultSeriesType : 'gauge',
					backgroundColor : 'rgba(0,0,0,0)'
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
						text : '能力损失指数U'
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
