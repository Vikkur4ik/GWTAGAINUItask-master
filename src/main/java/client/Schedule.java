package client;

import client.event.NextPageEvent;
import client.event.NextPageEventHandler;
import client.event.PreviousPageEvent;
import client.event.PreviousPageEventHandler;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;


public class Schedule implements EntryPoint {

    private List<List<String>> buses;
    Service service = GWT.create(Service.class);
    CellTable<List<String>> table = new CellTable<>();
    Pager pager = new Pager();

    public void onModuleLoad() {
        Defaults.setServiceRoot(GWT.getHostPageBaseURL());
        table.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);


        TextColumn<List<String>> numberColumn = new TextColumn<List<String>>() {
            @Override
            public String getValue(List<String> object) {
                return object.get(0);
            }
        };
        table.addColumn(numberColumn, "Number");
        TextColumn<List<String>> fromColumn = new TextColumn<List<String>>() {
            @Override
            public String getValue(List<String> object) {
                return object.get(1);
            }
        };
        table.addColumn(fromColumn, "From");
        TextColumn<List<String>> toColumn = new TextColumn<List<String>>() {
            @Override
            public String getValue(List<String> object) {
                return object.get(2);
            }
        };
        table.addColumn(toColumn, "To");
        TextColumn<List<String>> timeColumn = new TextColumn<List<String>>() {
            @Override
            public String getValue(List<String> object) {
                return object.get(3);
            }
        };
        table.addColumn(timeColumn, "Time");

        table.setRowCount(10);

        pager.bind();
        pager.addHandler(new NextPageEventHandler() {
            @Override
            public void onNextPage(NextPageEvent event) {
                loadSchedule(pager.getPage() + 1);
            }
        }, NextPageEvent.TYPE);
        pager.addHandler(new PreviousPageEventHandler() {
            @Override
            public void onPreviousPage(PreviousPageEvent event) {

                if (pager.getPage() > 1) {
                    loadSchedule(pager.getPage() - 1);
                }
            }
        }, PreviousPageEvent.TYPE);

        RootPanel.get("container").add(table);
        RootPanel.get("buttons").add(pager);
        loadSchedule(1);


    }

    public void updateTable() {
        service.getBusByPage(1, new MethodCallback<List<List<String>>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {

            }

            @Override
            public void onSuccess(Method method, List<List<String>> lists) {
                table.setRowData(lists);
            }
        });
    }

    private void loadSchedule(int page) {
        service.getBusByPage(page, new MethodCallback<List<List<String>>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {

            }

            @Override
            public void onSuccess(Method method, List<List<String>> lists) {
                table.setRowData(lists);
                pager.setPage(page);
            }
        });
    }
}
