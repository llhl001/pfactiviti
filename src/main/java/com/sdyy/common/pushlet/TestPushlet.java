package com.sdyy.common.pushlet;

import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.EventPullSource;
public class TestPushlet {     
    static public class InitPlushlet extends EventPullSource {  
    	public static int sessionCount=0;
        // 休眠五秒  
        @Override  
        protected long getSleepTime() {  
            return 20000;  
        }  
        @Override  
        protected Event pullEvent() {  
        	Event event = Event.createDataEvent("/test/pushMessage");  
        	
        	sessionCount=PushletSessionManager.getInstance().getSessionCount();
       	
            event.setField("sessionCount", sessionCount);  
            return event;  
        }  
    } 
    
  //推送的消息,Dispatcher.getInstance().multicast(event);
} 