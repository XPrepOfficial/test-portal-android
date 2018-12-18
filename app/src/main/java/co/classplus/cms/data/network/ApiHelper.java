package co.classplus.cms.data.network;

import co.classplus.cms.data.model.test.TestGetResponse;
import co.classplus.cms.data.model.test.TestInstructionsResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiHelper {

    @GET("test/{testId}/instructions")
    Observable<TestInstructionsResponse> getTestInstructions(@Path("testId") String testId);

    @GET("test/{testId}")
    Observable<TestGetResponse> getTestDetails(@Path("testId") String testId);
}
