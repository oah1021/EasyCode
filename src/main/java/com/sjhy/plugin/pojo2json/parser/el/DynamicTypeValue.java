package com.sjhy.plugin.pojo2json.parser.el;

import com.intellij.psi.PsiElement;

public abstract class DynamicTypeValue implements TypeValue {

    protected final PsiElement psiElement;

    public DynamicTypeValue(PsiElement psiElement) {
        this.psiElement = psiElement;
    }
}
