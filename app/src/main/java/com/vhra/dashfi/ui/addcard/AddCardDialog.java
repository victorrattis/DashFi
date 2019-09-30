package com.vhra.dashfi.ui.addcard;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.vhra.dashfi.R;

public class AddCardDialog extends Dialog
        implements AddCardDialogPresenter.View, View.OnClickListener {

    private AddCardDialogPresenter mPresenter;

    private Button mButton;
    private EditText mTitleEditText;
    private EditText mValueEditText;

    public AddCardDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_card);

        mButton = findViewById(R.id.button_save);
        mTitleEditText = findViewById(R.id.edit_title);
        mValueEditText = findViewById(R.id.edit_value);

        mButton.setOnClickListener(this);

        if (mPresenter != null) mPresenter.init();
    }

    @Override
    public void setPresenter(AddCardDialogPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showTitle(String title) {
        if (mTitleEditText != null) mTitleEditText.setText(title);
    }

    @Override
    public void showValue(String value) {
        if (mValueEditText != null) mValueEditText.setText(value);
    }

    @Override
    public void showAddValueButton() {
        if (mButton != null) {
            mButton.setText(R.string.text_add_value_title);
        }
    }

    @Override
    public void showSaveValueButton() {
        if (mButton != null) {
            mButton.setText(R.string.text_save_value_title);
        }
    }

    @Override
    public void dismissView() {
        this.dismiss();
    }

    @Override
    public void onClick(View view) {
        if (view != null && view.getId() == R.id.button_save) {
            mPresenter.onSaveValueClick(
                    mTitleEditText.getText().toString(),
                    mValueEditText.getText().toString());
        }
    }
}
