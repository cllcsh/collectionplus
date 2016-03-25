package com.osource.template2.generator;

import java.util.ArrayList;
import java.util.List;

import com.osource.common.developer.ModuleGenerator;

public class TemplateGenerator {
    private static String packName = "com.osource.template2.generator";// properties文件所在包名
    private static String basePagePath = "osource";// jsp文件存放根路径，所在工程下开始，即工程路径下的basePagePath

//    private static String fileName = "config_fav_task_points_config.properties";// 站内信

    public static void main(String[] args) {
    	List<String> list = new ArrayList<String>();
//    	list.add("config_fav_city.properties");
//    	list.add("config_fav_collection_category.properties");
//    	list.add("config_fav_collection_images.properties");
//    	list.add("config_fav_collection_lable.properties");
//    	list.add("config_fav_county.properties");
    	list.add("config_fav_collection_category.properties");
    	list.add("config_fav_collection_period.properties");
//    	list.add("config_fav_province.properties");
//    	list.add("config_fav_special.properties");
//    	list.add("config_fav_user_interes_category.properties");
//    	list.add("config_fav_user_title.properties");
//    	list.add("config_fav_enum.properties");
    	for (String fileName : list) {
    		new ModuleGenerator(packName, fileName, basePagePath).generate();
		}
    }
}
