package com.vhra.dashfi.ui.addvalue;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vhra.dashfi.R;

public class AddItemDialog extends Dialog implements AddValuePresenter.View {
    private AddValuePresenter mPresenter;

    private EditText mTitleEditText;
    private EditText mValueEditText;
    private EditText mLabelsEditText;

    private Button mButton;

    public AddItemDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_item);

        mTitleEditText = this.findViewById(R.id.edit_title);
        mValueEditText = this.findViewById(R.id.edit_value);
        mLabelsEditText = this.findViewById(R.id.edit_labels);

        mButton = this.findViewById(R.id.button_add_item);
        if (mButton != null)
            mButton.setOnClickListener(view ->
                mPresenter.onSaveValueClick(
                        mTitleEditText.getText().toString(),
                        mValueEditText.getText().toString(),
                        mLabelsEditText.getText().toString()));

        mPresenter.init();
        mTitleEditText.requestFocus();
    }

    @Override
    public void setPresenter(AddValuePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void dismissView() {
        this.dismiss();
    }

    @Override
    public void showValueAdded() {
        Toast.makeText(
                this.getContext(),
                R.string.text_value_added_successfully,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAddValueError() {
        Toast.makeText(
                this.getContext(),
                R.string.text_error_during_to_add_value,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void showTitle(String title) {
        if (mTitleEditText != null) {
            mTitleEditText.setText(title);
        }
    }

    @Override
    public void showValue(String value) {
        if (mValueEditText != null) {
            mValueEditText.setText(value);
        }
    }

    @Override
    public void showLabels(String labels) {
        if (mLabelsEditText != null) {
            mLabelsEditText.setText(labels);
        }
    }

    @Override
    public void showAddValueButton() {
        mButton.setText(R.string.text_add_value_title);
    }

    @Override
    public void showSaveValueButton() {
        mButton.setText(R.string.text_save_value_title);
    }
}
