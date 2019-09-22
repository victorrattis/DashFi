package com.vhra.dashfi.ui.addvalue;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vhra.dashfi.R;
import com.vhra.dashfi.ValueDetail;

public class AddItemDialog extends Dialog implements AddValuePresenter.View {
    private ValueDetail mValueDetail;
    private AddValuePresenter mPresenter;

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

        // TODO: move to the presenter
        if (mValueDetail != null) {
            titleEditText.setText(mValueDetail.getTitle());
            valueEditText.setText(String.valueOf(mValueDetail.getValue()));
            labelsEditText.setText(TextUtils.join(",", mValueDetail.getLabels()));
        }

        Button button = this.findViewById(R.id.button_add_item);
        if (button != null)
            button.setOnClickListener(view ->
                mPresenter.onSaveValueClick(
                        titleEditText.getText().toString(),
                        valueEditText.getText().toString(),
                        labelsEditText.getText().toString()));

        mPresenter.init();
        titleEditText.requestFocus();
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

    public void updateValueDetail(ValueDetail valueDetail) {
        mValueDetail = valueDetail;
    }
}
