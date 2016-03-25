package org.express.common.expression.SQL;

import java.util.ArrayList;
import java.util.List;

import org.express.util.Convert;
import org.express.util.StringUtil;

public class SQLParser 
{
	private List<ExpressionElement> list = null;
	//0开始,-1结束,1连续查找中,2找到完毕,3空白
	private int char_status = 0;
	//0终止，1开始
	private int quote_found = 0;
	//0开始,-1结束,1字段,2操作符,3值
	private int sentence_status = 0;
	//层级数，由括号决定
	private int brace_value = 0; 
	//临时存储对象
	private String el = "";
	
    //4种错误类型
    public static final int SYNTAX_ERROR = 0;    					//语法错误
    public static final int UNBALPARENS_ERROR = 1;    		//括号没有结束错误
    public static final int NOEXP_ERROR = 2;    						//表达式为空错误
    public static final int UNKNOWN_LOGIC_MARK = 3;    	//未知的逻辑连接符
    public static final int NO_CLOSING_QUOTATION = 4;     //没有闭合的引号
    
    //针对4种错误类型定义的4个错误提示
    public static final String[] ERROR_MESSAGES = {"语法错误", "括号不匹配", "表达式为空", "未知的逻辑连接符", "没有闭合的引号"};
	
	public List<ExpressionElement> getExpressionElements(String sql) throws Exception
	{
		list = new ArrayList<ExpressionElement>();
		
		if(sql == null || StringUtil.Trim(sql).length() == 0)
		{
			handleError(NOEXP_ERROR , "" , "");
		}
		
		char[] total = StringUtil.Trim(sql).toCharArray();
		for(int i=0; i< total.length; i++)
		{
			char c = total[i];
			
			//发现单引号
			if(isQuote(c))
			{
				if(sentence_status == 0 || (sentence_status==1 && char_status==3))
				{
					handleError(SYNTAX_ERROR , this.print(list) , el);
				}
				
				if(quote_found==0)
				{
					if(el.length() > 0)
					{
						if(isOperator(el))
						{
							if(sentence_status != 1)
							{
								handleError(SYNTAX_ERROR , this.print(list) , el);
							}
							list.add(new Operator(el , brace_value));
							el = "";
							sentence_status = 2;
						}
					}
					
					quote_found = 1;
					char_status = 1;
				}
				else {
					quote_found = 0;
					char_status = 2;
					el += ""+c;
					list.add(new Value(el , brace_value));
					el="";
					continue;
				}
				sentence_status = 3;
			}
			
			if(quote_found==1)
			{
				el += ""+c;
				continue;
			}
			
			//发现为空格字符
			if(c == ' ')
			{		
				if(char_status == 1)
				{
					if(isOperator(el))
					{
						if(sentence_status != 1)
						{
							handleError(SYNTAX_ERROR , this.print(list) , el);
						}
						list.add(new Operator(el , brace_value));
						el = "";
						sentence_status = 2;
					}
					else if(isLogicOperator(el))
					{
						if(sentence_status != 3)
						{
							handleError(SYNTAX_ERROR , this.print(list) , el);
						}
						list.add(new LogicOperator(el , brace_value));
						el = "";
						sentence_status = 0;
					}
					else 
					{
						if(sentence_status == 0)
						{
							list.add(new Field(el , brace_value));
							sentence_status = 1;
							el = "";
						}
						else if(sentence_status == 2)
						{
							list.add(new Value(el , brace_value));
							sentence_status = 3;
							el = "";
						}
						else 
						{
							handleError(SYNTAX_ERROR , this.print(list) , el);
						}
					}
				}
					
				char_status = 3;
				continue;
			}
			
			//发现左括号
			if(c == '(')
			{
				if(char_status==1) 
				{
					if(isLogicOperator(el))
					{
						if(sentence_status != 3)
						{
							handleError(SYNTAX_ERROR , this.print(list) , el);
						}
						list.add(new LogicOperator(el , brace_value));
						el = "";
						sentence_status = 0;
					}
					else 
					{
						handleError(UNKNOWN_LOGIC_MARK , this.print(list) , el);
					}
				}
				
				if(sentence_status != 0 && sentence_status != 3)
				{
					handleError(SYNTAX_ERROR , this.print(list) , el);
				}
				brace_value++;
				list.add(new LeftBrace(brace_value));
				char_status = 2;
				continue;
			}
			
			//发现右括号
			if(c == ')')
			{				
				if(char_status == 1 && el.length() > 0 && sentence_status==2)
				{
					if(sentence_status != 2)
					{
						handleError(SYNTAX_ERROR , this.print(list) , el);
					}
					list.add(new Value(el , brace_value));
					sentence_status = 3;
					el = "";
				}
				
				if(sentence_status != 3)
				{
					handleError(SYNTAX_ERROR , this.print(list) , el);
				}
				list.add(new RightBrace(brace_value));
				brace_value--;
				if(brace_value < 0)
				{
					handleError(UNBALPARENS_ERROR , this.print(list) , el);
				}
				
				char_status = 2;
				continue;
			}
			
			//发现是操作符
			if(isOperator(Convert.toString(c)))
			{
				if(char_status == 1)
				{
					//连续状态下
					if(!StringUtil.isNullorEmpty(el))
					{
						//如果继续是操作符(>= , <= , <>)
						if(el.length() == 1 && isOperator(el))
						{
							if(sentence_status != 1)
							{
								handleError(SYNTAX_ERROR , this.print(list) , el);
							}
							el += "" + c;
							if(isOperator(el))
								list.add(new Operator(el , brace_value));
							else
								handleError(SYNTAX_ERROR , this.print(list) , el);
							
							char_status = 2;
							sentence_status = 2;
							el = "";
							continue;
						}
						else 
						{
							if(sentence_status != 0)
							{
								handleError(SYNTAX_ERROR , this.print(list) , el);
							}
							//表明field采集结束
							list.add(new Field(el , brace_value));
							sentence_status = 1;
							el = "";
						}
					}
				}
				
				el += "" + c;
				char_status = 1;
				continue;
			}
			
			//如果是普通字符
			if(char_status==1)
			{
				if(isOperator(el))
				{
					if(sentence_status != 1)
					{
						handleError(SYNTAX_ERROR , this.print(list) , el);
					}
					list.add(new Operator(el , brace_value));
					el = "";
					sentence_status = 2;
				}
			}
				
			el += ""+c;
			char_status = 1;
			
			if(i==total.length-1)
			{
				if(sentence_status != 2)
				{
					handleError(SYNTAX_ERROR , this.print(list) , el);
				}
				list.add(new Value(el , brace_value));
				sentence_status = 3;
				char_status = 2;
				el = "";
			}
		}
		
		if(sentence_status != 3 && char_status != 2)
			handleError(SYNTAX_ERROR , this.print(list) , el);
		
		if(brace_value!=0)
			handleError(UNBALPARENS_ERROR , this.print(list) , el);
		
		if(quote_found != 0)
			handleError(NO_CLOSING_QUOTATION , this.print(list) , el);
		
		return list;
	}
	
