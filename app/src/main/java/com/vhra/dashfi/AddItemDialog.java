package com.vhra.dashfi;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class AddItemDialog extends Dialog {

    public interface OnAddItemCompleteListener {
        void onAddItemComplete(ValueDetail valueDetail);
    }

    private OnAddItemCompleteListener mOnAddItemCompleteListener;

    private ValueDetail mValueDetail;

    public AddItemDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_item);

        EditText titleEditText = this.findViewById(R.id.edit_title);
        EditText valueEditText = this.findViewById(R.id.edit_value);
        EditText labelsEditText = this.findViewById(R.id.edit_labels);

        if (mValueDetail != null) {
            titleEditText.setText(mValueDetail.getTitle());
            valueEditText.setText(String.valueOf(mValueDetail.getValue()));
            labelsEditText.setText(TextUtils.join(",", mValueDetail.getLabels()));
        }

        titleEditText.requestFocus();

        Button button = this.findViewById(R.id.button_add_item);
        button.setOnClickListener(view -> {
            ValueDetailImpl valueDetail = new ValueDetailImpl(
                    0,
                    titleEditText.getText().toString(),
                    valueEditText.getText().toString(),
                    labelsEditText.getText().toString());
            onAddItemClick(valueDetail);
            this.dismiss();
        });
    }

    public void updateValueDetail(ValueDetail valueDetail) {
        mValueDetail = valueDetail;
    }

    public void setAddItemCompleteListener(OnAddItemCompleteListener listener) {
        mOnAddItemCompleteListener = listener;
    }

    private void onAddItemClick(ValueDetail valueDetail) {
        if (mOnAddItemCompleteListener != null) {
            mOnAddItemCompleteListener.onAddItemComplete(valueDetail);
        }
    }
}
