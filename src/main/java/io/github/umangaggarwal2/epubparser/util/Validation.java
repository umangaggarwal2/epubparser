package io.github.umangaggarwal2.epubparser.util;

import static io.github.umangaggarwal2.epubparser.util.Constant.EPUB_MIMETYPE;

import io.github.umangaggarwal2.epubparser.exception.InvalidMimetypeException;

public final class Validation {

  private Validation() {
  }

  public static void validateMimeType(String mimetype) {
    if (!EPUB_MIMETYPE.equalsIgnoreCase(mimetype)) {
      throw new InvalidMimetypeException("Invalid mimetype " + mimetype);
    }
  }
}
