package me.syncify.unittestandroid;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by adarshpandey on 9/4/17.
 */

public class RxSchedulersTest extends RxSchedulersAbs {

    @Override
    public Scheduler getMainThreadScheduler() {
        return Schedulers.immediate();
    }

    @Override
    public Scheduler getIOScheduler() {
        return Schedulers.immediate();
    }

    @Override
    public Scheduler getComputationScheduler() {
        return Schedulers.immediate();
    }
}
