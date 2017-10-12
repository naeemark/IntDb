package com.intdb.android.features.catalog;

import com.intdb.android.features.catalog.interactor.CatalogInteractor;
import com.intdb.android.features.catalog.presenter.CarousalPresenter;
import com.intdb.android.features.catalog.presenter.impl.CarousalPresenterImpl;
import com.intdb.android.features.catalog.view.CarousalModule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.verify;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 12/10/2017
 */
public class CarousalPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private CatalogInteractor mInteractor;

    @Mock
    private CarousalModule mView;

    private CarousalPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        mPresenter = new CarousalPresenterImpl(mView, mInteractor);
    }

    @Test
    public void shouldShowLoading() throws Exception {

        //When
        mPresenter.onStart();

        //Then
        verify(mView).showLoading();
    }

    @Test
    public void shouldHideLoading() throws Exception {

        //When
        mPresenter.onComplete();

        //Then
        verify(mView).hideLoading();
    }

}