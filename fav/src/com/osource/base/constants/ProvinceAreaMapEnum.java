package com.osource.base.constants;

public enum ProvinceAreaMapEnum {

	SHANDONG("山东", "华东"),
	JIANGSU("江苏", "华东"),
	JIANGXI("江西", "华东"),
	ANHUI("安徽", "华东"),
	ZHEJIANG("江苏", "华东"),
	SHANGHAI("上海", "华东"),
	
	HUNAN("湖南", "华东"),
	GUANGDONG("广东", "华东"),
	SHANXI1("山西", "华东"),
	FUJIAN("福建 ", "华东"),
	
	BEIJING("北京 ", "华北"),
	TIANJING("天津 ", "华北"),
	HEBEI("河北 ", "华北"),
	NEIMENG("内蒙 ", "华北"),
	SHANXI("山西 ", "华北"),

	YUNNAN("云南 ", "西南"),
	GUIZHOU("贵州 ", "西南"),
	SICHUAN("四川 ", "西南"),
	CHONGQING("重庆", "西南"),
	XIZANG("西藏", "西南"),
	
	SHANXI2("陕西", "西北"),
	GANSU("甘肃", "西北"),
	NINGXIA("宁夏", "西北"),
	XINJIANG("新疆", "西北"),
	QINGHAI("青海", "西北")
	
	;

	private String province;
	
	private String area;
	
	ProvinceAreaMapEnum(String province, String area) {
		this.province = province;
		this.area = area;
	}

	public String getProvince() {
		return province;
	}

	public String getArea() {
		return area;
	}
	
	public static ProvinceAreaMapEnum getEnumByProvince(String province) {
		for(ProvinceAreaMapEnum tmp : ProvinceAreaMapEnum.values()) {
			if(province.startsWith(tmp.getProvince())) {
				return tmp;
			}
		}
		return null;
	}
}
