package com.awasiljew.code.forensics.vcs.git;

import com.awasiljew.code.test.LinuxOnlyTest;
import com.awasiljew.code.test.OnlyLinuxSupportedTestListener;
import com.awasiljew.code.test.SystemSpecificUnitTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.awasiljew.code.forensics.shell.BashShellCommandExecutorTest.testCommandExecutor;
import static com.awasiljew.code.forensics.vcs.git.GitShellBindings.gitShellBindings;
import static java.util.regex.Pattern.matches;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.testng.Assert.assertTrue;

@Listeners(value = {OnlyLinuxSupportedTestListener.class})
public class GitShellBindingsTest implements SystemSpecificUnitTest {

    private static final String GIT_VERSION_REGEX = "git version [0-9]\\.[0-9]\\.[0-9]";

    @LinuxOnlyTest
    @Test
    public void shouldCheckGitVersion() {
        // When
        String gitVersion = gitBindings().gitVersion();
        // Then
        assertTrue(matches(GIT_VERSION_REGEX, gitVersion), "Regex does not match: " + gitVersion);
    }

    @LinuxOnlyTest
    @Test
    public void shouldGetGitLog() {
        // When
        String evoLog = gitBindings().gitEvolutionLog();
        // Then
        assertTrue(isNotBlank(evoLog));
    }

    private GitShellBindings gitBindings() {
        return gitShellBindings()
                .commandExecutor(testCommandExecutor())
                .build();
    }

}