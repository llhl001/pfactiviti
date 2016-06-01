package com.sdyy.common.pushlet;


import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.Session;
import nl.justobjects.pushlet.util.PushletException;

public class PushletSessionManager extends nl.justobjects.pushlet.core.SessionManager {

	/**
	 * 
	 * @Title: createSession
	 * @author：liuyx 
	 * @date：2016年1月12日下午10:09:57
	 * @Description: 用userId替代随机生成的sessionId
	 * @param event
	 * @return
	 * @throws PushletException
	 */
	@Override
	public Session createSession(Event event) throws PushletException {  
        // Trivial  
        //return Session.create(createSessionId());  
        //return Session.create(createSessionId(), anEvent);  
        return Session.create(event.getField("userId", "visitor"));   
    }
}
