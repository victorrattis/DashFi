package com.vhra.dashfi;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.vhra.dashfi.dashboard.DashboardFragment;
import com.vhra.dashfi.data.ValuesRepository;
import com.vhra.dashfi.data.room.ValuesLocalDataSource;
import com.vhra.dashfi.domain.UseCaseHandler;
import com.vhra.dashfi.domain.UseCaseSchedulerImpl;
import com.vhra.dashfi.domain.usecase.SaveValueUseCase;
import com.vhra.dashfi.ui.addvalue.AddItemDialog;
import com.vhra.dashfi.ui.addvalue.AddValuePresenter;
import com.vhra.dashfi.utils.DiskIOThreadExecutor;
import com.vhra.dashfi.utils.ILog;
import com.vhra.dashfi.utils.LogUtils;
import com.vhra.dashfi.valuesviewer.ValuesViewerFragment;

public class MainActivity extends AppCompatActivity {
    boolean isDialogOpened = false;

    private ILog mLog;
    private UseCaseHandler mUseCaseHandler;
    private ValuesRepository mValuesRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLog = new LogUtils();
        mUseCaseHandler = new UseCaseHandler(new UseCaseSchedulerImpl());

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
            switch (id) {
                case R.id.nav_add_value:
                    openAddItemDialog();
                    break;
            }

//            if (id == R.id.nav_all_values) {
////                openValuesViewer();
//
//            } else
//            } else if (id == R.id.nav_dashboard) {
////                openDefaultDashboard();
//            }

            drawer.closeDrawer(GravityCompat.START);
            return true;
        });

//        openDefaultDashboard();
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
        new AddValuePresenter(
                dialog,
                mUseCaseHandler,
                new SaveValueUseCase(getValuesRepository(), mLog));

        dialog.setOnDismissListener(dialogInterface -> isDialogOpened = false);
        dialog.show();
        isDialogOpened = true;
    }

    private ValuesRepository getValuesRepository() {
        if (mValuesRepository == null) {
            mValuesRepository = createValuesRepository();
        }
        return mValuesRepository;
    }

    private ValuesRepository createValuesRepository() {
        DiskIOThreadExecutor diskIOThreadExecutor = new DiskIOThreadExecutor();
        ValuesLocalDataSource valuesLocalDataSource = new ValuesLocalDataSource(
                this.getApplication(), diskIOThreadExecutor, mLog);

        return new ValuesRepository(valuesLocalDataSource, mLog);
    }

    private void openDefaultDashboard() {
        Fragment newFragment = new DashboardFragment();
        replaceContent(newFragment, false);
    }

    private void openValuesViewer() {
        Fragment newFragment = new ValuesViewerFragment();
        replaceContent(newFragment, true);
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
