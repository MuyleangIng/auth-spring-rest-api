package co.istad.Banking.api.notification.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;

@Builder
public record CreateNotificationDto(@JsonProperty("included_segments")
                                    String[] includedSegments,
                                    ContentDto contents,
                                    @JsonProperty("app_id")
                                    @Value("${onesignal.app-id}")
                                    String appId
                                    ) {

}
