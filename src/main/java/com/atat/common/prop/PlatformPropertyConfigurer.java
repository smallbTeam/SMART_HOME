/**
 * 
 */
package com.atat.common.prop;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * @author jinhao
 */
public class PlatformPropertyConfigurer extends PropertyPlaceholderConfigurer {

    private Map<String, String> ctxPropertiesMap;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory factory, Properties props) throws BeansException {
        super.processProperties(factory, props);
        ctxPropertiesMap = new HashMap<String, String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value);
        }
    }

    public String getVal(String name) {
        return ctxPropertiesMap.get(name);
    }
}
