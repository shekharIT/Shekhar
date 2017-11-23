package jio.ril.com.riljio.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import jio.ril.com.riljio.R;
import jio.ril.com.riljio.adapter.TweetsAdapter;
import jio.ril.com.riljio.databinding.HomeFragmentBinding;
import jio.ril.com.riljio.model.TwitterModel;
import twitter4j.Status;

/**
 * Created by chkumar on 11/22/17.
 */

public class HomeFragment extends Fragment {
    public static final String TAG = "HomeFragment";
    HomeFragmentBinding binding;


    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment, container, false);
        binding = DataBindingUtil.bind(view);
        getActivity().setTitle(R.string.tweets);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

    public void setTwitterData(ArrayList<TwitterModel> list) {
        binding.recyclerView.setAdapter(new TweetsAdapter(getContext(), list));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
