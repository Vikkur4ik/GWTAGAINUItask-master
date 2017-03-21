package client.event;

import com.google.gwt.event.shared.GwtEvent;

public class PreviousPageEvent extends GwtEvent<PreviousPageEventHandler> {
    public static Type<PreviousPageEventHandler> TYPE = new Type<PreviousPageEventHandler>();

    public Type<PreviousPageEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(PreviousPageEventHandler handler) {
        handler.onPreviousPage(this);
    }
}
