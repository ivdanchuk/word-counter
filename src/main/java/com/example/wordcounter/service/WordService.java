package com.example.wordcounter.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class WordService {

  private final FileService fileService;

  public long getWorldCount(String dirPath, String givenWord) throws IOException {
    final var filePath = fileService.findLatestFile(dirPath).orElseThrow(NoSuchElementException::new);

    final Stream<String> words;
    try (Stream<String> lines = Files.lines(filePath, StandardCharsets.UTF_8)) {
      words = lines.flatMap(line -> Stream.of(line.split("\\s")));
    }
    return words.filter(world -> world.equals(givenWord)).count();
  }
}
