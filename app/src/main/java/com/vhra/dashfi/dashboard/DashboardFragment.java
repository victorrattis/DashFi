package com.vhra.dashfi.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vhra.dashfi.MessageEvent;
import com.vhra.dashfi.R;
import com.vhra.dashfi.cards.CardsRepository;
import com.vhra.dashfi.cards.MockCardsDataSource2;
import com.vhra.dashfi.values.MockValuesDataSource;
import com.vhra.dashfi.values.ValuesRepository;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DashboardFragment extends Fragment {
    public static final String TAG = "DashboardFragment";
    private CardsAdapter mCardsAdapter;

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

        // #1
//        ValuesRepository valuesRepository = ValuesRepository.createInstance(new MockValuesDataSource2());
//        CardsRepository cardsRepository = new CardsRepository(new MockCardsDataSource());

        // #2
        ValuesRepository valuesRepository = ValuesRepository.createInstance(new MockValuesDataSource());
        CardsRepository cardsRepository = new CardsRepository(new MockCardsDataSource2());


        RecyclerView recyclerView = view.findViewById(R.id.cards);
        initializeCardListView(recyclerView, getContext());

        mCardsAdapter.setValuesRepository(valuesRepository);

        mCardsAdapter.replaceData(cardsRepository.getCards());
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if ("UPDATE_DASHBOARD".equals(event.getAction())) {
            mCardsAdapter.notifyDataSetChanged();
        }
    }

    private void initializeCardListView(RecyclerView recyclerView, Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        mCardsAdapter = new CardsAdapter();
        recyclerView.setAdapter(mCardsAdapter);
    }
}
