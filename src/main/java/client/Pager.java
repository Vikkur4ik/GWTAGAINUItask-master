package client;

import client.event.NextPageEvent;
import client.event.PreviousPageEvent;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;

public class Pager extends Composite {


    interface PagerUiBinder extends UiBinder<HTMLPanel, Pager> {
    }

    private static PagerUiBinder ourUiBinder = GWT.create(PagerUiBinder.class);
    @UiField
    Button previousPageButton;
    @UiField
    Label currentPageLabel;
    @UiField
    Button nextPageButton;

    private int currentPage = 1;

    public Pager() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public void bind() {
        nextPageButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                fireEvent(new NextPageEvent());
            }
        });

        previousPageButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                fireEvent(new PreviousPageEvent());
            }

        });
    }

    public void setPage(int page) {
        currentPage = page;
        currentPageLabel.setText(String.valueOf(page));
    }

    public int getPage() {
        return currentPage;
    }
}