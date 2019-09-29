package com.vhra.dashfi.ui.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vhra.dashfi.MessageEvent;
import com.vhra.dashfi.R;
import com.vhra.dashfi.domain.model.CardDetail;
import com.vhra.dashfi.ui.dashboard.card.CardsAdapter;
import com.vhra.dashfi.values.MockValuesDataSource;
import com.vhra.dashfi.values.ValuesRepository;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class DashboardFragment extends Fragment implements DashboardPresenter.View {
    public static final String TAG = "DashboardFragment";

    private View mLoadingView;
    private TextView mWarningMessageView;
    private CardsAdapter mCardsAdapter;

    private DashboardPresenter mPresenter;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLoadingView = view.findViewById(R.id.loading);
        mWarningMessageView = view.findViewById(R.id.text_warning_message);
        RecyclerView recyclerView = view.findViewById(R.id.cards);

        initializeListView(recyclerView);

        if (mPresenter != null) {
            mPresenter.init();
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setPresenter(DashboardPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showCards(List<? extends CardDetail> cards) {
        hideWarningMessage();

        if (mCardsAdapter != null) {
            mCardsAdapter.replaceData(cards);
        }
    }

    @Override
    public void showLoading() {
        if (mLoadingView != null) mLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        if (mLoadingView != null) mLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void showNoCards() {
        if (mWarningMessageView != null) {
            mWarningMessageView.setVisibility(View.VISIBLE);
            mWarningMessageView.setText(R.string.text_no_cards_message);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if ("UPDATE_DASHBOARD".equals(event.getAction())) {
            mCardsAdapter.notifyDataSetChanged();
        }
    }

    private void initializeListView(final RecyclerView recyclerView) {
        // TODO: Remote it
        ValuesRepository valuesRepository = ValuesRepository.createInstance(new MockValuesDataSource());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());

        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        mCardsAdapter = new CardsAdapter(valuesRepository);
        recyclerView.setAdapter(mCardsAdapter);
    }

    private void hideWarningMessage() {
        if (mWarningMessageView != null) mWarningMessageView.setVisibility(View.GONE);
    }
}
