package co.classplus.cms.ui.base;

import android.os.Bundle;

import java.net.SocketTimeoutException;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;

import co.classplus.cms.R;
import co.classplus.cms.data.DataManager;
import co.classplus.cms.data.network.retrofit.RetrofitException;
import co.classplus.cms.utils.CommonUtils;
import co.classplus.cms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import rebus.permissionutils.PermissionEnum;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private final DataManager mDataManager;
    private final SchedulerProvider mSchedulerProvider;
    private final CompositeDisposable mCompositeDisposable;

    private V mMvpView;

    @Inject
    public BasePresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    @Override
    public PermissionEnum[] getPermissionEnums(String... permissions) {
        return CommonUtils.getPermissionEnums(permissions);
    }

    public V getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    @Override
    public void handleError(RetrofitException error, Bundle bundle, String apiTag) {
        if (error == null) {
            getMvpView().onError(R.string.api_default_error);
            return;
        }

        if (error.getKind() == RetrofitException.Kind.HTTP) {
            if (error.getErrorCode() == HttpsURLConnection.HTTP_UNAUTHORIZED && error.isTokenExpired()) {
//                doRefreshTokenApiCall(bundle, apiTag);
            } else {
                if (error.getErrorMessage() != null) {
                    getMvpView().onError(error.getErrorMessage());
                } else {
                    getMvpView().onError(R.string.some_error);
                }
            }
        } else if (error.getKind() == RetrofitException.Kind.NETWORK) {
            if (error.getCause() instanceof SocketTimeoutException) {
                getMvpView().onError(R.string.connection_error);
            } else {
                getMvpView().onError(R.string.connection_error);
            }
        } else {
            getMvpView().onError(R.string.some_error);
        }
    }

//    @Override
//    public void doRefreshTokenApiCall(Bundle bundle, String apiTag) {
//        getMvpView().showLoading();
//        getCompositeDisposable().add(getDataManager()
//                .doRefreshTokenApiCall(getRefreshJson())
//                .subscribeOn(getSchedulerProvider().io())
//                .observeOn(getSchedulerProvider().ui())
//                .subscribe(authTokenModel -> {
//                    getDataManager().setAccessToken(authTokenModel.getAuthToken().getToken());
//                    getDataManager().setTokenExpiryTime(authTokenModel.getAuthToken().getTokenExpiryTime());
//                    onAuthTokenRefresh(bundle, apiTag);
//                    getMvpView().hideLoading();
//                }, throwable -> {
//                    getMvpView().hideLoading();
//                    setUserAsLoggedOut(true);
//                }));
//    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
