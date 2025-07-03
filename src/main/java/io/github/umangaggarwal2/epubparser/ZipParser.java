package io.github.umangaggarwal2.epubparser;

import static io.github.umangaggarwal2.epubparser.util.Constant.CONTAINER_XML_PATH;
import static io.github.umangaggarwal2.epubparser.util.Constant.MIMETYPE;

import io.github.umangaggarwal2.epubparser.model.Epub;
import io.github.umangaggarwal2.epubparser.util.Validation;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ZipParser {

  private final ZipFile zipFile;
  private final Epub epub;

  public ZipParser(ZipFile zipFile) {
    this.zipFile = zipFile;
    this.epub = new Epub();
  }

  public ZipParser parseMimetype() throws IOException {
    ZipEntry mimeTypeEntry = zipFile.getEntry(MIMETYPE);
    try (InputStream is = zipFile.getInputStream(mimeTypeEntry)) {
      byte[] mimetypeBytes = is.readAllBytes();
      epub.setMimetype(new String(mimetypeBytes, StandardCharsets.UTF_8).trim());
    }
    Validation.validateMimeType(epub.getMimetype());
    return this;
  }

  public ZipParser parsePackagePath() throws IOException {
    ZipEntry containerEntry = zipFile.getEntry(CONTAINER_XML_PATH);
    try (InputStream is = zipFile.getInputStream(containerEntry)) {
      byte[] containerBytes = is.readAllBytes();
      String containerContent = new String(containerBytes, StandardCharsets.UTF_8);
      String path = extractFullPath(containerContent);
      epub.setPackagePath(path);
    }
    return this;
  }

  private String extractFullPath(String xmlContent) {
    String searchString = "rootfile full-path=\"";
    int startIndex = xmlContent.indexOf(searchString);
    if (startIndex == -1) {
      return null; // full-path not found
    }

    // Move past the search string to the start of the path
    startIndex += searchString.length();
    // Find the closing quote
    int endIndex = xmlContent.indexOf("\"", startIndex);
    if (endIndex == -1) {
      return null; // Malformed XML
    }

    // Extract the path between the quotes
    return xmlContent.substring(startIndex, endIndex);
  }

  public Epub build() {
    return epub;
  }
}
