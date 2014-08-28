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

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Andy Hedges
 * 
 */
public class Section implements Comparable<Section> {

    private int priority;

    private String mimeType;

    private List<Rule> lines = new ArrayList<Rule>();

    public void setPriority(final int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setMimeType(final String mimeType) {
        this.mimeType = mimeType;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setLines(final List<Rule> lines) {
        this.lines = lines;
    }

    public List<Rule> getLines() {
        return lines;
    }

    public int compareTo(final Section section) {
        return priority - section.priority;
    }

    public String toString() {
        StringBuffer text = new StringBuffer();
        text.append('[');
        text.append(priority);
        text.append(':');
        text.append(mimeType);
        text.append(']');
        return text.toString();
    }

}
