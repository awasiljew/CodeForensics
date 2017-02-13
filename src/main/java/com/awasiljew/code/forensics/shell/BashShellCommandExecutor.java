package com.awasiljew.code.forensics.shell;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.*;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang.StringUtils.isNotBlank;

@Builder(builderMethodName = "commandExecutor")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BashShellCommandExecutor {

    private static final Logger log = LoggerFactory.getLogger(BashShellCommandExecutor.class);
    private static final String EMPTY_RESULT = "";
    private static final String DEFAULT_SHELL = "bash";
    private static final String DEFAULT_SHELL_PARAM = "-c";

    private final long timeout;
    private final TimeUnit timeoutTimeUnit;
    private final String workingDirectory;
    private final ExecutorService executorService;

    public String executeCommand(String command) {
        if (isNotBlank(command)) {
            ProcessBuilder pb = buildProcessWithCommand(command);
            Process shell = null;
            try {
                shell = pb.start();
                Future<String> processReader = executorService.submit(new ProcessInputStreamConsumer(shell.getInputStream()));
                shell.waitFor(timeout, timeoutTimeUnit);
                return processReader.get(timeout, timeoutTimeUnit);
            } catch (InterruptedException | IOException | ExecutionException | TimeoutException ex) {
                log.error(ex.getMessage(), ex);
            } finally {
                if (shell != null) {
                    try {
                        shell.destroy();
                    } catch (Exception ex) {
                        log.error(ex.getMessage(), ex);
                    }
                }
            }
        }
        return EMPTY_RESULT;
    }

    private ProcessBuilder buildProcessWithCommand(String command) {
        ProcessBuilder pb = new ProcessBuilder(DEFAULT_SHELL, DEFAULT_SHELL_PARAM, command);
        setWorkingDirectory(pb);
        pb.redirectErrorStream(true);
        return pb;
    }

    private void setWorkingDirectory(ProcessBuilder pb) {
        ofNullable(workingDirectory)
                .map(File::new)
                .ifPresent(file -> {
                    if (file.isDirectory()) {
                        pb.directory(new File(workingDirectory));
                    }
                });
    }

}
