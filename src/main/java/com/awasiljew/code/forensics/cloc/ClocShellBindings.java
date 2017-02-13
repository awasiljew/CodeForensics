package com.awasiljew.code.forensics.cloc;

import com.awasiljew.code.forensics.shell.BashShellCommandExecutor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder(builderMethodName = "clocShellBindings")
@RequiredArgsConstructor
public class ClocShellBindings {

    private static final String CLOC_VERSION = "cloc --version";
    private static final String COUNT_LINES = "cloc ./ --by-file --csv --quiet --progress-rate=0";

    private final BashShellCommandExecutor commandExecutor;

    public String getClocVersion() {
        return commandExecutor.executeCommand(CLOC_VERSION);
    }

    public String countLines() {
        return commandExecutor.executeCommand(COUNT_LINES);
    }
}
