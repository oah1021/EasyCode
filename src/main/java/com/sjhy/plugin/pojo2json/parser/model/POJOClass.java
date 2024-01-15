package com.sjhy.plugin.pojo2json.parser.model;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;

import java.util.List;
import java.util.Map;

public class POJOClass extends POJOObject {

    protected final PsiClass psiClass;

    protected POJOClass(PsiClass psiClass) {
        this.psiClass = psiClass;
    }

    public static POJOClass init(PsiClass psiClass) {
        var pojo = new POJOClass(psiClass);
        pojo.recursionLevel = 0;
        pojo.ignoreProperties = List.of();
        pojo.psiClassGenerics = Map.of();
        return pojo;
    }

    public static POJOClass init(POJOVariable pojoVariable) {
        var pojo = new POJOClass(pojoVariable.psiClass);
        pojo.recursionLevel = pojoVariable.recursionLevel;
        pojo.ignoreProperties = pojoVariable.ignoreProperties;
        pojo.psiClassGenerics = pojoVariable.psiClassGenerics;
        return pojo;
    }

    public POJOField toField(PsiField psiField) {
        return POJOField.init(psiField, this);
    }

    public PsiClass getPsiClass() {
        return psiClass;
    }
}
