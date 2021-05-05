package IO;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream {
    InputStream in;


    @Override
    public int read() throws IOException {
        return in.read();
    }

    @Override
    public int read(byte[] b) throws IOException {

        for (int i = 0; i < 6; i++) {
            b[i]=(byte)in.read();
        }
        boolean zero=true;
        int data;
        int position = 6;
        while((data = in.read())!=-1){
            if(zero){
                for (int i = 0; i < data ; i++) {
                    b[position] = (byte) 0;
                    System.out.println(((int) b[position]));
                    position++;
                }
                zero = false;
                data=in.read();
            }
            else{
                for (int i = 0; i < data ; i++) {
                    b[position] = (byte) 1;
                    System.out.println(((int) b[position]));
                    position++;
                }
                zero = true;
                data=in.read();

            }

        }
        return 0;
    }

    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }

}
