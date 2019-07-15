package com.huanglf.test16.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.huanglf.test16.R;
import com.huanglf.test16.repository.database.Note;

import com.huanglf.test16.repository.database.Tag;
import com.huanglf.test16.ui.css.AddTagDialog;
import com.huanglf.test16.repository.impl.NoteRepositoryImpl;
import com.huanglf.test16.ui.css.TagFragment;
import com.huanglf.test16.ui.css.TagListViewModel;
import com.huanglf.test16.ui.jy.NoteFragment;
import com.huanglf.test16.ui.jy.NoteListViewModel;
import com.huanglf.test16.ui.ty.ChooseTagFragment;
import com.huanglf.test16.ui.ty.SaveViewModel;
import com.huanglf.test16.util.MessageUtil;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.onekeyshare.OnekeyShare;


public class MainActivity extends AppCompatActivity implements
        NoteFragment.OnListFragmentInteractionListener, TagFragment.OnListFragmentInteractionListener,
        AddTagDialog.OnCenterItemClickListener, ChooseTagFragment.OnListFragmentInteractionListener {
    private NoteListViewModel noteListViewModel;
    private TagListViewModel tagListViewModel;
    private SaveViewModel saveViewModel;
    private final String ARG_DATA = "note_data";
    private static AddTagDialog addTagDialog;
    public static int[] colorList = {R.drawable.tag, R.drawable.tag1, R.drawable.tag2,
            R.drawable.tag3, R.drawable.tag4, R.drawable.tag5, R.drawable.tag6};
    //当前改变的标签的使用次数
    private static int currentNumber = 0;
    //当前改变的标签的id编号
    private static int currentId = 0;
    //当前选择的颜色的编号
    private static int colorNum = 0;

    private static List<ImageView> imageViewList = new ArrayList<>();

    private static View itemView;
    private static EditText editText;
    NoteRepositoryImpl noteRepository = null;
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QMUIStatusBarHelper.translucent(this);
        noteListViewModel = ViewModelProviders.of(this).get(NoteListViewModel.class);
        saveViewModel = ViewModelProviders.of(this).get(SaveViewModel.class);
        tagListViewModel = ViewModelProviders.of(this).get(TagListViewModel.class);
        noteRepository = NoteRepositoryImpl.getInstance();
        //全局监听异常
        MessageUtil.getExceptionLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
        //全局监听剪切板
        final ClipboardManager clipboard = (ClipboardManager) getApplicationContext().getSystemService(getApplicationContext().CLIPBOARD_SERVICE);
        clipboard.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                ClipData data = clipboard.getPrimaryClip();
                ClipData.Item item = data.getItemAt(0);
                String content = item.getText().toString();
                Log.e("myLog", content + "--------------------");
                Note note = new Note();
                note.setTitle(content.substring(0, (content.length() < 8 ? content.length() - 1 : 7)));
                note.setContent(content);
                noteRepository.insertNote(note);
            }
        });
        thread = new Thread("Thread1") {
            boolean exit = false;
            public void run() {
                while (!exit) {
                    Tag tag1 = tagListViewModel.queryTag();
                    if (tag1 == null) {
                        Tag tag = new Tag(1, "未标签", 0, R.drawable.tag);
                        tagListViewModel.insertNewTag(tag);
                    }
                    exit = true;
                }
            }
        };
        thread.start();
    }

    @Override
    public void onNoteListListener(View v, Note note) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATA, note);
        Navigation.findNavController(v).navigate(R.id.toDetailFromMain, args);
    }

    @Override
    public void onShareListener(Note note) {
        showShare(note);
    }

    @Override
    public void onFavorListener(Note note) {
        noteListViewModel.setFavor(note);
    }

    @Override
    public void onDeleteListener(Note note) {
        noteListViewModel.removeItem(note);
    }

    private void showShare(Note note) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(getString(R.string.app_name));
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我的分享");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl("http://sharesdk.cn");
        // 启动分享GUI
        oks.show(this);
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
        if (addTagDialog == null) {
            addTagDialog = new AddTagDialog(this, R.layout.fragment_dialog,
                    new int[]{R.id.confirmAdd, R.id.cancleAdd, R.id.tagImage1, R.id.tagImage2,
                            R.id.tagImage3, R.id.tagImage4, R.id.tagImage5, R.id.tagImage6});
            addTagDialog.setOnCenterItemClickListener(this);
            //自己构建
            itemView = LayoutInflater.from(this).inflate(R.layout.fragment_dialog, null);
            //覆盖系统构建
            addTagDialog.setContentView(itemView);
            if (imageViewList.size() == 0) {
                imageViewList.add((ImageView) itemView.findViewById(R.id.tick1));
                imageViewList.add((ImageView) itemView.findViewById(R.id.tick2));
                imageViewList.add((ImageView) itemView.findViewById(R.id.tick3));
                imageViewList.add((ImageView) itemView.findViewById(R.id.tick4));
                imageViewList.add((ImageView) itemView.findViewById(R.id.tick5));
                imageViewList.add((ImageView) itemView.findViewById(R.id.tick6));
            }
            editText = itemView.findViewById(R.id.tagNameEdit);
        }
        editText.setText(item.getName());
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
                Toast.makeText(getApplicationContext(), "修改标签成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cancleAdd:
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "您已取消修改标签", Toast.LENGTH_SHORT).show();
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
