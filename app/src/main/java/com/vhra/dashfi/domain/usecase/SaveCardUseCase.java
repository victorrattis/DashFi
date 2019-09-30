package com.vhra.dashfi.domain.usecase;

import com.vhra.dashfi.domain.UseCase;
import com.vhra.dashfi.domain.model.CardDetail;
import com.vhra.dashfi.domain.model.ICardsRepository;
import com.vhra.dashfi.utils.Callback;

public class SaveCardUseCase implements UseCase<CardDetail, Integer> {

    private ICardsRepository mCardsRepository;

    public SaveCardUseCase(ICardsRepository cardsRepository) {
        mCardsRepository = cardsRepository;
    }

    @Override
    public void execute(CardDetail cardDetail, Callback<Integer> callback) {
        // TODO: Validate card detail info before saving it.

        mCardsRepository.saveCard(cardDetail, saved -> callback.onComplete(saved ? 0 : -1));
    }
}
