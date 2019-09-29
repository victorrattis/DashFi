package com.vhra.dashfi.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.vhra.dashfi.R;
import com.vhra.dashfi.domain.UseCaseHandler;
import com.vhra.dashfi.domain.model.ValueDetail;
import com.vhra.dashfi.domain.usecase.GetCardsUseCase;
import com.vhra.dashfi.domain.usecase.GetValuesUseCase;
import com.vhra.dashfi.domain.usecase.SaveValueUseCase;
import com.vhra.dashfi.ui.addvalue.AddItemDialog;
import com.vhra.dashfi.ui.addvalue.AddValuePresenter;
import com.vhra.dashfi.ui.dashboard.DashboardFragment;
import com.vhra.dashfi.ui.dashboard.DashboardPresenter;
import com.vhra.dashfi.ui.values.ValuesFragment;
import com.vhra.dashfi.ui.values.ValuesPresenter;
import com.vhra.dashfi.utils.Callback;
import com.vhra.dashfi.utils.ILog;
import com.vhra.dashfi.utils.LogUtils;

import static com.vhra.dashfi.DashFiApplication.getCardsRepository;
import static com.vhra.dashfi.DashFiApplication.getLog;
import static com.vhra.dashfi.DashFiApplication.getUseCaseHandler;
import static com.vhra.dashfi.DashFiApplication.getValuesRepository;
import static com.vhra.dashfi.utils.FragmentUtils.isFragmentInflated;
import static com.vhra.dashfi.utils.FragmentUtils.popBackStackTo;
import static com.vhra.dashfi.utils.FragmentUtils.replaceFragment;

public class HomeActivity extends AppCompatActivity implements
        HomePresenter.View, NavigationView.OnNavigationItemSelectedListener {
    public interface OpenAddValueView {
        void open(ValueDetail valueDetail);
    }

    private static final String TAG = "HomeActivity";

    private ILog mLog;
    private UseCaseHandler mUseCaseHandler;
    private SaveValueUseCase mSaveValueUseCase;
    private GetValuesUseCase mGetValuesUseCase;

    private HomePresenter mPresenter;

    private DrawerLayout mDrawer;
    private AddItemDialog mAddValueDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLog = new LogUtils();
        mUseCaseHandler = getUseCaseHandler(this);
        initializeLateralNavigation();

        new HomePresenter(this);

        mPresenter.init();
    }

    @Override
    public void onBackPressed() {
        // This screen should have one Fragment to show a content if there is not one should be
        // finished the activity.
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            finish();
        } else {
            super.onBackPressed();
        }
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

    // NavigationView.OnNavigationItemSelectedListener implementation

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.nav_add_value:
                mPresenter.onAddValueOptionClick();
                break;
            case R.id.nav_all_values:
                mPresenter.onAllValuesOptionClick();
                break;
            case R.id.nav_dashboard:
                mPresenter.onDefaultDashboardOptionClick();
        }
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // HomePresenter.View implementations

    @Override
    public void setPresenter(HomePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showAddValueDialog(final ValueDetail valueDetail) {
        if (mAddValueDialog == null) {
            mAddValueDialog = createAddItemDialog(valueDetail, (Void) -> mAddValueDialog = null);
        }

        if (!mAddValueDialog.isShowing()) {
            mAddValueDialog.show();
        }
    }

    @Override
    public void showDefaultDashBoard() {
        if (isFragmentInflated(this, DashboardFragment.TAG)) {
            mLog.d(TAG, "DashboardFragment already created");
            popBackStackTo(this, DashboardFragment.TAG);
            return;
        }

        replaceFragment(
                this, createDashBoardFragment(), DashboardFragment.TAG, DashboardFragment.TAG);
    }

    @Override
    public void showAllValues() {
        if (isFragmentInflated(this, ValuesFragment.TAG)) {
            Log.d(TAG, "ValuesFragment already created");
            return;
        }

        replaceFragment(this, createValuesFragment(), ValuesFragment.TAG, ValuesFragment.TAG);
    }

    private void initializeLateralNavigation() {
        mDrawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_options);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                mDrawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    // Dependencies --

    private ValuesFragment createValuesFragment() {
        ValuesFragment valuesFragment = new ValuesFragment();
        new ValuesPresenter(
                valuesFragment,
                mUseCaseHandler,
                getGetValuesUseCase(),
                valueDetail -> mPresenter.showValueUpdateDialog(valueDetail));
        return valuesFragment;
    }

    private DashboardFragment createDashBoardFragment() {
        DashboardFragment dashboardFragment = new DashboardFragment();
        new DashboardPresenter(
                dashboardFragment,
                mUseCaseHandler,
                new GetCardsUseCase(getCardsRepository(this), getLog()));
        return dashboardFragment;
    }

    private AddItemDialog createAddItemDialog(
            final ValueDetail valueDetail, final Callback<Void> callback) {
        AddItemDialog addItemDialog = new AddItemDialog(this);
        new AddValuePresenter(
                addItemDialog,
                valueDetail,
                mUseCaseHandler,
                getSaveValueUseCase());

        addItemDialog.setOnDismissListener(dialogInterface -> callback.onComplete(null));
        return addItemDialog;
    }

    private SaveValueUseCase getSaveValueUseCase() {
        if (mSaveValueUseCase == null) {
            mSaveValueUseCase = new SaveValueUseCase(getValuesRepository(this));
        }
        return mSaveValueUseCase;
    }

    private GetValuesUseCase getGetValuesUseCase() {
        if (mGetValuesUseCase == null) {
            mGetValuesUseCase = new GetValuesUseCase(getValuesRepository(this), mLog);
        }
        return mGetValuesUseCase;
    }
}
