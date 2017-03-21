package client.event;

import com.google.gwt.event.shared.GwtEvent;

public class NextPageEvent extends GwtEvent<NextPageEventHandler> {
    public static Type<NextPageEventHandler> TYPE = new Type<NextPageEventHandler>();

    public Type<NextPageEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(NextPageEventHandler handler) {
        handler.onNextPage(this);
    }
}
