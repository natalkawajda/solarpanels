package view;
import javafx.event.ActionEvent;

import javax.swing.*;

public class MainPageController {
    private ViewHandler viewHandler;
    public void init(ViewHandler viewHandler)
    {
        this.viewHandler = viewHandler;
    }
    public void handleManufacturer(ActionEvent e){
        viewHandler.changeScene(ViewHandler.MANUFACTURER);
    }
    public void handlePvPanels(ActionEvent e) {
        viewHandler.changeScene(ViewHandler.PV_PANELS);
    }
    public void handleThPanels(ActionEvent e) {
        viewHandler.changeScene(ViewHandler.TH_PANELS);
    }
    public void handlePanels(ActionEvent e) {
        viewHandler.changeScene((ViewHandler.PANELS));
    }
    public void closeButton(ActionEvent e) {
        //viewHandler.getConnection().close();
    }
}
