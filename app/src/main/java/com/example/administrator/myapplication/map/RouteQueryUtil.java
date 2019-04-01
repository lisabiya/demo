package com.example.administrator.myapplication.map;

import com.amap.api.maps.model.LatLng;
import com.amap.api.services.route.RouteSearch;
import com.blankj.utilcode.util.LogUtils;
import com.example.administrator.myapplication.base.BaseApplication;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function4;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class RouteQueryUtil {


    public static void driveRouteQuery(LatLng fromPosition, LatLng toPosition,
                                       RouteSearch.OnRouteSearchListener routeSearchListener) {
        RouteSearch routeSearch = new RouteSearch(BaseApplication.getInstance());
        routeSearch.setRouteSearchListener(routeSearchListener);
        RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo();

        RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo,
                RouteSearch.DRIVING_SINGLE_DEFAULT, null, null, "");
    }


    public static void rxJava1() {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                LogUtils.e("subscribe=" + 0);
                e.onNext("");
            }
        })
                .observeOn(Schedulers.io())
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) throws Exception {
                        LogUtils.e("map=" + 1);
                        return 1;
                    }
                })
                .flatMap(new Function<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> apply(Integer integer) throws Exception {
                        LogUtils.e("flatMap1=" + integer);
                        return makeRequest("aa");
                    }
                })
                .flatMap(new Function<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> apply(Integer integer) throws Exception {
                        LogUtils.e("flatMap2=" + integer);
                        return makeRequest2("aaa", 500);
                    }
                })
                .flatMap(new Function<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> apply(Integer integer) throws Exception {
                        LogUtils.e("flatMap3=" + integer);
                        return makeRequest2("aaaa", 200);
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.e("subscribe=" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public static void rxJava2() {
        ArrayList<String> strings = new ArrayList<>();
        strings.indexOf("");


        Observable.merge(makeRequest2("aaaa", 200), makeRequest("aaaaa")).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.e(integer);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {

            }
        });
    }


    public static void rxJava3() {
        Observable.zip(makeRequest("aaaaa"), makeRequest("aaaaa"), makeRequest("aaaaa"), makeRequest("aaaaa"), new Function4<Integer, Integer, Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2, Integer integer3, Integer integer4) throws Exception {
                return integer + integer2 + integer3 + integer4;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer o) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    public static void rxJava4() {
        PublishSubject loadPersonsCommand;

        PublishSubject<String> subject = PublishSubject.create();
        Disposable disposable0 = subject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                LogUtils.e("disposable0==" + s);
            }
        });
        subject.onNext("1");
        subject.onNext("2");

        Disposable disposable1 = subject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                LogUtils.e("disposable1==" + s);
            }
        });
        subject.onNext("3");
        subject.onNext("4");
        subject.onNext("5");
        Disposable disposable2 = subject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                LogUtils.e("disposable2==" + s);

            }
        });
        subject.onNext("6");
        subject.onNext("7");
        subject.onNext("8");
//        subject.onComplete();
        subject.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                LogUtils.e("disposable3==" + s);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e("disposable3==" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
        subject.onNext("9");
        subject.onNext("10");
    }

    public static void rxJava5() {
        PublishSubject<String> subject = PublishSubject.create();

        Observable<Integer> observable = Observable.just(1);

        observable
                .startWith(Observable.range(5, 4))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.e("onSubscribe");
                    }

                    @Override
                    public void onNext(Integer s) {
                        LogUtils.e("onNext=" + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e("onComplete");
                    }
                });


        final int type = 1;
        Disposable disposable = subject
                .compose(new ObservableTransformer<String, String>() {
                    @Override
                    public ObservableSource<String> apply(Observable<String> upstream) {

                        switch (type) {
                            case 1:
                                return upstream.filter(new Predicate<String>() {
                                    @Override
                                    public boolean test(String s) throws Exception {
                                        LogUtils.e("ObservableTransformer==" + s);
                                        return Integer.valueOf(s) == 6;
                                    }
                                });
                            case 2:
                                return upstream.takeWhile(new Predicate<String>() {
                                    @Override
                                    public boolean test(String s) throws Exception {
                                        LogUtils.e("ObservableTransformer==" + s);
                                        return Integer.valueOf(s) == 6;
                                    }
                                });
                            case 3:
                                return upstream.takeUntil(new Predicate<String>() {
                                    @Override
                                    public boolean test(String s) throws Exception {
                                        LogUtils.e("ObservableTransformer==" + s);
                                        return Integer.valueOf(s) == 6;
                                    }
                                });
                        }
                        return null;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        LogUtils.e("disposable1==" + s);
                    }
                });
        subject.onNext("1");
        subject.onNext("2");
        subject.onNext("3");
        subject.onNext("4");
        subject.onNext("5");
        subject.onNext("6");
        subject.onNext("7");
        subject.onNext("8");
        subject.onNext("9");
        subject.onNext("10");
        subject.onComplete();
    }


    private static Observable<Integer> makeRequest(String query) {
        return Observable.just(query)
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String query) throws Exception {
                        return query.length();
                    }
                });
    }

    private static Observable<Integer> makeRequest2(String query, int delay) {
        return Observable.just(query)
                .delay(delay, TimeUnit.MILLISECONDS)
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String query) throws Exception {
                        return query.length();
                    }
                });
    }

}
