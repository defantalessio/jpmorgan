package jpmorgan.simplestock;

import java.io.Serializable;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class Utility implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -45519340380878008L;
	
	public static String convertXmlDocumentToString(Document doc)throws Exception{
		String output = "";
		
		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			output = writer.getBuffer().toString().replaceAll("\n|\r", "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Exception on Conversion from org.w3c.dom.Document to String", e);
		}
		
		return output;
	}
	
	public static String getStringValue(Document doc, String exp)throws Exception{
		String val = "";
		try {
		XPath xpath =  XPathFactory.newInstance().newXPath();
		Node node = (Node) xpath.evaluate(exp, doc.getDocumentElement(), XPathConstants.NODE);
		val = node.getTextContent();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Exception on Conversion", e);
		}
		return val;
	}
	
	public static int getIntValue(Document doc, String exp)throws Exception{
		int val = 0;
		try {
		String v = getStringValue(doc, exp);
				
		if(!v.trim().isEmpty()) val = Integer.parseInt(v);
		
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Exception on Conversion", e);
		}
		return val;
	}
	
	public static BigDecimal getBigDecimalValue(Document doc, String exp)throws Exception{
		BigDecimal val = BigDecimal.ZERO;
		try {
			String v = getStringValue(doc, exp);
			if(!v.trim().isEmpty()) val = new BigDecimal(v);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Exception on Conversion", e);
		}
		return val;
	}

	public static long getDatabasePrimaryKey(){
		//long id = new Date().getTime();
		
		Random random = new Random();
		long id = random.nextInt(999999999) + 1000000000;
		
		return id;
	}
	
	
	public static String convertDateToString(java.sql.Date data)throws Exception
	{
		String _data = "";
		try{
			if(data == null)return _data;
			SimpleDateFormat formatter5=new SimpleDateFormat("yyyy-MM-dd");
			_data = formatter5.format(data);
		}
		
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Exception on Conversion", e);
		}
		return _data;
	}
	
	public static String convertDateToString(java.util.Date data)throws Exception
	{
		String _data = "";
		try{
			if(data == null)return _data;
			SimpleDateFormat formatter5=new SimpleDateFormat("yyyy-MM-dd");
			_data = formatter5.format(data);
		}
		
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Exception on Conversion", e);
		}
		return _data;
	}
	
	public static String convertDateToString(java.sql.Timestamp data, String format)throws Exception
	{
		String _data = "";
		try{
			if(data == null)return _data;
			SimpleDateFormat formatter5=new SimpleDateFormat(format);
			_data = formatter5.format(data);
		}
		
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Exception on Conversion", e);
		}
		return _data;
	}
	
	public static String convertDateToString(java.sql.Date data, String format)throws Exception
	{
		String _data = "";
		try{
			if(data == null)return _data;
			SimpleDateFormat formatter5=new SimpleDateFormat(format);
			_data = formatter5.format(data);
		}
		
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Exception on Conversion", e);
		}
		return _data;
	}
	
	public static String convertDateToString(java.util.Date data, String format)throws Exception
	{
		String _data = "";
		try{
			if(data == null)return _data;
			SimpleDateFormat formatter5=new SimpleDateFormat(format);
			_data = formatter5.format(data);
		}
		
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Exception on Conversion", e);
		}
		return _data;
	}
	
	public static java.util.Date convertStringToDate(String data) throws Exception{
		Date date = null;
		try{
			if(data == null)return date;
			date = new SimpleDateFormat().parse(data);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Exception on Conversion", e);
		}
		return date;
	}
	
	public static java.util.Date convertStringToDate(String data, String format) throws Exception{
		Date date = null;
		try{
			date = convertStringToDate(data, format, "");
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Exception on Conversion", e);
		}
		return date;
	}
	
	public static java.util.Date convertStringToDate(String data, String format, String nullValue) throws Exception{
		Date date = null;
		try{
			if(data == null)return date;
			if(data.equals(nullValue)) return date;
			
			date = new SimpleDateFormat(format).parse(data);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Exception on Conversion", e);
		}
		return date;
	}
	
	
	
	
	public static java.sql.Date convertStringToSqlDate(String data, String format) throws Exception{
		java.sql.Date date = null;
		try{
			if(data == null)return date;
			Date d = new SimpleDateFormat(format).parse(data);
			date = new java.sql.Date(d.getTime());
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Exception on Conversion", e);
		}
		return date;
	}
	
	public static java.sql.Date getDate(java.util.Date data)
	{
		java.sql.Date ret = null; 
		
		if(data != null) ret = new java.sql.Date(data.getTime());
		
		return ret;
	}

	public static Timestamp getDateTime(java.util.Date data)
	{
		Timestamp ret = null; 
		
		if(data != null) ret = new java.sql.Timestamp(data.getTime());
		
	
		return ret;
	}
	
	public static boolean isNull(Object o)
	{
		boolean isNull = false;
		
		if(o == null)
			isNull = true;
		else if(o instanceof String){
			 String s = (String)o;
			 if(s.trim().isEmpty()) isNull = true;
		}
		return isNull;
	}
	
	public static boolean isNullOrDefault(Object o)
	{
		boolean isNull = false;
		
		if(o == null)
			isNull = true;
		else if(o instanceof String){
			 String s = (String)o;
			 if(s.trim().isEmpty()) isNull = true;
		}
		else if(o instanceof String & o.equals("0"))
			isNull = true;
		return isNull;
	}
	
	public static int toInt(Object o, int defaultValue) {
		int ret = 0;
		if (o == null) ret = defaultValue;
		if (o instanceof String){
			String s = (String)o;
			if (s.trim().isEmpty()) ret = defaultValue;
			else ret = Integer.parseInt(((String) o).trim());
		}
		if (o instanceof BigDecimal) ret = ((BigDecimal) o).intValue();
		if (o instanceof Integer) ret = ((Integer) o).intValue();
		
		return ret;
	}
	
	public static boolean isNumeric(String s) {  
        return s.matches("\\d+");  
    }  

	public static Date toDate(Object o, Date defaultValue) {
		if (o == null)
			return defaultValue;
		if (o instanceof Timestamp)
			return new Date(((Timestamp) o).getTime());
		return (Date) o;
	}

	
	public static String toString(Object o, String defaultValue) {
		String ret = toString(o, defaultValue, false);
		return ret;
	}
	
	public static String toString(Object o, String defaultValue, boolean fixedSize) {
		String ret = defaultValue;
		if (o == null)
			ret = defaultValue;
		if (o instanceof String){
			String value = (String)o;
			if (value.trim().isEmpty()) ret = defaultValue;
			else {
				ret = ((String) o);
				if(!fixedSize) ret = ret.trim();
			}
		}
		if (o instanceof BigDecimal)
			ret = ((BigDecimal) o).toString();
		if (o instanceof Integer)
			ret = ((Integer) o).toString();
		if (o instanceof Double)
			ret = ((Double) o).toString();
		if (o instanceof Long)
			ret = ((Long) o).toString();
		
		return ret;
	}

	public static long toLong(Object o, long defaultValue) {
		long l = defaultValue;
	
		if (o instanceof Long) l = (long)o;
		
		if (o == null)
			l = defaultValue;
		if (o instanceof String){
			String s = (String)o; 
			if (!(s.trim().isEmpty())) l = Long.parseLong(s.trim());
		}
		if (o instanceof BigDecimal)
			l = ((BigDecimal) o).longValue();
		if (o instanceof Integer)
			l = ((Integer) o).longValue();
		if (o instanceof Double)
			l = ((Double) o).longValue();
		
		return l;
	}

	public static boolean toBoolean(Object o, boolean defaultValue) {
		if (o == null)
			return defaultValue;
		if (o instanceof String)
			return Boolean.parseBoolean(((String) o).trim());
		if (o instanceof BigDecimal) {
			int i = ((BigDecimal) o).intValue();
			return (i == 0) ? false : true;
		}
		return ((Boolean) o).booleanValue();
	}

	public static BigDecimal toBigDecimal(Object o, BigDecimal defaultValue) {
		BigDecimal ret = defaultValue;
		if (o == null)
			ret = defaultValue;
		if (o instanceof BigDecimal)
			ret = (BigDecimal) o;
		if (o instanceof String){
			String v = ((String)o).trim();
			if(v.trim().isEmpty())return defaultValue;
			ret = new BigDecimal(v);
		}
		if (o instanceof Double){
			ret = BigDecimal.valueOf((Double)o);
		}
			
		return ret;
	}
	
	public static BigDecimal toBigDecimal(Object o, BigDecimal defaultValue, int dec_len) {
		BigDecimal ret = defaultValue;
		if (o == null) return ret;
		if (o instanceof String){
			String value = (String)o;
			if(!value.trim().isEmpty()){ 
				if (dec_len > 0){
					int start_dec = value.length() - dec_len;
					value = value.substring(0, start_dec) + "." + value.substring(start_dec, value.length());
				}
				ret = new BigDecimal(value);
			}
		}
		if (o instanceof Double){
			ret = new BigDecimal(toDouble((Double)o, dec_len));
		}
		return ret;
	}
	
	public static double toDouble(Object o, int dec_len)
	{
		double ret = 0;
		if (o == null) return ret;
		if (o instanceof String){
			String value = (String)o;
			if (dec_len > 0){
				int start_dec = value.length() - dec_len;
				value = value.substring(0, start_dec) + "." + value.substring(start_dec, value.length());
			}
			if(!value.trim().isEmpty()) ret = Double.parseDouble(value);
		}
		if (o instanceof Double){
			ret = (Double)o;
			
		}
		return ret;
	}

	public static String getDecrased(String str, int decrase){
		return getDecrased(str, "", decrase);
	}
	
	public static String getDecrased(String str, String def, int decrase){
		String r = toString(str, def);
		int size = str.length(); 
		if (size <= 1) return str;
		if (decrase >= size) return str;
		
		r = str.substring(0, size - decrase);
		
		return r;
	}
	
	public static String getSubstring(String str, String def, int start, int end){
		String r = toString(str, def);
		int size = r.length(); 
		if(start >= end) return r;
		if(start >= size) return r;
		if(end >= size) return r;
		
		r = r.substring(start, end);
		
		return r;
	}
	
	public static String getSubstring(String str, String def, int start){
		String r = toString(str, def);
		int size = str.length(); 
		if(start >= size) return r;
		
		r = r.substring(start);
		
		return r;
	}
}
