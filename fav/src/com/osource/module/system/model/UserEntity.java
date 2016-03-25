package com.osource.module.system.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.osource.base.model.BaseEntity;
import com.osource.base.model.ColBean;
import com.osource.core.BeanProvider;
import com.osource.util.PwdEncoder;

/**
 * @author : Feng Jingzhun
 * @version : 1.0
 * @date : 2009-11-5 15:38:39
 */
public class UserEntity extends BaseEntity
{
    private ColBean<String> loginName = new ColBean<String>("login_name", ColBean.STRING);
    private ColBean<String> password = new ColBean<String>("password", ColBean.STRING);
    private ColBean<String> formerPassword = new ColBean<String>("former_password", ColBean.STRING);
    private ColBean<String> name = new ColBean<String>("name", ColBean.STRING);
    private ColBean<String> sex = new ColBean<String>("sex", ColBean.STRING);
    private ColBean<String> idNum = new ColBean<String>("id_num", ColBean.STRING);
    private ColBean<String> birthday = new ColBean<String>("birthday", ColBean.STRING);
    private ColBean<String> nativePlace = new ColBean<String>("native_place", ColBean.STRING);
    private ColBean<String> politicalStatus = new ColBean<String>("political_status", ColBean.STRING);
    private ColBean<String> locNo = new ColBean<String>("loc_no", ColBean.STRING);
    private ColBean<String> phoneNo = new ColBean<String>("phone_no", ColBean.STRING);
    private ColBean<String> workUnit = new ColBean<String>("work_unit", ColBean.STRING);
    private ColBean<String> establishment = new ColBean<String>("establishment", ColBean.STRING);
    private ColBean<String> duty = new ColBean<String>("duty", ColBean.STRING);
    private ColBean<String> address = new ColBean<String>("address", ColBean.STRING);
    private ColBean<String> staffType = new ColBean<String>("staff_type", ColBean.STRING);
    private ColBean<String> email = new ColBean<String>("e_mail", ColBean.STRING);
    private ColBean<String> faxNo = new ColBean<String>("fax_no", ColBean.STRING);
    private ColBean<String> postcode = new ColBean<String>("postcode", ColBean.STRING);
    private ColBean<String> eduBg = new ColBean<String>("edu_bg", ColBean.STRING);
    private ColBean<String> affiance = new ColBean<String>("affiance", ColBean.STRING);
    private ColBean<String> staffStatus = new ColBean<String>("staff_status", ColBean.STRING);
    private ColBean<String> workHis = new ColBean<String>("work_his", ColBean.STRING);
    private ColBean<String> studyHis = new ColBean<String>("study_his", ColBean.STRING);
    private ColBean<String> interest = new ColBean<String>("interest", ColBean.STRING);
    private ColBean<String> picPath = new ColBean<String>("pic_path", ColBean.STRING);
    private ColBean<Date> lastLoginTime = new ColBean<Date>("last_login_time", ColBean.DATETIME);
    private ColBean<Integer> onlineTimes = new ColBean<Integer>("online_times", ColBean.INTEGER);
    private ColBean<String> deptName = new ColBean<String>("elisor_name", ColBean.STRING);    
    private ColBean<String> approveFlag = new ColBean<String>("approve_flag", ColBean.STRING);
    
    private ColBean<String> nickname = new ColBean<String>("nickname", ColBean.STRING);
    private ColBean<String> userType = new ColBean<String>("user_type", ColBean.STRING);
    private ColBean<String> vipPass = new ColBean<String>("vip_pass", ColBean.STRING);
    private ColBean<String> country = new ColBean<String>("country", ColBean.STRING);
    
    private ColBean<String> rybm = new ColBean<String>("rybm", ColBean.STRING);
    /*private ColBean<String> ywm = new ColBean<String>("ywm", ColBean.STRING);
    private ColBean<String> mz = new ColBean<String>("mz", ColBean.STRING);
    private ColBean<String> byyx = new ColBean<String>("byyx", ColBean.STRING);
    private ColBean<String> zgxw = new ColBean<String>("zgxw", ColBean.STRING);
    private ColBean<String> zy = new ColBean<String>("zy", ColBean.STRING);
    private ColBean<String> ssjg = new ColBean<String>("ssjg", ColBean.STRING);
    private ColBean<String> cjgzsj = new ColBean<String>("cjgzsj", ColBean.STRING);*/
    private ColBean<String> jgbm = new ColBean<String>("jgbm", ColBean.STRING);
    
