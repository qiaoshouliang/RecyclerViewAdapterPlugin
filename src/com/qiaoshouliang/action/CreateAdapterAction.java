package com.qiaoshouliang.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.util.IconLoader;
import com.qiaoshouliang.UI.Dialog;

/**
 * Created by qiaoshouliang on 17/7/28.
 */
public class CreateAdapterAction extends AnAction {



//    public CreateAdapterAction() {
//        getTemplatePresentation().setIcon(IconLoader.getIcon("/icons/icon_tf.png"));
//    }
    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here

//        Presentation presentation = getTemplatePresentation();
//        presentation.setIcon(IconLoader.getIcon("icons/icon_a.png"));
        new Dialog(e).setVisible(true);

//        new CreateAdapter(e,"test","Home").execute();
    }
}
