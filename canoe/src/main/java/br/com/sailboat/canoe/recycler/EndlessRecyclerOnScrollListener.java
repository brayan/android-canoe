package br.com.sailboat.canoe.recycler;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    private int currentPage = 1;
    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = true; // True if we are still waiting for the last set of data to load.
    private int visibleThreshold = 50; // The minimum amount of items to have below your current scroll position before loading more.
    int firstVisibleItem, visibleItemCount, totalItemCount;


    private LinearLayoutManager manager;

    public EndlessRecyclerOnScrollListener(LinearLayoutManager manager) {
        this.manager = manager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        firstVisibleItem = manager.findFirstVisibleItemPosition();
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = manager.getItemCount();

//        Log.e("RECYCLER", "onScrolled() visibleItemCount: " + visibleItemCount + ", totalItemCount: " + totalItemCount + ", firstVisibleItem: " + firstVisibleItem);

        // TOTAL EXERCISES: 1000
        // TOTAL ITEM COUNT: 100


        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + visibleThreshold)) {
            // End has been reached

            // Do something
            currentPage++;

            onLoadMore(currentPage);

            loading = true;
        }
    }

    public abstract void onLoadMore(int current_page);

}
