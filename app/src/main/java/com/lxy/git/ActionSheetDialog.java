package com.lxy.git;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.List;

/**
 * @author liuxinyu
 * @date 2018/7/8  下午7:14
 */
public class ActionSheetDialog extends DialogFragment {

    private static List<String> mList;
    private RecyclerView mRecyclerView;
    private ActionSheetAdapter mAdapter;
    private OnActionSheetItemClickListener itemClickListener;

    public ActionSheetDialog() {
    }

    public static ActionSheetDialog newInstance(List<String> list) {
        mList = list;
        return new ActionSheetDialog();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setWindowAnimations(R.style.popup_window_bottom_anim);
        getDialog().getWindow().setGravity(Gravity.BOTTOM);

        WindowManager.LayoutParams lp = getDialog().getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes(lp);


        View view = LayoutInflater.from(getContext()).inflate(R.layout.action_sheet_content_view, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mAdapter = new ActionSheetAdapter(mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                int position = vh.getAdapterPosition();
                String name = mList.get(position);
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(name);
                }
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder vh) {

            }
        });

    }

    public void setOnActionSheetClick(OnActionSheetItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public interface OnActionSheetItemClickListener {
        void onItemClick(String name);
    }
}
