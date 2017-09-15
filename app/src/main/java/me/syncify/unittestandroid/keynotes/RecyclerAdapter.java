package me.syncify.unittestandroid.keynotes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;

import java.util.List;

import me.syncify.unittestandroid.R;

/**
 * Created by shaishavsarawat on 14/09/17 at 3:16 PM
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.KeyNoteViewHolder> {

    private List<KeyNote> keyNotes;
    private Context context;

    public RecyclerAdapter(Context context, List<KeyNote> keyNotes) {
        this.context = context;
        this.keyNotes = keyNotes;
    }

    @Override
    public KeyNoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new KeyNoteViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(KeyNoteViewHolder holder, int position) {
        KeyNote keyNote = keyNotes.get(position);

        holder.titleTv.setText(keyNote.title);
        holder.descTv.setText(keyNote.desc);

        RxView.clicks(holder.layout).subscribe( o -> {
            Toast.makeText(context, "Title: " + keyNote.title, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return keyNotes.size();
    }

    class KeyNoteViewHolder extends RecyclerView.ViewHolder {

        TextView titleTv;
        TextView descTv;

        View layout;

        KeyNoteViewHolder(View itemView) {
            super(itemView);

            layout = itemView;
            titleTv = (TextView) itemView.findViewById(R.id.title);
            descTv = (TextView) itemView.findViewById(R.id.desc);
        }

    }
}
