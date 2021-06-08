package com.emmanuel.sampleclassifiedsapp.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.emmanuel.sampleclassifiedsapp.BaseActivity;
import com.emmanuel.sampleclassifiedsapp.R;
import com.emmanuel.sampleclassifiedsapp.R.id;
import com.emmanuel.sampleclassifiedsapp.utils.Constants;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.Target;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemDetailView extends BaseActivity implements OnClickListener {
    TextView itemTitle;
    TextView itemPrice;
    RelativeLayout rlAppbar;
    ImageView backBtn;
    Intent intent;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_item_detail_view);
        itemPrice = findViewById(id.itemPrice);
        itemTitle = findViewById(id.itemTitle);
        rlAppbar = findViewById(id.rlAppbar);
        backBtn = findViewById(id.backBtn);
        intent = getIntent();
        setUI();
        clickListeners();
    }

    void setUI() {
        itemTitle.setText(intent.getStringExtra(Constants.NAME));
        itemPrice.setText(intent.getStringExtra(Constants.PRICE));
        Picasso.get().load(intent.getStringExtra(Constants.IMAGE)).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, LoadedFrom from) {
                Drawable image = new BitmapDrawable(bitmap);
                rlAppbar.setBackground(image);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }

    void clickListeners() {
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == backBtn) {
            finishActivity();
        }
    }

    @Override
    public void getResponse(@NotNull String apiType, @NotNull JsonObject respopnse) {

    }
}