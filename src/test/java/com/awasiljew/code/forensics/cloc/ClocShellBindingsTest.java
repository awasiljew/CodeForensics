package com.awasiljew.code.forensics.cloc;

import com.awasiljew.code.test.LinuxOnlyTest;
import com.awasiljew.code.test.OnlyLinuxSupportedTestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Collection;

import static com.awasiljew.code.forensics.shell.BashShellCommandExecutorTest.testCommandExecutor;
import static org.testng.Assert.assertNotNull;

@Listeners(value = {OnlyLinuxSupportedTestListener.class})
public class ClocShellBindingsTest {

    @LinuxOnlyTest
    @Test
    public void shouldCountLines() {
        // When
        Collection<CLocModel> lines = new CLocModelBuilder(gitBindings().countLines()).model();
        // Then
        assertNotNull(lines);
    }

    private ClocShellBindings gitBindings() {
        return ClocShellBindings.clocShellBindings()
                .commandExecutor(testCommandExecutor())
                .build();
    }

}