package com.qiaoshouliang.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.qiaoshouliang.WriteCommand.CreateAdapter;

/**
 * Created by qiaoshouliang on 17/7/28.
 */
public class CreateAdapterAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        new CreateAdapter(e).execute();
    }
}
