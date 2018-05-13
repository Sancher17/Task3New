package com.example.alex.collections.dataCollections.executor;

import android.os.Handler;
import android.os.Looper;


import com.example.alex.arch.LifecycleExecutor;
import com.example.alex.collections.CollectionsAdapter;
import com.example.alex.collections.CollectionsPresenter;
import com.example.alex.constants.Constants;
import com.example.alex.collections.dataCollections.CollectionsProcessor;
import com.example.alex.collections.dataCollections.ICollectionsProcessor;
import com.example.alex.dagger.AppInject;
import com.example.alex.utils.Logger;

import org.androidannotations.annotations.App;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ExecutorCollection implements LifecycleExecutor {

    private static Logger LOGGER = new Logger(ExecutorCollection.class);
    private int core = Runtime.getRuntime().availableProcessors();
    private ExecutorService executor = Executors.newFixedThreadPool(core+1);

    private ExecutorCollectionCallback callback;

    private Observer observer = null;


    /** c даггером * тут необходимо расскоментировать метод  getInject()*/
//    @Inject ICollectionsProcessor processor;
//    @Inject CollectionsAdapter adapter;

    /** без даггера */
    ICollectionsProcessor processor = new CollectionsProcessor();
    CollectionsAdapter adapter = new CollectionsAdapter();


    public ExecutorCollection(ExecutorCollectionCallback callback){
        this.callback = callback;
    }


    // пример со стартандроид ??
    class CallableLongAction implements Callable <Integer>{

        String data;

        CallableLongAction (String data){
            this.data = data;
        }
        @Override
        public Integer call() throws Exception {
            return longAction(data);
        }
    }

    private int longAction(String str){
        return Integer.parseInt(str);
    }

    @Override
    public void startCalculation(){

//        getInject();

        // TODO: 13.05.2018 сделать цикл запуска вычислений


        for (int i = 0; i < 20; i++) {
            doCalculateBackground(i, new ArrayList(), processor:: addToStart);
        }

        //отправитель данных
        Observable.fromCallable(new CallableLongAction("5"))
                .subscribeOn(Schedulers.from(executor))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

        // получатель данных
        observer = new Observer() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {

            }
        };

        doCalculateBackground(0, new ArrayList(), processor::addToStart);
        doCalculateBackground(1, new LinkedList(), processor::addToStart);
        doCalculateBackground(2, new CopyOnWriteArrayList(), processor::addToStart);

        doCalculateBackground(3, new ArrayList(), processor::addToMiddle);
        doCalculateBackground(4, new LinkedList(), processor::addToMiddle);
        doCalculateBackground(5, new CopyOnWriteArrayList(), processor::addToMiddle);

        doCalculateBackground(6, new ArrayList(), processor::addToEnd);
        doCalculateBackground(7, new LinkedList(), processor::addToEnd);
        doCalculateBackground(8, new CopyOnWriteArrayList(), processor::addToEnd);

        doCalculateBackground(9, new ArrayList(), processor::search);
        doCalculateBackground(10, new LinkedList(), processor::search);
        doCalculateBackground(11, new CopyOnWriteArrayList(), processor::search);

        doCalculateBackground(12, new ArrayList(), processor::removeStart);
        doCalculateBackground(13, new LinkedList(), processor::removeStart);
        doCalculateBackground(14, new CopyOnWriteArrayList(), processor::removeStart);

        doCalculateBackground(15, new ArrayList(), processor::removeMiddle);
        doCalculateBackground(16, new LinkedList(), processor::removeMiddle);
        doCalculateBackground(17, new CopyOnWriteArrayList(), processor::removeMiddle);

        doCalculateBackground(18, new ArrayList(), processor::removeEnd);
        doCalculateBackground(19, new LinkedList(), processor::removeEnd);
        doCalculateBackground(20, new CopyOnWriteArrayList(), processor::removeEnd);

    }


    public void doCalculateBackground(final int position, List list, ICollections func){
        LOGGER.log("doCalculateBackground // position " + position);
        LOGGER.log("run 0 " + Thread.currentThread());

//       getInject();

        callback.responseShowProgress(position);
        executor.submit(() -> {
            LOGGER.log("run 1 " + Thread.currentThread());
            int result = func.start(list);
            new Handler(Looper.getMainLooper()).post(() -> {
                LOGGER.log("run 3 " + Thread.currentThread());
                adapter.items.get(position).setResultOfCalculation(result);
                Constants.COUNT_OF_OPERATIONS_COLLECTIONS -= 1;
                callback.responseHideProgress(position);
            });
        });
    }

    public void doCalculateBackground_2(){





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
                callback.responseCalculationStopped();
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
    interface ICollections {
        int start(List list);
    }

    private void getInject(){
        AppInject.getComponent().inject(this);
    }

}