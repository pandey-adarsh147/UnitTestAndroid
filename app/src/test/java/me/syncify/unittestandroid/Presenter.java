package me.syncify.unittestandroid;

import javax.inject.Inject;

/**
 * Created by adarshpandey on 9/8/17.
 */

public class Presenter {

    @Inject
    public PrinterService printerService;

    public String printer() {
//        return printerService.print();
        return "ABC";
    }
}
