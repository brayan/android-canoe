package br.com.sailboat.canoe.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import br.com.sailboat.canoe.dialog.ErrorDialog;
import br.com.sailboat.canoe.dialog.MessageDialog;
import br.com.sailboat.canoe.dialog.ProgressDialog;
import br.com.sailboat.canoe.helper.LogHelper;
import br.com.sailboat.canoe.helper.UIHelper;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BasePresenter.View {

    private T presenter;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        setPresenter(newPresenterInstance());
        restoreViewModel(savedInstanceState);
        extractExtrasFromIntent(getActivity().getIntent());
        extractExtrasFromArguments();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initViews(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (resultCode) {
            case Activity.RESULT_OK: {
                onActivityResultOk(requestCode, data);
                break;
            }
            case Activity.RESULT_CANCELED: {
                onActivityResultCanceled(requestCode, data);
                break;
            }
        }

        postActivityResult(requestCode, data);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getPresenter().onSaveViewModelState(outState);
    }

    @Override
    public void closeKeyboard() {
        UIHelper.hideKeyboard(getActivity());
    }

    @Override
    public void setActivityToHideKeyboard() {
        UIHelper.setActivityToHideKeyboard(getActivity());
    }

    @Override
    public void showMessageDialog(String message) {
        MessageDialog.showMessage(getFragmentManager(), message, null);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void closeActivityWithResultOk() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void closeActivityWithResultOk(Intent intent) {
        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }

    @Override
    public void closeActivityWithResultCanceled() {
        getActivity().setResult(Activity.RESULT_CANCELED);
        getActivity().finish();
    }

    @Override
    public void printLogAndShowDialog(Exception e) {
        if (getActivity() != null) {
            LogHelper.logException(e);
            ErrorDialog.showDialog(getFragmentManager(), getActivity(), e);
        }
    }

    @Override
    public void showProgress() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog();
            progressDialog.show(getFragmentManager(), "DIALOG_PROGRESS");
        }
    }

    @Override
    public void dismissProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    private void restoreViewModel(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            getPresenter().restoreViewModel(savedInstanceState);
        }
    }

    private void extractExtrasFromIntent(Intent intent) {
        if (intent != null) {
            getPresenter().extractExtrasFromIntent(intent);
        }
    }

    private void extractExtrasFromArguments() {
        if (getArguments() != null && !getArguments().isEmpty()) {
            getPresenter().extractExtrasFromArguments(getArguments());
        }
    }

    public T getPresenter() {
        return presenter;
    }

    public void setPresenter(T presenter) {
        this.presenter = presenter;
    }

    protected abstract T newPresenterInstance();

    protected abstract int getLayoutId();

    protected abstract void initViews(View view);

    protected void onActivityResultOk(int requestCode, Intent data) {
    }

    protected void onActivityResultCanceled(int requestCode, Intent data) {
    }

    protected void postActivityResult(int requestCode, Intent data) {
    }

}
