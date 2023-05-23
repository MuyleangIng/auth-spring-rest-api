package co.istad.Banking.api.notification;

import co.istad.Banking.api.notification.web.CreateNotificationDto;
import co.istad.Banking.api.notification.web.NotificationDto;


public interface NotificationService {



    boolean pushNotification(CreateNotificationDto notificationDto);
}
