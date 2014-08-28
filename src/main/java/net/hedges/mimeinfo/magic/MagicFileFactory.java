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

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import net.hedges.mimeinfo.MimeInfoException;
import net.hedges.mimeinfo.io.RandomAccessByteArray;

public final class MagicFileFactory {

    private static final byte[] HEADER = new byte[] { 'M', 'I', 'M', 'E', '-', 'M', 'a', 'g', 'i', 'c', 0, '\n' };

    private MagicFileFactory() {
    }

    public static MagicFile create(final URL magicUrl) throws IOException, MimeInfoException {
        MagicFile magicFile = parseMagic(magicUrl);
        return magicFile;
    }

    private static MagicFile parseMagic(final URL magicUrl) throws IOException, MimeInfoException {
        MagicFile magicFile = new MagicFile();
        RandomAccessByteArray in = new RandomAccessByteArray(magicUrl.openStream());
        byte[] header = new byte[HEADER.length];
        in.readFully(header);
        if (!Arrays.equals(header, HEADER)) {
            throw new MimeInfoException("magic file is corrupt: " + magicUrl);
        }

        int b = in.read();
        if (b != '[') {
            throw new MimeInfoException("magic file is corrupt: " + magicUrl);
        }

        Section section = null;
        while ((section = readSection(in)) != null) {
            magicFile.getSections().add(section);
        }

        in.close();
        return magicFile;
    }

    private static Section readSection(final RandomAccessByteArray in) throws MimeInfoException, IOException {
        Section section = new Section();
        int b;
        StringBuffer priority = new StringBuffer();
        while ((b = in.read()) != ':') {
            if (b == -1) {
                return null;
            }
            priority.append((char) b);
        }
        section.setPriority(Integer.parseInt(priority.toString()));

        StringBuffer mimeType = new StringBuffer();
        while ((b = in.read()) != ']') {
            mimeType.append((char) b);
        }

        section.setMimeType(mimeType.toString());

        b = in.read();

        if (b != '\n') {
            throw new MimeInfoException("magic file is corrupt");
        }
        Rule line = null;

        while ((line = readLine(in)) != null) {
            section.getLines().add(line);
        }

        return section;
    }

    // TODO: this is too complicated, simplify
    private static Rule readLine(final RandomAccessByteArray in) throws IOException {
        int b = in.read();
        if (b == '[' || b == -1) {
            return null;
        }

        Rule line = new Rule();
        if (b != '>') {
            StringBuffer indent = new StringBuffer();
            indent.append((char) b);
            while ((b = in.read()) != '>') {
                indent.append((char) b);
            }
            line.setIndent(Integer.parseInt(indent.toString()));
        }
        StringBuffer startOffset = new StringBuffer();
        while ((b = in.read()) != '=' && b != -1) {
            startOffset.append((char) b);
        }
        line.setStartOffset(Integer.parseInt(startOffset.toString()));
        int valueLength = in.readUnsignedShort();

        byte[] valueBuff = new byte[valueLength];
        in.readFully(valueBuff);
        line.setValue(valueBuff);

        b = in.read();
        if (b == '&') {
            byte[] maskBuff = new byte[valueLength];
            in.readFully(maskBuff);
            line.setMask(maskBuff);
            b = in.read();
        }

        if (b == '~') {
            StringBuffer wordSize = new StringBuffer();
            while ((b = in.read()) != '+' && b != '\n') {
                wordSize.append((char) b);
            }
            line.setWordSize(Integer.parseInt(wordSize.toString()));
        }

        if (b == '+') {
            StringBuffer rangeLength = new StringBuffer();
            while ((b = in.read()) != '\n') {
                rangeLength.append((char) b);
            }
            line.setRangeLength(Integer.parseInt(rangeLength.toString()));
        }

        if (b != '\n') {
            line.setIgnore(true);
            while ((b = in.read()) != '\n') {
            }
        }

        return line;
    }

}
