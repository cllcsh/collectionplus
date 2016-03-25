/**
 * 
 * 
 * @file fstat.js
 * @since v0.1
 */



// 显示能力损失统计
function generateColumnChartByDayMultiple(divId,type) {
	if ("1" == type)
	{
		textValue='安全风险指数图';
	}
	else if ("2" == type)
	{
		textValue='安全状态趋势图';
	}
	else
	{
		textValue='信息系统能力损失趋势';
	}
	$.ajax({
		type: "post",
		url: "module/basedata/trend_uHistogram.do?mode=ajaxMode",
		data:{"trendForm.orgId": orgManager.currentOrg.id,"trendForm.type":type,"trendForm.curOrNext":"1","trendForm.orgOrNode":"1","trendForm.time":"2013-12-23 15:00:00"},
		dataType:"json",
		beforeSend: function(XMLHttpRequest){

		},
		success: function(data){
			generateColumnChart(data, divId,textValue);

		},
		complete: function(XMLHttpRequest, textStatus){

		},
		error: function(){//请求出错处理
			 console.debug('获取统计信息出错，请稍后再试！');
			}
		});
	
}

function generateColumnChart(data,divId,textValue)
{
	$('#'+divId).highcharts({

		chart: {
					type: 'column',
					margin: [ 50, 50, 100, 80],
					backgroundColor : 'rgba(0,0,0,0)'
				},
				title : {
					text : textValue,
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
				xAxis: {
					categories: data.nameList,
					labels: {
						rotation: -45,
						align: 'right',
						style: {
							fontSize: '13px',
							fontFamily: 'Verdana, sans-serif'
						}
					}
				},
				yAxis: {
					min: 0,
					title: {
						text: textValue
					}
				},
				legend: {
					enabled: false
				},
				tooltip: {
					pointFormat: textValue,
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
//								alert(e.point.id);
								// 进入具体风险类别统计 如中危、高危及低危统计视图
//						       generateColumnChartByDayMultiple(divId, e.point.category)
								//generateColumnFChartByCategoryMultiple();

							}
						}
					}
				},
				series: [{
					name: 'EventCount',
					data: data.countList,
					dataLabels: {
						enabled: true,
						rotation: 0,
						color: '#FFFFFF',
						align: 'center',
						x: 4,
						y: 10,
						style: {
							fontSize: '13px',
							fontFamily: 'Verdana, sans-serif',
							textShadow: '0 0 3px black'
							}
						}
					}]
			});
}

//生成U、S、F状态趋势图
function generateUSFChartTrend(divId, type){//alert("generateUSFChartTrend");
	console.info("生成U、S、F状态趋势图(generateUSFChartTrend)");
	generateSplineChartByDeptMultiple(divId, type, orgManager.currentOrg.id);
}

// 获得U、S、F状态趋势图数据
function generateSplineChartByDeptMultiple(divId, type, deptId) {
	if ("1" == type)
	{
		textValue='安全风险指数趋势图';
	}
	else if ("2" == type)
	{
		textValue='安全状态趋势图';
	}
	else
	{
		textValue='信息系统能力损失趋势';
	}
	
	var data = {"resultList":[{"data":[[1387894255515,0],[1387894315515,0],[1387894375515,0],[1387894435515,0],[1387894495515,0],[1387894555515,0],[1387894615515,0],[1387894675515,0],[1387894735515,0],[1387894795515,0]],"name":"南京","type":""}],"deptList":[{"南京":"17"}]};
	generageSplineCharByDept(data,divId,textValue, type);
	
	/*$.ajax({
		type: "post",
		url: "module/basedata/trend_queryTrend.do?mode=ajaxMode",
		data:{"trendForm.orgId": deptId,"trendForm.type":type,"trendForm.curOrNext":"1"},
		dataType:"json",
		beforeSend: function(XMLHttpRequest){

		},
		success: function(data){
			generageSplineCharByDept(data,divId,textValue, type);
			 
		},
		complete: function(XMLHttpRequest, textStatus){

		},
		error: function(){//请求出错处理
			 console.debug('获取统计信息出错，请稍后再试！');
		}
	});*/

}

