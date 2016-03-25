/**
*  保留小数多少位
*  number 格式化对象
*  pos 保留位数
**/
function number_format(number, pos) {
	var strNum=new String(number);
	dPos = strNum.indexOf(".");
	if (dPos >= 0)
	{
		var beStr = strNum.substring(0,dPos);
		var enStr = strNum.substring(dPos + 1);
		if (enStr.length <= pos)
		{
			return strNum;
		} else {
			return number.toFixed(pos);
		}
	} else {
		return strNum;
	}
}