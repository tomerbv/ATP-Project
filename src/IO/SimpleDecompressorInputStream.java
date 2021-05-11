package IO;

import java.io.IOException;
import java.io.InputStream;

/**
 * a class to implement decompressing the compressed byte maze the Simple way
 */
public class SimpleDecompressorInputStream extends InputStream {
    InputStream in;


    @Override
    public int read() throws IOException {
        return in.read();
    }

    /**
     * @param b the byte array that we will write to
     * @return integer just for the return statement
     * the function writes to b the data using the simple algorith decompression
     * first we decomporess the metadata then we check each value and fill b the number of times of the value in the order of 0 then 1
     * @throws IOException
     */
    @Override
    public int read(byte[] b) throws IOException {
        byte[] Compressed = in.readAllBytes();
        int metacount = 0;
        int bCount = 0;
        int CmprsCount = 0;
        while (metacount <6){
            if(Compressed[CmprsCount]>0){
                b[bCount++] = Compressed[CmprsCount++];
                metacount++;
            }
            else{
                b[bCount++] = Compressed[CmprsCount++];
            }
        }
        int num = 0;
        int CmprsNum;
        while(CmprsCount < Compressed.length){
            CmprsNum = Compressed[CmprsCount++];
            for (int i = 0; i < CmprsNum ; i++) {
                b[bCount++] = (byte) num;
            }
            num = 1-num;
            }
        return 0;
    }

    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }

}
