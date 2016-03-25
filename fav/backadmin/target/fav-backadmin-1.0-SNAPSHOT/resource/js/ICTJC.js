Namespace=new Object();Namespace.register=function(fullNS){var nsArray=fullNS.split('.');var sEval="";var sNS="";for(var i=0;i<nsArray.length;i++){if(i!=0)sNS+=".";sNS+=nsArray[i];sEval+="if (typeof("+sNS+") == 'undefined') "+sNS+" = new Object();"}if(sEval!="")eval(sEval)}
Namespace.register("ICTJC");
ICTJC.getContextPath = function(){
	if(_contextPath && typeof(_contextPath) != "undefined"){
		return _contextPath;
	}
	return "";
}
Namespace.register("ICTJC.archives");
ICTJC.ajax = function(url,formData,callback){
	$.ajax({
		type: "POST",
		url: ICTJC.getContextPath() + url,
		data: formData,
		success: function(data){
			if(jQuery.isFunction(callback)){
				callback(data);
			}
		},
		error: function(){
			
		},
		dataType: "json"
	});
};
ICTJC.archives.saveResume = function(formData,callback){
	ICTJC.ajax("/module/archives/resume_save.do?mode=ajaxJson",callback);
};
ICTJC.archives.updateResume = function(){
	ICTJC.ajax("/module/archives/resume_update.do?mode=ajaxJson",callback);
};
