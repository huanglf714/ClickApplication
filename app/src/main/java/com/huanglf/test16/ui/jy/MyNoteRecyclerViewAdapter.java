package com.huanglf.test16.ui.jy;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huanglf.test16.repository.database.Note;
import com.huanglf.test16.ui.jy.NoteFragment.OnListFragmentInteractionListener;
import com.huanglf.test16.R;
import com.huanglf.test16.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyNoteRecyclerViewAdapter extends RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder> {

    private List<Note> mValues;
    private final OnListFragmentInteractionListener mListener;
    private final String UN_FAVOR = "unfavor";
    private final String FAVOR = "favor";

    public MyNoteRecyclerViewAdapter(List<Note> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Note note = mValues.get(position);
        holder.mItem = note;
        holder.mNote.setText(note.getTitle());
//        设置收藏图标
        if (note.getStar()) {
            holder.mFavor.setImageResource(R.drawable.start_selected);
            holder.mFavor.setTag(FAVOR);
        } else {
            holder.mFavor.setImageResource(R.drawable.start);
            holder.mFavor.setTag(UN_FAVOR);
        }
//        添加分享响应事件
        holder.mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onShareListener(holder.mItem);
                }
            }
        });
        //添加收藏响应
        holder.mFavor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof ImageView) {
                    ImageView favorIcon = (ImageView) v;
                    if (favorIcon.getTag().equals(FAVOR)) {
                        favorIcon.setImageResource(R.drawable.start);
                        favorIcon.setTag(UN_FAVOR);
                        holder.mItem.setStar(false);
                    } else {
                        favorIcon.setImageResource(R.drawable.start_selected);
                        favorIcon.setTag(FAVOR);
                        holder.mItem.setStar(true);
                    }
                }
                if (null != mListener) {
                    mListener.onFavorListener(holder.mItem);
                }
            }
        });
//        刪除监听
        holder.mMenuDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("JY", "onClick: " + position + "---------" + holder.getAdapterPosition());
                mValues.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                if (null != mListener) {
                    mListener.onDeleteListener(holder.mItem);
                }
            }
        });
        //进入编辑页面
        holder.mContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onNoteListListener(v, holder.mItem);
                }
            }
        });
    }


    public void setmValues(List<Note> notes) {
        this.mValues = notes;
    }

    public void notifyData() {
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final LinearLayout mContent;
        public final TextView mNote;
        public final ImageView mShare;
        public final ImageView mFavor;
        public final View mSeparator;
        public final TextView mMenuDelete;
        public Note mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContent = view.findViewById(R.id.content);
            mNote = view.findViewById(R.id.note);
            mShare = view.findViewById(R.id.share);
            mFavor = view.findViewById(R.id.favor);
            mSeparator = view.findViewById(R.id.separator);
            mMenuDelete = view.findViewById(R.id.menu_delete);
        }


        public float getActionWidth() {
            return mMenuDelete.getWidth();
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNote.getText() + "'";
        }
    }
}
