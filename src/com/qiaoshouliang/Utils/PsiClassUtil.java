package com.qiaoshouliang.Utils;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;

/**
 * Created by qiaoshouliang on 17/7/31.
 */
public class PsiClassUtil {

    public static PsiClass getPsiClassByName(Project project, String name) {

        GlobalSearchScope searchScope = GlobalSearchScope.allScope(project);

        PsiClass[] psiClasses = PsiShortNamesCache.getInstance(project).getClassesByName(name, searchScope);//NotNull
        PsiClass psiClass = null;
        if (psiClasses.length != 0) {//if the class already exist.
            psiClass = psiClasses[0];
        }//and
        return psiClass;
    }
}
