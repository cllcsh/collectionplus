/**
   * jPagination for  jQuery分页插件
   * 功能：指定页数内静态分页，超过指定页数后ajax请求下一组静态分页
   * @param config 插件配置
   */

var pageOptionList = [5,10,20,50,100];

jQuery.fn.pagination = function(config){
	init("#"+this.attr("id"),config);
}

/**
   * 初始化，主程序
   * @param t 容器的ID，带#号
   * @param config 插件配置
   */
function init(t,config){
	//公有变量
	var dataStore = config.dataStore;																															//数据
	//var totalRecord = config.totalRecord > 0 ? config.totalRecord : 0;																	//总记录数
	var totalRecord = 0;
	var ajaxDataType = config.ajaxDataType ? config.ajaxDataType : "json";
	/*if(totalRecord == 0){
		jQuery(t).css("text-align","center");
		jQuery(t).css("line-height","50px");
		jQuery(t).html("很遗憾，没有检索到任何记录！");
		return;
	}*/
	var configPage = config.perPage > 0 ? config.perPage : 10;																					
	//var perPage = jQuery.cookie(t+"_perPage") == null ? configPage : parseInt(jQuery.cookie(t+"_perPage"));				//每页显示记录数
	var perPage = configPage;				//每页显示记录数
	var proxyUrl = config.proxyUrl ? config.proxyUrl : 'pgdataproxy.jsp';															//数据代理地址
	var groupSize = 1;																																//组大小
	var barPosition = config.barPosition == null ? 'bottom' : config.barPosition;													//工具条位置
	var ajaxParam = config.ajaxParam;																																//ajax的请求参数
	var showMode = config.showMode == null ? 'full' : config.showMode;															//显示模式
	var totalPage = Math.ceil(totalRecord/perPage);																									//总页数

	var callback = config.callback == null ? function(cpage,fn){} : config.callback;
	
	
	//私有变量
	//var currentPage = jQuery.cookie(t+"_currentPage") == null ? 1 : parseInt(jQuery.cookie(t+"_currentPage"));				//当前页码
	var currentPage = config.currentPage > 0 ? config.currentPage : 1;				//当前页码
	var startRecord;																																								//每页起始记录
	var endRecord;	 																																							//每页结束记录
	var gpStartPage;
	var gpEndPage;
	var gpStartRecord;
	var gpEndRecord;

	//数据容器
	var container = '<div class="pgContainer"></div>'

	//添加工具条
	var toolbar = '<div class="pgToolbar noprint">';
	toolbar += '<div class="pgPanel">';
	if(showMode == 'full'){
		toolbar += '<div><select class="pgPerPage">';
		var tflag = true;
		var tmpOption = "";
		for(var i in pageOptionList){
			if(perPage === pageOptionList[i]){
				tflag = false;
			}
			tmpOption += '<option value="'+pageOptionList[i]+'">'+pageOptionList[i]+'</option>';
		}
		if(tflag){
			toolbar += '<option value="'+perPage+'">'+perPage+'</option>';
		}
		toolbar += tmpOption;
		toolbar += '</select>&nbsp;</div>';
		toolbar += '<div class="separator"></div>';
	}
	toolbar += '<div class="pgBtn pgFirst" title="首页"></div>';
	toolbar += '<div class="pgBtn pgPrev" title="上页"></div>';
	if(showMode != 'mini'){
		toolbar += '<div class="separator"></div>';
		toolbar += '<div>第 <input class="pgCurrentPage" type="text" value="' + currentPage + '" title="页码" /> 页 / 共 <span class="pgTotalPage">' + totalPage + '</span> 页</div>';
		toolbar += '<div class="separator"></div>';
	}
	toolbar += '<div class="pgBtn pgNext" title="下页"></div>';
	toolbar += '<div class="pgBtn pgLast" title="尾页"></div>';
	if(groupSize){
		toolbar += '<div class="separator"></div>';
		toolbar += '<div class="pgBtn pgRefresh" title="刷新"></div>';
	}
	if(showMode == 'full'){
		toolbar += '<div class="separator"></div>';
		toolbar += '<div class="pgSearchInfo">检索到&nbsp;' + totalRecord + '&nbsp;条记录，显示第&nbsp;<span class="pgStartRecord">' + startRecord + '</span>&nbsp;条&nbsp;-&nbsp;第&nbsp;<span class="pgEndRecord">' + endRecord + '</span>&nbsp;条记录</div>';
	}
	toolbar += '<hr class="cleanFloat" /></div>';
	toolbar += '</div>';

	//if(perPage<totalRecord)
		switch(barPosition){
			case 'top':
				jQuery(t).html(toolbar+container);
				break;
			case 'bottom':
				jQuery(t).html(container+toolbar);
				break;
			default:
				jQuery(t).html(toolbar+container+toolbar);
		}
//	else
	//	jQuery(t).html(container);

	var btnRefresh = jQuery(t+" .pgRefresh");														//刷新按钮
	var btnNext =jQuery(t+" .pgNext");																	//下一页按钮
	var btnPrev = jQuery(t+" .pgPrev");																	//上一页按钮
	var btnFirst = jQuery(t+" .pgFirst");																	//首页按钮
	var btnLast = jQuery(t+" .pgLast");																	//末页按钮
	var btnGo = jQuery(t+" .pgNext,"+t+" .pgLast");
	var btnBack = jQuery(t+" .pgPrev,"+t+" .pgFirst");
	var btn = jQuery(t+" .pgFirst,"+t+" .pgPrev,"+t+" .pgNext,"+t+" .pgLast");
	var mask;
	
	var valCurrentPage = jQuery(t+" .pgCurrentPage");
	var valStartRecord = jQuery(t+" .pgStartRecord");
	var valEndRecord =jQuery(t+" .pgEndRecord");
	var valContainer = jQuery(t+" .pgContainer");
	var valPerPage = jQuery(t+" .pgPerPage");
	var valTotalPage = jQuery(t+" .pgTotalPage");

	jQuery(t+" .pgPerPage").attr("value",perPage);
	getGroupStartEnd();
	getStartEnd();
	if(dataStore==null)
		getRemoteData();
	else{
		getStartEnd();
		loadData();
		refresh();
	}

	//刷新按钮监听
	btnRefresh.bind("mousedown",pressHandler).bind("mouseup",unpressHandler).bind("mouseout",unpressHandler);

	//刷新工具栏
	//refresh();
	
	//按钮监听
	btnNext.click(
		function(){
			if(currentPage < totalPage){
				if(!dataStore || currentPage == gpEndPage){
					currentPage += 1;
					getGroupStartEnd();
					getStartEnd();
					getRemoteData();
				}else{
					currentPage += 1;
					getStartEnd();
					loadData();
					refresh();
				}
			}
		}
	);	
	btnPrev.click(
		function(){
			if(currentPage > 1){
				if(!dataStore || currentPage == gpStartPage){
					currentPage -= 1;
					getGroupStartEnd();
					getStartEnd();
					getRemoteData();
				}else{
					currentPage -= 1;
					getStartEnd();
					loadData();
					refresh();
				}
			}
		}
	);
	btnFirst.click(
		function(){
			if(!dataStore || currentPage > 1){
				if(gpStartPage > 1){
					currentPage = 1;
					getGroupStartEnd();
					getStartEnd();
					getRemoteData();
				}else{
					currentPage = 1;
					getStartEnd();
					loadData();
					refresh();
				}
			}
		}
	);
	btnLast.click(
		function(){
			if(!dataStore || currentPage < totalPage){
				if(gpEndPage > 0 && gpEndPage < totalPage){
					currentPage = totalPage;
					getGroupStartEnd();
					getStartEnd();
					getRemoteData();
				}else{
					currentPage = totalPage;
					getStartEnd();
					loadData();
					refresh();
				}
			}
		}
	);
	btnRefresh.click(
		function(){
			getGroupStartEnd();
			getStartEnd();
			getRemoteData();
		}
	);
	
	valCurrentPage.keypress(function(event) {   
        if (!$.browser.mozilla) {
            if (event.keyCode && (event.keyCode < 48 || event.keyCode > 57)) {
                // ie6,7,8,opera,chrome管用
                event.preventDefault();
            }
        } else {
            if (event.charCode && (event.charCode < 48 || event.charCode > 57)) {
                // firefox管用
                event.preventDefault();
            }
        }
    });
	//页码输入框监听
	/*valCurrentPage.keydown(
		function(){
			if(event.keyCode==13){
				var targetPage = parseInt(jQuery(this).val());
				if(targetPage<1){
					targetPage = 1;
					jQuery(this).val(targetPage);
				}
				if(targetPage > totalPage){
					targetPage = totalPage;
					//jQuery(this).val(targetPage);
				}
				if(targetPage>=1 && targetPage<=totalPage){
					if(!dataStore || gpStartPage > targetPage || (gpEndPage > 0 && gpEndPage < targetPage)){
						currentPage = targetPage;
						getGroupStartEnd();
						getStartEnd();
						getRemoteData();
					}else{
						currentPage = targetPage;
						getStartEnd();
						loadData();
						refresh();
					}
				}
				event.preventDefault();
			}
		}
	);*/
	valCurrentPage.change(function(){
		var reg = new RegExp("^([+-]?)\\d*\\.?\\d+$", "g");
		var targetPage = jQuery(this).val();
		if (!reg.test(targetPage)) {
			targetPage = 1;
			jQuery(this).val(targetPage);
        }
		if(targetPage<1){
			targetPage = 1;
			jQuery(this).val(targetPage);
		}
		if(targetPage > totalPage){
			targetPage = totalPage;
			jQuery(this).val(targetPage);
		}
		if(targetPage>=1 && targetPage<=totalPage){
			if(!dataStore || gpStartPage > targetPage || (gpEndPage > 0 && gpEndPage < targetPage)){
				currentPage = targetPage;
				getGroupStartEnd();
				getStartEnd();
				getRemoteData();
			}else{
				currentPage = targetPage;
				getStartEnd();
				loadData();
				refresh();
			}
		}
	});/*.blur(
			function(){
				var targetPage = parseInt(jQuery(this).val());
				if(targetPage<1){
					targetPage = 1;
					jQuery(this).val(targetPage);
				}
				if(targetPage > totalPage){
					targetPage = totalPage;
					jQuery(this).val(targetPage);
				}
				if(targetPage>=1 && targetPage<=totalPage){
					if(!dataStore || gpStartPage > targetPage || (gpEndPage > 0 && gpEndPage < targetPage)){
						currentPage = targetPage;
						getGroupStartEnd();
						getStartEnd();
						getRemoteData();
					}else{
						currentPage = targetPage;
						getStartEnd();
						loadData();
						refresh();
					}
				}
			}
		);*/
	
	valPerPage.change(
		function(){
			perPage = parseInt(jQuery(this).val());
			currentPage = 1;
			jQuery(t+" .pgPerPage").attr("value",perPage);
			totalPage = Math.ceil(totalRecord/perPage);
			if(groupSize){
				getGroupStartEnd();
				getStartEnd();
				getRemoteData();
			}else{
				getStartEnd();
				loadData();
				refresh();				
			}
			jQuery(this).blur();
			document.body.focus();
			//valCurrentPage.eq(0)[0].focus();
		}
	);
	
	/*********************************init私有函数***************************************************/
	/**
	   * 置为正在检索状态
	   */
	function startLoad(){
		jQuery(t).addClass("container");
		mask = document.createElement('div');
		jQuery(mask).addClass("mask");
		jQuery(mask).css("height",jQuery(t).height());
		jQuery(t).append(mask);
		jQuery(t+" .pgRefresh").addClass("pgLoad");
		jQuery(t+" .pgSearchInfo").html("正在检索中，请稍后...");
	}
	
	/**
	   * 置为结束检索状态
	   */
	function overLoad(){
		jQuery(t+" .pgRefresh").removeClass("pgLoad");//alert("gpStartRecord:"+gpStartRecord+"|startRecord:" + startRecord)
		jQuery(t+" .pgSearchInfo").html('检索到&nbsp;' + totalRecord + '&nbsp;条记录，显示第&nbsp;<span class="pgStartRecord">' + startRecord + '</span>&nbsp;条&nbsp;-&nbsp;第&nbsp;<span class="pgEndRecord">' + endRecord + '</span>&nbsp;条记录');
		jQuery(mask).remove();
		//jQuery(mask).fadeOut("slow");
	}

	/**
	   * 获得远程数据
	   */
	function getRemoteData(){
		startLoad();
		if(ajaxDataType == "html"){
			if(ajaxParam == null) ajaxParam = {};
			/* modify start by luojin 20091229 */
			//ajaxParam.page = currentPage;
			//ajaxParam.limit = perPage;
			var url = (proxyUrl.indexOf('?') == -1) ? proxyUrl + '?page=' + currentPage + '&limit=' + perPage
					: proxyUrl + '&page=' + currentPage + '&limit=' + perPage;
			valContainer.load(url,ajaxParam,function(data){
			/* modify end by luojin 20091229 */
				refreshStateData();
				getGroupStartEnd();
				getStartEnd();
				refresh();
				overLoad();
				callback(perPage,function(){//页面刷新函数
					getGroupStartEnd();
					getStartEnd();
					getRemoteData();
				});
			});
		}else{
		jQuery.ajax(
			{
				type: "POST",
				url: proxyUrl + "?startrecord="+gpStartRecord+"&endrecord="+gpEndRecord ,
				cache: false,
				data: ajaxParam,
				dataType: "script",
				timeout: 30000,
				success: function(msg){//alert(msg)
					eval(msg);
					getStartEnd();
					loadData();
					refresh();
					overLoad();
				},
				error: function(){
					alert("请求失败或超时，请稍后再试！");
					overLoad();
					return;
				}
			}
		);
		}
	}
	
	function getTotalRecord() {
		if(ajaxDataType == "html"){
			if(jQuery(t).find("#totalNum").size() > 0){
				if(jQuery(t).find("#totalNum").val()){
					totalRecord = jQuery(t).find("#totalNum").val();
				}
			} else {
				alert("获取总条数失败，请稍后再试!")
			}
		}
	}

	function refreshStateData() {
		getTotalRecord();
		totalPage = Math.ceil(totalRecord/perPage);
		if(currentPage > totalPage){
			currentPage = totalPage;
		}
	}

	/**
	   * 获得当前页开始结束记录
	   */
	function getStartEnd(){
		//if(groupSize){
			//startRecord = (currentPage-1)*perPage+1 - gpStartRecord + 1;
			//endRecord = Math.min(currentPage*perPage,totalRecord) - gpStartRecord + 1;
		//}else{
			startRecord = Math.max((currentPage-1)*perPage+1,0);
			endRecord = Math.min(currentPage*perPage,totalRecord);
		//}
	}

	/**
	   * 获得当前组开始结束页码
	   */
	function getGroupStartEnd(){
		if(groupSize==null)
			return;
		var groupId = Math.ceil(currentPage/groupSize);
		gpStartPage = (groupId-1)*groupSize+1;
		gpEndPage = Math.min(groupId*groupSize,totalPage);
		gpStartRecord = (gpStartPage-1)*perPage+1;
		gpEndRecord = Math.min(gpEndPage*perPage,totalRecord);
	}

	/**
	   * 刷新数据容器
	   */
	function loadData(){
		var view = "";
		/* add by luoj 20090710 */
		if(dataStore && dataStore.length > 0){
		/* add by luoj 20090710 */
		for(var i=0;i<=dataStore.length-1;i++)
			view += dataStore[i];
		valContainer.html(view);
		/* add by luoj 20090710 */
		}
		/* add by luoj 20090710 */
	}

	/**
	   * 刷新工具栏状态
	   */
	function refresh(){
		//当前页码写入cookie
		//jQuery.cookie(t+'_currentPage', currentPage);
		//jQuery.cookie(t+'_perPage', perPage);

		valCurrentPage.val(currentPage);
		valStartRecord.html(startRecord);
		valEndRecord.html(endRecord);
		valTotalPage.html(totalPage);
		
		btn.unbind("mousedown",pressHandler);
		btn.bind("mouseup",unpressHandler);
		btn.bind("mouseout",unpressHandler);
		enabled();
		if(currentPage > 1){
			btnBack.bind("mousedown",pressHandler);
			btnPrev.addClass("pgPrev");
			btnFirst.addClass("pgFirst");
		} else {
			btnPrev.addClass("pgPrevDisabled");
			btnFirst.addClass("pgFirstDisabled");
		}
		if(currentPage < totalPage){
			btnGo.bind("mousedown",pressHandler);
			btnNext.addClass("pgNext");
			btnLast.addClass("pgLast");
		} else {
			btnNext.addClass("pgNextDisabled");
			btnLast.addClass("pgLastDisabled");
		}
		overLoad();
	}
	
	/**
	   * 移除按钮disabled状态样式
	   */
	function enabled(){
			btnNext.removeClass("pgNextDisabled");
			btnPrev.removeClass("pgPrevDisabled");
			btnFirst.removeClass("pgFirstDisabled");
			btnLast.removeClass("pgLastDisabled");
	}

	/**
	   * 添加按钮按下状态样式
	   */
	function pressHandler(){
		jQuery(this).addClass("pgPress");
	}

	/**
	   * 移除按钮按下状态样式
	   */
	function unpressHandler(){
		jQuery(this).removeClass("pgPress");
	}

}