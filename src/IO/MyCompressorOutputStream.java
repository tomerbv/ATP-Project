package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
    OutputStream out;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

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
        int currbyte;
        while(i < b.length){
            currbyte = 0;
            for (int j = 0; j < 8 && i < b.length; j++) {
                currbyte = (currbyte << 1);
                if(b[i++] == 1)
                    currbyte += 1;
            }
            write((byte) currbyte);
        }

    }
}