	private boolean isOperator(String c) 
	{
		if(c.equals("<") || c.equals(">") || c.equals("=") || c.equals("<=") || c.equals(">=") || c.equals("<>"))
		{
			return true;
		}
		return false;
	}
	
	private boolean isLogicOperator(String o) 
	{
		if(o.equals("and") || o.equals("or") || o.equals("not"))
		{
			return true;
		}
		
		return false;
	}
	
	private boolean isQuote(char o)
	{
		return o == '\'';
	}
	
	 /**
	  * 处理异常情况
	  */
	 private void handleError(int errorType , String msg , String tempStr) throws Exception {
	  //遇到异常情况时，根据错误类型，取得异常提示信息，将提示信息封装在异常中抛出
		 if(!msg.equals(""))
			 msg = " :: 发现于　" + msg + "　附近";
		 if(!tempStr.equals(""))
			 msg+=",错误字符 :: " + tempStr;
		 throw new Exception(ERROR_MESSAGES[errorType] + msg);
	 }
	
	public static void main(String[] args) {
		SQLParser s = new SQLParser();
		try {
		  List<ExpressionElement>  a =	s.getExpressionElements("a='hah'");
		  s.printall(a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public String print(List<ExpressionElement>  a)
	{
		StringBuilder sBuilder = new StringBuilder();
		for(ExpressionElement c : a)
		{
			sBuilder.append(c.print());
		}
		return sBuilder.toString();
	}
	
	private void printall(List<ExpressionElement>  a) 
	{
		for(ExpressionElement c : a)
		{ 
			System.out.print(c.print());
		}
		System.out.println("");
		for(ExpressionElement c : a)
		{ 
			System.out.print(c.getDeep());
		}
		System.out.println("");
		System.out.println("size :: " + a.size());
	}
}
