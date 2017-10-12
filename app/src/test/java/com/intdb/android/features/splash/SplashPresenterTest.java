package com.intdb.android.features.splash;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 */
public class SplashPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private SplashInteractor mInteractor;

    @Mock
    private SplashView mView;

    private SplashPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        mPresenter = new SplashPresenterImpl(mInteractor);
        mPresenter.onViewAttached(mView);
    }

    @Test
    public void shouldShowInternetError() {
        //Given
        when(mInteractor.isNetworkConnected()).thenReturn(false);

        //When
        mPresenter.checkNetwork();

        //Then
        verify(mView).showNetworkError();
    }

    @Test
    public void shouldLaunchNextActivity() {

        when(mInteractor.isSplashDone()).thenReturn(true);

        mPresenter.onStart(true);

        verify(mView).launchNextActivity();
    }

    @Test
    public void shouldStartLoading() {
        mPresenter.startLoading();

        verify(mView).showLoading();
    }

    @Test
    public void shouldStopLoading() {
        mPresenter.stopLoading();

        verify(mView).hideLoading();
    }

}