    //关联字段
    //private String deptName;

    //建立实体与表映射
    public UserEntity()
    {
        this.setTableName("tb_user");
    }

    public ColBean<String> getLoginName()
    {
        return loginName;
    }

    public void setLoginName(ColBean<String> loginName)
    {
        this.loginName = loginName;
    }

    public ColBean<String> getPassword()
    {
        return password;
    }

    public void setPassword(ColBean<String> password)
    {
        this.password = password;
    }

    public ColBean<String> getFormerPassword()
    {
        return formerPassword;
    }

    public void setFormerPassword(ColBean<String> formerPassword)
    {
        this.formerPassword = formerPassword;
    }

    public ColBean<String> getName()
    {
        return name;
    }

    public void setName(ColBean<String> name)
    {
        this.name = name;
    }

    public ColBean<String> getSex()
    {
        return sex;
    }

    public void setSex(ColBean<String> sex)
    {
        this.sex = sex;
    }

    public ColBean<String> getIdNum()
    {
        return idNum;
    }

    public void setIdNum(ColBean<String> idNum)
    {
        this.idNum = idNum;
    }

    public ColBean<String> getBirthday()
    {
        return birthday;
    }

    public void setBirthday(ColBean<String> birthday)
    {
        this.birthday = birthday;
    }

    public ColBean<String> getNativePlace()
    {
        return nativePlace;
    }

    public void setNativePlace(ColBean<String> nativePlace)
    {
        this.nativePlace = nativePlace;
    }

    public ColBean<String> getPoliticalStatus()
    {
        return politicalStatus;
    }

    public void setPoliticalStatus(ColBean<String> politicalStatus)
    {
        this.politicalStatus = politicalStatus;
    }

    public ColBean<String> getLocNo()
    {
        return locNo;
    }

    public void setLocNo(ColBean<String> locNo)
    {
        this.locNo = locNo;
    }

    public ColBean<String> getPhoneNo()
    {
        return phoneNo;
    }

    public void setPhoneNo(ColBean<String> phoneNo)
    {
        this.phoneNo = phoneNo;
    }

    public ColBean<String> getWorkUnit()
    {
        return workUnit;
    }

    public void setWorkUnit(ColBean<String> workUnit)
    {
        this.workUnit = workUnit;
    }

    public ColBean<String> getEstablishment()
    {
        return establishment;
    }

    public void setEstablishment(ColBean<String> establishment)
    {
        this.establishment = establishment;
    }

   public ColBean<String> getDuty()
    {
        return duty;
    }

    public void setDuty(ColBean<String> duty)
    {
        this.duty = duty;
    }

    public ColBean<String> getAddress()
    {
        return address;
    }

    public void setAddress(ColBean<String> address)
    {
        this.address = address;
    }

    public ColBean<String> getStaffType()
    {
        return staffType;
    }

    public void setStaffType(ColBean<String> staffType)
    {
        this.staffType = staffType;
    }

    public ColBean<String> getEmail()
    {
        return email;
    }

    public void setEmail(ColBean<String> email)
    {
        this.email = email;
    }

    public ColBean<String> getFaxNo()
    {
        return faxNo;
    }

    public void setFaxNo(ColBean<String> faxNo)
    {
        this.faxNo = faxNo;
    }

    public ColBean<String> getPostcode()
    {
        return postcode;
    }

    public void setPostcode(ColBean<String> postcode)
    {
        this.postcode = postcode;
    }

    public ColBean<String> getEduBg()
    {
        return eduBg;
    }

    public void setEduBg(ColBean<String> eduBg)
    {
        this.eduBg = eduBg;
    }

    public ColBean<String> getAffiance()
    {
        return affiance;
    }

    public void setAffiance(ColBean<String> affiance)
    {
        this.affiance = affiance;
    }

    public ColBean<String> getStaffStatus()
    {
        return staffStatus;
    }

    public void setStaffStatus(ColBean<String> staffStatus)
    {
        this.staffStatus = staffStatus;
    }

