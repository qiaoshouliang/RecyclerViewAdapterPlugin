package com.qiaoshouliang.Utils;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.IOException;

/**
 * Created by qiaoshouliang on 17/7/31.
 */
public class VirtualFileUtil {


    public static VirtualFile createFolderIfNotExist(Project project) throws IOException {

        String folder = "app/src/main/res/layout";

        VirtualFile directory = project.getBaseDir();
        String[] folders = folder.split("/");
        for (String childFolder : folders) {
            VirtualFile childDirectory = directory.findChild(childFolder);
            if (childDirectory != null && childDirectory.isDirectory()) {
                directory = childDirectory;
            } else {
                directory = directory.createChildDirectory(project, childFolder);
            }
        }
        return directory;
    }


    public static void createVirutalFile(Project project,String fileName, String code) throws IOException {


        VirtualFile directory = createFolderIfNotExist(project);
        VirtualFile virtualFile = directory.findChild(fileName);
        if (virtualFile == null) {
            virtualFile = directory.createChildData(project, fileName);
            virtualFile.setBinaryContent(code.getBytes());

        } else {

        }
    }


    public static VirtualFile setFileContent(VirtualFile createdFile, String code) {
        try {
            createdFile.setBinaryContent(code.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        openFileInEditor(project, createdFile);
        return createdFile;
    }
}
