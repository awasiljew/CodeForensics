package com.awasiljew.code.forensics.vcs.git;

import org.testng.annotations.Test;

import java.util.Collection;

import static org.testng.Assert.assertNotNull;

public class GitNumStatCommitParserTest {

    private static final String TEST_DATA = "[c1f490a] Adam Wasiljew 2017-02-13 1. Project setup: - PMD plugin - lombok - log4j with SLF4J - unit test support (testng) - jgit 2. Bash shell integration draft classes 3. Git shell integration draft classes 4. Main window menu - main catalog selection 5. Basic unit tests supporting classes, example tests\n" +
            "72\t8\tpom.xml\n" +
            "44\t17\tsrc/main/java/com/awasiljew/code/forensics/FXMLController.java\n" +
            "2\t2\tsrc/main/java/com/awasiljew/code/forensics/MainApp.java\n" +
            "71\t0\tsrc/main/java/com/awasiljew/code/forensics/shell/BashShellCommandExecutor.java\n" +
            "51\t0\tsrc/main/java/com/awasiljew/code/forensics/shell/ProcessInputStreamConsumer.java\n" +
            "24\t0\tsrc/main/java/com/awasiljew/code/forensics/vcs/git/GitShellBindings.java\n" +
            "28\t10\tsrc/main/resources/fxml/Scene.fxml\n" +
            "36\t0\tsrc/test/java/com/awasiljew/code/forensics/shell/BashShellCommandExecutorTest.java\n" +
            "64\t0\tsrc/test/java/com/awasiljew/code/forensics/shell/ProcessInputStreamConsumerTest.java\n" +
            "44\t0\tsrc/test/java/com/awasiljew/code/forensics/vcs/git/GitShellBindingsTest.java\n" +
            "11\t0\tsrc/test/java/com/awasiljew/code/test/LinuxOnlyTest.java\n" +
            "35\t0\tsrc/test/java/com/awasiljew/code/test/OnlyLinuxSupportedTestListener.java\n" +
            "8\t0\tsrc/test/java/com/awasiljew/code/test/SystemSpecificUnitTest.java\n" +
            "5\t0\tsrc/test/resources/log4j.properties\n" +
            "\n" +
            "[7e462e0] Adam Wasiljew 2017-02-12 Initial draft of new project\n" +
            "3\t0\t.gitignore\n" +
            "108\t0\tpom.xml\n" +
            "31\t0\tsrc/main/java/com/awasiljew/code/forensics/FXMLController.java\n" +
            "37\t0\tsrc/main/java/com/awasiljew/code/forensics/MainApp.java\n" +
            "15\t0\tsrc/main/resources/fxml/Scene.fxml\n" +
            "3\t0\tsrc/main/resources/styles/Styles.css\n" +
            "\n" +
            "[48ba1db] Adam Wasiljew 2017-02-12 Initial commit\n" +
            "1\t0\tREADME.md\n";

    @Test
    public void shouldBuildModel() {
        // When
        Collection<CommitModel> commits = new GitNumStatCommitParser().items(TEST_DATA);
        // Then
        assertNotNull(commits);
    }

}