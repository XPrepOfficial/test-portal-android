package co.classplus.cms.data.model.base;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SectionBaseModel extends CmsNameId {

    @Expose
    @SerializedName("order")
    private int order;
    @Expose
    @SerializedName("createdAt")
    private String createdAt;
    @Expose
    @SerializedName("updatedAt")
    private String updatedAt;

    protected SectionBaseModel(Parcel in) {
        super(in);
    }
}
