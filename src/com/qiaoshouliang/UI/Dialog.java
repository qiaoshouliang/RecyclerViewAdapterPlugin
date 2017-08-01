package com.qiaoshouliang.UI;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.wm.WindowManager;
import com.qiaoshouliang.WriteCommand.CreateAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dialog extends JDialog {
    private final AnActionEvent e;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField fileName;
    private JTextField sourceName;



    public Dialog(AnActionEvent e) {

        this.e = e;
        setMinimumSize(new Dimension(303,176));
//        setLocationRelativeTo(null);
        setLocationRelativeTo(WindowManager.getInstance().getFrame(e.getProject()));

        setTitle("New RecyclerViewAdapter");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        new CreateAdapter(e,fileName.getText(),sourceName.getText()).execute();

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
