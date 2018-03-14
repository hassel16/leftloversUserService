package com.tokensafeservice.tokensafeservice;


public class Service {
    public String serviceName, serviceUrl;
    public int servicePort;
    public int serviceId;

    Service(String serviceName, String serviceAddress, int servicePort) {
        this.serviceName = serviceName;
        this.serviceAddress = serviceAddress;
        this.servicePort = servicePort;
    }
}
