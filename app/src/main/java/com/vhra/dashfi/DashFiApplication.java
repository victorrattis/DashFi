package com.vhra.dashfi;

import android.app.Application;
import android.content.Context;

import com.vhra.dashfi.data.cards.CardsLocalDataSource;
import com.vhra.dashfi.data.cards.CardsRepository;
import com.vhra.dashfi.data.value.ValuesLocalDataSource;
import com.vhra.dashfi.data.value.ValuesRepository;
import com.vhra.dashfi.domain.UseCaseHandler;
import com.vhra.dashfi.domain.UseCaseSchedulerImpl;
import com.vhra.dashfi.domain.model.ICardsRepository;
import com.vhra.dashfi.domain.model.IValuesRepository;
import com.vhra.dashfi.utils.DiskIOThreadExecutor;
import com.vhra.dashfi.utils.ILog;
import com.vhra.dashfi.utils.LogUtils;

public class DashFiApplication extends Application {

    private ILog mLog;
    private UseCaseHandler mUseCaseHandler = null;
    private IValuesRepository mValuesRepository = null;
    private CardsRepository mCardsRepository = null;

    public static DashFiApplication getDashFiApplication(final Context context) {
        try {
            return (DashFiApplication) context.getApplicationContext();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static UseCaseHandler getUseCaseHandler(final Context context) {
        DashFiApplication application = getDashFiApplication(context);
        return application != null ? application.getUseCaseHandler() : null;
    }

    public static IValuesRepository getValuesRepository(final Context context) {
        DashFiApplication application = getDashFiApplication(context);
        return application != null ? application.getValuesRepository() : null;
    }

    public static ICardsRepository getCardsRepository(final Context context) {
        DashFiApplication application = getDashFiApplication(context);
        return application != null ? application.getCardsRepository() : null;
    }

    public static ILog getLog() {
        return new LogUtils();
    }

    private UseCaseHandler getUseCaseHandler() {
        if (mUseCaseHandler == null) {
            mUseCaseHandler = new UseCaseHandler(new UseCaseSchedulerImpl());
        }
        return mUseCaseHandler;
    }

    private IValuesRepository getValuesRepository() {
        if (mValuesRepository == null) {
            mValuesRepository = createValuesRepository();
        }
        return mValuesRepository;
    }

    private CardsRepository getCardsRepository() {
        if (mCardsRepository == null) {
            mCardsRepository = createGetCardsUseCase();
        }
        return mCardsRepository;
    }

    private IValuesRepository createValuesRepository() {
        DiskIOThreadExecutor diskIOThreadExecutor = new DiskIOThreadExecutor();
        ValuesLocalDataSource valuesLocalDataSource = new ValuesLocalDataSource(
                this, diskIOThreadExecutor, getLog());

        return new ValuesRepository(valuesLocalDataSource, getLog());
    }

    private CardsRepository createGetCardsUseCase() {
        return new CardsRepository(
                new CardsLocalDataSource(this, new DiskIOThreadExecutor(), getLog()));
    }
}
