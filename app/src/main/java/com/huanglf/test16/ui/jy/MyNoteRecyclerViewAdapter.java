package com.huanglf.test16.ui.jy;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huanglf.test16.repository.Note;
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

    private final List<Note> mValues;
    private final OnListFragmentInteractionListener mListener;

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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Note note = mValues.get(position);
        holder.mItem = note;
        holder.mNote.setText("你好啊");
//        设置收藏图标
        holder.mFavor.setImageResource(R.drawable.start);
        holder.mFavor.setTag("unfavor");
//        添加分享响应事件
        holder.mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("JY", "onClick: ----------------------------share");
            }
        });
        //添加收藏响应
        holder.mFavor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof ImageView){
                    ImageView favorIcon = (ImageView)v;
                    if(favorIcon.getTag().equals("favor")){
                        favorIcon.setImageResource(R.drawable.start);
                        favorIcon.setTag("unfavor");
                    }else{
                        favorIcon.setImageResource(R.drawable.start_selected);
                        favorIcon.setTag("favor");
                    }
                }
                Log.d("JY", "onClick: -------------------------favor");
            }
        });
        //进入编辑页面
        holder.mContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
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


        public float getActionWidth(){
            return mMenuDelete.getWidth();
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNote.getText() + "'";
        }
    }
}
