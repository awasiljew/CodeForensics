package com.awasiljew.code.forensics.shell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

import static java.util.Optional.ofNullable;

public class ProcessInputStreamConsumer implements Callable<String> {

    private static final Logger log = LoggerFactory.getLogger(ProcessInputStreamConsumer.class);
    private InputStream processInputStream;

    public ProcessInputStreamConsumer(InputStream processInputStream) {
        this.processInputStream = processInputStream;
    }

    @Override
    public String call() throws Exception {
        StringBuilder result = new StringBuilder();
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(processInputStream);
            BufferedReader buf = new BufferedReader(reader);
            String line;
            boolean nextLine = false;
            while ((line = buf.readLine()) != null) {
                if (nextLine) result.append("\n");
                else nextLine = true;
                result.append(line);
            }
        } catch (IOException ioe) {
            log.error(ioe.getMessage(), ioe);
        } finally {
            ofNullable(reader).ifPresent(inputStreamReader -> {
                try {
                    inputStreamReader.close();
                } catch (IOException ex) {
                    log.error(ex.getMessage(), ex);
                }
            });
        }
        return result.toString();
    }

}
