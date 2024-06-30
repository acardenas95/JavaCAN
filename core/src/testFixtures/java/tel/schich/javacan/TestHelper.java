/*
 * The MIT License
 * Copyright © 2018 Phillip Schichtel
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
package tel.schich.javacan;

import org.junit.jupiter.api.Assertions;
import tel.schich.javacan.platform.linux.LinuxNativeOperationException;

import java.nio.ByteBuffer;

public class TestHelper {

    public static RawCanChannel createChannelWithFd(int fd) {
        return new RawCanChannelImpl(fd);
    }

    public static int createInvalidFd() throws LinuxNativeOperationException {
        int fd = SocketCAN.createRawSocket();
        SocketCAN.close(fd);
        return fd;
    }

    public static ByteBuffer directBufferOf(byte[] data) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public static void assertByteBufferEquals(ByteBuffer expected, ByteBuffer actual) {
        final ByteBuffer expectedArrayBacked = ByteBuffer.allocate(expected.remaining());
        final ByteBuffer actualArrayBacked = ByteBuffer.allocate(actual.remaining());

        expectedArrayBacked.put(expected);
        actualArrayBacked.put(actual);

        Assertions.assertArrayEquals(expectedArrayBacked.array(), actualArrayBacked.array());
    }
}
