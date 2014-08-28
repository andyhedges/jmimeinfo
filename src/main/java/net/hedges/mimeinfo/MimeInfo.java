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
package net.hedges.mimeinfo;

import java.io.File;
import java.io.IOException;

import net.hedges.mimeinfo.globs.GlobsFile;
import net.hedges.mimeinfo.globs.GlobsFileFactory;
import net.hedges.mimeinfo.magic.MagicFile;
import net.hedges.mimeinfo.magic.MagicFileFactory;
import net.hedges.mimeinfo.util.IoUtil;

/**
 * 
 * @author Andy Hedges
 * 
 */

public class MimeInfo {

    private MagicFile magic;

    private GlobsFile globs;

    protected MimeInfo(final File baseDir) throws MimeInfoException, IOException {
        if (!baseDir.exists() || !baseDir.isDirectory()) {
            throw new MimeInfoException(
                    "Database does not exist or is not a directory: "
                            + baseDir.getAbsolutePath());
        }
        magic = MagicFileFactory.create(new File(baseDir, "magic").toURI()
                .toURL());
        globs = GlobsFileFactory.create(new File(baseDir, "globs").toURI()
                .toURL());
    }

    protected MimeInfo() throws MimeInfoException, IOException {
        magic = MagicFileFactory.create(IoUtil
                .getClasspathUrl("share/mime/magic"));
        globs = GlobsFileFactory.create(IoUtil
                .getClasspathUrl("share/mime/globs"));
    }

    public String getMimeType(final File file) throws IOException {
        String mimeType = magic.matchTo(file, 60);
        if (mimeType != null) {
            return mimeType;
        }
        mimeType = globs.match(file);
        if (mimeType != null) {
            return mimeType;
        }
        return magic.matchFrom(file, 59);
    }

}
