package jio.ril.com.riljio.fragment;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import jio.ril.com.riljio.R;
import jio.ril.com.riljio.databinding.HashListviewBinding;

/**
 * Created by chkumar on 11/22/17.
 */

public class HashTagFragment extends Fragment {
    onHashTagClickListener mOnHashTagClickListener;

    public interface onHashTagClickListener {
        public void onHashTagClickInterface(String hashTag);
    }

    public static final String KEY_HASH_LIST = "key_hash_list";
    ArrayList<String> mHashTagList;
    HashListviewBinding binding;


    public HashTagFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mOnHashTagClickListener = (onHashTagClickListener) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.hash_listview, container, false);
        binding = DataBindingUtil.bind(view);
        getActivity().setTitle(R.string.top10);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Set<String> set = new HashSet<>(getArguments().getStringArrayList(KEY_HASH_LIST));
        mHashTagList = new ArrayList<>(set);
        if (mHashTagList.size() == 0) {// if HashTag is empty adding a static hashtag.
            mHashTagList.add("#Waterloo");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, mHashTagList);
        binding.hashListview.setAdapter(adapter);
        binding.hashListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mOnHashTagClickListener != null) {
                    mOnHashTagClickListener.onHashTagClickInterface(mHashTagList.get(position));
                }
            }
        });
    }
}
