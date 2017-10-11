package com.intdb.android.app.interactor;

import rx.Observable;
import rx.Observer;

public interface BaseInteractor {

    <T> void subscribe(Observable<T> observable, Observer<T> observer);

    void unsubscribe();

    void cancelOnGoingHttpRequest();

}
