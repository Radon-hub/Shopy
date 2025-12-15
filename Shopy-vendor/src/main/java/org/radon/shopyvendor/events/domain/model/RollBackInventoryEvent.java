package org.radon.shopyvendor.events.domain.model;

import java.util.List;

public class RollBackInventoryEvent {
    private List<RollBackEventModel> items;

    public RollBackInventoryEvent() {
    }

    public RollBackInventoryEvent(List<RollBackEventModel> items) {
        this.items = items;
    }

    public List<RollBackEventModel> getItems() {
        return items;
    }

    public void setItems(List<RollBackEventModel> items) {
        this.items = items;
    }
}
