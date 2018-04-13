package sandy.csvproject;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class BackgroundWorker extends AsyncTask<CsvSample,String,String>{
    Context context;
    AlertDialog alertDialog;

    BackgroundWorker (Context ctx){
        context=ctx;
    }
    CsvSample sampleobj=new CsvSample();
    @Override
    protected String doInBackground(CsvSample... params) {
        String id=params[0].getId();
        String name=params[0].getName();
        long phno=params[0].getNum();
        String address=params[0].getAddress();
        Log.d("BGActivity","Id is  --------- "+id);
        Log.d("BGActivity","Name is  --------- "+name);
        Log.d("BGActivity","Number is  --------- "+phno);
        Log.d("BGActivity","Address is  --------- "+address);
        String log_url="http://192.168.212.2/send.php";
            URL url;
            try {
                url = new URL(log_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("id", "UTF-8")+"="+URLEncoder.encode(id, "UTF-8")+"&"+URLEncoder.encode("name", "UTF-8")+"="+URLEncoder.encode(name, "UTF-8")+"&"+URLEncoder.encode("addr", "UTF-8")+"="+URLEncoder.encode(address, "UTF-8")+"&"+URLEncoder.encode("num", "UTF-8")+"="+URLEncoder.encode(String.valueOf(phno), "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!=null){
                    result +=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog =new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Send Status");
    }
    @Override
    protected void onPostExecute(String s) {
        alertDialog.setMessage(s);
        alertDialog.show();

    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
}
