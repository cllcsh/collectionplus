(function(){
    'use strict';

    $('.swiper-container').css({height: $(window).width()/2});
    var mySwiper = new Swiper ('.swiper-container', {
        loop: true,
        pagination: '.swiper-pagination',
        autoplay: 3000,
    });
    
    viewReal();
}());

var myLineChart;

function showLine(labels, minPrice, averagePrice, minBuyPrice) {
	var ctx = document.getElementById("J-Canvas").getContext("2d");
    var data = {
        labels: labels,
        datasets: [
            {
                label: "最新平均底价",
                fillColor: "rgba(237,28,12,0)",
                strokeColor: "rgba(237,28,12,1)",
                pointColor: "rgba(237,28,12,1)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(220,220,220,1)",
                data: averagePrice
            },
            {
                label: "最新成交底价",
                fillColor: "rgba(85,191,52,0)",
                strokeColor: "rgba(85,191,52,1)",
                pointColor: "rgba(85,191,52,1)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(151,187,205,1)",
                data: minBuyPrice
            },
            {
                label: "最新车源底价",
                fillColor: "rgba(233,203,35,0)",
                strokeColor: "rgba(233,203,35,1)",
                pointColor: "rgba(233,203,35,1)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(151,187,205,1)",
                data: minPrice
            }
        ]
    };
    
    if (myLineChart) myLineChart.destroy();
	
	var array = [Math.max.apply(null, minPrice), Math.max.apply(null, minBuyPrice), Math.max.apply(null, averagePrice)];
	var maxPrice = Math.max.apply(null, array);

    myLineChart = new Chart(ctx).Line(data, {
        animationEasing : 'easeOutQuint',
        scaleOverride : true,
        scaleSteps : 10,
        scaleStepWidth : (maxPrice * 1.4 / 10).toFixed(0),
        scaleStartValue : 0,
        multiTooltipTemplate: "<%= datasetLabel %> : <%= value %>"
    });
}

