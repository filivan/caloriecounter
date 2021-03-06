package filivan.caloriecounter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.Objects;


public class StartScreen extends AppCompatActivity {

    private Drawer.Result drawerResult = null;
    private Button mBreakfast;
    private Button mLunch;
    private Button mDinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        mBreakfast = (Button) findViewById(R.id.button5);
        mLunch = (Button) findViewById(R.id.button6);
        mDinner = (Button) findViewById(R.id.button8);

        mBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartScreen.this, Breakfast.class);
                startActivity(intent);
            }
        });

        mLunch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (StartScreen.this, Lunch.class);
                startActivity(intent);
            }
        });

        mDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartScreen.this, Dinner.class);
                startActivity(intent);
            }
        });

        // Инициализируем Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // Инициализируем Navigation Drawer
        drawerResult = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withHeader(R.layout.drawer_header)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_diet).withIcon(R.mipmap.diet),
                        new PrimaryDrawerItem().withName(R.string.drawer_calculator).withIcon(R.mipmap.calculator),
                        new PrimaryDrawerItem().withName(R.string.drawer_statistics).withIcon(R.mipmap.statistics),
                        new PrimaryDrawerItem().withName(R.string.drawer_settings).withIcon(R.mipmap.settings)

                )
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        InputMethodManager inputMethodManager = (InputMethodManager)
                                StartScreen.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(Objects.requireNonNull(
                                StartScreen.this.getCurrentFocus()).getWindowToken(), 0);
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                    }

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                    }
                })
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        switch (position) {

                            case 0:
                                startActivity(new Intent(StartScreen.this, StartScreen.class));
                                break;
                            case 1:
                                startActivity(new Intent(StartScreen.this, Calculator.class));
                                break;
                            case 2:
                                startActivity(new Intent(StartScreen.this, Statistics.class));
                                break;
                            case 3:
                                startActivity(new Intent(StartScreen.this, Settings.class));
                                break;
                        }

                    }
                })
                .build();

    }

    @Override
    public void onBackPressed() {
        if (drawerResult.isDrawerOpen()) {
            drawerResult.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

}
