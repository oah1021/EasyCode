package com.sjhy.plugin.tool;

import com.google.common.collect.Lists;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiModifier;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 获取选择的一个类
     * @param event 事件
     * @return
     */
    public static PsiClass selectedClass(AnActionEvent event) {
        List<PsiClass> psiClassList = selectedClassList(event);
        if (CollectionUtil.isEmpty(psiClassList)) {
            throw new RuntimeException("没有获取到所选择的类");
        }
        if (psiClassList.size() > 1) {
            MessageDialogUtils.showMessageBox("提示", "仅支持操作一个实体类");
        }
        return psiClassList.get(0);
    }

    /**
     * 获取选择的类
     * @param event 事件
     * @return
     */
    public static List<PsiClass> selectedClassList(AnActionEvent event) {
        Project project = event.getProject();
        if (project == null) {
            return null;
        }
        // 过滤选择Java文件
        VirtualFile[] psiFiles = event.getData(CommonDataKeys.VIRTUAL_FILE_ARRAY);
        if (psiFiles == null) {
            return null;
        }
        PsiManager psiManager = PsiManager.getInstance(project);
        java.util.List<PsiJavaFile> psiJavaFiles = Arrays.stream(psiFiles)
                .map(psiManager::findFile)
                .filter(f -> f instanceof PsiJavaFile)
                .map(f -> (PsiJavaFile) f)
                .collect(Collectors.toList());
        if (psiJavaFiles.size() == 0) {
            return null;
        }

        // 获取选中的类
        List<PsiClass> psiClassList = ClassUtils.resolvePsiClassByFile(psiJavaFiles);
        if (psiClassList.size() == 0) {
            return null;
        }
        return psiClassList;
    }
}
