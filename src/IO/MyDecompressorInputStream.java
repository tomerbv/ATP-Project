package IO;

import java.io.IOException;
import java.io.InputStream;

/**
 * a class to implement decompressing the compressed byte maze in our way
 */
public class MyDecompressorInputStream extends InputStream {
    InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    /**
     * @param b same as SimpleDecompresser
     * @return same as SimpleDecompresser
     * in this method we implement the read method with the opposite algorithm of MyCompresser
     * @throws IOException
     */
    @Override
    public int read(byte[] b) throws IOException {
        byte[] Compressed = in.readAllBytes();
        int metacount = 0;
        int bCount = 0;
        int CmprsCount = 0;
        while (metacount <6){
            if(Compressed[CmprsCount]>=0){
                b[bCount++] = Compressed[CmprsCount++];
                metacount++;
            }
            else{
                b[bCount++] = Compressed[CmprsCount++];
            }
        }

        byte CmprsNum;
        while (bCount < b.length) {
            CmprsNum = Compressed[CmprsCount++];
            if (CmprsNum < 0){
                b[bCount++] = (byte) 1;
                CmprsNum += 128;
            }
            else
                b[bCount++] = (byte) 0;
            for (int i = 1; i < 8 && bCount < b.length; i++) {
                if ((CmprsNum >> (7-i)) % 2 == 1)
                    b[bCount++] = (byte) 1;
                else
                    b[bCount++] = (byte) 0;
            }

        }
        return 0;
    }
}
