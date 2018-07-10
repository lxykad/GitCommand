package com.lxy.git;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import java.util.List;

/**
 * @author liuxinyu
 * @date 2018/7/8  下午7:14
 */
public class ActionSheetDialog extends BaseDialogFragment {

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
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.action_sheet_content_view;
    }

    @Override
    protected void init(View view) {
        getDialog().getWindow().setWindowAnimations(R.style.popup_window_bottom_anim);
        getDialog().getWindow().setGravity(Gravity.BOTTOM);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mAdapter = new ActionSheetAdapter(mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);

        if (mList.size() > 5) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.height = 600;
            mRecyclerView.setLayoutParams(params);
        }

        mRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                int position = vh.getAdapterPosition();
                String name = mList.get(position);
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(name);
                }
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
