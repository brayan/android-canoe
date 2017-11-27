package br.com.sailboat.canoe.view.about;

import android.os.Bundle;

import java.util.List;

import br.com.sailboat.canoe.base.BasePresenter;
import br.com.sailboat.canoe.recycler.RecyclerItem;

public class AboutPresenter extends BasePresenter<AboutPresenter.View> implements AboutAdapter.Callback {

    private AboutViewModel viewModel = new AboutViewModel();

    public AboutPresenter(AboutPresenter.View view) {
        super(view);
    }

    @Override
    public void extractExtrasFromArguments(Bundle arguments) {
        List<RecyclerItem> recyclerItems = (List<RecyclerItem>) arguments.getSerializable("RECYCLER_ITEMS");

        getRecyclerItems().clear();
        getRecyclerItems().addAll(recyclerItems);
    }

    @Override
    protected void postResume() {
        getView().updateList();
    }

    @Override
    public List<RecyclerItem> getRecyclerItems() {
        return viewModel.getRecyclerItems();
    }


    public interface View extends BasePresenter.View {
        void updateList();
    }


}
