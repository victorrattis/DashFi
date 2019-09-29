package com.vhra.dashfi.ui.dashboard.card;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vhra.dashfi.DashFiApplication;
import com.vhra.dashfi.domain.UseCaseHandler;
import com.vhra.dashfi.domain.model.CardDetail;
import com.vhra.dashfi.domain.usecase.SumValuesForLabelsUseCase;
import com.vhra.dashfi.ui.dashboard.card.list.ListItemCardPresenter;
import com.vhra.dashfi.ui.dashboard.card.list.ListItemCardView;
import com.vhra.dashfi.ui.dashboard.card.simple.CardSimplePresenter;
import com.vhra.dashfi.ui.dashboard.card.simple.CardSimpleView;
import com.vhra.dashfi.values.ValuesRepository;

import java.util.List;

import static com.vhra.dashfi.DashFiApplication.getDashFiApplication;
import static com.vhra.dashfi.DashFiApplication.getLog;
import static com.vhra.dashfi.DashFiApplication.getUseCaseHandler;
import static com.vhra.dashfi.DashFiApplication.getValuesRepository;

public class CardsAdapter extends RecyclerView.Adapter<CardView> {
    private List<? extends CardDetail> mCards;
    private ValuesRepository mValuesRepository;

    public CardsAdapter(final ValuesRepository valuesRepository) {
        mValuesRepository = valuesRepository;
    }

    @Override
    public int getItemViewType(int position) {
        CardDetail cardView = getItem(position);
        return cardView != null ? cardView.getType() : 0;
    }

    @NonNull
    @Override
    public CardView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // TODO: Factory of Cards
        if (viewType == 1) {
            ListItemCardView listItemCardView = ListItemCardView.create(parent);
            new ListItemCardPresenter(listItemCardView, mValuesRepository);
            return listItemCardView;
        }

        // Default card view
        CardSimpleView cardSimpleView = CardSimpleView.create(parent);
        new CardSimplePresenter(
                cardSimpleView,
                getUseCaseHandler(parent.getContext()),
                new SumValuesForLabelsUseCase(getValuesRepository(parent.getContext()), getLog()));
        return cardSimpleView;
    }

    @Override
    public void onBindViewHolder(@NonNull CardView holder, int position) {
        holder.updateValues(getItem(position));
    }

    @Override
    public int getItemCount() {
        return mCards != null ? mCards.size() : 0;
    }

    public void replaceData(List<? extends CardDetail> cardDetailList) {
        mCards = cardDetailList;
        notifyDataSetChanged();
    }

    private CardDetail getItem(int position) {
        return mCards != null ? mCards.get(position) : null;
    }
}
