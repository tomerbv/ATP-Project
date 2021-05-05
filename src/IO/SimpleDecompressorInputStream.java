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
        int metacount = 0;
        int place = 0;
        while (metacount <6){
            if(b[place]>0){
                b[place]=(byte)in.read();
                metacount++;
                place++;

            }
            else{
                b[place]=(byte)in.read();
                place++;

            }
        }
        int num = 0;
        int data;
        int position = place;
        while((data = in.read())!=-1){
                for (int i = 0; i < data ; i++) {
                    b[position] = (byte)num;
                   // System.out.println(((int) b[position]));
                    position++;
                }
                num=1-num;
            }
        return 0;
    }

    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }

}
