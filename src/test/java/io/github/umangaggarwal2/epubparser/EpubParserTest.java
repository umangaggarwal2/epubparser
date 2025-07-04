package io.github.umangaggarwal2.epubparser;

import static org.junit.Assert.assertEquals;

import io.github.umangaggarwal2.epubparser.model.Epub;
import io.github.umangaggarwal2.epubparser.util.Constant;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EpubParserTest {

  private static final String TEST_PACKAGE_PATH = "OEBPS/content.opf";

  @Test
  public void parseEpub3() throws IOException {
    Epub expected = new Epub();
    expected.setMimetype(Constant.EPUB_MIMETYPE);
    expected.setPackagePath(TEST_PACKAGE_PATH);
    assertEquals(expected, new EpubParser("src/test/resources/frankenstein-epub3.epub").parse());
  }

  @Test
  public void parseEpub2() throws IOException {
    Epub expected = new Epub();
    expected.setMimetype(Constant.EPUB_MIMETYPE);
    expected.setPackagePath(TEST_PACKAGE_PATH);
    assertEquals(expected, new EpubParser("src/test/resources/frankenstein-epub2.epub").parse());
  }
}