/**
   * jPagination for  jQuery分页插件
   * 功能：指定页数内静态分页，超过指定页数后ajax请求下一组静态分页
   * @author 陈健
   * @version 1.0 beta 4
   * @date 2008-04-23
   * @param config 插件配置
   */
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
	var totalRecord = config.totalRecord > 0 ? config.totalRecord : 0;																	//总记录数
	if(totalRecord == 0){
		jQuery(t).css("text-align","center");
		jQuery(t).css("line-height","50px");
		jQuery(t).html("很遗憾，没有检索到任何记录！");
		return;
	}
	var buildData = config.buildData ? config.buildData : function(datas,start,end){
			var view = "";
			for(var i=start-1;i<=end-1;i++)
				view += datas[i];
			return view;
		};
	var configPage = config.perPage > 0 ? config.perPage : 10;																					
//	var perPage = jQuery.cookie(t+"_perPage") == null ? configPage : parseInt(jQuery.cookie(t+"_perPage"));				//每页显示记录数
	var perPage = configPage;
	var proxyUrl = config.proxyUrl > 0 ? config.proxyUrl : 'pgdataproxy.jsp';															//数据代理地址
	var groupSize = config.groupSize;																																//组大小
	var barPosition = config.barPosition == null ? 'bottom' : config.barPosition;													//工具条位置
	var ajaxParam = config.ajaxParam;																																//ajax的请求参数
	var showMode = config.showMode == null ? 'full' : config.showMode;															//显示模式

	//私有变量
	var totalPage = Math.ceil(totalRecord/perPage);																									//总页数
//	var currentPage = jQuery.cookie(t+"_currentPage") == null ? 1 : parseInt(jQuery.cookie(t+"_currentPage"));				//当前页码
	var currentPage = 1;
	var startRecord;																																								//每页起始记录
	var endRecord;	 																																							//每页结束记录
	var gpStartPage;
	var gpEndPage;
	var gpStartRecord;
	var gpEndRecord;

	//数据容器
	var container = '<div class="pgContainer"></div>'

	//添加工具条
	var toolbar = '<div class="pgToolbar">';
	toolbar += '<div class="pgPanel">';
	if(showMode == 'full'){
		toolbar += '<div><select class="pgPerPage">';
		if(config.perPage>0)
			toolbar += '<option value="'+config.perPage+'">'+config.perPage+'</option>';
		toolbar += '<option value="5">5</option>';
		toolbar += '<option value="10">10</option>';
		toolbar += '<option value="15">15</option>';
		toolbar += '<option value="20">20</option>';
		toolbar += '<option value="25">25</option>';
		toolbar += '<option value="40">40</option>';
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

	if(perPage<totalRecord)
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
	else
		jQuery(t).html(container);

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
	refresh();
	
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
	
	//页码输入框监听
	valCurrentPage.keydown(
		function(){
			var targetPage = parseInt(jQuery(this).val());
			if(event.keyCode==13 && targetPage>=1 && targetPage<=totalPage){
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
	);
	
	valPerPage.change(
		function(){
			perPage = parseInt(jQuery(this).val());
			currentPage = 1;
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
		jQuery(t+" .pgRefresh").removeClass("pgLoad");
		jQuery(t+" .pgSearchInfo").html('检索到&nbsp;' + totalRecord + '&nbsp;条记录，显示第&nbsp;<span class="pgStartRecord">' + startRecord + '</span>&nbsp;条&nbsp;-&nbsp;第&nbsp;<span class="pgEndRecord">' + endRecord + '</span>&nbsp;条记录');
		jQuery(mask).remove();
		//jQuery(mask).fadeOut("slow");
	}

	/**
	   * 获得远程数据
	   */
	function getRemoteData(){
		startLoad();
		jQuery.ajax(
			{
				type: "POST",
				url: proxyUrl + "?startrecord="+gpStartRecord+"&endrecord="+gpEndRecord ,
				cache: false,
				data: ajaxParam,
				dataType: "script",
				timeout: 30000,
				success: function(msg){
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
	
	/**
	   * 获得当前页开始结束记录
	   */
	function getStartEnd(){
		if(groupSize){
			startRecord = (currentPage-1)*perPage+1 - gpStartRecord + 1;
			endRecord = Math.min(currentPage*perPage,totalRecord) - gpStartRecord + 1;
		}else{
			startRecord = (currentPage-1)*perPage+1;
			endRecord = Math.min(currentPage*perPage,totalRecord);
		}
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
		var view = buildData(dataStore,startRecord,endRecord);
		//for(var i=startRecord-1;i<=endRecord-1;i++)
			//view += dataStore[i];
		valContainer.html(view);
	}

	/**
	   * 刷新工具栏状态
	   */
	function refresh(){
		//当前页码写入cookie
//		jQuery.cookie(t+'_currentPage', currentPage);
//		jQuery.cookie(t+'_perPage', perPage);

		valCurrentPage.val(currentPage);
		valStartRecord.html(startRecord);
		valEndRecord.html(endRecord);
		valTotalPage.html(totalPage);
		
		btn.unbind("mousedown",pressHandler);
		btn.bind("mouseup",unpressHandler);
		btn.bind("mouseout",unpressHandler);
		if(currentPage == totalPage){
			enabled();
			btnBack.bind("mousedown",pressHandler);
			btnNext.addClass("pgNextDisabled");
			btnLast.addClass("pgLastDisabled");
		}else	if(currentPage == 1){
			enabled();
			btnGo.bind("mousedown",pressHandler);
			btnPrev.addClass("pgPrevDisabled");
			btnFirst.addClass("pgFirstDisabled");
		}else{
			enabled();
			btnBack.bind("mousedown",pressHandler);
			btnGo.bind("mousedown",pressHandler);
			btnNext.addClass("pgNext");
			btnPrev.addClass("pgPrev");
			btnFirst.addClass("pgFirst");
			btnLast.addClass("pgLast");
		}
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