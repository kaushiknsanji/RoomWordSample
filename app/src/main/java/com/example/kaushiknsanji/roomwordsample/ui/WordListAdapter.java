package com.example.kaushiknsanji.roomwordsample.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kaushiknsanji.roomwordsample.R;
import com.example.kaushiknsanji.roomwordsample.data.local.Word;

import java.util.List;

/**
 * RecyclerView Adapter class used by the {@link MainActivity}
 * to display and manage the list of {@link Word}
 *
 * @author Kaushik N Sanji
 */
public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private List<Word> mWords;

    public WordListAdapter() {
    }

    /**
     * Called when RecyclerView needs a new {@link WordViewHolder} of the given type to represent
     * an item.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(WordViewHolder, int)
     */
    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link WordViewHolder#itemView} to reflect the item at the given
     * position.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if (mWords != null) {
            holder.mWordTextView.setText(mWords.get(position).getWord());
        } else {
            holder.mWordTextView.setText("No Word");
        }
    }

    public void setWords(List<Word> words) {
        mWords = words;
        notifyDataSetChanged();
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        if (mWords != null) {
            return mWords.size();
        }
        return 0;
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView mWordTextView;

        WordViewHolder(View itemView) {
            super(itemView);

            mWordTextView = itemView.findViewById(R.id.textView);
        }
    }
}
