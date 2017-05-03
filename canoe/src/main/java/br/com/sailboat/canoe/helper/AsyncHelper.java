package br.com.sailboat.canoe.helper;

import android.os.AsyncTask;

public class AsyncHelper extends AsyncTask<Void, Void, Exception> {

    private AsyncHelper.Callback callback;

    public static void execute(AsyncHelper.Callback callback) {
        new AsyncHelper(callback).execute();
    }

    private AsyncHelper(AsyncHelper.Callback callback) {
        this.callback = callback;
    }

    @Override
    protected Exception doInBackground(Void... voids) {
        try {
            callback.doInBackground();
            return null;
        } catch (Exception e) {
            return e;
        }
    }

    @Override
    protected void onPostExecute(Exception e) {
        if (e == null) {
            callback.onSuccess();
        } else {
            callback.onFail(e);
        }
    }

    public interface Callback {
        void doInBackground() throws Exception;
        void onSuccess();
        void onFail(Exception e);
    }

}
