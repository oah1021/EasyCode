package com.sjhy.plugin.pojo2json.parser.model;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiVariable;

import java.util.List;
import java.util.Map;

public class POJOVariable extends POJOObject {

    protected final PsiVariable psiVariable;

    protected PsiType psiType;

    protected PsiClass psiClass;

    protected final String srcName;

    protected String name;


    protected POJOVariable(PsiVariable psiVariable) {
        this.psiVariable = psiVariable;
        this.psiType = psiVariable.getType();
        this.srcName = psiVariable.getName();
        this.name = psiVariable.getName();
    }

    public static POJOVariable init(PsiVariable psiVariable,
                                    Map<String, PsiType> psiClassGenerics) {
        var pojo = new POJOVariable(psiVariable);
        pojo.recursionLevel = 0;
        pojo.ignoreProperties = List.of();
        pojo.psiClassGenerics = psiClassGenerics;
        return pojo;
    }


    public POJOVariable deepVariable(PsiType deepType, Map<String, PsiType> psiClassGenerics) {
        this.recursionLevel++;
        this.psiType = deepType;
        this.psiClassGenerics = psiClassGenerics;
        return this;
    }

    public POJOClass deepClass(PsiClass psiClass, Map<String, PsiType> psiClassGenerics) {
        this.recursionLevel++;
        this.psiClass = psiClass;
        this.psiClassGenerics = psiClassGenerics;
        return POJOClass.init(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public PsiVariable getPsiVariable() {
        return psiVariable;
    }

    public PsiType getPsiType() {
        return psiType;
    }

    public PsiClass getPsiClass() {
        return psiClass;
    }

    public String getSrcName() {
        return srcName;
    }

    public String getName() {
        return name;
    }
}
