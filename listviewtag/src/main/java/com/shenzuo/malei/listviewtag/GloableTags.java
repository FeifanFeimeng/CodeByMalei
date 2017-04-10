package com.shenzuo.malei.listviewtag;

import java.util.ArrayList;

/**
 * Created by MaLei on 2017/4/10.
 * Email:ml1995@mail.ustc.edu.cn
 */

public class GloableTags {
    private static ArrayList<String> mCheckedTags = new ArrayList<>();

    public static ArrayList<String> getCheckedTags() {
        return mCheckedTags;
    }

    public static void  setCheckedTags(ArrayList<String> checkedTags) {
        mCheckedTags = checkedTags;
    }
}
