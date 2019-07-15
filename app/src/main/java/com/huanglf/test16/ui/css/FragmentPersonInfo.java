package com.huanglf.test16.ui.css;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.huanglf.test16.R;
import com.huanglf.test16.repository.impl.UserRepositoryImpl;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.QMUIWindowInsetLayout;


import java.util.ArrayList;
import java.util.List;

import static cn.bmob.v3.Bmob.getApplicationContext;
import static com.huanglf.test16.ClickApplication.sharedPreferences;
import static com.huanglf.test16.ui.MainActivity.colorList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPersonInfo extends Fragment implements AddTagDialog.OnCenterItemClickListener {
    QMUIWindowInsetLayout topBarView = null;
    QMUITopBarLayout personTopBar = null;
    TextView accountTv = null;
    TextView changePasswordTv = null;
    Button logout = null;
    ImageView addTagBtn = null;
    private TagListViewModel tagListViewModel;
    private static AddTagDialog addTagDialog;
    //当前选择的颜色的编号
    private static int colorNum = 0;
    private static List<ImageView> imageViewList = new ArrayList<>();
    private static View itemView;

    public FragmentPersonInfo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_person_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String accountStr = sharedPreferences.getString("account", "");
        accountTv = view.findViewById(R.id.accountTv);
        accountTv.setText(accountStr);
        logout = view.findViewById(R.id.logout);
        addTagBtn = view.findViewById(R.id.addTagBtn);
        topBarView = view.findViewById(R.id.person_top_bar);
        personTopBar = topBarView.findViewById(R.id.topBar);
        personTopBar.setTitle("个人信息");
        personTopBar.setBackgroundColor(Color.parseColor("#359BFF"));
        personTopBar.addLeftImageButton(R.drawable.person_back, R.id.right_new);
        topBarView.findViewById(R.id.right_new).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.toMainFromPerson);
            }
        });
        changePasswordTv = view.findViewById(R.id.changePasswordTv);
        changePasswordTv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ChangePasswordViewModel.setChangePasswordLiveData();
                Navigation.findNavController(getView()).navigate(R.id.action_fragmentPersonInfo_to_fragmentChangePassword2);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().remove("isLogin").commit();
                UserRepositoryImpl.getInstance().setLoginUserData(new MutableLiveData<String>());
                Navigation.findNavController(v).navigate(R.id.action_fragmentPersonInfo_to_fragmentLaunch);
            }
        });
        if (addTagDialog == null) {
            addTagDialog = new AddTagDialog(getActivity(), R.layout.fragment_dialog,
                    new int[]{R.id.confirmAdd, R.id.cancleAdd, R.id.tagImage1, R.id.tagImage2,
                            R.id.tagImage3, R.id.tagImage4, R.id.tagImage5, R.id.tagImage6});
            addTagDialog.setOnCenterItemClickListener(this);
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_dialog, null);
            //覆盖系统构建
            addTagDialog.setContentView(itemView);
        }
        if (imageViewList.size() == 0) {
            imageViewList.add((ImageView) itemView.findViewById(R.id.tick1));
            imageViewList.add((ImageView) itemView.findViewById(R.id.tick2));
            imageViewList.add((ImageView) itemView.findViewById(R.id.tick3));
            imageViewList.add((ImageView) itemView.findViewById(R.id.tick4));
            imageViewList.add((ImageView) itemView.findViewById(R.id.tick5));
            imageViewList.add((ImageView) itemView.findViewById(R.id.tick6));
        }
        addTagBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTagDialog.show();
            }
        });
        tagListViewModel = ViewModelProviders.of(this).get(TagListViewModel.class);
    }

    @Override
    public void OnCenterItemClick(AddTagDialog dialog, View view) {
        switch (view.getId()) {
            case R.id.confirmAdd:
                String name = ((EditText) itemView.findViewById(R.id.tagNameEdit)).getText().toString();
                int image = colorList[colorNum + 1];
                tagListViewModel.insertNewTag(name, image);
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "添加标签成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cancleAdd:
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "您已取消添加标签", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tagImage1:
                setImageVisibility(colorNum, 0);
                break;
            case R.id.tagImage2:
                setImageVisibility(colorNum, 1);
                break;
            case R.id.tagImage3:
                setImageVisibility(colorNum, 2);
                break;
            case R.id.tagImage4:
                setImageVisibility(colorNum, 3);
                break;
            case R.id.tagImage5:
                setImageVisibility(colorNum, 4);
                break;
            case R.id.tagImage6:
                setImageVisibility(colorNum, 5);
                break;
            default:
                break;
        }
    }

    private static void setImageVisibility(int oldNum, int newNum) {
        imageViewList.get(oldNum).setVisibility(View.INVISIBLE);
        imageViewList.get(newNum).setVisibility(View.VISIBLE);
        colorNum = newNum;
    }
}
