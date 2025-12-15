package org.radon.shopy.events.application.port.out;

import org.radon.shopy.events.domain.model.UserRegisteredEvent;

public interface EventRepository {
    void sendUserRegisteredEvent(UserRegisteredEvent model);
}
