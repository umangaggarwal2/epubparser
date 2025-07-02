package io.github.umangaggarwal2.epubparser;

import static io.github.umangaggarwal2.epubparser.util.Constant.MIMETYPE;

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
  private String mimetype;

  public ZipParser parseMimetype() throws IOException {
    ZipEntry mimeTypeEntry = zipFile.getEntry(MIMETYPE);
    try (InputStream is = zipFile.getInputStream(mimeTypeEntry)) {
      byte[] mimetypeBytes = is.readAllBytes();
      mimetype = new String(mimetypeBytes, StandardCharsets.UTF_8).trim();
    }
    Validation.validateMimeType(mimetype);
    return this;
  }

  public String build() {
    return mimetype;
  }
}
