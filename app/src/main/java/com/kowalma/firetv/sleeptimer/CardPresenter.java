package com.kowalma.firetv.sleeptimer;

/**
 * Created by kowalma on 09.12.17.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.BaseCardView;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.net.URI;
import java.util.Timer;

public class CardPresenter extends Presenter {

    private static final String TAG = CardPresenter.class.getSimpleName();

    private static int CARD_WIDTH = 313;
    private static int CARD_HEIGHT = 176;

    static class ViewHolder extends Presenter.ViewHolder {
        private TimerCard timerCard;
        private ImageCardView mCardView;

        public ViewHolder(View view) {
            super(view);
            mCardView = (ImageCardView) view;
        }

        public void setTimerCard(TimerCard timerCard) {
            timerCard = timerCard;
        }

        public TimerCard getTimerCard() {
            return timerCard;
        }

        public ImageCardView getCardView() {
            return mCardView;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        Context context = parent.getContext();

        ImageCardView cardView = new ImageCardView(context);
        cardView.setCardType(BaseCardView.CARD_TYPE_INFO_UNDER);
        cardView.setInfoVisibility(BaseCardView.CARD_REGION_VISIBLE_ALWAYS);
        cardView.setFocusable(true);
        cardView.setFocusableInTouchMode(true);
        cardView.setBackgroundColor(context.getResources().getColor(R.color.default_background));
        cardView.setInfoAreaBackgroundColor(context.getResources().getColor(R.color.fastlane_background));
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        TimerCard timerCard= (TimerCard) item;
        ((ViewHolder) viewHolder).setTimerCard(timerCard);

        ((ViewHolder) viewHolder).mCardView.setTitleText(timerCard.getTitle());
        ((ViewHolder) viewHolder).mCardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT);
    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        // To Do
    }

    @Override
    public void onViewAttachedToWindow(Presenter.ViewHolder viewHolder) {
        // TO DO
    }
}