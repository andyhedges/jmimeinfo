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
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * 
 * @author Andy Hedges
 * 
 */
public class Rule {

    private int indent = 0;
    private int startOffset;
    private byte[] value;
    private byte[] mask;
    private int wordSize = 1;
    private int rangeLength = 1;
    private boolean ignore = false;

    public void setIndent(final int indent) {
        this.indent = indent;
    }

    public int getIndent() {
        return indent;
    }

    public void setStartOffset(final int startOffset) {
        this.startOffset = startOffset;
    }

    public int getStartOffset() {
        return startOffset;
    }

    public void setValue(byte[] value) {
        this.value = value;
        if (mask == null) {
            mask = new byte[value.length];
            Arrays.fill(mask, (byte) 0xff);
        }
    }

    public byte[] getValue() {
        return value;
    }

    public void setMask(byte[] mask) {
        this.mask = mask;
    }

    public byte[] getMask() {
        return mask;
    }

    public void setWordSize(final int wordSize) {
        this.wordSize = wordSize;
    }

    public int getWordSize() {
        return wordSize;
    }

    public void setRangeLength(final int rangeLength) {
        this.rangeLength = rangeLength;
    }

    public int getRangeLength() {
        return rangeLength;
    }

    public void setIgnore(final boolean ignore) {
        this.ignore = ignore;
    }

    public boolean isIgnore() {
        return ignore;
    }

    public String toString() {
        StringBuffer text = new StringBuffer();
        text.append(indent);
        text.append('>');
        text.append(startOffset);
        text.append('=');
        text.append(toHexString(value));
        text.append('&');
        text.append(toHexString(mask));
        text.append('~');
        text.append(wordSize);
        text.append('+');
        text.append(rangeLength);
        return text.toString();
    }

    private static String toHexString(final byte[] raw) {
        String hex = "";
        for (int i = 0; i < raw.length; i++) {
            int b = raw[i] & 0xF0;
            b = (byte) (b >>> 4);
            hex += hexmap[b];
            b = raw[i] & 0x0F;
            hex += hexmap[b];
        }
        return hex;
    }

    private static char[] hexmap = { '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    public boolean match(final File file) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        boolean match = false;
        for (int offset = startOffset; offset < startOffset + rangeLength; offset++) {
            if (raf.length() < offset + value.length) {
                match = false;
                break;
            }

            raf.seek(offset);
            byte[] sample = new byte[value.length];
            raf.readFully(sample);

            if (equalsMaskedValue(sample)) {
                match = true;
                break;
            }

        }
        raf.close();
        return match;
    }

    private boolean equalsMaskedValue(final byte[] sample) {
        for (int i = 0; i < sample.length; i++) {
            if ((value[i] & mask[i]) != (sample[i] & mask[i])) {
                return false;
            }
        }
        return true;
    }

}
