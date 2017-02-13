package com.awasiljew.code.forensics.vcs.git;

import java.util.Collection;
import java.util.regex.Pattern;

public class GitNumStatFileParser {

    private static final Pattern PATTERN = Pattern.compile("([0-9]*)\\s([0-9]*)\\s(.*)");
    private final GitNumStatParser parser = new GitNumStatParser(PATTERN);

    public Collection<String> items(String data) {
        return parser.items(data);
    }

}
