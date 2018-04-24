package com.example.alex.dataMaps.executor;

import android.os.Handler;
import android.os.Looper;

import com.example.alex.constants.Constants;
import com.example.alex.arch.LifecycleExecutor;
import com.example.alex.dataMaps.IMapsProcessor;
import com.example.alex.dataMaps.MapsProcessor;
import com.example.alex.maps.MapsAdapter;
import com.example.alex.maps.MapsPresenter;
import com.example.alex.utils.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

public class ExecutorMaps implements LifecycleExecutor {

    private static Logger LOGGER = new Logger(ExecutorMaps.class);
    private int core = Runtime.getRuntime().availableProcessors();
    private ExecutorService executor = Executors.newFixedThreadPool(core+1);

    private ExecutorMapsCallback callback;

    private IMapsProcessor processor = new MapsProcessor();

    private MapsAdapter adapter = new MapsAdapter();

    private MapsPresenter presenter;

    @Inject
    public ExecutorMaps(ExecutorMapsCallback callback){
        this.callback = callback;
    }


    @Override
    public void startCalculation(){

       doCalculateBackground(0, new TreeMap(), processor::add);
       doCalculateBackground(1, new HashMap(), processor::add);

       doCalculateBackground(2, new TreeMap(), processor::search);
       doCalculateBackground(3, new HashMap(), processor::search);

       doCalculateBackground(4, new TreeMap(), processor::remove);
       doCalculateBackground(5, new HashMap(), processor::remove);

    }


    public void doCalculateBackground(final int position, Map map, IMaps func){
        LOGGER.log("doCalculateBackground // position " + position);
        LOGGER.log("run 0 " + Thread.currentThread());
        callback.responseShowProgress(position);
        executor.submit(() -> {
            LOGGER.log("run 1 " + Thread.currentThread());
            int result = func.start(map);
            new Handler(Looper.getMainLooper()).post(() -> {
                LOGGER.log("run 3 " + Thread.currentThread());
                adapter.items.get(position).setResultOfCalculation(result);
                Constants.COUNT_OF_OPERATIONS_COLLECTIONS -= 1;
                callback.responseHideProgress(position);
            });
        });
    }

    @Override
    public void stopCalculation() {
        executor.shutdownNow();
        ExecutorService interrupt = Executors.newFixedThreadPool(1);
        interrupt.submit(() -> {
            while (!executor.isTerminated()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            new Handler(Looper.getMainLooper()).post(() -> {
                presenter = new MapsPresenter();
                presenter.calculationStopped();
                Constants.COUNT_OF_OPERATIONS_COLLECTIONS = 21;
            });
        });
    }

    @Override
    public boolean isRunning(){
        if (executor != null) {
            return executor.isShutdown();
        }
        return false;
    }

    //functional interface
    interface IMaps {
        int start(Map map);
    }
}