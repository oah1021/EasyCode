package com.sjhy.plugin.tool;

import com.google.common.collect.Lists;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiModifier;

import java.util.Arrays;
import java.util.List;

public class ClassUtils {

    /**
     * 解析类
     */
    public static List<PsiClass> resolvePsiClassByFile(List<PsiJavaFile> psiJavaFiles) {
        List<PsiClass> psiClassList = Lists.newArrayListWithCapacity(psiJavaFiles.size());
        for (PsiJavaFile psiJavaFile : psiJavaFiles) {
            Arrays.stream(psiJavaFile.getClasses())
                    .filter(o -> o.getModifierList() != null && o.getModifierList().hasModifierProperty(PsiModifier.PUBLIC))
                    .findFirst().ifPresent(psiClassList::add);
        }
        return psiClassList;
    }
}
