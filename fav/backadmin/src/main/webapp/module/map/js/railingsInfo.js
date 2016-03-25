var iid = 0;
function getRailing(config){
				document.openDialog("railing").html("<div><iframe onload='javascript:setUseFlag()' border='0' frameborder='0' width='450px' height='200px' id='railingList' src='"+_contextPath+"/module/map/railings_init.do'></iframe><br/><div style='height:5px;'></div><input  class='button'  type='button' value='确定' id='ok'></div>");
	$("#ok").bind("click",config,re);
	//iid = setInterval(setUseFlag, 100);
	//alert(document.frames["railingList"].useFlag);//此时还没有渠道useFlag的值 
}

function setUseFlag(){
	if(document.frames["railingList"].useFlag == "1"){
		document.frames["railingList"].useFlag = "0";
		//window.clearInterval(iid);
		//alert(document.frames["railingList"].useFlag);//等上面的iframe刷出来之后再设值，所以时间不能太短 ,而时间太长会导致用户触发时值还未变
	}
	
}
function re(config){
	var callback = config.data.callback == null ? function(cpage,fn){} : config.data.callback;

	var param = {id : document.frames["railingList"].getID,
				name : document.frames["railingList"].getName,
				type : document.frames["railingList"].getType}
	jQuery.extend(param, config.data.data);
	callback(param);
	jQuery("#railing").dialog('close');
}