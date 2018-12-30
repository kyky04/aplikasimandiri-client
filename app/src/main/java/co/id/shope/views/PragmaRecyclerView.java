package co.id.shope.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import co.id.shope.adapters.EmptyAdapter;
import co.id.shope.adapters.ProgressAdapter;

public class PragmaRecyclerView extends RecyclerView {
    Context context;
    public PragmaRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public PragmaRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    public void vertical(){
        this.setLayoutManager(new LinearLayoutManager(context));
    }

    public void showEmpty() {
        EmptyAdapter adapter = new EmptyAdapter(context);
        this.setAdapter(adapter);
    }

    public void showProgress() {
        ProgressAdapter adapter = new ProgressAdapter(context);
        this.setAdapter(adapter);
    }

    public void showProgress(int type) {
        ProgressAdapter adapter = new ProgressAdapter(context,type);
        this.setAdapter(adapter);
    }
}
