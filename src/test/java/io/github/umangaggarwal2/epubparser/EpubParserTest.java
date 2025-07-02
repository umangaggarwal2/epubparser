package io.github.umangaggarwal2.epubparser;

import static org.junit.Assert.assertEquals;

import io.github.umangaggarwal2.epubparser.util.Constant;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EpubParserTest {

  @Test
  public void parseEpub3() throws IOException {

    assertEquals(Constant.EPUB_MIMETYPE,
        new EpubParser("src/test/resources/frankenstein-epub3.epub").parse());
  }

  @Test
  public void parseEpub2() throws IOException {
    assertEquals(Constant.EPUB_MIMETYPE,
        new EpubParser("src/test/resources/frankenstein-epub2.epub").parse());
  }
}