//显示实时线
function viewReal() {
	if ($("#realLine").hasClass("active")) {
		return false;
	}
	$("#realLine").addClass("active");
	$("#weekLine").removeClass("active");
	$("#dayLine").removeClass("active");
	
	var data = {"carForm.brandId":$("#brandId").val(), 
			"carForm.versionId":$("#versionId").val(),
			"carForm.seriesId":$("#seriesId").val(),
			"carForm.modelsId":$("#modelsId").val(),
			"carForm.modelyearId":$("#modelyearId").val()};
	
	$.ajax({
		type: "POST",
		url: "/buy_viewPriceHour.do",
		data: data,
		success: function(data){
			if(data.message != undefined && data.message.length>0){
				var labels = [];
				var minPrice = [];
				var averagePrice = [];
				var minBuyPrice = [];
				for(var i=0; i<data.message.length; i++){
					labels[data.message.length - i - 1] = data.message[i].dataHour;
					minPrice[data.message.length - i - 1] = (data.message[i].lowerSellPrice / 10000.0).toFixed(2);
					averagePrice[data.message.length - i - 1] = (data.message[i].avePrice / 10000.0).toFixed(2);
					minBuyPrice[data.message.length - i - 1] = (data.message[i].lpMix / 10000.0).toFixed(2);
				}
				showLine(labels, minPrice, averagePrice, minBuyPrice);
			} else {
				var price = (parseInt($("#price").val()) / 10000.0).toFixed(2);
				
				var myDate = new Date();
				var hour = myDate.getHours();
				var labels = [];
				var minPrice = [];
				var averagePrice = [];
				var minBuyPrice = [];
				for (var i = 0; i <= hour; i++) {
					labels[i] = i;
					minPrice[i] = price;
					averagePrice[i] = 0;
					minBuyPrice[i] = 0;
				}
				showLine(labels, minPrice, averagePrice, minBuyPrice);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取数据失败");
		},
		dataType: "json"
	});
}

// 显示日线
function viewDay() {
	if ($("#dayLine").hasClass("active")) {
		return false;
	}
	$("#dayLine").addClass("active");
	$("#weekLine").removeClass("active");
	$("#realLine").removeClass("active");
	
	var data = {"carForm.brandId":$("#brandId").val(), 
			"carForm.versionId":$("#versionId").val(),
			"carForm.seriesId":$("#seriesId").val(),
			"carForm.modelsId":$("#modelsId").val(),
			"carForm.modelyearId":$("#modelyearId").val()};
	
	$.ajax({
		type: "POST",
		url: "/buy_viewPriceDay.do",
		data: data,
		success: function(data){
			if(data.message != undefined && data.message.length>0){
				var labels = [];
				var minPrice = [];
				var averagePrice = [];
				var minBuyPrice = [];
				for(var i=0; i<data.message.length; i++){
					labels[data.message.length - i - 1] = data.message[i].dataMonth + "." + data.message[i].dataDay;
					minPrice[data.message.length - i - 1] = (data.message[i].lowerSellPrice / 10000.0).toFixed(2);
					averagePrice[data.message.length - i - 1] = (data.message[i].avePrice / 10000.0).toFixed(2);
					minBuyPrice[data.message.length - i - 1] = (data.message[i].lpMix / 10000.0).toFixed(2);
				}
				showLine(labels, minPrice, averagePrice, minBuyPrice);
			} else {
				var price = (parseInt($("#price").val()) / 10000.0).toFixed(2);
				
				var myDate = new Date();
				var labels = [];
				for (var i = 0; i < 7; i++) {
					labels[6 - i] = (myDate.getMonth() + 1) + "." + myDate.getDate();
					myDate.setDate(myDate.getDate() - 1);
				}
				var minPrice = [price, price, price, price, price, price, price];
				var averagePrice = [0, 0, 0, 0, 0, 0, 0];
				var minBuyPrice = [0, 0, 0, 0, 0, 0, 0];
				showLine(labels, minPrice, averagePrice, minBuyPrice);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取数据失败");
		},
		dataType: "json"
	});
}

// 显示周线
function viewWeek() {
	if ($("#weekLine").hasClass("active")) {
		return false;
	}
	$("#weekLine").addClass("active");
	$("#dayLine").removeClass("active");
	$("#realLine").removeClass("active");
	
	var data = {"carForm.brandId":$("#brandId").val(), 
			"carForm.versionId":$("#versionId").val(),
			"carForm.seriesId":$("#seriesId").val(),
			"carForm.modelsId":$("#modelsId").val(),
			"carForm.modelyearId":$("#modelyearId").val()};
	
	$.ajax({
		type: "POST",
		url: "/buy_viewPriceWeek.do",
		data: data,
		success: function(data){
			var labels = ["前6周","前5周","前4周","前3周","前2周","前1周","当前周"];
			if(data.message != undefined && data.message.length>0){
				var minPrice = [];
				var averagePrice = [];
				var minBuyPrice = [];
				for(var i=data.message.length-1; i>=0; i--){
					// labels[i] = data.message[i].dataYear + "年" + data.message[i].dataMonth + "月第" + data.message[i].dataWeek + "周";
					minPrice[5 - i] = (data.message[i].lowerSellPrice / 10000.0).toFixed(2);
					averagePrice[5 - i] = (data.message[i].avePrice / 10000.0).toFixed(2);
					minBuyPrice[5 - i] = (data.message[i].lpMix / 10000.0).toFixed(2);
				}
				// labels[6] = "当前周";
				minPrice[6] = (data.lineWeek.lowerSellPrice / 10000.0).toFixed(2);
				averagePrice[6] = (data.lineWeek.avePrice / 10000.0).toFixed(2);
				minBuyPrice[6] = (data.lineWeek.lpMix / 10000.0).toFixed(2);
				showLine(labels, minPrice, averagePrice, minBuyPrice);
			} else {
				var price = (parseInt($("#price").val()) / 10000.0).toFixed(2);
				// var labels = ["第1周","第2周","第3周","第4周","第5周","第6周","当前周"];
				var minPrice = [price, price, price, price, price, price, price];
				var averagePrice = [0, 0, 0, 0, 0, 0, 0];
				var minBuyPrice = [0, 0, 0, 0, 0, 0, 0];
				showLine(labels, minPrice, averagePrice, minBuyPrice);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("获取数据失败");
		},
		dataType: "json"
	});
}