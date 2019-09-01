package com.vhra.dashfi.dashboard;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vhra.dashfi.CardDetail;
import com.vhra.dashfi.R;

import java.util.List;

public class ListItemCardView extends CardView implements ListItemCardPresenter.View {
    private TextView mTitleText;
    private TextView mValueText;
    private RecyclerView mItemListView;

    private ListItemCardPresenter mPresenter;
    private ListItemCardAdpater mListItemCardAdpater;

    private ListItemCardView(@NonNull View itemView) {
        super(itemView);

        mTitleText = itemView.findViewById(R.id.text_title);
        mValueText = itemView.findViewById(R.id.text_value);
        mItemListView = itemView.findViewById(R.id.list_view_items);

        initializeItemListView();
    }

    static ListItemCardView create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_item_card, parent, false);
        return new ListItemCardView(view);
    }

    @Override
    public void updateValues(CardDetail cardDetail) {
        if (cardDetail == null) {
            return;
        }

        if (mPresenter != null) mPresenter.init(cardDetail);

        mTitleText.setOnClickListener((view) -> {
            if (mPresenter != null) mPresenter.onTitleViewClick();
        });

        mValueText.setOnClickListener((view) -> {
            if (mPresenter != null) mPresenter.onValueViewClick();
        });
    }

    @Override
    public void setPresenter(ListItemCardPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showTitle(String title) {
        mTitleText.setText(title);
    }

    @Override
    public void showValue(String value) {
        mValueText.setVisibility(View.VISIBLE);
        mValueText.setText(value);
    }

    @Override
    public void hideValue() {
        mValueText.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showItems(List<CardItem> items) {
        mListItemCardAdpater.replaceData(items);
    }

    private void initializeItemListView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mItemListView.setLayoutManager(linearLayoutManager);

        mListItemCardAdpater = new ListItemCardAdpater();
        mItemListView.setAdapter(mListItemCardAdpater);

        mItemListView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(
                    @NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    recyclerView.getParent().requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });
    }
}

