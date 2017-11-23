package jio.ril.com.riljio.activity;

import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jio.ril.com.riljio.R;
import jio.ril.com.riljio.databinding.ActivityHomeBinding;
import jio.ril.com.riljio.fragment.HashTagFragment;
import jio.ril.com.riljio.fragment.HomeFragment;
import jio.ril.com.riljio.model.TwitterModel;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

public class HomeActivity extends AppCompatActivity implements HashTagFragment.onHashTagClickListener {

    ArrayList<String> hashTags = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new TwitterTask().execute("");
        ActivityHomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        addHomeFragment();
    }

    private class TwitterTask extends AsyncTask<String, Void, List<twitter4j.Status>> {
        @Override
        protected List<twitter4j.Status> doInBackground(String... hashTag) {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey("fgsdfg")
                    .setOAuthConsumerSecret("sfhgdgf")
                    .setOAuthAccessToken("djhgjdgj65875")
                    .setOAuthAccessTokenSecret("sdfhfdhh");
            //AsyncTwitterFactory af = new AsyncTwitterFactory(cb.build());
            //System.setProperty("java.net.useSystemProxies", "true");
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();

            try {
                if (!hashTag[0].isEmpty()) {
                    Query query = new Query("https://api.twitter.com/1.1/search/tweets.json?q=" + hashTag[0]+"&include_entities=true");
                    QueryResult rs = twitter.search(query);
                    return rs.getTweets();
                } else {
                    return twitter.getHomeTimeline();
                }

            } catch (final TwitterException e) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<twitter4j.Status> statuses) {
            super.onPostExecute(statuses);
            if (statuses == null)
                return;// application's rate limit might having been exhausted for the resource .
            ArrayList<TwitterModel> list = new ArrayList<>(statuses.size());
            for (twitter4j.Status status : statuses) {
                Log.d("MyConnectActivity", status.getUser().getName() + ":" +
                        status.getText());
                Log.d("getHashtagEntities", status.getHashtagEntities().length > 0 ?
                        status.getHashtagEntities()[0].getText() : "empty");
                for (int i = 0; i < status.getHashtagEntities().length; i++) {
                    hashTags.add(status.getHashtagEntities()[i].getText());
                }
                list.add(new TwitterModel(status.getUser().getName(),
                        status.getUser().getProfileImageURL(),
                        status.getText(), status.getUser().getCreatedAt().toString()));
            }
            HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager()
                    .findFragmentByTag(HomeFragment.TAG);
            if (homeFragment != null) {
                homeFragment.setTwitterData(list);
            }

        }
    }

    public void onTweeterClick(View v) {
        addHomeFragment();
        new TwitterTask().execute("");
    }

    public void onHashTagClick(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HashTagFragment fragment = new HashTagFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(HashTagFragment.KEY_HASH_LIST, hashTags);
        fragment.setArguments(args);
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onHashTagClickInterface(String hashTag) {
        addHomeFragment();
        new TwitterTask().execute(hashTag);
    }

    private void addHomeFragment() {
        HomeFragment fragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment, HomeFragment.TAG);
        fragmentTransaction.commit();
    }
}
