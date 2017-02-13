package com.awasiljew.code.forensics.vcs.git;

import lombok.*;

@Builder(builderMethodName = "gitCommitItem")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
public class CommitFileItemModel {

    private final String fileName;
    private final String added;
    private final String removed;

}
