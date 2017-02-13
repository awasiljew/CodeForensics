package com.awasiljew.code.forensics.shell;

import com.awasiljew.code.test.LinuxOnlyTest;
import com.awasiljew.code.test.SystemSpecificUnitTest;
import org.testng.annotations.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.awasiljew.code.forensics.shell.BashShellCommandExecutor.commandExecutor;
import static org.testng.Assert.assertEquals;

public class BashShellCommandExecutorTest implements SystemSpecificUnitTest {

    private static final String ECHO_MESSAGE = "some text\nwith multiple lines\n";
    private static final String ECHO_COMMAND = String.format("echo '%s'", ECHO_MESSAGE);

    @LinuxOnlyTest
    @Test
    public void shouldExecuteBashEchoCommand() {
        // When
        String command = testCommandExecutor().executeCommand(ECHO_COMMAND);
        // Then
        assertEquals(command, ECHO_MESSAGE);
    }

    public static BashShellCommandExecutor testCommandExecutor() {
        return commandExecutor()
                .executorService(Executors.newSingleThreadExecutor())
                .timeout(1)
                .timeoutTimeUnit(TimeUnit.MINUTES)
                .workingDirectory(".")
                .build();
    }

}