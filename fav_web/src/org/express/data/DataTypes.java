package org.express.data;

public class DataTypes 
{
	public enum DataType
	{
		/**
		 * 字符串(char,varchar,longvarchar)
		 */
		String,
		/**
		 * 整型(Integer， small Integer)
		 */
		Integer,
		/**
		 * 单精度(Float,Real)
		 */
		Float,
		/**
		 * 双精度
		 */
		Double,
		/**
		 * 长整型
		 */
		Long,
		/**
		 * 日期
		 */
		Date,
		/**
		 * 时间
		 */
		Time,
		/**
		 * 时间戳
		 */
		TimeStamp,
		/**
		 * 布尔（tinyint, bit）
		 */
		Boolean,
		/**
		 * 高精度数据，货币（decimal , numberic）
		 */
		Number,
		/**
		 * 字节数组（byte[]）
		 */
		Bytes,
		/**
		 * 无或者未知
		 */
		Null,
		/**
		 * BLOB
		 */
		Blob,
		/**
		 * CLOB
		 */
		Clob,
		/**
		 * 一切对象
		 */
		Object
	}
	
	/**
	 * 转化JDBC的数据类型为简单的数据保存类型
	 * @param param
	 * @return
	 */
	public static DataType getDataType(Integer param)
	{
		DataType dt;
		
		switch(param)
		{
			case -1:  //TEXT
			case 1:  //CHAR
			case 12://VARCHAR
			{
				dt = DataType.String;
				break;
			}
			case 4: //INTEGER
			{
				dt = DataType.Long;
				break;
			}
			case 5: //SMALLINT,MEDIUMINT
			case -6: //TINYINT
			{
				dt = DataType.Integer;
				break;
			}
			case 6://real
			case 7://float
			{
				dt = DataType.Float;
				break;
			}
			case 8://double
			{
				dt = DataType.Double;
				break;
			}
			case 91:
			{
				dt = DataType.Date;
				break;
			}
			case 92:
			{
				dt = DataType.Time;
				break;
			}
			case 93:
			{
				dt = DataType.TimeStamp;
				break;
			}
			case -7: //BIT
			{
				dt = DataType.Boolean;
				break;
			}
			case -5: //BIGINT
			case 2: //numberic
			case 3: //DECIMAL
			{
				dt = DataType.Number;
				break;
			}
			case -2:
			case -3:
			case -4:
			{
				dt = DataType.Bytes;
				break;
			}
			case 2004:
			{
				dt = DataType.Blob;
				break;
			}
			case 2005:
			{
				dt = DataType.Clob;
				break;
			}
			default:
			{
				dt = DataType.Null;
			}
		}
		
		return dt;
	}
}
