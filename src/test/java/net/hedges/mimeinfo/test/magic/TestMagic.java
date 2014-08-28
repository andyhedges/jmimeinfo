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
package net.hedges.mimeinfo.test.magic;

import java.io.IOException;

import junit.framework.TestCase;
import net.hedges.mimeinfo.MimeInfoException;
import net.hedges.mimeinfo.magic.MagicFileFactory;
import net.hedges.mimeinfo.util.IoUtil;

public class TestMagic extends TestCase {

    public void testNonCorruptMagic() throws IOException, MimeInfoException {
        MagicFileFactory.create(IoUtil.getClasspathUrl("share/mime/magic"));
    }

    public void testCorruptHeaderMagic() throws IOException {
        // Just picked a file that I know exists but isn't a magic file
        boolean exceptionHappened = false;
        try {
            MagicFileFactory.create(IoUtil.getClasspathUrl("share/mime/globs"));
        } catch (MimeInfoException e) {
            exceptionHappened = true;
        }
        assertTrue(exceptionHappened);
    }

}
