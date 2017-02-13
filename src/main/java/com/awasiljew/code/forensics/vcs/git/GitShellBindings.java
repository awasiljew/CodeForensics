package com.awasiljew.code.forensics.vcs.git;

import com.awasiljew.code.forensics.shell.BashShellCommandExecutor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder(builderMethodName = "gitShellBindings")
@RequiredArgsConstructor
public class GitShellBindings {

    private static final String GIT_VERSION = "git --version";
    private static final String GIT_EVOLUTION_LOG = "git log --pretty=format:'[%h] %an %ad %s' --date=short --numstat";

    private final BashShellCommandExecutor commandExecutor;

    public String gitVersion() {
        return commandExecutor.executeCommand(GIT_VERSION);
    }

    public String gitEvolutionLog() {
        return commandExecutor.executeCommand(GIT_EVOLUTION_LOG);
    }

}
