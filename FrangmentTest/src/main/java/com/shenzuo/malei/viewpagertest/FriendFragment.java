package com.shenzuo.malei.viewpagertest;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by MaLei on 2017/4/20.
 * Email:ml1995@mail.ustc.edu.cn
 */

public class FriendFragment extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_firend, container, false);
    }

}