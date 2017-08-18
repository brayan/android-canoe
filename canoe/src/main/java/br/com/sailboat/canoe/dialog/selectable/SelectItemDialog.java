package br.com.sailboat.canoe.dialog.selectable;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import br.com.sailboat.canoe.R;
import br.com.sailboat.canoe.base.BaseDialogFragment;
import br.com.sailboat.canoe.helper.StringHelper;

public class SelectItemDialog extends BaseDialogFragment implements SelectableItemAdapter.Callback {

    private TextView tvTitle;
    private RecyclerView recycler;

    private List<SelectableItem> items;
    private SelectableItem selectedItem;

    private String title;

    private SelectItemDialog.Callback callback;


    public static void show(FragmentManager manager, String title, List<SelectableItem> items,
            SelectableItem selectedItem, SelectItemDialog.Callback callback) {
        SelectItemDialog dialog = new SelectItemDialog();
        dialog.callback = callback;
        dialog.items = items;
        dialog.selectedItem = selectedItem;
        dialog.title = title;
        dialog.show(manager, SelectItemDialog.class.getName());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.dlg_select_item, null);
        initViews(view);
        updateViews();
        return buildDialog(view);
    }

    @Override
    public void onClickItem(int position) {
        callback.onClickItem(getItems().get(position));
        dismiss();
    }

    private void initViews(View view) {
        tvTitle = (TextView) view.findViewById(R.id.dlg_select_item__tv__title);

        recycler = (RecyclerView) view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(new SelectableItemAdapter(this));
    }

    private void updateViews() {
        if (StringHelper.isNotEmpty(title)) {
            tvTitle.setText(title);
            tvTitle.setVisibility(View.VISIBLE);
        } else {
            tvTitle.setVisibility(View.GONE);
        }

        recycler.getAdapter().notifyDataSetChanged();
    }

    private Dialog buildDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);

        return builder.create();
    }

    @Override
    public SelectableItem getSelectedItem() {
        return selectedItem;
    }

    @Override
    public List<SelectableItem> getItems() {
        return this.items;
    }

    public interface Callback {
        void onClickItem(SelectableItem item);
    }
}
