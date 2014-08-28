/*
 * jmimeinfo is an implementation of shared mime info specification 
 * Copyright (C) 2006  Andy Hedges
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package net.hedges.mimeinfo.test.magic;

import java.io.IOException;

import junit.framework.TestCase;
import net.hedges.mimeinfo.MimeInfoException;
import net.hedges.mimeinfo.magic.MagicFile;
import net.hedges.mimeinfo.magic.MagicFileFactory;
import net.hedges.mimeinfo.util.IoUtil;

public class TestMagicMatcher extends TestCase {

    private static MagicFile magic;

    public synchronized void setUp() throws IOException, MimeInfoException {
        if (magic == null) {
            magic = MagicFileFactory.create(IoUtil.getClasspathUrl("share/mime/magic"));
        }
    }

    public void testMagicMatcherFlv() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.flv"));
        assertEquals("application/x-flash-video", mime);
    }

    public void testMagicMatcherMov() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.mov"));
        assertEquals("video/quicktime", mime);
    }

    public void testMagicMatcherWmv() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.wmv"));
        assertEquals("video/x-ms-asf", mime);
    }

    public void testMagicMatcherMpg() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.mpg"));
        assertEquals("video/mpeg", mime);
    }

    public void testMagicMatcherAvi() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.avi"));
        assertEquals("video/x-msvideo", mime);
    }

    public void testMagicMatcherSh() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.sh"));
        assertEquals("application/x-shellscript", mime);
    }

    public void testMagicMatcherJpg() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.jpg"));
        assertEquals("image/jpeg", mime);
    }

    public void testMagicMatcherAu() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.au"));
        assertEquals("audio/basic", mime);
    }

    public void testMagicMatcherBin() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.bin"));
        assertNull(mime);
    }

    public void testMagicMatcherBmp() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.bmp"));
        assertEquals("image/bmp", mime);
    }

    public void testMagicMatcherEps() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.eps"));
        assertEquals("application/postscript", mime);
    }

    public void testMagicMatcherGif() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.gif"));
        assertEquals("image/gif", mime);
    }

    public void testMagicMatcherPng() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.png"));
        assertEquals("image/png", mime);
    }

    public void testMagicMatcherWav() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.wav"));
        assertEquals("audio/x-wav", mime);
    }

    public void testMagicMatcherXls() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.xls"));
        assertEquals("application/x-ole-storage", mime);
    }

    public void testMagicMatcherSo() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.so"));
        assertEquals("application/x-sharedlib", mime);
    }

    public void testMagicMatcherDll() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.dll"));
        assertEquals("application/x-ms-dos-executable", mime);
    }

    // the test executable here is corrupt as I couldn't work out how to create
    // a valid
    // one that would trigger the second rule.
    public void testMagicMatcherExecutable() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test"));
        assertEquals("application/x-executable", mime);
    }

    public void testMagicMatcherTar() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.tar"));
        assertEquals("application/x-tar", mime);
    }

    public void testMagicMatcherTarGz() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.tar.gz"));
        assertEquals("application/x-gzip", mime);
    }

    public void testMagicMatcherExe() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.exe"));
        assertEquals("application/x-ms-dos-executable", mime);
    }

    public void testMagicMatcherPsd() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.psd"));
        assertEquals("image/x-psd", mime);
    }

    public void testMagicMatcherTiff() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.tiff"));
        assertEquals("image/tiff", mime);
    }

    public void testMagicMatcherBZip2() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.tar.bz2"));
        assertEquals("application/x-bzip", mime);
    }

    public void testMagicMatcherZip() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.zip"));
        assertEquals("application/zip", mime);
    }

    public void testMagicMatcherWordDoc() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.doc"));
        assertEquals("application/x-ole-storage", mime);
    }

    public void testMagicMatcherExcelXls() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.xls"));
        assertEquals("application/x-ole-storage", mime);
    }

    public void testMagicMatcherPowerPointPpt() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.ppt"));
        assertEquals("application/x-ole-storage", mime);
    }

    public void testMagicMatcherODFOdt() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.odt"));
        assertEquals("application/zip", mime);
    }

    public void testMagicMatcherRtf() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.rtf"));
        assertEquals("application/rtf", mime);
    }

    public void testMagicMatcherOpenOfficeSwx() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.sxw"));
        assertEquals("application/zip", mime);
    }

    public void testMagicMatcherStarWriterSdw() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.sdw"));
        assertEquals("application/x-ole-storage", mime);
    }

    public void testMagicMatcherODFOds() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.ods"));
        assertEquals("application/zip", mime);
    }

    public void testMagicMatcherLotus123Wk1() throws IOException, MimeInfoException {
        String mime = magic.match(IoUtil.getClasspathFile("test.wk1"));
        assertEquals("application/vnd.lotus-1-2-3", mime);
    }

}
