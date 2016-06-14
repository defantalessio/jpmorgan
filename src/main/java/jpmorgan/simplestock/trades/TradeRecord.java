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
 
public class TradeRecord  extends AbstractMessageTransformer{

	 
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
		Map dataToRecord = new HashMap();
		
		Map payload = (Map) message.getPayload();
		
		try {
			String stockSymbol = (String)payload.get("stockSymbol");
			int quantity = (int)payload.get("quantity");
			String type = (String)payload.get("type");
			BigDecimal price = (BigDecimal)payload.get("price");
			 
			
			// validation data
			
			if(stockSymbol.isEmpty())throw new Exception("StockSymbol is Empty");
			if(!(quantity > 0))throw new Exception("Quantity not valid");
			if(!type.equals("B") && !type.equals("S"))throw new Exception("Type not valid: only S or B available");
			if(price.compareTo(new BigDecimal(0))< 0) throw new Exception("price not valid");
			
			dataToRecord.put("stockSymbol", stockSymbol);
			dataToRecord.put("quantity", quantity);
			dataToRecord.put("type", type);
			dataToRecord.put("price", price);
			dataToRecord.put("timestamp", new Date());
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new TransformerException(this, e);
		}
		
		return dataToRecord;
	}
	

}


