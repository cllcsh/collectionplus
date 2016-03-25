/**
   * checkbox for  jQuery全选/取消全选插件
   * 功能：指定的范围内checkbox的全选/取消全选功能
   * @author luoj
   * @version 1.0
   * @date 2009-04-18
   * @param config 插件配置
   */

jQuery.fn.checkbox = function(config){
	var parent = $(this);
	function init(all,item){
		parent.find(":checkbox." + all).bind("click",function(){	//绑定全选框的点击事件
			parent.find(":checkbox." + item).attr("checked", $(this).attr("checked"));
			parent.find(":checkbox." + all).attr("checked", $(this).attr("checked"));
		});

		parent.find(":checkbox." + item).bind("click",function(){	//绑定子选择框的点击事件
			if(parent.find(":checkbox." + item).size() === parent.find(":checkbox." + item + ":checked").size()){
				parent.find(":checkbox." + all).attr("checked",true);
			}else{
				parent.find(":checkbox." + all).attr("checked",false);
			}
		});

		if(parent.find(":checkbox." + item).size() === parent.find(":checkbox." + item + ":checked").size()){	//初始化按钮的选中状态
			parent.find(":checkbox." + all).attr("checked",true);
		}
	}
	
	/**
	 * 获取已选中值, 并以逗号分隔的字符串返回数据
	 */
	this.val = function(item){
		var string = "";
		var num = 0;
		parent.find(":checkbox." + item + ":checked").each(function(){
			if(num > 0){
				string += ",";
			}
			string += $(this).val();
			num = num + 1;
		});
        return string;
	};
	
	for(var i in config){
		init(config[i].all, config[i].item);
	}
	return this;
}