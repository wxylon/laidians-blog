package com.laidians.utils;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 参考：org.apache.catalina.util.StringManager
 * @author wxylon@gmail.com
 * @date 2013-1-23
 */
public class Environment {

	private static final Log log = LogFactory.getLog(Environment.class);
	
	private static Map<String, Environment> environments = new HashMap<String, Environment>();
	private Properties properties = new Properties();
	private volatile long lastModify = 0l;
	private String file;
	
	
	private Environment(String file) {
		this.file = file;
		load(file);
	}
	
	private void load(String propertieFile){
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		if(null == cl){
			cl = this.getClass().getClassLoader();
		}
		
		if(!properties.isEmpty()){
			properties.clear();
		}
		try {
			properties.load(cl.getResourceAsStream(propertieFile));
		} catch (IOException e) {
		}
	}
	
	private boolean getLastModify(ClassLoader cl, String propertieFile){
		boolean ifLoad = false;
		String file = cl.getResource(propertieFile).getFile();
		long now = new File(file).lastModified();
		if(((now - lastModify) / 1000) > 60){
			ifLoad = true;
			lastModify = now;
		}
		return ifLoad;
	}
	
	
	/**
     * Get a string from the underlying resource bundle.
     *
     * @param key The resource name
     */
    public String getString(String key) {
        return MessageFormat.format(getStringInternal(key), (Object [])null);
    }


    protected String getStringInternal(String key) {
        if (key == null) {
            String msg = "key is null";
            throw new NullPointerException(msg);
        }

        String str = null;
       
        return properties.getProperty(key, str);
    }

    /**
     * Get a string from the underlying resource bundle and format
     * it with the given set of arguments.
     *
     * @param key The resource name
     * @param args Formatting directives
     */

    public String getString(String key, Object[] args) {
    	load(file);
    	
        String iString = null;
        String value = getStringInternal(key);
        
        try {
            Object nonNullArgs[] = args;
            for (int i=0; i<args.length; i++) {
                if (args[i] == null) {
                    if (nonNullArgs==args) nonNullArgs=(Object[])args.clone();
                    nonNullArgs[i] = "null";
                }
            }

            iString = MessageFormat.format(value, nonNullArgs);
        } catch (IllegalArgumentException iae) {
            StringBuffer buf = new StringBuffer();
            buf.append(value);
            for (int i = 0; i < args.length; i++) {
                buf.append(" arg[" + i + "]=" + args[i]);
            }
            iString = buf.toString();
        }
        return iString;
    }

    /**
     * Get a string from the underlying resource bundle and format it
     * with the given object argument. This argument can of course be
     * a String object.
     *
     * @param key The resource name
     * @param arg Formatting directive
     */

    public String getString(String key, Object arg) {
        Object[] args = new Object[] {arg};
        return getString(key, args);
    }

    /**
     * Get a string from the underlying resource bundle and format it
     * with the given object arguments. These arguments can of course
     * be String objects.
     *
     * @param key The resource name
     * @param arg1 Formatting directive
     * @param arg2 Formatting directive
     */

    public String getString(String key, Object arg1, Object arg2) {
        Object[] args = new Object[] {arg1, arg2};
        return getString(key, args);
    }

    /**
     * Get a string from the underlying resource bundle and format it
     * with the given object arguments. These arguments can of course
     * be String objects.
     *
     * @param key The resource name
     * @param arg1 Formatting directive
     * @param arg2 Formatting directive
     * @param arg3 Formatting directive
     */

    public String getString(String key, Object arg1, Object arg2, Object arg3) {
        Object[] args = new Object[] {arg1, arg2, arg3};
        return getString(key, args);
    }

    /**
     * Get a string from the underlying resource bundle and format it
     * with the given object arguments. These arguments can of course
     * be String objects.
     *
     * @param key The resource name
     * @param arg1 Formatting directive
     * @param arg2 Formatting directive
     * @param arg3 Formatting directive
     * @param arg4 Formatting directive
     */

    public String getString(String key, Object arg1, Object arg2, Object arg3, Object arg4) {
        Object[] args = new Object[] {arg1, arg2, arg3, arg4};
        return getString(key, args);
    }

    /**
     * Get the StringManager for a particular package. If a manager for
     * a package already exists, it will be reused, else a new
     * StringManager will be created and returned.
     *
     * @param packageName The package name
     */

    public synchronized static Environment getEnvironment(String resources) {
    	Environment ement = environments.get(resources);

        if (ement == null) {
        	ement = new Environment(resources);
        	environments.put(resources, ement);
        }
        return ement;
    }
}
