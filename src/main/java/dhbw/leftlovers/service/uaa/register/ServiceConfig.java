package dhbw.leftlovers.service.uaa.register;


import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

@Configuration
public class ServiceConfig implements ApplicationListener<EmbeddedServletContainerInitializedEvent>{

    private String serviceName= "UAAService", serviceAddress = "https://uaaservice.herokuapp.com/";
    private int servicePort=443;

    public String getServiceName() {
        return serviceName;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public int getPort() {
        return servicePort;
    }

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
        ServiceRegistration serviceRegistration = new ServiceRegistration(this.serviceName,this.serviceAddress,this.servicePort);

    }
}
