package com.jabirdeveloper.tinderswipe;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

public class CardStackCallback extends DiffUtil.Callback {

    private List<ItemModel> old, recent;

    public CardStackCallback(List<ItemModel> old, List<ItemModel> recent) {
        this.old = old;
        this.recent = recent;
    }

    @Override
    public int getOldListSize() {
        return old.size();
    }

    @Override
    public int getNewListSize() {
        return recent.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return old.get(oldItemPosition).getImage() == recent.get(newItemPosition).getImage();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return old.get(oldItemPosition) == recent.get(newItemPosition);
    }
}
