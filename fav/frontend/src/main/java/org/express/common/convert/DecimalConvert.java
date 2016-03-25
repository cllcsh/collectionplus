package org.express.common.convert;

import org.apache.commons.beanutils.Converter;
import org.express.util.Convert;

public class DecimalConvert  implements Converter{
	public Object convert(@SuppressWarnings("rawtypes") Class arg0, Object arg1) {
        try{  
            return Convert.toDecimal(arg1);
        }  
        catch(Exception e){  
            return null;  
        }
    }  
}
