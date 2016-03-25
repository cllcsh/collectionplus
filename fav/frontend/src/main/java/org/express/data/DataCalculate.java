package org.express.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.express.common.expression.SQL.ExpressionElement;
import org.express.common.expression.SQL.ExpressionGroup;
import org.express.common.expression.SQL.Field;
import org.express.common.expression.SQL.LogicOperator;
import org.express.common.expression.SQL.Operator;
import org.express.common.expression.SQL.SQLParser;
import org.express.common.expression.SQL.Value;
import org.express.data.DataTypes.DataType;
import org.express.util.Convert;
import org.express.util.DateUtil;

/**
 * 用于DataTable筛选逻辑运算的计算类
 *
 * @author Rei Ayanami
 */
public class DataCalculate {
    private DataTable dt = null;

    public DataCalculate(DataTable dt) {
        this.dt = dt;
    }

    public DataTable getDataTable() {
        return dt;
    }

    public void setDataTable(DataTable dt) {
        this.dt = dt;
    }

    /**
     * 找到符合记录的列
     *
     * @param condition (语法遵循基本的布尔逻辑检索,支持括号嵌套，与或非关系运算符和常用的运算符)
     * @return 符合逻辑的行
     * @throws Exception
     */
    public List<DataRow> findWithExpression(String condition) throws Exception {
        //Date startTime = new Date();
        List<DataRow> frows = null;
        SQLParser parser = new SQLParser();

        try {
            List<ExpressionElement> l_parser = parser.getExpressionElements(condition);
            if (l_parser != null && l_parser.size() > 0) {
                List<DataRow> temp_fRows = null;

                int maxDeep = 0;
                int minDeep = 1;
                for (ExpressionElement e : l_parser) {
                    if (e.getDeep() > maxDeep)
                        maxDeep = e.getDeep();

                    if (e.getDeep() < minDeep)
                        minDeep = e.getDeep();
                }
                if (minDeep > maxDeep) minDeep = maxDeep;

                Map<Integer, List<List<DataRow>>> total = new LinkedHashMap<Integer, List<List<DataRow>>>();
                for (int i = maxDeep; i >= minDeep; i--) {
                    List<List<DataRow>> group = new ArrayList<List<DataRow>>();
                    List<ExpressionElement> t_e = new ArrayList<ExpressionElement>();
                    Integer Assm = 0;
                    boolean isCreate = false;
                    for (ExpressionElement e : l_parser) {
                        if (!isCreate && e.getDeep() == i + 1) {
                            isCreate = true;
                            //已经创建好，并且计算过的数据列组
                            ExpressionGroup eg = new ExpressionGroup(total.get(i + 1).get(Assm), e.getDeep());
                            t_e.add(eg);
                            Assm++;
                            continue;
                        }

                        if (e.getDeep() >= i + 1)
                            continue;

                        if (e.getDeep() == i) {
                            isCreate = false;
                            t_e.add(e);
                        } else {
                            isCreate = false;
                            if (t_e.size() > 0) {
                                temp_fRows = calc(null, t_e);
                                t_e = new ArrayList<ExpressionElement>();
                                group.add(temp_fRows);
                            }
                        }
                    }

                    if (t_e.size() > 0) {
                        temp_fRows = calc(null, t_e);
                        group.add(temp_fRows);
                    }

                    total.put(i, group);
                }
                frows = total.get(minDeep).get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        //System.out.println("查找耗时：" + ((new Date()).getTime() - startTime.getTime()) + "毫秒");
        return frows;
    }

    /**
     * 计算表达式子句
     *
     * @param dr
     * @param e_list
     * @return
     * @throws Exception
     */
    private List<DataRow> calc(List<DataRow> dr, List<ExpressionElement> e_list) throws Exception {
        List<DataRow> frows = null;
        List<DataRow> temp_fRows = null;
        ExpressionGroup temp_eg = null;
        if (dr == null)
            dr = this.dt.getRows();

        Field f = null;
        Operator o = null;
        Value v = null;
        String logic = null;
        ExpressionGroup eg = null;

        for (ExpressionElement e : e_list) {
            if (e.isLeftBrace() || e.isRightBrace())
                continue;

            if (e.isField()) {
                f = (Field) e;
                continue;
            }

            if (e.isOperator()) {
                o = (Operator) e;
                continue;
            }

            if (e.isValue()) {
                v = (Value) e;

                if (logic != null)
                    temp_fRows = calcElement(dr, f, o, v);
                else
                    frows = calcElement(dr, f, o, v);

                if (logic != null && frows != null && temp_fRows != null) {
                    //表达式与表达式逻辑合并
                    frows = logicElement(logic, frows, temp_fRows);
                    logic = null;
                    temp_fRows = null;
                }

                if (logic != null && temp_fRows != null && eg != null) {
                    //表达式与运算组逻辑合并
                    frows = logicElement(logic, temp_fRows, eg.getGroupRows());
                    logic = null;
                    eg = null;
                    temp_fRows = null;
                }
            }

            if (e.isExpressionGroup()) {
                if (eg != null)
                    temp_eg = (ExpressionGroup) e;
                else
                    eg = (ExpressionGroup) e;

                if (logic != null && frows != null && eg != null) {
                    //表达式与运算组逻辑合并
                    frows = logicElement(logic, frows, eg.getGroupRows());
                    eg = null;
                    logic = null;
                }

                if (logic != null && eg != null && temp_eg != null) {
                    //运算组与运算组逻辑合并
                    frows = logicElement(logic, eg.getGroupRows(), temp_eg.getGroupRows());
                    eg = null;
                    temp_eg = null;
                    logic = null;
                }
            }

            if (e.isLogicOperator()) {
                logic = ((LogicOperator) e).getLogicoperator();
            }
        }

        if (frows == null && eg != null)
            frows = eg.getGroupRows();
        return frows == null ? dr : frows;
    }

    /**
     * 计算基本运算逻辑
     *
     * @param drs
     * @param f
     * @param o
     * @param v
     * @return
     * @throws Exception
     */
    private List<DataRow> calcElement(List<DataRow> drs, Field f, Operator o, Value v) throws Exception {
        List<DataRow> frows = new ArrayList<DataRow>();

        if (!this.dt.getColumns().contains(f.getField()))
            throw new Exception("数据表不包含" + f.getField() + "字段");

        for (DataRow dr : drs) {
            if (equalsObject(this.dt.getColumns().get(f.getField()), dr.getValue(f.getField()), o.getOperator(), v.getValue()))
                frows.add(dr);
        }
        return frows;
    }

    /**
     * 根据列的不同类型比较不同的值
     *
     * @param dc
     * @param a
     * @param oper
     * @param b
     * @return
     * @throws Exception
     */
    private boolean equalsObject(DataColumn dc, Object a, String oper, String b) throws Exception {
        if (a == null && b.length() == 0)
            return true;
        else if (a == null)
            return false;

        switch (dc.getDataType()) {
            case Integer: {
                //整型
                if (oper.equals("="))
                    return Convert.toInt(a).equals(Convert.toInt(b));
                else if (oper.equals("<"))
                    return Convert.toInt(a) < Convert.toInt(b);
                else if (oper.equals(">"))
                    return Convert.toInt(a) > Convert.toInt(b);
                else if (oper.equals("<="))
                    return Convert.toInt(a) <= Convert.toInt(b);
                else if (oper.equals(">="))
                    return Convert.toInt(a) >= Convert.toInt(b);
                else if (oper.equals("<>"))
                    return !Convert.toInt(a).equals(Convert.toInt(b));
            }
            case Long: {
                //长整型
                if (oper.equals("="))
                    return Convert.toLong(a).equals(Convert.toLong(b));
                else if (oper.equals("<"))
                    return Convert.toLong(a) < Convert.toLong(b);
                else if (oper.equals(">"))
                    return Convert.toLong(a) > Convert.toLong(b);
                else if (oper.equals("<="))
                    return Convert.toLong(a) <= Convert.toLong(b);
                else if (oper.equals(">="))
                    return Convert.toLong(a) >= Convert.toLong(b);
                else if (oper.equals("<>"))
                    return !Convert.toLong(a).equals(Convert.toLong(b));
            }
            case Float: {
                //单精度
                if (oper.equals("="))
                    return Convert.toFloat(a).equals(Convert.toFloat(b));
                else if (oper.equals("<"))
                    return Convert.toFloat(a) < Convert.toFloat(b);
                else if (oper.equals(">"))
                    return Convert.toFloat(a) > Convert.toFloat(b);
                else if (oper.equals("<="))
                    return Convert.toFloat(a) <= Convert.toFloat(b);
                else if (oper.equals(">="))
                    return Convert.toFloat(a) >= Convert.toFloat(b);
                else if (oper.equals("<>"))
                    return !Convert.toFloat(a).equals(Convert.toFloat(b));
            }
            case Double: {
                //双精度
                if (oper.equals("="))
                    return Convert.toDouble(a).equals(Convert.toDouble(b));
                else if (oper.equals("<"))
                    return Convert.toDouble(a) < Convert.toDouble(b);
                else if (oper.equals(">"))
                    return Convert.toDouble(a) > Convert.toDouble(b);
                else if (oper.equals("<="))
                    return Convert.toDouble(a) <= Convert.toDouble(b);
                else if (oper.equals(">="))
                    return Convert.toDouble(a) >= Convert.toDouble(b);
                else if (oper.equals("<>"))
                    return !Convert.toDouble(a).equals(Convert.toDouble(b));
            }
            case Number: {
                //货币
                if (oper.equals("="))
                    return Convert.toDecimal(a).compareTo(Convert.toDecimal(b)) == 0;
                else if (oper.equals("<"))
                    return Convert.toDecimal(a).compareTo(Convert.toDecimal(b)) < 0;
                else if (oper.equals(">"))
                    return Convert.toDecimal(a).compareTo(Convert.toDecimal(b)) > 0;
                else if (oper.equals("<="))
                    return Convert.toDecimal(a).compareTo(Convert.toDecimal(b)) <= 0;
                else if (oper.equals(">="))
                    return Convert.toDecimal(a).compareTo(Convert.toDecimal(b)) >= 0;
                else if (oper.equals("<>"))
                    return Convert.toDecimal(a).compareTo(Convert.toDecimal(b)) != 0;
            }
            case Time:
            case TimeStamp:
            case Date: {
                //日期，时间，时间戳
                Date a1 = null;
                Date b2 = null;

                if (dc.getDataType() == DataType.Date) {
                    a1 = DateUtil.parseObject(a);
                    a1 = DateUtil.Create(DateUtil.getYear(a1), DateUtil.getMonth(a1), DateUtil.getDay(a1));
                    b2 = DateUtil.DateParse(b);
                } else if (dc.getDataType() == DataType.Time) {
                    a1 = new Date(DateUtil.parseObject(a).getTime() / 1000 * 1000);
                    b2 = DateUtil.DateTimeParse(b);
                } else {
                    a1 = DateUtil.parseObject(a);
                    b2 = DateUtil.TimeStampParse(b);
                }

                //时间型
                if (oper.equals("="))
                    return a1.getTime() == b2.getTime();
                else if (oper.equals("<"))
                    return a1.getTime() < b2.getTime();
                else if (oper.equals(">"))
                    return a1.getTime() > b2.getTime();
                else if (oper.equals("<="))
                    return a1.getTime() <= b2.getTime();
                else if (oper.equals(">="))
                    return a1.getTime() >= b2.getTime();
                else if (oper.equals("<>"))
                    return a1.getTime() != b2.getTime();
            }
            case Boolean: {
                //布尔型
                if (oper.equals("="))
                    return Convert.toBoolean(a).equals(Convert.toBoolean(b));
                else if (oper.equals("<>"))
                    return !Convert.toBoolean(a).equals(Convert.toBoolean(b));
                else
                    throw new Exception(dc.getColumnName() + "字段不支持" + oper + "运算符");
            }
            case String: {
                //字符型
                if (oper.equals("="))
                    return Convert.toString(a).equals(Convert.toString(b));
                else if (oper.equals("<>"))
                    return !Convert.toString(a).equals(Convert.toString(b));
                else
                    throw new Exception(dc.getColumnName() + "字段不支持" + oper + "运算符");
            }
            default:
                throw new Exception(dc.getColumnName() + "字段不支持运算");
        }
    }

    /**
     * 使用逻辑运算两个相邻的数据对象
     *
     * @param logic
     * @param a
     * @param b
     * @return
     */
    private List<DataRow> logicElement(String logic, List<DataRow> a, List<DataRow> b) {
        List<DataRow> frows = new ArrayList<DataRow>();

        if (logic.equals("and")) {
            //交集（与）
            Set<DataRow> ah = new HashSet<DataRow>();
            ah.addAll(a);
            Set<DataRow> bh = new HashSet<DataRow>();
            bh.addAll(b);

            ah.retainAll(bh);
            frows.addAll(ah);
        } else if (logic.equals("or")) {
            //并集（或）
            Set<DataRow> ah = new HashSet<DataRow>();
            ah.addAll(a);
            ah.addAll(b);
            frows.addAll(ah);
        } else if (logic.equals("not")) {
            //差集（非）
            Set<DataRow> ah = new HashSet<DataRow>();
            ah.addAll(a);
            for (DataRow dr_b : b) {
                if (ah.contains(dr_b))
                    ah.remove(dr_b);
            }
            frows.addAll(ah);
        }
        return frows;
    }

    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();
        DataTable dt = new DataTable();
        dt.addColumn("a", DataType.Integer);
        dt.addColumn("b", DataType.Integer);
        dt.addColumn("c", DataType.Integer);
        dt.addColumn("d", DataType.Integer);
        dt.addColumn("e", DataType.Integer);

        DataRow dr = null;
        for (int i = 0; i <= 1000000; i++) {
            dr = dt.newRow();
            dr.setValue("a", Convert.toDouble(i));
            dr.setValue("b", Convert.toDouble(i * 2));
            dr.setValue("c", i * 3);
            dr.setValue("d", i * 4);
            dr.setValue("e", i * 5);
            dt.addRow(dr);
        }

        long a = System.currentTimeMillis() - startTime;
        System.out.println("创建耗时：" + a + "毫秒");
        List<DataRow> drList = null;
        try {
            drList = dt.findWithExpression(" a=100000");
            dt.sort("a desc,b desc");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        if (drList != null) {
            System.out.println(drList);
        }
        System.out.println("查找耗时：" + (System.currentTimeMillis() - startTime - a) + "毫秒");
    }
}
