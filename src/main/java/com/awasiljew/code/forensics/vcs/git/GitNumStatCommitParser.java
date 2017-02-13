package com.awasiljew.code.forensics.vcs.git;

import java.util.*;
import java.util.function.Function;
import java.util.stream.StreamSupport;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class GitNumStatCommitParser {

    public Collection<CommitModel> items(String data) {
        return stream(data.split("\n\n"))
                .map(commitData -> {
                    CommitModel.CommitModelBuilder gitCommit = CommitModel.gitCommit();
                    Collection<String> commitDetails = asList(commitData.split("\n"));
                    Iterator<String> detailsIterator = commitDetails.iterator();
                    if (detailsIterator.hasNext()) {
                        Collection<String> commitHeaderDetails = new GitNumStatCommitHeaderParser().items(detailsIterator.next());
                        Iterator<String> commitDetailsIterator = commitHeaderDetails.iterator();
                        setData(commitDetailsIterator,
                                gitCommit::sha,
                                gitCommit::author,
                                gitCommit::date,
                                gitCommit::commitMessage);
                    }
                    List<CommitFileItemModel> files = StreamSupport.stream(Spliterators.spliteratorUnknownSize(detailsIterator, Spliterator.ORDERED), false)
                            .map(s -> {
                                CommitFileItemModel.CommitFileItemModelBuilder fileEvolutionModelBuilder = CommitFileItemModel.gitCommitItem();
                                Collection<String> fileDetails = new GitNumStatFileParser().items(s);
                                setData(fileDetails.iterator(),
                                        fileEvolutionModelBuilder::added,
                                        fileEvolutionModelBuilder::removed,
                                        fileEvolutionModelBuilder::fileName
                                );
                                return fileEvolutionModelBuilder.build();
                            })
                            .collect(toList());
                    gitCommit.files(files);
                    return gitCommit.build();
                })
                .collect(toList());
    }

    private void setData(Iterator<String> items, Function<String, ?>... setters) {
        stream(setters)
                .forEach(setter -> {
                    if (items.hasNext()) {
                        setter.apply(items.next());
                    }
                });
    }

}
