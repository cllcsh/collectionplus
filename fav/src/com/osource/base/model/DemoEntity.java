package com.osource.base.model;

/**
 * @author : Feng Jingzhun
 * @version : 1.0
 * @date : 2009-11-6 11:15:34
 */
public class DemoEntity
{

    public static void main(String[] args)
    {
        int rowNo = 55;
        int pageSize =100;
        int t = (rowNo-1) / pageSize+1;
        System.out.println(t);
//
//        BaseDao basedao = new BaseDao();
//        UserEntity u = new UserEntity();
//        u.addCriteria().andCondition(ColBean.NOT_NULL, "id");
//        List<UserEntity> l = basedao.queryForList("demo_select", u,0,2);
//        System.out.println("size = " + l.size());
//        for (UserEntity a : l)
//        {
//            System.out.println("~~ id = " + a.getId());
//            System.out.println("~~ insertDate = " + a.getInsertDate());
//        }
    }

    /*
public static void main(String[] args)
{
  UserEntity e;
  BaseDao basedao = new BaseDao();

  //insert
  System.out.println("insert------");
  e = (UserEntity) setUserSession(new UserEntity());
  e.setId("9999");
  e.setLoginName("test_sql");
  e.setPassword("b57863378e54d8c713f6473d59a752ee");
  e.setName("测试脚本");
  e.setBirthday("1988-08-08");
  e.setDuty("1");
  e.setStaffType("1");
  try
  {
      basedao.insert("solo_insert", e);
  }
  catch (IctException e1)
  {
      e1.printStackTrace();
  }

  //query
  //<input name="xxxForm.userEntity.id"/>
  //<input name="xxxForm.userEntity.id.opType"/>
  System.out.println("query------");
  e = (UserEntity) setUserSession(new UserEntity());
  e.getId().setOpType(ColBean.EQUAL);
  e.setId("9999");
  e.getBirthday().setOpType(ColBean.BETWEEN);
  e.getBirthday().setValues("1977-07-07", "1999-09-09");
  e.getName().setOpType(ColBean.NOT_IN);
  e.getName().setValues("测", "试", "脚", "本");
  e.addCriteria(e.getColList());//service
  List<Integer> l1 = basedao.queryForList("demo_select_adv", e);
  System.out.println("select 1");
  for (Integer i : l1)
  {
      System.out.println(i);
  }

  e = (UserEntity) setUserSession(new UserEntity());
  e.addCriteria()
          .andCondition(ColBean.NULL, "former_password")
          .andCondition(ColBean.NOT_NULL, "id")
          .andCondition(ColBean.EQUAL, "id", 9999)
          .andCondition(ColBean.NOT_EQUAL, "login_name", "aaa")
          .andCondition(ColBean.GREATER, "id", 1000)
          .andCondition(ColBean.GREATER_EQUAL, "id", 1000)
          .andCondition(ColBean.LESS, "id", 10000)
          .andCondition(ColBean.LESS_EQUAL, "id", 10000)
          .andCondition(ColBean.IN, "name", "测试脚本", "", "", "")
          .andCondition(ColBean.NOT_IN, "birthday", new Date())
          .andCondition(ColBean.BETWEEN, "id", 1, 1)
          .andCondition(ColBean.NOT_BETWEEN, "id", 9999, 9999)
          .andCondition(ColBean.LIKE, "name", "试脚")
          .andCondition(ColBean.F_LIKE, "name", "脚本")
          .andCondition(ColBean.B_LIKE, "name", "测试")
          ;
  e.getUserExt().setAgeStart(1);
  List<Integer> l2 = basedao.queryForList("demo_select_adv", e);
  System.out.println("select 2");
  for (Integer i : l2)
  {
      System.out.println(i);
  }

  //update
  System.out.println("update------");
  e = (UserEntity) setUserSession(new UserEntity());
  e.setEMail("A@B.com");
  e.addCriteria()
          .andCondition(ColBean.EQUAL, "id", 9999)
          ;
  try
  {
      basedao.update("demo_update", e);
  }
  catch (IctException ee)
  {
      ee.printStackTrace();
  }

  //delete
  System.out.println("delete------");
  e = (UserEntity) setUserSession(new UserEntity());
  e.addCriteria()
          .andCondition(ColBean.EQUAL, "id", 9999)
          ;
  try
  {
      basedao.delete("demo_delete", e);
  }
  catch (IctException ee)
  {
      ee.printStackTrace();
  }


}

private static BaseEntity setUserSession(BaseEntity e)
{
  UserSession us = new UserSession();
  us.setDeptId(1);
  us.setUserId(1);
  us.setDeptNode("000");
  e.setUserSession(us);
  return e;
}      */
}
