package dhbw.leftlovers.service.uaa.register;


public class Service {
    public String serviceName, serviceUrl;
    public int servicePort;
    public int serviceId;

    Service(String serviceName, String serviceUrl, int servicePort) {
        this.serviceName = serviceName;
        this.serviceUrl = serviceUrl;
        this.servicePort = servicePort;
    }
}
