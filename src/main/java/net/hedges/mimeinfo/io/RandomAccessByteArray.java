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
package net.hedges.mimeinfo.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
public class RandomAccessByteArray {

    private byte[] byteArray;
    private int position = 0;

    /**
     * This should not be used to read very large files as it reads the file
     * entirely into memory and therefore will, with large files, cause an
     * OutOfMemoryException - of course large is a relative thing.
     * 
     * @author Andy Hedges
     * 
     */
    public RandomAccessByteArray(final InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buff = new byte[32 * 1024]; // TODO: magic number
        int read = 0;
        while ((read = in.read(buff)) > -1) {
            out.write(buff, 0, read);
        }
        in.close();
        out.close(); // not actually needed
        byteArray = out.toByteArray();
    }

    public synchronized void readFully(final byte[] b) {
        System.arraycopy(byteArray, position, b, 0, b.length);
        position += b.length;
    }

    public synchronized int read() {
        if (position + 1 > byteArray.length) {
            return -1;
        }
        byte b = byteArray[position];
        position++;
        return (int) b;
    }

    public synchronized int readUnsignedShort() {
        int b1 = read();
        int b2 = read();
        if ((b1 | b2) < 0) {
            return -1;
        }
        return (b1 << 8) + (b2 << 0);
    }

    public void close() {
    } // Whatever!

}
