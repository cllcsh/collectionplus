package org.express.common.convert;

import org.apache.commons.beanutils.Converter;
import org.express.util.Convert;
import org.express.util.DateUtil;

public class DateConvert implements Converter{
	public Object convert(@SuppressWarnings("rawtypes") Class arg0, Object arg1) {
        try{  
            String a = Convert.toString(arg1).trim();
            return DateUtil.DateTimeParse(a);
        }  
        catch(Exception e){  
            return null;  
        }
    }  
}
