package com.example.ideanote.toolbarshowandhidesample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ideanote.toolbarshowandhidesample.model.Article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final static int HIDE_ANIMATE_DURATION = 300;

    private boolean mActionBarShown = true;

    private ArrayList<View> mViewCollection = new ArrayList<>();

    private int mActionBarAutoHideSensivity = 0;

    private int mActionBarAutoHideMinY = 0;

    private int mActionBarAutoHideSignal = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initialize();

        mViewCollection.add(toolbar);
        mViewCollection.add(findViewById(R.id.headerbar));

        setupRecyclerView();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActionBarShown = !mActionBarShown;
                onActionBarAutoShowOrHide(mActionBarShown);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initialize() {
        mActionBarAutoHideSensivity = getResources().getDimensionPixelSize(
                R.dimen.action_bar_auto_hide_sensivity);

        mActionBarAutoHideMinY = getResources().getDimensionPixelSize(
                R.dimen.action_bar_auto_hide_min_y);
    }

    private void autoShownOrHideActionBar(boolean show) {
        if (show == mActionBarShown) {
            return;
        }

        mActionBarShown = show;
        onActionBarAutoShowOrHide(show);
    }

    private void onMainContentScrolled(int currentY, int deltaY) {
        if (deltaY > mActionBarAutoHideSensivity) {
            deltaY = mActionBarAutoHideSensivity;
        } else if (deltaY < -mActionBarAutoHideSensivity) {
            deltaY = -mActionBarAutoHideSensivity;
        }

        if (Math.signum(deltaY) * Math.signum(mActionBarAutoHideSignal) < 0) {
            mActionBarAutoHideSignal = deltaY;
        } else {
            mActionBarAutoHideSignal += deltaY;
        }

        boolean shouldShown = currentY < mActionBarAutoHideMinY ||
                (mActionBarAutoHideSignal <= -mActionBarAutoHideSensivity);
        autoShownOrHideActionBar(shouldShown);
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private Map<Integer, Integer> heights = new HashMap<>();

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                View firstVisibleItemView = recyclerView.getChildAt(0);
                if (firstVisibleItemView == null) {
                    return;
                }

                int firstVisibleItem = recyclerView.getChildAdapterPosition(firstVisibleItemView);
                heights.put(firstVisibleItem, firstVisibleItemView.getHeight());

                int previousItemsHeight = 0;
                for (int i=0; i < firstVisibleItem; ++i) {
                    previousItemsHeight += heights.get(i) != null ? heights.get(i) : 0;
                }

                int currentScrollY = previousItemsHeight - firstVisibleItemView.getTop()
                        + recyclerView.getPaddingTop();

                Log.i(MainActivity.class.getSimpleName(), "currentScrollY=" + currentScrollY);
                onMainContentScrolled(currentScrollY, dy);
            }
        });

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(createArticles());
        recyclerViewAdapter.setOnItemClickListener(mOnItemClickListener);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private List<Article> createArticles() {
        ArrayList<Article> articles = new ArrayList<>();
        for (int i=0; i < 50; ++i) {
            articles.add(new Article("Title" + i, "Description" + i));
        }
        return articles;
    }

    private void onActionBarAutoShowOrHide(boolean shown) {
        for (View view : mViewCollection) {
            if (shown) {
                ViewCompat.animate(view)
                        .translationY(0)
                        .alpha(1)
                        .setDuration(HIDE_ANIMATE_DURATION)
                        .setInterpolator(new DecelerateInterpolator())
                        .withLayer();
            } else {
                ViewCompat.animate(view)
                        .translationY(-view.getBottom())
                        .alpha(0)
                        .setDuration(HIDE_ANIMATE_DURATION)
                        .setInterpolator(new DecelerateInterpolator())
                        .withLayer();
            }
        }
    }

    private RecyclerViewAdapter.OnItemClickListener mOnItemClickListener =
            new RecyclerViewAdapter.OnItemClickListener() {
                @Override
                public void onClick(Article article) {
                    Toast.makeText(MainActivity.this, "CLICK!", Toast.LENGTH_SHORT).show();
                }
            };
}
