package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

public class DirectoryViewer {

    private int count;
    private Predicate<File> predicate;
    private QueryType findMode;

    private List<File> processDirectory(File file) {
        DirectoryRecursiveProcessor directoryRecursiveProcessor = new DirectoryRecursiveProcessor(file);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        return forkJoinPool.invoke(directoryRecursiveProcessor);
    }

    public List<File> bigDirectories(String dirName, int countFiles) {
        count = countFiles;
        findMode = QueryType.DIRECTORY;

        return processDirectory(new File(dirName));
    }

    public List<File> filesByPredicate(String dirName, Predicate<File> filePredicate) {
        predicate = filePredicate;
        findMode = QueryType.FILE;

        return processDirectory(new File(dirName));
    }

    private class DirectoryRecursiveProcessor extends RecursiveTask<List<File>> {

        private final File currentDirectory;

        DirectoryRecursiveProcessor(File currentDirectory) {
            this.currentDirectory = currentDirectory;
        }

        @Override
        protected List<File> compute() {
            List<File> answer = new ArrayList<>();

            File[] children = currentDirectory.listFiles();
            if (children == null || children.length == 0) {
                return answer;
            }

            if (findMode == QueryType.DIRECTORY) {
                long filesCount = Arrays.stream(children).filter(file -> !file.isDirectory()).count();
                if (filesCount >= count) {
                    answer.add(currentDirectory);
                }
            } else {
                answer.addAll(Arrays.stream(children).filter(file -> !file.isDirectory()).filter(predicate).toList());
            }

            List<DirectoryRecursiveProcessor> subTasks = new ArrayList<>();
            for (File child : children) {
                DirectoryRecursiveProcessor task = new DirectoryRecursiveProcessor(child);
                task.fork();
                subTasks.add(task);
            }

            for (var task : subTasks) {
                answer.addAll(task.join());
            }
            return answer;
        }
    }
}
