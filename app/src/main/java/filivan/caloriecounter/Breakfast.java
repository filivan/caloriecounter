package filivan.caloriecounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

import filivan.caloriecounter.Adapter.SearchAdapter;
import filivan.caloriecounter.Database.Database;

public class Breakfast extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    SearchAdapter mSearchAdapter;

    MaterialSearchBar mMaterialSearchBar;
    List<String> suggestList = new ArrayList<>();

    Database mDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_search);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mMaterialSearchBar = (MaterialSearchBar) findViewById(R.id.search_bar);

        mDatabase = new Database(this);

        mMaterialSearchBar.setHint("Search");
        mMaterialSearchBar.setCardViewElevation(10);
        loadSuggestList();

        mMaterialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> suggest = new ArrayList<>();
                for (String search : suggestList) {
                    if (search.toLowerCase().contains(mMaterialSearchBar.getText().toLowerCase())) {
                        suggest.add(search);
                    }
                }
                mMaterialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mMaterialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if (!enabled) {
                    mRecyclerView.setAdapter(mSearchAdapter);
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

        mSearchAdapter = new SearchAdapter(this, mDatabase.getCalories());
        mRecyclerView.setAdapter(mSearchAdapter);
    }

    private void startSearch(String text) {

        mSearchAdapter = new SearchAdapter(this, mDatabase.getCalorieByName(text));
        mRecyclerView.setAdapter(mSearchAdapter);
    }

    private void loadSuggestList() {
        suggestList = mDatabase.getNames();
        mMaterialSearchBar.setLastSuggestions(suggestList);
    }
}


