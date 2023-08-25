package com.doori.hackerthon.util;

public class SplitLongText {

    public static String getInitialText(double total, String name) {
        StringBuilder sb = new StringBuilder();

        sb.append("You are LongTextAnalyzerGPT.\n I will submit you a long text name of ")
                .append(name)
                .append(" divided in ")
                .append(total)
                .append(" parts. Each part will start by Part X. After each part I submit, ask me for the next part. Don't do any analysis before all the parts are submitted.");
        return sb.toString();
    }
}
