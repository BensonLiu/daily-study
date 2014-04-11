package com.yongpoliu.jmx;

import com.sun.jdmk.comm.HtmlAdaptorServer;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * native JMX demo
 *
 * @author liuyongpo@gmail.com
 */
public class JmxDemo {

    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();

        platformMBeanServer.registerMBean(new Config(), new ObjectName("jmx-demo:name=config"));

        HtmlAdaptorServer htmlAdaptorServer = new HtmlAdaptorServer();
        htmlAdaptorServer.setPort(8091);

        platformMBeanServer.registerMBean(htmlAdaptorServer, new ObjectName("jmx-demo:name=htmlAdaptor,port=8091"));

        htmlAdaptorServer.start();
    }
}
