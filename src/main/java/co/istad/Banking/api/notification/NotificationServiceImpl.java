package co.istad.Banking.api.notification;
import co.istad.Banking.api.notification.web.CreateNotificationDto;
import co.istad.Banking.api.notification.web.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotificationServiceImpl  implements  NotificationService{

    @Value("${onesignal.app-id}")
    public String oneSignalAppId;


    @Value("${onesignal.rest-api-key}")
    public String restApiKey;



    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate){
        this.restTemplate= restTemplate;
    }
    @Override
    public boolean pushNotification(CreateNotificationDto notificationDto) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("accept", "application/json");
        httpHeaders.set("Authorization", "Basic " + restApiKey);
        httpHeaders.set("content-type", "application/json");



        NotificationDto body = NotificationDto.builder()
                .appId(oneSignalAppId)
                .includedSegments(notificationDto.includedSegments())
                .contents(notificationDto.contents())
                .build();


        HttpEntity<NotificationDto> requestBody = new HttpEntity<>(body, httpHeaders);

        ResponseEntity<?> response =restTemplate.postForEntity(
            "https://onesignal.com/api/v1/notifications",
            requestBody,
            Map.class
            );


        return response.getStatusCode() == HttpStatus.OK;
    }
}
