package co.classplus.app.cms.di.module;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

@Module
public class ModuleUtil {

    @Provides
    public RestTemplate provideRestTemplate() {
        return new RestTemplate();
    }

}

@Singleton
@Component(
        modules = {
                ModuleUtil.class
        })
public interface MainComponent {
    void inject(Postman postman);
}
