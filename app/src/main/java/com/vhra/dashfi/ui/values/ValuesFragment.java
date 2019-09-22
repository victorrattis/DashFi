package com.vhra.dashfi.ui.values;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vhra.dashfi.R;
import com.vhra.dashfi.ValueDetail;
import com.vhra.dashfi.ui.addvalue.AddItemDialog;
import com.vhra.dashfi.ui.addvalue.AddValuePresenter;

import java.util.List;

public class ValuesFragment extends Fragment implements ValuesPresenter.View {
    private ValueItemAdpater mAdapter;

    private boolean isDialogOpened = false;
    private ValuesPresenter mPresenter;
    private View mLoadingView;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_values_viewer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.list_view_values);
        initializeCardListView(recyclerView, view.getContext());

        mLoadingView = view.findViewById(R.id.loading);
        if (mPresenter != null) mPresenter.init();
    }

    private void initializeCardListView(RecyclerView recyclerView, Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        mAdapter = new ValueItemAdpater();
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(valueDetail -> mPresenter.onUpdateValueDetail(valueDetail));
    }

    @Override
    public void setPresenter(ValuesPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showValues(List<? extends ValueDetail> values) {
        mAdapter.replaceData(values);
    }

    @Override
    public void showEmptyValues() {
        mAdapter.replaceData(null);
    }

    @Override
    public void showLoading() {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading() {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showErrorGettingValues() {
        Toast.makeText(
                this.getContext(),
                R.string.text_error_getting_values,
                Toast.LENGTH_LONG).show();
    }

    private void openAddItemDialog(ValueDetail valueDetail) {
        if (isDialogOpened || this.getContext() == null) return;

        // TODO: Implement it!
//        AddItemDialog dialog = new AddItemDialog(this.getContext());
//        dialog.updateValueDetail(valueDetail);
//        dialog.setOnDismissListener(dialogInterface -> isDialogOpened = false);
//        dialog.show();
//        isDialogOpened = true;
    }
}
