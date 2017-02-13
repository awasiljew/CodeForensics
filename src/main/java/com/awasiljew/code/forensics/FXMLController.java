package com.awasiljew.code.forensics;

import com.awasiljew.code.forensics.shell.BashShellCommandExecutor;
import com.awasiljew.code.forensics.vcs.git.GitShellBindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FXMLController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    public void handleSelectDirectoryMenuItemAction(ActionEvent event) throws IOException, GitAPIException {
        File file = new DirectoryChooser()
                .showDialog(anchorPane.getScene().getWindow());
        System.out.println("File: " + file);
        Repository repository = new FileRepositoryBuilder()
                .setGitDir(new File(file + "/.git"))
                .build();
        Git git = new Git(repository);

        git.log().call().forEach(revCommit -> {
            System.out.println("[" + revCommit.getName() + "] " + revCommit.getAuthorIdent().getName() + " " + revCommit.getShortMessage());
        });

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        GitShellBindings gitShellBindings = GitShellBindings.gitShellBindings()
                .commandExecutor(BashShellCommandExecutor.commandExecutor()
                        .executorService(executorService)
                        .timeout(1)
                        .timeoutTimeUnit(TimeUnit.MINUTES)
                        .workingDirectory(file.getAbsolutePath())
                        .build())
                .build();

        System.out.println("\n" + gitShellBindings.gitEvolutionLog());

        executorService.shutdown();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
