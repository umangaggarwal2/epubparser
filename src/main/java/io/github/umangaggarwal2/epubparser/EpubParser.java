package io.github.umangaggarwal2.epubparser;

import io.github.umangaggarwal2.epubparser.model.Epub;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipFile;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EpubParser {

  private final String epubPath;

  public Epub parse() throws IOException {
    File file = new File(epubPath);
    if (!file.exists()) {
      throw new FileNotFoundException("EPUB file not found: " + epubPath);
    }
    return extractZip(file);
  }

  private Epub extractZip(File epubFile) throws IOException {
    try (ZipFile zipFile = new ZipFile(epubFile)) {
      // extract mimetype
      ZipParser zipParser = new ZipParser(zipFile);
      return zipParser.parseMimetype().parsePackagePath().build();
    }
  }
}
