package IO;

import java.io.IOException;
import java.io.OutputStream;

public class SimpleCompressorOutputStream extends OutputStream {
    OutputStream out;

    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        for (int i = 0; i < 6; i++) {
            out.write(b[i]);
        }
        if(b[6]==1){
            write(0);
        }
        int count=1;
        for (int i = 7; i <b.length ; i++) {


            if(b[i]==b[i-1]){
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
        out.close();

    }
}
