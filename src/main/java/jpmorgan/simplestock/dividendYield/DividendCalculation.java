package jpmorgan.simplestock.dividendYield;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.api.transport.PropertyScope;
import org.mule.transformer.AbstractMessageTransformer;

import java.util.Map;

import jpmorgan.simplestock.MuleFlowContext;
import jpmorgan.simplestock.Utility;
import jpmorgan.simplestock.managers.GlobalBeverageManager;
 
public class DividendCalculation  extends AbstractMessageTransformer{

	 
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
		BigDecimal dividend = null;
		
		Map payload = (Map) message.getPayload();
		
		List<Map> globalBeverage  = message.getProperty("globalBeverage", PropertyScope.INVOCATION);
		
		try {
			BigDecimal tickerPrice = Utility.toBigDecimal((String)payload.get("tickerPrice"), BigDecimal.ZERO);
			String symbol = (String)payload.get("stockSymbol");
		
		
			Map exc = GlobalBeverageManager.getExchange(globalBeverage, symbol);
			
			BigDecimal div =  new BigDecimal((int)exc.get("lastDividend"));
			String type = (String)exc.get("type");
			BigDecimal fixedDividend =   new BigDecimal((int)exc.get("fixedDividend"));
			BigDecimal parValue =  new BigDecimal((int)exc.get("parValue"));
			
			// Rounding and precision scale not specified
			
			if(type.equals("Common")){
				dividend = div.divide(tickerPrice);
			}
			if(type.equals("Preferred")){
				BigDecimal num = fixedDividend.multiply(parValue).divide(new BigDecimal(100));
				dividend = num.divide(tickerPrice);
			}
			
			if(dividend == null) throw new Exception("Calculation not available.");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new TransformerException(this, e);
		}
		
		return dividend;
	}
	

}

