/**
* 买车脚本
*/
$().ready(function () {
	$("#innercolorDiv").children("div").on('click', function () {
		$("#innercolorId").val($(this).attr("value"));
		reloadPage(1);
	});
	$("#outercolorDiv").children("div").on('click', function () {
		$("#outercolorId").val($(this).attr("value"));
		reloadPage(1);
	});
	$("#fuelDiv").children("div").on('click', function () {
		$("#fuel").val($(this).attr("value"));
		reloadPage(1);
	});
	$("#enginesDiv").children("div").on('click', function () {
		$("#enginesId").val($(this).attr("value"));
		reloadPage(1);
	});
	$("#searchPriceDiv").children("div").on('click', function () {
		$("#searchPrice").val($(this).attr("value"));
		reloadPage(1);
	});
	$("#depositRatioDiv").children("div").on('click', function () {
		$("#depositRatio").val($(this).attr("value"));
		reloadPage(1);
	});
	$("#orderDiv").children("div").on('click', function () {
		var orderName = $("#orderName").val();
		var orderType = $("#orderType").val();
		
		if (orderName == $(this).attr("value")) {
			if (orderType == "desc") {
				$("#orderType").val("asc");
			} else {
				$("#orderType").val("desc");
			}
		} else {
			if ($(this).attr("value") == "price") {
				$("#orderType").val("asc");
			} else {
				$("#orderType").val("desc");
			}
		}
		$("#orderName").val($(this).attr("value"));
		reloadPage(1);
	});
	$(".comm-pagin").children("a .item").on("click", function() {
		alert($(this).text());
	});
});

/**
 * 重新载入界面
 * @param type 类型，页码是否重置
 * @return
 */
function reloadPage(type) {
	if (type == 1) {
		$("#cuurentPage").val(1);
	}
	$("#queryForm").submit();
}

function versionChange() {
	$("#seriesId").val(0);
	$("#modelsId").val(0);
	$("#modelyearId").val(0);
	
	reloadPage(1);
}

function seriesChange() {
	$("#modelsId").val(0);
	$("#modelyearId").val(0);
	
	reloadPage(1);
}

function modelsChange() {
	$("#modelyearId").val(0);
	
	reloadPage(1);
}

/**
 * 分页跳转
 * @param toPage
 * @return
 */
function turnToPage(toPage) {
	if (toPage == -1) { // 上一页
		if ($("#cuurentPage").val() == 1) {
			return false;
		}
		var page = parseInt($("#cuurentPage").val()) - 1;
		if (page < 1) {
			$("#cuurentPage").val(1);
		} else {
			$("#cuurentPage").val(page);
		}
	} else if (toPage == -2) { // 下一页
		if ($("#cuurentPage").val() == $("#allPage").val()) {
			return false;
		}
		var page = parseInt($("#cuurentPage").val()) + 1;
		if (page > $("#allPage").val()) {
			$("#cuurentPage").val($("#allPage").val());
		} else {
			$("#cuurentPage").val(page);
		}
	} else if (toPage == 0) { // 输入框跳转
		if ($("#cuurentPage").val() == $("#turnPage").val()) {
			return false;
		}
		var page = $("#turnPage").val();
		if (page > $("#allPage").val()) {
			return false;
		} else if (page < 1) {
			return false;
		} else {
			$("#cuurentPage").val(page);
		}
	} else {
		if (toPage == $("#cuurentPage").val()) {
			return false;
		} else {
			$("#cuurentPage").val(toPage);
		}
	}
	reloadPage(0);
}