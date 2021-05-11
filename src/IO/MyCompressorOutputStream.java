package IO;

import java.io.IOException;
import java.io.OutputStream;

/**
 * this class represents the decorator the uses our method to compress the maze data
 */
public class MyCompressorOutputStream extends OutputStream {
    OutputStream out;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    /**
     * @param b same as Simple
     * in this method we implemetnt our strategy to compress the maze data, this strategy compresses the metadat the same way as
     * the simple compresser, the content of the maze is decompressed by turning every 8 bites in to 1, because the maze only has 1's and 0's
     * we can treat every sequence of 8 bytes as 1 but with the value of that 1 byte being the value that the 8 bytes represent as a binary number.
     * @throws IOException
     */
    @Override
    public void write(byte[] b) throws IOException {
        int metacount = 0;
        int i = 0;
        while (metacount <6){
            if(b[i]>=0){
                write(b[i]);
                metacount++;
                i++;

            }
            else{
                write(b[i]);
                i++;

            }
        }
        byte currbyte;
        int j;
        while(i < b.length){
            currbyte = 0;
            j = 0;
            while(j < 8 && i < b.length) {
                currbyte = (byte)(currbyte << 1);
                if(b[i++] == 1)
                    currbyte += 1;
                j++;
            }
            while(j < 8) {
                currbyte = (byte) (currbyte << 1);
                j++;
            }
            write(currbyte);
        }

    }
}
