package jpmorgan.simplestock.managers;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.context.MuleContextAware;
import org.mule.construct.Flow;

public class GlobalBeverageManager{


	
	public static Map getExchange(List<Map> globalB, String stockSymbol) throws Exception
	{
		Map exchange = null;
		
		try{
		 
			for (Map map : globalB) {
				if(((String)map.get("symbol")).equals(stockSymbol))exchange = map;
			}
						
		}
		catch(Exception e){
			throw new Exception(e);
		}
	
	return exchange;
	}
	
	public static List<Map> getTrades(List<Map> trades, String stockSymbol) throws Exception
	{
		List<Map> tradesToCalculate = new ArrayList<Map>();
		
		try{
		 
			for (Map map : trades) {
				
				String s = (String)map.get("stockSymbol");
				Date d = (Date)map.get("timestamp");
				
				Date now = new Date();
				Date checkDate = DateUtils.addMinutes(now, -15);
			
				
				if(s.equals(stockSymbol) && d.after(checkDate))
					tradesToCalculate.add(map);
			}
						
		}
		catch(Exception e){
			throw new Exception(e);
		}
	
	return tradesToCalculate;
	}
}