package com.awasiljew.code.forensics.vcs.git;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GitNumStatParser {

    private Pattern pattern;

    public GitNumStatParser(Pattern pattern) {
        this.pattern = pattern;
    }

    public Collection<String> items(String data) {
        Matcher matcher = pattern.matcher(data);
        List<String> result = new ArrayList<>();
        if (matcher.find()) {
            int groupCount = matcher.groupCount();
            for (int i = 1; i <= groupCount; i++) {
                result.add(matcher.group(i));
            }
        }
        return result;
    }

}
