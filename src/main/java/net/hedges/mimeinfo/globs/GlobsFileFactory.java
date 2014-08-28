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
package net.hedges.mimeinfo.globs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;

import net.hedges.mimeinfo.MimeInfoException;

public final class GlobsFileFactory {

    private GlobsFileFactory() {
    }

    public static GlobsFile create(final URL globsUrl) throws IOException, MimeInfoException {
        GlobsFile globsFile = parseGlobs(globsUrl);

        return globsFile;
    }

    private static GlobsFile parseGlobs(final URL globsUrl) throws IOException, MimeInfoException {
        GlobsFile globsFile = new GlobsFile();
        BufferedReader in = new BufferedReader(new InputStreamReader(globsUrl.openStream()));
        String line = null;
        while ((line = in.readLine()) != null) {
            if (line.trim().startsWith("#")) {
                continue;
            }
            String[] parts = line.split(":");
            if (parts.length != 2) {
                throw new MimeInfoException("globs file is corrupt: " + globsUrl);
            }
            Glob glob = new Glob();
            glob.setMimeType(parts[0]);
            glob.setGlobPattern(parts[1]);
            globsFile.getGlobs().add(glob);
        }
        Collections.sort(globsFile.getGlobs());
        in.close();
        return globsFile;

    }

}
