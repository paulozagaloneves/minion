
package com.example.k8s.controller;

import com.example.k8s.config.MinionConfig;
import com.example.k8s.lib.MinionsLibrary;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("/")
public class ServerInfoController {
  private final String version = "0.1";

  @Autowired
  Environment environment;

  private MinionsLibrary minionsLibrary;

  @Autowired
  private MinionConfig minionConfig;

  public ServerInfoController(MinionsLibrary minionsLibrary) {
    this.minionsLibrary = minionsLibrary;
  }

  @GetMapping
  public String logServerInstance() throws Exception {
    // Port
    String port = environment.getProperty("server.port");

    // Local address
    String localHostAddress = InetAddress.getLocalHost().getHostAddress();
    String localHostname = InetAddress.getLocalHost().getHostName();

    // Remote address
    String remoteHostAddress = InetAddress.getLoopbackAddress().getHostAddress();
    String remoteHostname = InetAddress.getLoopbackAddress().getHostName();

    StringBuilder sb = new StringBuilder("");
    sb.append("Datetime: " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
        .append("<br>");
    sb.append("Server Port: " + port).append("<br>");
    sb.append("Local Address: " + localHostAddress).append("<br>");
    sb.append("Local Hostname: " + localHostname).append("<br>");
    sb.append("Remote Address: " + remoteHostAddress).append("<br>");
    sb.append("Remote Hostname: " + remoteHostname).append("<br>");
    sb.append("os.name: " + System.getProperty("os.name")).append("<br>");
    sb.append("os.arch: " + System.getProperty("os.arch")).append("<br>");
    sb.append("java.vm.name: " + System.getProperty("java.vm.name")).append("<br>");
    sb.append("java.vendor.version: " + System.getProperty("java.vendor.version")).append("<br>");
    sb.append("java.vm.version: " + System.getProperty("java.vm.version")).append("<br>");
    sb.append("java.version: " + System.getProperty("java.version")).append("<br>");
    sb.append("java.runtime.version: " + System.getProperty("java.runtime.version")).append("<br>");


    sb.append("\tVersion: " + version).append("<br><br><br>");
    String minion = minionsLibrary.getMinion(minionConfig.getType());
    sb.append(minion);

    return sb.toString();
  }

}
