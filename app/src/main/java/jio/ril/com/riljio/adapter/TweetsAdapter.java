package jio.ril.com.riljio.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import jio.ril.com.riljio.R;
import jio.ril.com.riljio.databinding.ListItemBinding;
import jio.ril.com.riljio.model.TwitterModel;

/**
 * Created by chkumar on 11/22/17.
 */

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.MyViewHolder>{
    private static final String LOG_TAG = "TweetsAdapter";
    ArrayList<TwitterModel> list;
    private Context mContext;

    public TweetsAdapter(Context context, ArrayList<TwitterModel> list) {
        mContext = context;
        this.list = list;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext), R.layout.list_item, parent, false);

        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TwitterModel tm = list.get(position);
        holder.mBinding.setTwitterModel(tm);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ListItemBinding mBinding;

        public MyViewHolder( View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}
