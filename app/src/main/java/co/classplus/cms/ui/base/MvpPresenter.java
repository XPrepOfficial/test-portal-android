package co.classplus.cms.ui.base;

import co.classplus.cms.data.network.retrofit.RetrofitException;
import rebus.permissionutils.PermissionEnum;

public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

    void onDetach();

    void handleError(RetrofitException error);

    PermissionEnum[] getPermissionEnums(String... permissions);
}
