package br.com.sailboat.canoe.helper;

import android.os.AsyncTask;

public class AsyncHelper extends AsyncTask<Void, Void, Exception> {

    private AsyncHelper.Callback callback;

    public static AsyncTask execute(AsyncHelper.Callback callback) {
        return new AsyncHelper(callback).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private AsyncHelper(AsyncHelper.Callback callback) {
        this.callback = callback;
    }

    @Override
    protected Exception doInBackground(Void... voids) {
        if (!isCancelled()) {
            try {
                callback.doInBackground();
                return null;
            } catch (Exception e) {
                return e;
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Exception e) {
        if (!isCancelled()) {
            if (e == null) {
                callback.onSuccess();
            } else {
                callback.onFail(e);
            }
        }
    }

    public interface Callback {
        void doInBackground() throws Exception;
        void onSuccess();
        void onFail(Exception e);
    }

}
