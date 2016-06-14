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
 
public class StockPrice  extends AbstractMessageTransformer{

	 
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
		BigDecimal stockPrice = null;
		
		Map payload = (Map) message.getPayload();
		List<Map> trades  = message.getProperty("trades", PropertyScope.INVOCATION);
		
		try {
			String stockSymbol = (String)payload.get("stockSymbol");
		 
			List<Map> myTrades = GlobalBeverageManager.getTrades(trades, stockSymbol);
			 
			BigDecimal num = new BigDecimal(0);
			int den = 0;
			
			for (Map map : myTrades) {
				int quantity  = (int)map.get("quantity");
				BigDecimal p = new BigDecimal((int)map.get("price"));
				
				num = num.add(p);
				den += quantity;
			}
			
			// Rounding and precision scale not specified
			stockPrice = num.divide(new BigDecimal(den));
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new TransformerException(this, e);
		}
		
		return stockPrice;
	}
	

}


