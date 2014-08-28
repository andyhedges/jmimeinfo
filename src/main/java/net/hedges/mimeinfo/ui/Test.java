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
package net.hedges.mimeinfo.ui;

import java.io.File;
import java.io.IOException;

import net.hedges.mimeinfo.MimeInfo;
import net.hedges.mimeinfo.MimeInfoException;
import net.hedges.mimeinfo.MimeInfoFactory;

/**
 * 
 * @author Andy Hedges
 * 
 */
public final class Test {

    private Test() {
    }

    public static void main(final String[] args) throws IOException, MimeInfoException {
        if (args.length != 1) {
            System.out.println("usage: java net.hedges.mimeinfo.ui.Test <filename>");
            System.exit(0);
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.println(file.getAbsolutePath() + " does not exist");
            System.exit(1);
        }

        MimeInfo mimeInfo = MimeInfoFactory.create();
        System.out.println(mimeInfo.getMimeType(file));
    }

}
