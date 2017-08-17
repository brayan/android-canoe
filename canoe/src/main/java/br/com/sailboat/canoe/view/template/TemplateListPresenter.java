package br.com.sailboat.canoe.view.template;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.base.BasePresenter;
import br.com.sailboat.canoe.helper.AsyncHelper;
import br.com.sailboat.canoe.recycler.RecyclerItem;

public class TemplateListPresenter extends BasePresenter<TemplateListPresenter.View> {

    private TemplateListViewModel viewModel = new TemplateListViewModel();

    public TemplateListPresenter(TemplateListPresenter.View view) {
        super(view);
    }

    @Override
    protected void onResumeFirstSession() {
        loadContent();
    }

    @Override
    protected void onResumeAfterRestart() {
        updateContentViews();
    }

    public void onClickItemList(int position) {
        RecyclerItem item = (RecyclerItem) getList().get(position);
        view.startDetailsActivity(item);
    }

    public void onClickFab() {
        view.startInsertActivity();
    }

    public void postActivityResult() {
        loadContent();
    }

    public List<RecyclerItem> getList() {
        return viewModel.getList();
    }

    public void onClickMenuAbout() {
        view.startAboutActivity();
    }

    private void loadContent() {
        AsyncHelper.execute(new AsyncHelper.Callback() {

            List<RecyclerItem> list = new ArrayList<>();

            @Override
            public void doInBackground() throws Exception {
                list = new ArrayList<>();
            }

            @Override
            public void onSuccess() {
                getList().clear();
                getList().addAll(list);
                updateContentViews();
            }

            @Override
            public void onFail(Exception e) {
                printLogAndShowDialog(e);
                updateContentViews();
            }

        });
    }

    private void updateContentViews() {
        view.updateList();
        updateListVisibility();
    }

    private void updateListVisibility() {
        if (getList().isEmpty()) {
            view.hideList();
            view.showEmptyList();
            view.expandToolbar();
        } else {
            view.showList();
            view.hideEmptyList();
        }
    }


    public interface View extends BasePresenter.View {
        void updateList();
        void hideList();
        void showEmptyList();
        void showList();
        void hideEmptyList();
        void startInsertActivity();
        void startDetailsActivity(RecyclerItem taskId);
        void setTitle(String title);
        void expandToolbar();
        void startAboutActivity();
    }


}
