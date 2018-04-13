package sandy.csvproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendcsvdata();
    }
    public List<CsvSample> csvSamples=new ArrayList<>();
    public void sendcsvdata(){
        InputStream ips= getResources().openRawResource(R.raw.file);
        BufferedReader reader=new BufferedReader(new InputStreamReader(ips, Charset.forName("UTF-8")));
        String rline="";
        try {
            while((rline=reader.readLine())!=null){
                Log.d("MyActivity","Line :"+rline);
                String[] tokens=rline.split(",");
                CsvSample sample=new CsvSample();
                sample.setId(tokens[0]);
                sample.setName(tokens[1]);
                sample.setNum(Long.parseLong(tokens[2]));
                sample.setAddress(tokens[3]);
                csvSamples.add(sample);
                Log.d("MyActivity","Created "+sample);
                Log.d("MyActivity","Id is  --------- "+sample.getId());
                Log.d("MyActivity","Name is  --------- "+sample.getName());
                Log.d("MyActivity","Number is  --------- "+sample.getNum());
                Log.d("MyActivity","Address is  --------- "+sample.getAddress());
                String id=sample.getId();
                String name=sample.getName();
                long num=sample.getNum();
                String address=sample.getAddress();

                BackgroundWorker bw=new BackgroundWorker(this);
                bw.execute(sample);
            }
        } catch (IOException e) {
            Log.wtf("MyActivity","Error reading line from File"+rline,e);
            e.printStackTrace();
        }


    }
}