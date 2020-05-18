package com.example.k8s.component;

import java.net.InetAddress;
import java.util.List;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoggingBean implements InitializingBean {
  
  @Autowired
  Environment environment;

  @Override
  public void afterPropertiesSet() throws Exception {
    log.info("===========================================================================");
    try {
      logServerInstance();
      logActiveSpringProfiles();
    } catch (Exception ex) {
      log.error("Initializing Bean failed:", ex);
    }
    log.info("===========================================================================");
  }

  private void logServerInstance() throws Exception {
    // Port
    String port = environment.getProperty("server.port");
    // Local address
    String localHostAddress = InetAddress.getLocalHost().getHostAddress();
    String localHostname = InetAddress.getLocalHost().getHostName();

    // Remote address
    String remoteHostAddress = InetAddress.getLoopbackAddress().getHostAddress();
    String remoteHostname = InetAddress.getLoopbackAddress().getHostName();
    
    StringBuilder sb = new StringBuilder("Server Instance: \n");
    sb.append("\tServer Port: " + port).append("\n");
    sb.append("\tLocal Address: " + localHostAddress).append("\n");
    sb.append("\tLocal Hostname: " + localHostname).append("\n");
    sb.append("\tRemote Address: " + remoteHostAddress).append("\n");
    sb.append("\tRemote Hostname: " + remoteHostname).append("\n");
    
    log.info(sb.toString());
  }

  private void logActiveSpringProfiles() {
    // TODO Auto-generated method stub
    
  }


  private String getValueOfProperty(final Environment env, final String propertyKey, final String propertyDefaultValue, final List<String> acceptablePropertyValues) {
    String propValue = env.getProperty(propertyKey);
    if (propValue == null) {
      propValue = propertyDefaultValue;
      log.warn("The {} doesn't have an explicit value; default value is = {}", propertyKey, propertyDefaultValue);
    }
    
    if(acceptablePropertyValues != null) {
      if (!acceptablePropertyValues.contains(propValue)) {
        log.warn("The property = {} has an invalid value = {}", propertyKey, propValue);
      }
    }
    
    if (propValue == null) {
      log.warn("The property = {} is null", propertyKey);
    }
    
    return propValue;
    
  }
}
