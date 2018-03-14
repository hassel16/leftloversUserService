package dhbw.leftlovers.service.uaa.register;


import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ServiceRegistration {


    private Service ownService;

    ServiceRegistration(String serviceName, String serviceAddress, int servicePort) {
        this.ownService = new Service(serviceName, serviceAddress, servicePort);
        this.registerInServiceRegister();

    }

    private void registerInServiceRegister() {
        Gson g = new GsonBuilder().create();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(g.toJson(this.ownService, Service.class), headers);
        ResponseEntity<String> response = restTemplate.postForEntity("https://leftloversgateway.azurewebsites.net/APIGateway/ServiceRegister?password=leftlovers_wwi16B3",entity,String.class);
        this.ownService = (g.fromJson(response.getBody(), Service.class));
    }

}

