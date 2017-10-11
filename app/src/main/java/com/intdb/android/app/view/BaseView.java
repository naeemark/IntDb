package com.intdb.android.app.view;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 11/10/2017
 */

public interface BaseView {

    void showLoading();

    void showLoading(String message);

    void hideLoading();

    void showErrorWithMessage(String errorText);

    void showErrorLoading();

    void showToast(String message);

    void showNetworkError();
}