    public ColBean<String> getWorkHis()
    {
        return workHis;
    }

    public void setWorkHis(ColBean<String> workHis)
    {
        this.workHis = workHis;
    }

    public ColBean<String> getStudyHis()
    {
        return studyHis;
    }

    public void setStudyHis(ColBean<String> studyHis)
    {
        this.studyHis = studyHis;
    }

    public ColBean<String> getInterest()
    {
        return interest;
    }

    public void setInterest(ColBean<String> interest)
    {
        this.interest = interest;
    }

    public ColBean<String> getPicPath()
    {
        return picPath;
    }

    public void setPicPath(ColBean<String> picPath)
    {
        this.picPath = picPath;
    }
/*
    public String getDeptName()
    {
        return deptName;
    }

    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }
*/
    public ColBean<Date> getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(ColBean<Date> lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public ColBean<Integer> getOnlineTimes() {
		return onlineTimes;
	}

	public void setOnlineTimes(ColBean<Integer> onlineTimes) {
		this.onlineTimes = onlineTimes;
	}

	private UserExt userExt;  //额外查询用字段

    public UserExt getUserExt()
    {
        if (userExt == null)
        {
            userExt = new UserExt();
        }
        return userExt;
    }

    public void setUserExt(UserExt userExt)
    {
        this.userExt = userExt;
    }

    public void encodePassword()
    {
        List<String> fpList = formerPassword.getValues();
        if (fpList != null && fpList.size() > 0)
        {
            List<String> p = new ArrayList<String>();
            for (String fp : fpList)
            {
                p.add(((PwdEncoder) BeanProvider.getBean("pwdEncoder")).encodePassword(fp));
            }
            password.setValues(p);
        }
        else
        {
            password.setValues(null);
        }
    }

	public ColBean<String> getDeptName() {
		return deptName;
	}

	public void setDeptName(ColBean<String> deptName) {
		this.deptName = deptName;
	}

	public ColBean<String> getApproveFlag() {
		return approveFlag;
	}

	public void setApproveFlag(ColBean<String> approveFlag) {
		this.approveFlag = approveFlag;
	}

	public ColBean<String> getRybm() {
		return rybm;
	}

	public void setRybm(ColBean<String> rybm) {
		this.rybm = rybm;
	}

	/*public ColBean<String> getYwm() {
		return ywm;
	}

	public void setYwm(ColBean<String> ywm) {
		this.ywm = ywm;
	}

	public ColBean<String> getMz() {
		return mz;
	}

	public void setMz(ColBean<String> mz) {
		this.mz = mz;
	}

	public ColBean<String> getByyx() {
		return byyx;
	}

	public void setByyx(ColBean<String> byyx) {
		this.byyx = byyx;
	}

	public ColBean<String> getZgxw() {
		return zgxw;
	}

	public void setZgxw(ColBean<String> zgxw) {
		this.zgxw = zgxw;
	}

	public ColBean<String> getZy() {
		return zy;
	}

	public void setZy(ColBean<String> zy) {
		this.zy = zy;
	}

	public ColBean<String> getSsjg() {
		return ssjg;
	}

	public void setSsjg(ColBean<String> ssjg) {
		this.ssjg = ssjg;
	}

	public ColBean<String> getCjgzsj() {
		return cjgzsj;
	}

	public void setCjgzsj(ColBean<String> cjgzsj) {
		this.cjgzsj = cjgzsj;
	}*/

	public ColBean<String> getJgbm() {
		return jgbm;
	}

	public void setJgbm(ColBean<String> jgbm) {
		this.jgbm = jgbm;
	}

	public ColBean<String> getNickname() {
		return nickname;
	}

	public void setNickname(ColBean<String> nickname) {
		this.nickname = nickname;
	}

	public ColBean<String> getUserType() {
		return userType;
	}

	public void setUserType(ColBean<String> userType) {
		this.userType = userType;
	}

	public ColBean<String> getVipPass() {
		return vipPass;
	}

	public void setVipPass(ColBean<String> vipPass) {
		this.vipPass = vipPass;
	}

	public ColBean<String> getCountry() {
		return country;
	}

	public void setCountry(ColBean<String> country) {
		this.country = country;
	}
}
