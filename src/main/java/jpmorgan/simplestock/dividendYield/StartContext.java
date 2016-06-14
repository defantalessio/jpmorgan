package jpmorgan.simplestock.dividendYield;

import java.util.HashMap;
import java.util.Map;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;
import org.mule.api.transport.PropertyScope;
import org.apache.log4j.MDC;
import jpmorgan.simplestock.MuleFlowContext;
 
public class StartContext implements Callable{

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		
		MuleMessage message = eventContext.getMessage();
		
		
		MuleFlowContext fc  = message.getProperty("muleFlowContext", PropertyScope.SESSION);
		if(fc == null){ fc = new MuleFlowContext(); fc.setUniqueKey(message.getUniqueId()); fc.setExceptionKeys(new HashMap<String,Object>()); fc.setMessageProperties(new HashMap<String,Object>());}
		
		fc.setFlowService("dividendYield");
		fc.getExceptionKeys().put("exceptionPayload", message.getPayload());
		
		message.setProperty("muleFlowContext", fc, PropertyScope.SESSION);
		
		
		MDC.put("flowService", fc.getFlowService());
		MDC.put("uniqueKey", fc.getUniqueKey());
		
		
		
		return message;
	}
	

}

