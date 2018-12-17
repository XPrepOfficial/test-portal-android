package co.classplus.cms.data.model.test;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParagraphModel implements Parcelable {

    @Expose
    @SerializedName("_id")
    private String _id;
    @Expose
    @SerializedName("description")
    private String description;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    protected ParagraphModel(Parcel in) {
        _id = in.readString();
        description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(description);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ParagraphModel> CREATOR = new Parcelable.Creator<ParagraphModel>() {
        @Override
        public ParagraphModel createFromParcel(Parcel in) {
            return new ParagraphModel(in);
        }

        @Override
        public ParagraphModel[] newArray(int size) {
            return new ParagraphModel[size];
        }
    };
}
