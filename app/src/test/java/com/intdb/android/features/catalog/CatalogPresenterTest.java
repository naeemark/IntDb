package com.intdb.android.features.catalog;

import com.intdb.android.features.catalog.interactor.CatalogInteractor;
import com.intdb.android.features.catalog.presenter.CatalogPresenter;
import com.intdb.android.features.catalog.presenter.impl.CatalogPresenterImpl;
import com.intdb.android.features.catalog.view.CatalogView;

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
 * @since 12/10/2017
 */
public class CatalogPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private CatalogInteractor mInteractor;

    @Mock
    private CatalogView mView;

    private CatalogPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        mPresenter = new CatalogPresenterImpl(mInteractor);
        mPresenter.onViewAttached(mView);
    }


    @Test
    public void shouldLoadCarousals() throws Exception {
        //Given
        when(mInteractor.isNetworkConnected()).thenReturn(true);

        //When
        mPresenter.onStart(true);

        //Then
        verify(mView).loadCarousals();
    }

    @Test
    public void shouldShowNetworkError() {
        //Given
        when(mInteractor.isNetworkConnected()).thenReturn(false);

        //When
        mPresenter.onStart(true);

        //Then
        verify(mView).showNetworkError();
    }

}