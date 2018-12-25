package co.classplus.app.cms.data.network;

import com.google.gson.JsonObject;

import co.classplus.app.cms.data.model.solutions.TestSolutionResponse;
import co.classplus.app.cms.data.model.submit.SubmitTestResponse;
import co.classplus.app.cms.data.model.test.TestGetResponse;
import co.classplus.app.cms.data.model.test.TestInstructionsResponse;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiHelper {

    @GET("test/{testId}/instructions")
    Observable<TestInstructionsResponse> getTestInstructions(@Header("x-cms-access-token") String accessToken, @Path("testId") String testId);

    @GET("test/{testId}")
    Observable<TestGetResponse> getTestDetails(@Header("x-cms-access-token") String accessToken, @Path("testId") String testId);

    @POST("test/start")
    Observable<TestGetResponse> startTestApi(@Header("x-cms-access-token") String accessToken, @Body JsonObject jsonObject);

    @POST("test/evaluate")
    Observable<SubmitTestResponse> submitTest(@Header("x-cms-access-token") String accessToken, @Body JsonObject jsonObject);

    @GET("test/{testId}/student/{studentTestId}/solutions")
    Observable<TestSolutionResponse> getTestSolutions(@Header("x-cms-access-token") String accessToken, @Path("testId") String testId, @Path("studentTestId") String studentTestId);
}
