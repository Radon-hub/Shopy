package org.radon.shopy.events.application.port.in;

import org.radon.shopy.events.domain.model.UserRegisteredEvent;

public interface SendUserRegisteredEventUseCase {
    void sendEvent(UserRegisteredEvent model);
}
