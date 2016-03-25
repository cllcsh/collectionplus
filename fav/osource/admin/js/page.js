function Page(showPageCountDiv,action) {
	var start = 1;
	var page;
	var list;
	var jsonDate;
	var pCount=0;
	var maxPageButton=5;
	var end;
	var pageNumber;
	var showPageCountDiv = showPageCountDiv;
	this.params={};
	this.showPageCountDiv=showPageCountDiv;
	this.showPageCount=function(){
		var o  = this;
		this.showPageCountDiv.html("");
		if(pCount > 1){
			var ul = $("<ul/>");
			var li_a_first = $(["<a href='javascript:void(0)' pageNo='",1,"'/>"].join(""));
			var li_a_have =$(["<a href='javascript:void(0)' pageNo='",pCount,"'/>"].join(""));
			ul.append($("<li/>").append(li_a_first.text('首页')));
			for(var i=start;i<end+1;i++){
				var pageNo = i;
				var li_a = $(["<a href='javascript:void(0)' pageNo='",pageNo,"'/>"].join(""));
				if(pageNumber == pageNo){
					ul.append($("<li/>").addClass("active").append($("<a/>").text(pageNo)));
				}else{
					ul.append($("<li/>").append(li_a.text(pageNo)));
				}				
			}
			ul.append($("<li/>").append(li_a_have.addClass("current").text('尾页')));			
			this.showPageCountDiv.append(ul);
		}
		o.showPageCountDiv.find("a[href]").click(function(){
			 pageNo = $(this).attr("pageNo");
			 o.pageTo(pageNo);		 
		});
	}

	this.showContent = function(){}
	var initDate=function(page){
		page = page;
		pCount = parseInt(page.totalCount % page.pageSize == 0 ? page.totalCount
				/ page.pageSize : page.totalCount / page.pageSize + 1);
		end = pCount;
		pageNumber=page.pageNumber;
		list = page.list;
		//alert(var pCount)
		if(pCount > 1){
			// 当前总页面数超过可显示的数量
			if (pCount > maxPageButton) {
				half = parseInt((maxPageButton / 2) - 1);
				if (maxPageButton % 2 > 0) {
					half++;
				}
				// 当前页面是否过显示数的一半
				// 不是,则从1开始显示.
				if (pageNumber > half) {
					// 是,还要检查,当前页面后面是否还有足够页面显示后面一半.
					afterN = pCount - pageNumber;
					if (afterN < half) {
						// 不够,则从最后倒推n个页面.
						end = pCount;
						start = pCount - maxPageButton + 1;
					} else {
						// 够,则从当前页面之前的n/2个页面开始显示.
						start = pageNumber - half;
						end = start + maxPageButton - 1;
					}
		
				} else {
					end = maxPageButton;
				}
			}
			start = parseInt(start);
			end = parseInt(end);
			
		}
	}
	this.over = function(fnc){
		this.showContent = fnc;
	}
	
	this.pageTo = function(i){
		var o = this;
		
		//var a = action +"?pageNumber="+i;
		this.params['pageNumber']=i;
//        for (var key in this.params){
//        	a = a +"&"+key+"="+this.params[key];		     
//        }
		$.post(action,this.params,function(json){
			
			jsonDate = json;
			//alert(JSON.stringify(json));
			initDate(json.page);
			o.showContent();
			o.showPageCount();
		},"json");

	}
	this.init = function(){
		jsonDate="";
		this.pageTo(0);		
	}
	this.getPageList = function(){
		return list;
	}
	this.getJsonDate = function(){
		return jsonDate;
	}
	this.getPCount = function(){
		alert(showContent);
	}
	this.setAction = function(ac){
		action = ac;
	}
	this.getPageNumber=function(){
		return pageNumber;
	}
	
}