
var highlightindex = -1; //定义高亮显示索引,-1代表不高亮任何行 
var delayTime = 500; //默认延迟0.5秒
var minPrefix = 1; //定义最小几个字符开始搜索
var mouseOverCss = { background: "white", cursor: " pointer" }; //mouseover时样式
var mouseOutCss = { background: "white" }; //mouseout时样式
var grayCss = { background: "#cef", cursor: " pointer" };
var upDownGrayCss = { background: "#cef" };
var upDownWhiteCss = { background: "white" };
var wordInput = $("#name");
function change() {
        var wordInputOffset = wordInput.position();
        $("#divAutoList")
              .css("position", "absolute")
              .css("border", "1px black solid") //边框 黑色
              .css("top", wordInputOffset.top + wordInput.height() + 5 + "px")
              .css("left", wordInputOffset.left)
              .css("background", "white")
              .height(200)
              .width(wordInput.width() + 2); 
        document.getElementById("criminalId").value="";
        var myEvent = event || window.event;
        var keyCode = myEvent.keyCode;
        var autoNode = $("#divAutoList"); //可供选择的项
        if (keyCode != 38 && keyCode != 40) { //不是三个特殊键，可以搜索
            var wordText = $("#name").val();
            document.getElementById("nameTemp").value=$("#name").val();
            document.getElementById("eduDeptTemp").value=$("#deptId").val();
            document.getElementById("allCriminalIdTemp").value=$("#allCriminalId").val();
	        var formData = $("#commonForm").serialize();
        //ajax请求
        $.ajax({
		type: "POST",
		url: "voiceCheck_findCriminal.do?mode=ajaxJson",
		data: formData,
		success: function(json){
		            var wordNodes = $(json);
		            if(wordNodes==null){
		            return;
		            }
                    autoNode.html("");
                    wordNodes.each(function(i) {
                        var wrodNode = wordNodes[i].value;
                        var newDivNode = $("<div>").attr("id", i);
                        newDivNode.html(wrodNode).appendTo(autoNode); 
                        //添加光标进入事件, 高亮节点
                        newDivNode.mouseover(function() {
                            if (highlightindex != -1) {
                                $("#divAutoList").children("div").eq(highlightindex).css(mouseOverCss);
                            }
                            highlightindex = $(this).attr("id");
                            $(this).css(grayCss);
                        });

                        //添加光标移出事件,取消高亮
                        newDivNode.mouseout(function() {
                            $(this).css(mouseOutCss);
                        });

                        newDivNode.mousedown(function() {
                            var comText = $(this).text();
                            $("#divAutoList").hide();
                            highlightindex = -1;
                            $("#name").val(comText);
                            ensure(wordNodes[$(this).attr("id")].key);
                            autoNode.html("");
                            wordNodes="";
                        });
                    });
                    if (wordNodes.length > 0) {
                        autoNode.show();
                    }
                    else {
                        autoNode.hide();
                        highlightindex = -1;
                    }

                }, 
               dataType: "json"
               }); 
        }
        else if (keyCode == 38) {//输入向上,选中文字高亮
            var autoNodes = $("#divAutoList").children("div")
            if (highlightindex != -1) {
                autoNodes.eq(highlightindex).css(upDownWhiteCss);
                highlightindex--;
            }
            else {
                highlightindex = autoNodes.length - 1;
            }

            if (highlightindex == -1) {
                highlightindex = autoNodes.length - 1;
            }
            autoNodes.eq(highlightindex).css(upDownGrayCss);
             $("#name").val(autoNodes.eq(highlightindex).text());
        }
        else if (keyCode == 40) {//输入向下,选中文字高亮
            var autoNodes = $("#divAutoList").children("div")
            if (highlightindex != -1) {
                autoNodes.eq(highlightindex).css(upDownWhiteCss);
            }
            highlightindex++;
            if (highlightindex == autoNodes.length) {
                highlightindex = 0;
            }
            autoNodes.eq(highlightindex).css(upDownGrayCss);
             $("#name").val(autoNodes.eq(highlightindex).text());
        }
    }
function ensure(it){
    document.getElementById("criminalId").value=it; 
}
function makeEmpty(){
    document.getElementById("criminalId").value=""; 
    document.getElementById("name").value=""; 
    var autoNode = $("#divAutoList"); //可供选择的项
    autoNode.hide();
}