//绘制U、S、F状态趋势图
function generageSplineCharByDept(data, divId, textValue, type)
{
	//var data = {"resultList":[{"data":[[1387894255515,0],[1387894315515,0],[1387894375515,0],[1387894435515,0],[1387894495515,0],[1387894555515,0],[1387894615515,0],[1387894675515,0],[1387894735515,0],[1387894795515,0]],"name":"南京","type":""}],"deptList":[{"南京":"17"}]};
	//alert(deptArr.length+">>"+deptArr[0]["南京"]);
	
	var deptArr = data.deptList;//保存部门id与部门名称对应关系

	var chart = new Highcharts.Chart(
			{
				chart : {
					renderTo : divId,
					defaultSeriesType : 'spline',
					backgroundColor : 'rgba(0,0,0,0)'
				},
				title : {
					text : textValue,
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
					//categories : data,
					type: 'datetime',
					dateTimeLabelFormats: { // don't display the dummy year
						day: '%e日',
						month: '%b %y',
						year: '%Y'
					}
//					formatter: function() {           
//                    	return  Highcharts.dateFormat('%Y-%m-%d', this.value); 
//					} 
				},
				yAxis : {
					title : {
						text : textValue
					},
					min : 0
				},
				plotOptions : {
				
					series : {
						cursor : 'pointer',
						events : {
							click : function(e) {
								//alert('type:'+e.point.series.type);
							    //alert(e.point.category);
								//alert(e.point.series.name);
								var deptName = e.point.series.name;
								//var deptId = getIdByDeptName(deptArr, deptName);
								//generateSplineChartByDeptMultiple(divId, type, deptId);//产生下级部门的趋势图
							}
						}	
					}
				},
				tooltip : {
					shared:true,
					useHTML:true,
					headerFormat:'<small>{point.key}</small><table>',
					pointFormat:'<tr><td style="color:{series.color}">{series.name}:</td>'+
								'<td style="text-align:right"><b>{point.y}</b></td></tr>',
					footerFormat:'</table>',
					valueDecimals:2,
					valuePrefix:'$',
					valueSuffix:'EUR'
					/*formatter : function() {
						//return '<b>能力损失</b>';
						 return '<b>' + this.series.name + '</b><br/>'
						 + Highcharts.dateFormat('%e日', this.x) + ': '
						 + this.y;
					}*/
				},
				series : data.resultList

			});
	
}

//根据部门名称获得部门Id
function getIdByDeptName(deptArr, deptName){//alert("getIdByDeptName");
	if(deptArr != null && deptArr != undefined){
		for(var i=0;i<deptArr.length;i++){
		    //alert(deptArr[i][deptName]);
			if(deptArr[i][deptName] != null){
				return deptArr[i][deptName];
			}
		}
	}
	
	return -1;
}

//单个驾驶盘公用方法
function drawGaugeChartSingle(divId,handleId,showValue,title){
	drawGaugeChart(divId,handleId,showValue, title,{
		chart : {
			renderTo : divId,
			defaultSeriesType : 'gauge',
			backgroundColor : 'rgba(0,0,0,0)',
			events : {
				click : function(e) {
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
			background : null
			// 极坐标图或角度测量仪的中心点，像数组[x,y]一样定位。位置可以是以像素为单位的整数或者是绘图区域的百分比
			//center : [ '50%', '30%' ],
			//size : 180
		},
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
				text : title
			},
			plotBands : [ {
				from : 0,
				to : 30,
				innerRadius: 97,
				color : '#55BF3B'
			}, {
				from : 30,
				to : 70,
				innerRadius: 97,
				color : '#DDDF0D' // yellow
			}, {
				from : 70,
				to : 100,
				innerRadius: 97,
				color : '#DF5353' // red
			} ]
		},
		plotOptions : {
			series : {
				cursor : 'pointer',
				events : {
					click : function(e) {
			}
		}
			}
		},
		series : [ {
			name : title,
			//data : [ 80 ],
			data: [new Number(showValue).valueOf()],
			tooltip : {
				valueSuffix : ' '
			}
		} ]

	});
}


//示例：全范围安全状态趋势指势图
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
					renderTo : 'UChart',
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
						text : '安全状态指数U'
					},
					min : 0
				},
				plotOptions : {
					series : {
						cursor : 'pointer',
						events : {
							click : function(e) {
								alert(this.series.name);
								//alert(e.point.category);
								// 进入具体风险类别统计 如中危、高危及低危统计视图

					}
				}
					}
				},
				tooltip : {
					formatter : function() {
						//return '<b>能力损失20</b>';
						 return '<b>' + this.series.name + '</b><br/>'
						 + Highcharts.dateFormat('%e. %b', this.x) + ': '
						 + this.y + ' m';
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

// 示例：安全状态风险系数驾驶盘
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
					alert('');
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
				center : [ '50%', '30%' ],
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
								alert('');
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
