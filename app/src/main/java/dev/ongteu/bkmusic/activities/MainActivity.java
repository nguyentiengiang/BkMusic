package dev.ongteu.bkmusic.activities;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.fragment.CategoriesFragment;
import dev.ongteu.bkmusic.fragment.MainActivityFragment;
import dev.ongteu.bkmusic.fragment.MyPlayerFragment;
import dev.ongteu.bkmusic.utils.Constant;
import dev.ongteu.bkmusic.utils.Loader;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainActivityFragment.OnFragmentInteractionListener {

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, MainActivityFragment.newInstance("", ""))
                    .commit();
        }

        // Add Splash Screen then move Loader too
        Loader appLoader = new Loader(this);

        mTitle = mDrawerTitle = getTitle();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            if (doubleBackToExitPressedOnce) {
//                super.onBackPressed();
//                return;
//            }
//            this.doubleBackToExitPressedOnce = true;
////            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
//
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    doubleBackToExitPressedOnce = false;
//                }
//            }, 2000);
//        }
//        if (getFragmentManager().getBackStackEntryCount() > 0) {
//            getFragmentManager().popBackStack();
//        }

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            getFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        String NAME_FRM_BACK_STACK = "HOME";
        int COL_NUMBER = 3;
        switch (id) {
            case R.id.nav_hotMusic:
                fragment = CategoriesFragment.newInstance(COL_NUMBER, 1);
                NAME_FRM_BACK_STACK = "HOT_MUSIC";
                setTitle(R.string.menuHotMusic);
                break;
            case R.id.nav_popularAlbum:
                fragment = CategoriesFragment.newInstance(COL_NUMBER, 2);
                NAME_FRM_BACK_STACK = "POPULAR_ALBUM";
                setTitle(R.string.menuPopularAlbum);
                break;
            case R.id.nav_musicChart:
                fragment = CategoriesFragment.newInstance(COL_NUMBER, 3);
                NAME_FRM_BACK_STACK = "MUSIC_CHART";
                setTitle(R.string.menuMusicChart);
                break;
            case R.id.nav_now_playing:
                fragment = MyPlayerFragment.newInstance(Constant.PLAY_TYPE_UNKNOW, "", 0);
                NAME_FRM_BACK_STACK = "NOW_PLAYING";
                setTitle(getString(R.string.NOW_PLAYING));
                break;
            case R.id.nav_myFavorite:
            default:
                fragment = MainActivityFragment.newInstance("", "");
                setTitle(R.string.menuFavorMusic);
                break;
        }
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        // NOT FIX ISSUE BackStack
//        fragmentManager.beginTransaction().addToBackStack(NAME_FRM_BACK_STACK)
//                .replace(R.id.fragment_container, fragment).commit();
        replaceFragment(fragment, NAME_FRM_BACK_STACK);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void replaceFragment(Fragment fragment, String NAME_FRM_BACK_STACK) {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (currentFragment.getClass() == fragment.getClass()) {
            // nothing to do
            return;
        }
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        android.R.anim.fade_in, android.R.anim.fade_out,
                        android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(NAME_FRM_BACK_STACK)
                .commit();
    }

}
