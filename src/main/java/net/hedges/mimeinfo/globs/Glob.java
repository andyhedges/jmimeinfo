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

import java.io.File;
import java.util.regex.Pattern;

import net.hedges.mimeinfo.MimeInfoException;


/**
 * 
 * @author Andy Hedges
 * 
 */
public class Glob implements Comparable<Glob> {

    private String mimeType;

    private Pattern regex;
    private String globPattern;


    public void setMimeType(final String mimeType) {
        this.mimeType = mimeType;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setGlobPattern(final String globPattern)
            throws MimeInfoException {
        this.globPattern = globPattern;
        regex = Pattern.compile(Glob2Regex.convert(globPattern));
    }

    private boolean isLiteral() {
        /* TODO: This is not quite accurate as a literal here could be
         marked as a non literal. (e.g. 'ab]ah' is literal but would
         not be flagged as such by this method). */
        return globPattern.contains("]") || globPattern.contains("*")
                || globPattern.contains("[") || globPattern.contains("#")
                || globPattern.contains("?");
    }

    public int compareTo(final Glob glob) {
        if (glob.isLiteral() && !isLiteral()) {
            return -1;
        } else if (!glob.isLiteral() && isLiteral()) {
            return 1;
        }
        return -globPattern.length() + glob.globPattern.length();
    }

    public boolean equals(final Object object) {
        if (object == null) {
            return false;
        }
        if (!(object instanceof Glob)) {
            return false;
        }
        Glob glob = (Glob) object;
        return globPattern.equals(glob.globPattern)
                && mimeType.equals(glob.mimeType);
    }
    
    public int hashCode(){
        int hash = 1;
        hash =  hash * 47 + globPattern.hashCode();
        hash = hash * 47 + mimeType.hashCode();
        return hash;
    }

    public String toString() {
        return mimeType + ":" + globPattern;
    }

    public boolean match(final File file) {
        String name = file.getName();
        return regex.matcher(name).matches();
    }

}
