package com.sjhy.plugin.tool;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * 剪切板操作工具类
 */
public class ClipboardUtils {
    private volatile static Clipboard clipboard;

    static {
        if (clipboard == null) {
            synchronized (Clipboard.class) {
                if (clipboard == null) {
                    clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                }
            }
        }
    }

    private ClipboardUtils() {
    }

    /**
     * 将指定内容复制到剪切板
     * @param text 要复制的内容
     */
    public static void copyContentToClipboard(String text) {
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, null);
    }


}
