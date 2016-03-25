/**
 * 将组织信息组织化
 * @param div 添加到哪个div里面,WebRoot 地址
 * @return
 */
function organization(div,webRoot){
	var $div = div;
	var action =webRoot+"getAllList.action";
	var name = "organ";
	function getAreaById(id,index){
		var param = {};
		if(id!=-1){
			param['organ.id']=id;
		}
		var $s = getSelect(index);
		if((index>0 && id!=-1) || (index==0 && id==-1)){
			$.post(action,param,function(json){
				if(json.status=="success"){
					if(json.list!=undefined)
					{
						$.each(json.list,function(i,area){
							$s.append(getAreaOption(area));
						});
						$s.change(function(){
							changeArea($(this));
						});
						$div.append($s);
						return;
					}
				}
				if(id==-1 && index==0){
					$div.append($s);
				}
			},"json");	
		}
	}
	this.init = function(path){
		
		if(path!=""){
			var areaids = new Array();
			path = "-1,"+path;
			areaids = path.split(",");
			getArea(areaids,0);
		}else{
			getAreaById(-1,0);
		}
	}
	//显示子级选中
	function getArea(areaids,i){
		if(i+1<areaids.length){
			var param = {};
			if(areaids[i]!=-1){
				param['organ.id']=areaids[i];
			}
			$.post(action,param,function(json){
				if(json.status=="success"){
					if(json.list!=undefined)
					{
							var $s =getSelect(i+1);
							$.each(json.list,function(k,area){
								$s.append(getAreaOption(area));
							});
							$s.change(function(){
								changeArea($(this));
							});
							div.append($s);
							//选中地区
							$("select[name="+name+"][index="+i+"]").find("option[value="+areaids[i]+"]").attr("selected","true");
					}
				}
				//加载完顶级后才能加载下一级,防止异步时数据显示混乱
				getArea(areaids,++i);
			},"json");
		}else{
			//最后一级地区,有子级则触发事件将其显示
			 $("select[name="+name+"][index="+i+"]").find("option[value="+areaids[i]+"]").attr("selected","true");
			 $("select[name="+name+"][index="+i+"]").change();	
			}
		}
    function changeArea(s){
		var id = s.find("option:selected").val();
		var index = s.attr("index");
		$div.find("select[index="+index+"]").nextAll().remove();
		getAreaById(id,parseInt(index)+1);
    }
	function getSelect(index){
		var $s = $("<select/>");
		$s.attr("index",index);
		$s.attr("name",name);
		$s.append(getDefualOption());
		return $s;
	}
	function getDefualOption(){
		var $option = $("<option/>");
		$option.val(-1);
		$option.text("-----请选择------");
		return $option;
	}
	function getAreaOption(area){
		var $option = $("<option/>");
		$option.val(area.id);
		$option.text(area.name);
		return $option;
	}
}
