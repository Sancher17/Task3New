package com.example.alex.collections.dataCollections.executor;

import android.os.Handler;
import android.os.Looper;

import com.example.alex.arch.LifecycleExecutor;
import com.example.alex.collections.CollectionsAdapter;
import com.example.alex.collections.dataCollections.CollectionsProcessor;
import com.example.alex.collections.dataCollections.ICollectionsProcessor;
import com.example.alex.constants.Constants;
import com.example.alex.dagger.AppInject;
import com.example.alex.utils.Logger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class ExecutorCollection implements LifecycleExecutor {

    private static Logger LOGGER = new Logger(ExecutorCollection.class);
    private int core = Runtime.getRuntime().availableProcessors();
    private ExecutorService executor;

    private ExecutorCollectionCallback callback;

    private ICollectionsProcessor processor;

    private int count = 0;


    public ExecutorCollection(ExecutorCollectionCallback callback) {
        this.callback = callback;
    }

    @Override
    public void startCalculation() {

        processor = new CollectionsProcessor();

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

    private void doCalculateBackground(final int position, List list, ICollections func) {

        executor = Executors.newFixedThreadPool(core + 1);
        callback.responseShowProgress(position);

        // развернутый способ 1 ,  кастомный Observable
//        Observable.OnSubscribe<Integer> onSubscribe = subscriber -> {
//            int result = func.start(list);
//            subscriber.onNext(result);
//        };
//
//        Observable.create(onSubscribe)
//                .subscribeOn(Schedulers.from(executor))
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);

        // сжатый способ (аналогично способу 1)
        Observable.fromCallable(() -> func.start(list))
                .subscribeOn(Schedulers.from(executor))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {}
                    @Override
                    public void onError(Throwable e) {}
                    @Override
                    public void onNext(Integer result) {
                        LOGGER.log("onNext " + count + "  " + Schedulers.computation());
                        count++;
                        CollectionsAdapter.items.get(position).setResultOfCalculation(result);
                        Constants.COUNT_OF_OPERATIONS_COLLECTIONS -= 1;
                        callback.responseHideProgress(position);
                    }
                });
    }

    @Override
    public void stopCalculation() {
        LOGGER.log("stop / Schedulers ");
        executor.shutdownNow();
        Schedulers.shutdown();
    }


//    @Override
//    public void stopCalculation() {
//        executor.shutdownNow();
//        ExecutorService interrupt = Executors.newFixedThreadPool(1);
//        interrupt.submit(() -> {
//            while (!executor.isTerminated()) {
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            new Handler(Looper.getMainLooper()).post(() -> {
//                callback.responseCalculationStopped();
//                Constants.COUNT_OF_OPERATIONS_COLLECTIONS = 21;
//            });
//        });
//    }

    @Override
    public boolean isRunning() {
        if (executor != null) {
            return executor.isShutdown();
        }
        return false;
    }

    //functional interface
    private interface ICollections {
        int start(List list);
    }

}