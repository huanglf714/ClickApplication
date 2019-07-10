package com.huanglf.test16.repository;

import androidx.lifecycle.LiveData;

import com.huanglf.test16.repository.database.Note;

import java.util.List;

/**
 * Date: 2019/7/8
 * Author: huanglf
 * description:
 */
public interface INoteRepository {
    /**
     * 插入一条笔记
     */
    void insertNote(Note note);

    /**
     * 更新笔记
     *
     * @param note
     */
    void updateNote(Note note);

    /**
     * 删除单条笔记
     *
     * @param note
     */
    void deleteOneNote(Note note);

    /**
     * 批量删除笔记
     *
     * @param notes
     */
    void deleteNotes(Note[] notes);

    /**
     * 查询所有笔记
     *
     * @return
     */
    LiveData<List<Note>> loadAllNotes();

    /**
     * 查询收藏的笔记
     *
     * @return
     */
    LiveData<List<Note>> loadStartNotes();

    /**
     * 合并笔记
     *
     * @param notes
     */
    void combineNotes(Note[] notes);

    /**
     * 加星收藏
     *
     * @param note
     */
    void addStar(Note note);

    /**
     * 取消收藏
     *
     * @param note
     */
    void cancelStar(Note note);

}
