package jpmorgan.simplestock.peratio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.api.transport.PropertyScope;
import org.mule.transformer.AbstractMessageTransformer;

import java.util.Map;

import jpmorgan.simplestock.MuleFlowContext;
import jpmorgan.simplestock.managers.GlobalBeverageManager;
 
public class PeRatioCalculation  extends AbstractMessageTransformer{

	 
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
		BigDecimal dividend = null;
		
		Map payload = (Map) message.getPayload();
		
		List<Map> globalBeverage  = message.getProperty("globalBeverage", PropertyScope.INVOCATION);
		
		try {
			BigDecimal tickerPrice = new BigDecimal((String)payload.get("tickerPrice"));
			String symbol = (String)payload.get("stockSymbol");
		
		
			Map exc = GlobalBeverageManager.getExchange(globalBeverage, symbol);
			
			BigDecimal div =  new BigDecimal((int)exc.get("lastDividend"));
			
			
			// Rounding and precision scale not specified
			dividend = tickerPrice.divide(div);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new TransformerException(this, e);
		}
		
		return dividend;
	}
	

}

