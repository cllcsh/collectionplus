/**
 * 文件名：TaskPointsConfigInfo.java
 * 
 * 版本信息：  2.0
 * 日期：2015-11-06
 * 
 */
package com.osource.module.fav.model;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.osource.core.model.BaseModel;
import com.osource.orm.ibatis.TableNameAware;

/**
 * 项目名称：osource
 * <p>类名称：TaskPointsConfigInfo
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-06
 * @version 2.0
 */
@SuppressWarnings("serial")
public class TaskPointsConfigInfo extends BaseModel implements TableNameAware {
	// 任务类型：发藏品：release_collection，发个人动态：release_dynamic，获赞：get_like，更换头像：change_avatar
    private String taskName;
    // 任务类型：发藏品：release_collection，发个人动态：release_dynamic，获赞：get_like，更换头像：change_avatar
    private String taskNameDesc;
    // 积分
    private int points;
    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public String getTaskNameDesc() {
		return taskNameDesc;
	}
	public void setTaskNameDesc(String taskNameDesc) {
		this.taskNameDesc = taskNameDesc;
	}
	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
	public String getDbTableName() {
		return "tb_task_points_config";
	}
}