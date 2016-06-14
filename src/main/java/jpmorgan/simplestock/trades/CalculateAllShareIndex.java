package jpmorgan.simplestock.trades;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.api.transport.PropertyScope;
import org.mule.transformer.AbstractMessageTransformer;

import java.util.Map;

import jpmorgan.simplestock.MuleFlowContext;
import jpmorgan.simplestock.managers.GlobalBeverageManager;
import org.apache.commons.math3.stat.StatUtils;

public class CalculateAllShareIndex  extends AbstractMessageTransformer{

	 
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
		double index = 0;
		
		
		List<Map> trades  = (List<Map>)  message.getPayload();
		
		try {
						
			double[] arr  = new double[trades.size()];
			
			for(int i=0; i<=(trades.size()-1); i++){
				arr[i] = (double)((BigDecimal)trades.get(i).get("price")).doubleValue();
			}
			/*
			for (Map map : trades) {
				BigDecimal p = (BigDecimal)map.get("price");
				if(price.equals(BigDecimal.ZERO)) price = p;
				else price = price.multiply(p);
			}
			*/
			
			// Rounding and precision scale not specified
						
			index = StatUtils.geometricMean(arr);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new TransformerException(this, e);
		}
		
		return index;
	
	}
}


