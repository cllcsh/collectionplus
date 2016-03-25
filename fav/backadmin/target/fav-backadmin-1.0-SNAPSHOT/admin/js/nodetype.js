function nodetype(webRoot) {
	var date={};
	var action = webRoot +"findNodeTypeValue.action";
	var flag = false;
	function initDate(callback,s,key){
		$.post(action,null,function(json){
			date = json.o;
			flag = true;
			callback(s,key);
			
		},"json");
	}
	
	this.setSelect = function(s,k){
		if(flag)
		{
			for(var key in date){
				var $option = $("<option/>");
				$option.val(key);
				$option.text(date[key]);
				s.append($option);
			}
			if(k!=-1){
				s.find("option[value="+k+"]").attr("selected","true");
			}
		}else{
			initDate(this.setSelect,s,k);
		}
	}
	this.getTextForKey = function(s,key){
		if(flag){
			s.text(date[key]);
		}else{
			initDate(this.getTextForKey,s,key);
		}
	}
}