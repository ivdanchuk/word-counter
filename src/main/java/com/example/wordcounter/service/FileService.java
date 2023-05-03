package com.example.wordcounter.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

@Service
public class FileService {

  public Optional<Path> findLatestFile(final String dirPath) throws IOException {
    try (Stream<Path> files = Files.list(Paths.get(dirPath))) {
      return files
        .filter(file -> !Files.isDirectory(file))
        .max(Comparator.comparingLong(f -> f.toFile().lastModified()))
        .stream().findFirst();
    }
  }
}
