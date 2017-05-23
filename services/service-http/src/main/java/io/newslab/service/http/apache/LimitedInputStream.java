package io.newslab.service.http.apache;

import io.newslab.service.http.HttpResponse;
import org.apache.http.ContentTooLongException;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LimitedInputStream extends FilterInputStream {

    /** the wrapped input stream */
    private final InputStream in;

    /** the max length to provide */
    private final long max;

    /** the number of bytes already returned */
    private long pos = 0;

    private HttpResponse httpResponse;

    public LimitedInputStream(InputStream in, long size, HttpResponse httpResponse) {
        super(in);
        this.max = size;
        this.in = in;
        this.httpResponse = httpResponse;
    }

    @Override
    public int read() throws IOException {
        checkSize();

        int result = in.read();
        pos++;
        return result;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        checkSize();

        long maxRead = max>=0 ? Math.min(len, max-pos) : len;
        int bytesRead = in.read(b, off, (int)maxRead);

        if (bytesRead==-1) {
            return -1;
        }

        pos += bytesRead;
        return bytesRead;
    }

    private void checkSize() throws IOException {
        if (pos >= max) {
            in.close();

            synchronized (this) {
                if (httpResponse != null) {
                    httpResponse.abort();
                }
            }

            throw new ContentTooLongException("Content too long (more than " + max + " bytes). Stream closed.");
        }
    }

    @Override
    public long skip(long n) throws IOException {
        long toSkip = max>=0 ? Math.min(n, max-pos) : n;
        long skippedBytes = in.skip(toSkip);
        pos += skippedBytes;
        return skippedBytes;
    }

}
