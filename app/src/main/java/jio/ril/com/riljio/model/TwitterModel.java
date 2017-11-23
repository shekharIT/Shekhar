package jio.ril.com.riljio.model;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jio.ril.com.riljio.BR;

/**
 * Created by chkumar on 11/22/17.
 */

public class TwitterModel extends BaseObservable {
    String mPosterUrl;
    String mName;
    String mDetail;
    String mTimeStamp;

    public TwitterModel(String name, String posterUrl, String detail, String timeStamp) {
        this.mName = name;
        this.mPosterUrl = posterUrl;
        this.mDetail = detail;
        this.mTimeStamp = timeStamp;
    }

    @Bindable
    public String getName() {
        return mName;
    }

    @Bindable
    public String getPosterUrl() {
        return mPosterUrl;
    }

    @Bindable
    public String getDescription() {
        return mDetail;
    }

    @Bindable
    public String getTimeStamp() {
        return mTimeStamp;
    }

    @Bindable
    public String getDetail() {
        return mDetail;
    }

    public void setPosterUrl(String mPosterUrl) {
        this.mPosterUrl = mPosterUrl;
        notifyPropertyChanged(BR.posterUrl);
    }

    public void setDescription(String mDescription) {
        this.mDetail = mDescription;
        notifyPropertyChanged(BR.detail);
    }

    public void setTimeStamp(String mTimeStamp) {
        this.mTimeStamp = mTimeStamp;
        notifyPropertyChanged(BR.timeStamp);
    }
}