/*
 * jmimeinfo is an implementation of shared mime info specification 
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Andy Hedges
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.hedges.mimeinfo.test.globs;

import java.io.IOException;

import junit.framework.TestCase;
import net.hedges.mimeinfo.MimeInfoException;
import net.hedges.mimeinfo.globs.GlobsFile;
import net.hedges.mimeinfo.globs.GlobsFileFactory;
import net.hedges.mimeinfo.util.IoUtil;

public class TestGlobsMatcher extends TestCase {

    private GlobsFile globs;

    public synchronized void setUp() throws IOException, MimeInfoException {
        if (globs == null) {
            globs = GlobsFileFactory.create(IoUtil.getClasspathUrl("share/mime/globs"));
        }
    }

    public void testGlobsMatcherFlv() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.flv"));
        assertEquals("application/x-flash-video", mime);
    }

    public void testGlobsMatcherMov() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.mov"));
        assertEquals("video/quicktime", mime);
    }

    public void testGlobsMatcherWmv() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.wmv"));
        assertEquals("video/x-ms-wmv", mime);
    }

    public void testGlobsMatcherSh() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.sh"));
        assertEquals("application/x-shellscript", mime);
    }

    public void testGlobsMatcherJpg() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.jpg"));
        assertEquals("image/jpeg", mime);
    }

    public void testGlobsMatcherMpg() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.mpg"));
        assertEquals("video/mpeg", mime);
    }

    public void testGlobsMatcherAvi() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.avi"));
        assertEquals("video/x-msvideo", mime);
    }

    public void testGlobsMatcherAu() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.au"));
        assertEquals("audio/basic", mime);
    }

    public void testGlobsMatcherBin() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.bin"));
        assertEquals("application/octet-stream", mime);
    }

    public void testGlobsMatcherBmp() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.bmp"));
        assertEquals("image/bmp", mime);
    }

    public void testGlobsMatcherEps() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.eps"));
        assertEquals("image/x-eps", mime);
    }

    public void testGlobsMatcherGif() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.gif"));
        assertEquals("image/gif", mime);
    }

    public void testGlobsMatcherPng() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.png"));
        assertEquals("image/png", mime);
    }

    public void testGlobsMatcherWav() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.wav"));
        assertEquals("audio/x-wav", mime);
    }

    public void testGlobsMatcherXls() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.xls"));
        assertEquals("application/vnd.ms-excel", mime);
    }

    public void testGlobsMatcherSo() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.so"));
        assertEquals("application/x-sharedlib", mime);
    }

    public void testGlobsMatcherDll() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.dll"));
        assertNull(mime);
    }

    public void testGlobsMatcherExecutable() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test"));
        assertNull(mime);
    }

    public void testGlobsMatcherTar() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.tar"));
        assertEquals("application/x-tar", mime);
    }

    public void testGlobsMatcherTarGz() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.tar.gz"));
        assertEquals("application/x-compressed-tar", mime);
    }

    public void testGlobsMatcherExe() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.exe"));
        assertEquals("application/x-executable", mime);
    }

    public void testGlobsMatcherPsd() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.psd"));
        assertEquals("image/x-psd", mime);
    }

    public void testGlobsMatcherTiff() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.tiff"));
        assertEquals("image/tiff", mime);
    }

    public void testGlobsMatcherBZip2() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.tar.bz2"));
        assertEquals("application/x-bzip-compressed-tar", mime);
    }

    public void testGlobsMatcherZip() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.zip"));
        assertEquals("application/zip", mime);
    }

    public void testGlobsMatcherC() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.c"));
        assertEquals("text/x-csrc", mime);
    }

    public void testGlobsMatcherWordDoc() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.doc"));
        assertEquals("application/msword", mime);
    }

    public void testGlobsMatcherExcelXls() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.xls"));
        assertEquals("application/vnd.ms-excel", mime);
    }

    public void testGlobsMatcherPowerPointPpt() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.ppt"));
        assertEquals("application/vnd.ms-powerpoint", mime);
    }

    public void testMagicMatcherODFOdt() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.odt"));
        assertEquals("application/vnd.oasis.opendocument.text", mime);
    }

    public void testMagicMatcherRtf() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.rtf"));
        assertEquals("application/rtf", mime);
    }

    public void testMagicMatcherOpenOfficeSwx() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.sxw"));
        assertEquals("application/vnd.sun.xml.writer", mime);
    }

    public void testMagicMatcherStarWriterSdw() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.sdw"));
        assertEquals("application/vnd.stardivision.writer", mime);
    }

    public void testMagicMatcherODFOds() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.ods"));
        assertEquals("application/vnd.oasis.opendocument.spreadsheet", mime);
    }

    public void testMagicMatcherLotus123Wk1() throws IOException, MimeInfoException {
        String mime = globs.match(IoUtil.getClasspathFile("data/test.wk1"));
        assertEquals("application/vnd.lotus-1-2-3", mime);
    }

}
