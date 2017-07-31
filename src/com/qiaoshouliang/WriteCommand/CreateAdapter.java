package com.qiaoshouliang.WriteCommand;

import com.intellij.ide.IdeView;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.qiaoshouliang.Utils.PsiClassUtil;

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


    public CreateAdapter(AnActionEvent event) {
        super(event.getProject());

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
        String dataSource = "Home";
        String layout = "item_"+dataSource.toLowerCase();

        String binding = underlineToCamel(layout)+"Binding";

        Map<String,String> map = new HashMap<>();

        map.put("DATA_SOURCE",dataSource);
        map.put("LAYOUT",layout);
        map.put("PACKAGE",getPackageName(project));
        map.put("BINDING",binding);


        PsiClass psiClass = directoryService.createClass(directory.getParentDirectory(),"TextAdapter","RecyclerViewAdapter",false,map);

        PsiElementFactory factory = JavaPsiFacade.getInstance(project).getElementFactory();
        PsiClass dateSourceClass = PsiClassUtil.getPsiClassByName(project,dataSource);

        PsiImportStatement importStatement = factory.createImportStatement(dateSourceClass);

        ((PsiJavaFile) psiClass.getContainingFile()).getImportList().add(importStatement);

        JavaCodeStyleManager styleManager = JavaCodeStyleManager.getInstance(project);
        styleManager.optimizeImports(psiClass.getContainingFile());
        styleManager.shortenClassReferences(psiClass);

    }
}
