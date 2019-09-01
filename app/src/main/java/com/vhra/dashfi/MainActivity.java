package com.vhra.dashfi;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.vhra.dashfi.dashboard.DashboardFragment;
import com.vhra.dashfi.values.ValuesRepository;
import com.vhra.dashfi.valuesviewer.ValuesViewerFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    boolean isDialogOpened = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_options);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();

            if (id == R.id.nav_all_values) {
                openValuesViewer();

            } else if (id == R.id.nav_add_value) {
                openAddItemDialog();

            } else if (id == R.id.nav_dashboard) {
                openDefaultDashboard();
            }

            drawer.closeDrawer(GravityCompat.START);
            return true;
        });

        openDefaultDashboard();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openAddItemDialog() {
        if (isDialogOpened) return;

        AddItemDialog dialog = new AddItemDialog(this);
        dialog.setAddItemCompleteListener((value) -> {
            isDialogOpened = false;
            addItem(value);
        });
        dialog.setOnDismissListener(dialogInterface -> isDialogOpened = false);
        dialog.show();
        isDialogOpened = true;
    }

    private void openDefaultDashboard() {
        Fragment newFragment = new DashboardFragment();
        replaceContent(newFragment, false);
    }

    private void openValuesViewer() {
        Fragment newFragment = new ValuesViewerFragment();
        replaceContent(newFragment, true);
    }

    private void addItem(ValueDetail value) {
        try {
            ValuesRepository.getInstance().addValue(value);
            EventBus.getDefault().post(new MessageEvent("UPDATE_DASHBOARD"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showToast(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show();
    }

    private void replaceContent(Fragment fragment, boolean backstack) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, fragment);
        if (backstack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
