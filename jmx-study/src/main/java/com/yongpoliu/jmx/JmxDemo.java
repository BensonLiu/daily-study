package com.yongpoliu.jmx;

import com.sun.jdmk.comm.HtmlAdaptorServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadInfo;

/**
 * native JMX demo
 *
 * @author liuyongpo@gmail.com
 */
public class JmxDemo {

    private final static Logger LOG = LoggerFactory.getLogger(JmxDemo.class);

    public static void main(String[] args) throws Exception {
        MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();

        platformMBeanServer.registerMBean(new Config(), new ObjectName("jmx-demo:name=config"));

        HtmlAdaptorServer htmlAdaptorServer = new HtmlAdaptorServer();
        htmlAdaptorServer.setPort(8091);

        platformMBeanServer.registerMBean(htmlAdaptorServer, new ObjectName("jmx-demo:name=htmlAdaptor,port=8091"));

        htmlAdaptorServer.start();

        LOG.warn("jmx-demo started......");

        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();

        double systemLoadAverage = operatingSystemMXBean.getSystemLoadAverage();

        LOG.warn("Current system load is {}", systemLoadAverage);

        ThreadInfo[] allThreads = ManagementFactory.getThreadMXBean().dumpAllThreads(true, true);

        for (ThreadInfo threadInfo : allThreads) {
            LOG.warn(threadInfo.toString());
        }

        MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();

        LOG.warn("heap used is {}", heapMemoryUsage);

    }
}
