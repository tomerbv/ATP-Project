package IO;

import java.io.IOException;
import java.io.OutputStream;

/**
 * the class represents a decorator to compress the algorithm to compress the data that will be wriiten
 */
public class SimpleCompressorOutputStream extends OutputStream {
    OutputStream out;

    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    /**
     * @param b the array that we will compress and write
     * the method first compresses all the meta data then counts the number of appereances in a row of the same value then writes it ,
     * starting with 0's then 1's
     * @throws IOException
     */
    @Override
    public void write(byte[] b) throws IOException {
        int metacount = 0;
        int i = 0;
        while (metacount <6){
            if(b[i]>0){
                write(b[i]);
                metacount++;
                i++;

            }
            else{
                write(b[i]);
                i++;

            }
        }

        if(b[i++]==1){
            write(0);
        }

        int count=1;
        for (int j = i; j <b.length ; j++) {
            if(b[j]==b[j-1]){
                if(count==255){
                    out.write(255);
                    out.write(0);
                    count=1;
                }
                count++;
            }
            else{
                out.write(count);
                count=1;
            }
        }
        out.write(count);
        out.close();

    }
}
