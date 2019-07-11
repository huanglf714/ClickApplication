package com.huanglf.test16.ui;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.huanglf.test16.R;
import com.huanglf.test16.repository.database.Note;
import com.huanglf.test16.repository.database.Tag;
import com.huanglf.test16.ui.css.AddTagDialog;
import com.huanglf.test16.ui.css.TagFragment;
import com.huanglf.test16.ui.css.TagListViewModel;
import com.huanglf.test16.ui.jy.NoteFragment;
import com.huanglf.test16.ui.jy.NoteListViewModel;
import com.huanglf.test16.util.MessageUtil;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements
        NoteFragment.OnListFragmentInteractionListener, TagFragment.OnListFragmentInteractionListener,
        AddTagDialog.OnCenterItemClickListener {
    private NoteListViewModel noteListViewModel;
    private TagListViewModel tagListViewModel;
    private final String ARG_DATA = "note_data";
    private AddTagDialog addTagDialog;
    public static int[] colorList = {R.drawable.tag, R.drawable.tag1, R.drawable.tag2,
            R.drawable.tag3, R.drawable.tag4, R.drawable.tag5, R.drawable.tag6};
    //当前改变的标签的使用次数
    private static int currentNumber = 0;
    //当前改变的标签的id编号
    private static int currentId = 0;
    //当前选择的颜色的编号
    private static int colorNum = 0;

    private static List<ImageView> imageViewList = new ArrayList<>();

    private View itemView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QMUIStatusBarHelper.translucent(this);
        noteListViewModel = ViewModelProviders.of(this).get(NoteListViewModel.class);
        tagListViewModel = ViewModelProviders.of(this).get(TagListViewModel.class);

        //全局监听异常
        MessageUtil.getExceptionLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onNoteListListener(View v, Note note) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATA, note);
        Navigation.findNavController(v).navigate(R.id.toDetailFromMain, args);
    }

    @Override
    public void onShareListener(Note note) {

    }

    @Override
    public void onFavorListener(Note note) {
        noteListViewModel.setFavor(note);
    }

    @Override
    public void onDeleteListener(Note note) {
        noteListViewModel.removeItem(note);
    }

    @Override
    public void onListFragmentInteraction(Tag item) {
        //监听标签列表响应事件，修改标签
        DiyDialog4(item);
    }

    @Override
    public void ondelect(final Tag tag) {
        final AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(MainActivity.this);
        alterDiaglog.setTitle("删除标签");//文字
        alterDiaglog.setMessage("是否删除" + tag.getName() + "标签？");//提示消息
        //积极的选择
        alterDiaglog.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tagListViewModel.deleteTag(tag);
                Toast.makeText(MainActivity.this, "已删除", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        //消极的选择
        alterDiaglog.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "已取消删除", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        //显示
        alterDiaglog.show();
    }

    private void DiyDialog4(Tag item) {
        addTagDialog = new AddTagDialog(this, R.layout.fragment_dialog,
                new int[]{R.id.confirmAdd, R.id.cancleAdd, R.id.tagImage1, R.id.tagImage2,
                        R.id.tagImage3, R.id.tagImage4, R.id.tagImage5, R.id.tagImage6});
        addTagDialog.setOnCenterItemClickListener(this);
        //自己构建
        itemView = LayoutInflater.from(this).inflate(R.layout.fragment_dialog, null);
        //覆盖系统构建
        addTagDialog.setContentView(itemView);
        imageViewList.add((ImageView) itemView.findViewById(R.id.tick1));
        imageViewList.add((ImageView) itemView.findViewById(R.id.tick2));
        imageViewList.add((ImageView) itemView.findViewById(R.id.tick3));
        imageViewList.add((ImageView) itemView.findViewById(R.id.tick4));
        imageViewList.add((ImageView) itemView.findViewById(R.id.tick5));
        imageViewList.add((ImageView) itemView.findViewById(R.id.tick6));
        currentNumber = item.getNumber();
        currentId = item.getId();
        addTagDialog.show();
    }

    @Override
    public void OnCenterItemClick(AddTagDialog dialog, View view) {
        switch (view.getId()) {
            case R.id.confirmAdd:
                String name = ((EditText) itemView.findViewById(R.id.tagNameEdit)).getText().toString();
                int image = colorList[colorNum + 1];
                tagListViewModel.updateNewTag(currentId, name, image, currentNumber);
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "点击了修改标签", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cancleAdd:
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "您已取消修改标签", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tagImage1:
                Log.e("mylog", colorNum + "gggggggggggg");
                imageViewList.get(colorNum).setVisibility(View.INVISIBLE);
                imageViewList.get(0).setVisibility(View.VISIBLE);
                colorNum = 0;
                break;
            case R.id.tagImage2:
                Log.e("mylog", colorNum + "hhhhhhhhhhhh");
                imageViewList.get(colorNum).setVisibility(View.INVISIBLE);
                imageViewList.get(1).setVisibility(View.VISIBLE);
                colorNum = 1;
                break;
            case R.id.tagImage3:
                Log.e("mylog", colorNum + "kkkkkkkkkkkk");
                imageViewList.get(colorNum).setVisibility(View.INVISIBLE);
                imageViewList.get(2).setVisibility(View.VISIBLE);
                colorNum = 2;
                break;
            case R.id.tagImage4:
                Log.e("mylog", colorNum + "lllllllllllllll");
                imageViewList.get(colorNum).setVisibility(View.INVISIBLE);
                imageViewList.get(3).setVisibility(View.VISIBLE);
                colorNum = 3;
                break;
            case R.id.tagImage5:
                Log.e("mylog", colorNum + "tttttttttttttttt");
                imageViewList.get(colorNum).setVisibility(View.INVISIBLE);
                imageViewList.get(4).setVisibility(View.VISIBLE);
                colorNum = 4;
                break;
            case R.id.tagImage6:
                Log.e("mylog", colorNum + "eeeeeeeeeeeeeeeeee");
                imageViewList.get(colorNum).setVisibility(View.INVISIBLE);
                imageViewList.get(5).setVisibility(View.VISIBLE);
                colorNum = 5;
                break;
            default:
                break;
        }
    }
}
