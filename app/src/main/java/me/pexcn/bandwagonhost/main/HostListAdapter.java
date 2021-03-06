/*
 * BandwagonHost - A bandwagonhost.com client for Android
 * Copyright (C) 2016 Xingyu Chen <pexcn97@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package me.pexcn.bandwagonhost.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.data.local.entity.Host;

/**
 * Created by pexcn on 2016-07-01.
 */
@SuppressWarnings("WeakerAccess")
public class HostListAdapter extends RecyclerView.Adapter<HostListAdapter.ViewHolder> {
    private List<Host> mHosts;

    public HostListAdapter(List<Host> hosts) {
        this.mHosts = hosts;
        this.setHasStableIds(true);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(v -> mOnClick.onItemClick(v, position));
        holder.itemView.setOnLongClickListener(v -> {
            mOnLongClick.onItemLongClick(v, position);
            return true;
        });
        holder.mTitle.setText(mHosts.get(position).title);
        holder.mVeid.setText(holder.mVeid.getContext().getString(R.string.item_text_veid, mHosts.get(position).veid));
    }

    @Override
    public long getItemId(int position) {
        return mHosts.get(position).id;
    }

    @Override
    public int getItemCount() {
        return mHosts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mVeid;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mVeid = (TextView) itemView.findViewById(R.id.tv_veid);
        }
    }

    private OnItemClickListener mOnClick;
    private OnItemLongClickListener mOnLongClick;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnClick = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mOnLongClick = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }
}
