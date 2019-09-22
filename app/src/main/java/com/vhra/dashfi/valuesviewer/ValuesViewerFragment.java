package com.vhra.dashfi.valuesviewer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vhra.dashfi.ui.addvalue.AddItemDialog;
import com.vhra.dashfi.R;
import com.vhra.dashfi.ValueDetail;
import com.vhra.dashfi.values.MockValuesDataSource;
import com.vhra.dashfi.values.ValuesRepository;

public class ValuesViewerFragment extends Fragment {
    private ValueItemAdpater mAdapter;

    private boolean isDialogOpened = false;
    private ValuesRepository mValuesRepository;

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

        mValuesRepository = ValuesRepository.getInstance();
        if (mValuesRepository == null) {
            mValuesRepository = ValuesRepository.createInstance(new MockValuesDataSource());
        }

        mValuesRepository.getAllValues(valueDetailList -> mAdapter.replaceData(valueDetailList));
    }

    private void initializeCardListView(RecyclerView recyclerView, Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        mAdapter = new ValueItemAdpater();
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(this::openAddItemDialog);
    }

    private void openAddItemDialog(ValueDetail valueDetail) {
        if (isDialogOpened || this.getContext() == null) return;

        AddItemDialog dialog = new AddItemDialog(this.getContext());
        dialog.updateValueDetail(valueDetail);
//        dialog.setAddItemCompleteListener(updatedValue -> {
//            isDialogOpened = false;
//            mValuesRepository.updateValue(updatedValue, data -> mAdapter.notifyDataSetChanged());
//        });
        dialog.setOnDismissListener(dialogInterface -> isDialogOpened = false);
        dialog.show();
        isDialogOpened = true;
    }
}
