package co.classplus.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import co.classplus.data.DataManager;
import co.classplus.data.DataManagerImpl;
import co.classplus.data.network.ApiHelper;
import co.classplus.data.network.ApiHelperImpl;
import co.classplus.data.prefs.PreferencesHelper;
import co.classplus.data.prefs.PreferencesHelperImpl;
import co.classplus.di.ApplicationContext;
import co.classplus.di.PreferenceInfo;
import dagger.Module;
import dagger.Provides;

import static co.classplus.data.prefs.PreferencesHelperImpl.PREF_NAME;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

//    @Provides
//    @DatabaseInfo
//    String provideDatabaseName() {
//        return AppConstants.DB_NAME;
//    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(DataManagerImpl appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(PreferencesHelperImpl appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(ApiHelperImpl appApiHelper) {
        return appApiHelper;
    }

//    @Provides
//    @Singleton
//    DbHelper provideDbHelper(DbHelperImpl appDbHelper) {
//        return appDbHelper;
//    }
}
