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
import net.hedges.mimeinfo.globs.GlobsFileFactory;
import net.hedges.mimeinfo.util.IoUtil;

public class TestGlobs extends TestCase {

    public void testNonCorruptGlobs() throws IOException, MimeInfoException {
        GlobsFileFactory.create(IoUtil.getClasspathUrl("share/mime/globs"));
    }

    public void testCorruptHeaderGlobs() throws IOException {
        // Just picked a file that I know exists but isn't a magic file
        boolean exceptionHappened = false;
        try {
            GlobsFileFactory.create(IoUtil.getClasspathUrl("share/mime/magic"));
        } catch (MimeInfoException e) {
            exceptionHappened = true;
        }
        assertTrue(exceptionHappened);
    }
}
