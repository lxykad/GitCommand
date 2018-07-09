package com.lxy.git;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxinyu
 * @date 2018/7/8  下午7:11
 */
public class MainActivity extends AppCompatActivity {

    private TestPresenter mPresenter;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        TestPresenterImpl impl = new TestPresenterImpl();
        mPresenter = impl;
        getLifecycle().addObserver(impl);

    }

    private void initData() {
        for (int i = 0; i < 3; i++) {
            mList.add("name: " + i);
        }
    }

    public void showActionSheet(final View view) {
        ActionSheetDialog dialog = ActionSheetDialog.newInstance(mList);
        dialog.show(getSupportFragmentManager(), "");
        dialog.setOnActionSheetClick(new ActionSheetDialog.OnActionSheetItemClickListener() {
            @Override
            public void onItemClick(String name) {
                Toast.makeText(view.getContext(),name,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
