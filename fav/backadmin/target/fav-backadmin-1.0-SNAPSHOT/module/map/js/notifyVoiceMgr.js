/**
 * 抽查声纹识别页面脚本
 * @author zhouhaiyan
 * @create 2012-05-14
 * @file notifyVoice.js
 * @since v0.1
 */

//注册
function voice_register(id, msdn)
{
	$.ajax({
		type: "POST",
		url: "notifyVoice_register.do?criminalId="+id + "&msdn=" + msdn,
		success: function(json){
			alert(json.message);
			if(json.codeid == 0){
			}
		},
		dataType: "json"
	})
}

//验证编辑
//function voice_check(id, msdn){
//	location.href("notifyVoice_edit.do?voiceCheckForm.criminalId="+id);
//}

//验证
function voice_check(id, msdn, voiceNo)
{
	$.ajax({
		type: "POST",
		url: "notifyVoice_check.do?recordflag=0&imptype=9&criminalId="+id + "&msdn=" + msdn + "&voiceNo=" + voiceNo,
		success: function(json){
			alert(json.message);
			if(json.codeid == 0){
			}
		},
		dataType: "json"
	})
}

//修改
function voice_update(id, msdn, voiceNo)
{
	$.ajax({
		type: "POST",
		url: "notifyVoice_update.do?criminalId="+id + "&msdn=" + msdn + "&voiceNo=" + voiceNo,
		success: function(json){
			alert(json.message);
			if(json.codeid == 0){
			}
		},
		dataType: "json"
	})
}

//删除
function voice_delete(id, msdn, voiceNo)
{
	$.ajax({
		type: "POST",
		url: "notifyVoice_delete.do?criminalId="+id + "&msdn=" + msdn + "&voiceNo=" + voiceNo,
		success: function(json){
			alert(json.message);
			if(json.codeid == 0){
			}
		},
		dataType: "json"
	})
}



