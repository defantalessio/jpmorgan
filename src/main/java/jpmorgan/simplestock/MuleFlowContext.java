package jpmorgan.simplestock;



import java.io.Serializable;
import java.util.Map;

public class MuleFlowContext implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8918963402140754370L;

	private String flowService; 
	private String uniqueKey;
	private Map<String, Object> exceptionKeys;
	private Map<String, Object> messageProperties;
	
	
	public MuleFlowContext (){}


	public String getFlowService() {
		return flowService;
	}


	public void setFlowService(String flowService) {
		this.flowService = flowService;
	}


	public String getUniqueKey() {
		return uniqueKey;
	}


	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}


	public Map<String, Object> getExceptionKeys() {
		return exceptionKeys;
	}


	public void setExceptionKeys(Map<String, Object> exceptionKeys) {
		this.exceptionKeys = exceptionKeys;
	}


	public Map<String, Object> getMessageProperties() {
		return messageProperties;
	}


	public void setMessageProperties(Map<String, Object> messageProperties) {
		this.messageProperties = messageProperties;
	}


	
	
	@Override
	public String toString() {
		return "MuleFlowContext [flowService=" + flowService + ", uniqueKey="
				+ uniqueKey + ", exceptionKeys=" + exceptionKeys
				+ ", messageProperties=" + messageProperties + "]";
	}

	
	
	
	

}
