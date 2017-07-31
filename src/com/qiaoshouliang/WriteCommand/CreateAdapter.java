package com.qiaoshouliang.WriteCommand;

import com.intellij.ide.IdeView;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtilBase;
import com.intellij.psi.xml.XmlFile;
import com.intellij.util.FileContentUtil;
import com.qiaoshouliang.Utils.EventLogger;
import com.qiaoshouliang.Utils.PsiClassUtil;
import com.qiaoshouliang.Utils.StringUtil;
import com.qiaoshouliang.Utils.VirtualFileUtil;

import java.util.HashMap;
import java.util.Map;

import static com.qiaoshouliang.Utils.CommonUtil.getPackageName;
import static com.qiaoshouliang.Utils.StringUtil.underlineToCamel;

/**
 * Created by qiaoshouliang on 17/7/28.
 */
public class CreateAdapter extends WriteCommandAction.Simple {
    private final PsiElementFactory factory;
    private final Project project;
    private final JavaDirectoryService directoryService;
    private final PsiDirectory directory;
    private final PsiFile file;
    private final String fileName;
    private final String sourceName;

    public CreateAdapter(AnActionEvent event, String fileName, String sourceName) {
        super(event.getProject());

        this.fileName = fileName;
        this.sourceName = sourceName;
        this.project = event.getProject();

        factory = JavaPsiFacade.getElementFactory(project);
        file = event.getData(LangDataKeys.PSI_FILE);
        directoryService = JavaDirectoryService.getInstance();
        IdeView ideView = event.getRequiredData(LangDataKeys.IDE_VIEW);
        directory = ideView.getOrChooseDirectory();

    }

    @Override
    protected void run() throws Throwable {
//        directoryService.createClass(directory.getParentDirectory(), "BasePresenter", "BasePresenter");

        String layout = "item" + StringUtil.camelToUnderline(fileName);

        String binding = underlineToCamel(layout) + "Binding";
        Map<String, String> map = new HashMap<>();

        map.put("DATA_SOURCE", sourceName);
        map.put("LAYOUT", layout);
        map.put("PACKAGE", getPackageName(project));
        map.put("BINDING", binding);

        if (directory.findFile(fileName + ".java") != null) {
            Messages.showErrorDialog("Generation failed, " +
                            fileName + " already exists",
                    "File already exists");
        }


        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<layout>\n" +
                "\n" +
                "    <LinearLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
                "        xmlns:tools=\"http://schemas.android.com/tools\"\n" +
                "        android:layout_width=\"wrap_content\"\n" +
                "        android:layout_height=\"wrap_content\"\n" +
                "        android:gravity=\"center_horizontal\"\n" +
                "        android:orientation=\"vertical\">\n" +
                "\n" +
                "    </LinearLayout>\n" +
                "</layout>\n";


        VirtualFile virtualFile = VirtualFileUtil.createFolderIfNotExist(project);
        VirtualFileUtil.createVirutalFile(project, layout+".xml", xml);

        PsiClass psiClass = directoryService.createClass(directory, fileName, "RecyclerViewAdapter", false, map);
        PsiElementFactory factory = JavaPsiFacade.getInstance(project).getElementFactory();
        PsiClass dateSourceClass = PsiClassUtil.getPsiClassByName(project, sourceName);

        PsiImportStatement importStatement = factory.createImportStatement(dateSourceClass);

        ((PsiJavaFile) psiClass.getContainingFile()).getImportList().add(importStatement);

        JavaCodeStyleManager styleManager = JavaCodeStyleManager.getInstance(project);
        styleManager.optimizeImports(psiClass.getContainingFile());
        styleManager.shortenClassReferences(psiClass);


    }
}
