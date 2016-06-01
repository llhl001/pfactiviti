package com.sdyy.common.utils;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.builder.xml.XMLMapperEntityResolver;
import org.apache.ibatis.exceptions.ExceptionFactory;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * 用于重新加载Mapper的工具
 */
public class MybatisMapperLoadUtil implements InitializingBean, ApplicationContextAware {
	// 日志记录器
		private final Logger logger = Logger.getLogger(this.getClass());
    private final HashMap<String, String> mappers = new HashMap<String, String>();
    private volatile ConfigurableApplicationContext context = null;
    private volatile Scanner scanner = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = (ConfigurableApplicationContext) applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    	setScanner(new Scanner());
        /*try {
            
            new Timer(true).schedule(new TimerTask() {
                public void run() {
                    try {
                    	if("off".equals(ConfigService.getConfigRealTime(ConfigService.DYNAMIC_LOAD_MYBATIS_XML_SWITCH))) {
                    		
                    	}else {
                            if (scanner.isChanged()) {
                                System.out.println("load mapper.xml");
                                scanner.reloadXML();
                            }
                    	}

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 10 * 1000, 5 * 1000);
        } catch (Exception e1) {
            e1.printStackTrace();
        }*/
    }

    public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	@SuppressWarnings("unchecked")
	public
    class Scanner {
    	//此处应调整为可配置
        private static final String XML_RESOURCE_PATTERN = "com/sdyy/**/model/*.xml";//ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "**/*Mapper.xml";
        private final ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        public Scanner() throws IOException {
            Resource[] resources = findResource();
            if (resources != null) {
                for (Resource resource : resources) {
                    String key = resource.getURI().toString();
                    String value = getMd(resource);
                    mappers.put(key, value);
                }
            }
        }
        public void reloadXML() throws Exception {
            SqlSessionFactory factory = context.getBean(SqlSessionFactory.class);
            Configuration configuration = factory.getConfiguration();
            removeConfig(configuration);
            
            
            //①使用ant表达式匹配相应路径的xml写法
            for (Resource resource : findResource()) {
                try {
                    XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(resource.getInputStream(), configuration, resource.toString(), configuration.getSqlFragments());
                    xmlMapperBuilder.parse();
                } finally {
                    ErrorContext.instance().reset();
                }
            }
            
            //②使用mybatis-config配置文件写死xml路径的写法
           /* InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            
            try {
                XPathParser parser = new XPathParser(inputStream, true, null, new XMLMapperEntityResolver());
                for (XNode child : parser.evalNode("/configuration").evalNode("mappers").getChildren()) {
                	String resourcePath = child.getStringAttribute("resource");
                	Resource resource = resourcePatternResolver.getResource(resourcePath);
                    XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(resource.getInputStream(), configuration, resource.toString(), configuration.getSqlFragments());
                    xmlMapperBuilder.parse();

                }
              } catch (Exception e) {
                throw ExceptionFactory.wrapException("Error building SqlSession.", e);
              } finally {
                ErrorContext.instance().reset();
                try {
                  inputStream.close();
                } catch (IOException e) {
                  // Intentionally ignore. Prefer previous error.
                }
              }*/
            
            
        }
        private void removeConfig(Configuration configuration) throws Exception {
            Class<?> classConfig = configuration.getClass();
            clearMap(classConfig, configuration, "mappedStatements");
            clearMap(classConfig, configuration, "caches");
            clearMap(classConfig, configuration, "resultMaps");
            clearMap(classConfig, configuration, "parameterMaps");
            clearMap(classConfig, configuration, "keyGenerators");
            clearMap(classConfig, configuration, "sqlFragments");
            clearSet(classConfig, configuration, "loadedResources");
        }
        private void clearMap(Class<?> classConfig, Configuration configuration, String fieldName) throws Exception {
            Field field = classConfig.getDeclaredField(fieldName);
            field.setAccessible(true);
            ((Map) field.get(configuration)).clear();
        }
        private void clearSet(Class<?> classConfig, Configuration configuration, String fieldName) throws Exception {
            Field field = classConfig.getDeclaredField(fieldName);
            field.setAccessible(true);
            ((Set) field.get(configuration)).clear();
        }
        public boolean isChanged() throws IOException {
            boolean isChanged = false;
            for (Resource resource : findResource()) {
                String key = resource.getURI().toString();
                String value = getMd(resource);
                if (!value.equals(mappers.get(key))) {
                    isChanged = true;
                    mappers.put(key, value);
                }
            }
            return isChanged;
        }
        private Resource[] findResource() throws IOException {
            return resourcePatternResolver.getResources(XML_RESOURCE_PATTERN);
        }
        private String getMd(Resource resource) throws IOException {
            return new StringBuilder().append(resource.contentLength()).append("-").append(resource.lastModified()).toString();
        }
    }
}
