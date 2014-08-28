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
package net.hedges.mimeinfo.magic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Andy Hedges
 * 
 */
public final class MagicFile {

    private List<Section> sections = new ArrayList<Section>();

    protected MagicFile() {
    }

    protected void setSections(final List<Section> sections) {
        this.sections = sections;
    }

    public List<Section> getSections() {
        return sections;
    }

    public String toString() {
        StringBuffer text = new StringBuffer();
        for (Section section : sections) {
            text.append(section);
            text.append('\n');
            for (Rule line : section.getLines()) {
                text.append(line);
                text.append('\n');
            }
        }
        return text.toString();
    }

    // TODO: tidy up this method it is far too complicated and hard to
    // understand
    public String match(final File file, final int startAt, final int endAt) throws IOException {
        String lastMime = null;
        for (Section section : sections) {
            if (section.getPriority() < endAt) { // gone too far therefore
                                                    // abort
                return null;
            }
            if (section.getPriority() > startAt) { // not there yet therefore
                                                    // skip
                continue;
            }
            List<Rule> lines = section.getLines();
            int indentLevel = 0;
            boolean matched = true;
            for (Rule line : lines) {
                if (line.getIndent() == indentLevel) {
                    indentLevel++;
                    if (line.match(file)) {
                        lastMime = section.getMimeType();
                        matched = true;
                    } else {
                        indentLevel--;
                        matched = false;
                    }
                }
            }
            if (matched) {
                return lastMime;
            }
        }
        return null;
    }

    public String match(final File file) throws IOException {
        return match(file, Integer.MAX_VALUE, 0);
    }

    public String matchTo(final File file, final int endAt) throws IOException {
        return match(file, Integer.MAX_VALUE, endAt);
    }

    public String matchFrom(final File file, final int startAt) throws IOException {
        return match(file, startAt, 0);
    }

}
