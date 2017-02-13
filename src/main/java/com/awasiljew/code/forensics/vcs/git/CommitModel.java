package com.awasiljew.code.forensics.vcs.git;

import lombok.*;

import java.util.Collection;

@Builder(builderMethodName = "gitCommit")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class CommitModel {

    private final String sha;
    private final String author;
    private final String date;
    private final String commitMessage;
    private final Collection<CommitFileItemModel> files;

}
