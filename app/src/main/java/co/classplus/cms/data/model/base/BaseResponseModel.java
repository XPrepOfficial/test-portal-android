package co.classplus.cms.data.model.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponseModel {

    @SerializedName("code")
    @Expose
    private int code;

    @Expose
    @SerializedName("status")
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
