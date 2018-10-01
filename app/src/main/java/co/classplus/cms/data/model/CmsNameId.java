package co.classplus.cms.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CmsNameId implements Parcelable {

    private String _id;
    private String name;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected CmsNameId(Parcel in) {
        _id = in.readString();
        name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(name);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CmsNameId> CREATOR = new Parcelable.Creator<CmsNameId>() {
        @Override
        public CmsNameId createFromParcel(Parcel in) {
            return new CmsNameId(in);
        }

        @Override
        public CmsNameId[] newArray(int size) {
            return new CmsNameId[size];
        }
    };
}