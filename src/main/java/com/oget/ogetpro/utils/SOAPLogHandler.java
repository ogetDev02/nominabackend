package com.oget.ogetpro.utils;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oget.ogetpro.utils.Helper;

public class SOAPLogHandler extends BasicHandler 
{
	private static final long serialVersionUID = -8696438806629817763L;
	private static final Logger logger = LoggerFactory.getLogger(SOAPLogHandler.class);

	@Override
	public void invoke(MessageContext msgContext) throws AxisFault 
	{
		if (msgContext.getResponseMessage() != null && msgContext.getResponseMessage().getSOAPPart() != null) 
		{
			Helper.writeLog(logger, "INFO", "Axis Response: "+msgContext.getResponseMessage().getSOAPPartAsString());
	    } 
		else {
	        if (msgContext.getRequestMessage() != null && msgContext.getRequestMessage().getSOAPPartAsString() != null) 
	        	Helper.writeLog(logger, "INFO", "Axis Request: "+msgContext.getRequestMessage().getSOAPPartAsString());
	    }

	}

}
