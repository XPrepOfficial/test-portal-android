package co.classplus.app.cms.data.network.retrofit;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitException extends RuntimeException {

    public static RetrofitException httpError(String url, Response response, Retrofit retrofit) {
        String message = response.code() + " " + response.message();
        return new RetrofitException(message, url, response, Kind.HTTP, null, retrofit);
    }

    static RetrofitException networkError(IOException exception) {
        return new RetrofitException(exception.getMessage(), null, null, Kind.NETWORK, exception, null);
    }

    static RetrofitException unexpectedError(Throwable exception) {
        return new RetrofitException(exception.getMessage(), null, null, Kind.UNEXPECTED, exception, null);
    }

    /**
     * Identifies the event kind which triggered a {@link RetrofitException}.
     */
    public enum Kind {
        /**
         * An {@link IOException} occurred while communicating to the server.
         */
        NETWORK,
        /**
         * A non-200 HTTP status code was received from the server.
         */
        HTTP,
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }

    // Generic variables
    private final String url;
    private final Response response;
    private final Kind kind;
    private final Retrofit retrofit;

    // Variables related to our server responses
    private int errorCode;
    private String errorMessage;
    private boolean isTokenExpired;
    private boolean isOtpInvalid;
    private JSONObject errorJsonObject;

    private RetrofitException(String message, String url, Response response, Kind kind, Throwable exception, Retrofit retrofit) {
        super(message, exception);
        this.url = url;
        this.response = response;
        this.kind = kind;
        this.retrofit = retrofit;

        if (response != null) {
            try {
                this.errorCode = response.code();
                if (response.errorBody() != null) {
                    String jsonStr = response.errorBody().string();
                    this.errorJsonObject = new JSONObject(jsonStr);
                    if (this.errorJsonObject.has("message")) {
                        this.errorMessage = this.errorJsonObject.getString("message");
                    }
                    if (this.errorJsonObject.has("data")) {
                        JSONArray array = new JSONArray(this.errorJsonObject.getString("data"));
                        if (array.length() > 0) {
                            JSONObject data = array.getJSONObject(0);
                            if (data.has("tokenExpired")) {
                                int tokenExpired = data.getInt("tokenExpired");
                                this.isTokenExpired = tokenExpired == 1;
                            }
                            if (data.has("otpInvalid")) {
                                int otpInvalid = data.getInt("otpInvalid");
                                this.isOtpInvalid = otpInvalid == 1;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * The request URL which produced the error.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Response object containing status code, headers, body, etc.
     */
    public Response getResponse() {
        return response;
    }

    /**
     * The event kind which triggered this error.
     */
    public Kind getKind() {
        return kind;
    }

    /**
     * The Retrofit this request was executed on
     */
    public Retrofit getRetrofit() {
        return retrofit;
    }

    /**
     * The error code which was returned
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * The error message which was returned
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * For our server if token was expired or not
     */
    public boolean isTokenExpired() {
        return isTokenExpired;
    }

    /**
     * For our server if invalid otp was sent
     */
    public boolean isOtpInvalid() {
        return isOtpInvalid;
    }

    /**
     * The JSONObject for error body which is returned
     */
    public JSONObject getErrorJsonObject() {
        return errorJsonObject;
    }

    /**
     * HTTP response body converted to specified {@code type}. {@code null} if there is no
     * response.
     *
     * @throws IOException if unable to convert the body to the specified {@code type}.
     */
    public <T> T getErrorBodyAs(Class<T> type) throws IOException {
        if (response == null || response.errorBody() == null) {
            return null;
        }
        Converter<ResponseBody, T> converter = retrofit.responseBodyConverter(type, new Annotation[0]);
        return converter.convert(response.errorBody());
    }
}