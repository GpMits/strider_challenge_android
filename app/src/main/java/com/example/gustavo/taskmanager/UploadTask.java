package com.example.gustavo.taskmanager;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Gustavo on 20/02/2017.
 */

public class UploadTask extends AsyncTask<Bitmap, Void, Void> {
    private MainActivity mainActivity;

    public UploadTask(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    protected Void doInBackground(Bitmap... bitmaps) {
        MultiValueMap<String, Object> multipartMap = new LinkedMultiValueMap<>();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmaps[0].compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        File arq = null;
        try {
            arq = new File(mainActivity.getFilesDir().getPath().toString() + "/" + mainActivity.tasks[mainActivity.position].getId() + ".png");
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(arq));
            bos.write(byteArray);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        multipartMap.add("file", new FileSystemResource(arq));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("multipart", "form-data"));

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(multipartMap, headers);

        System.out.println("Request for File Upload : " + request);

        ResponseEntity<byte[]> result = restTemplate.exchange(
                "http://192.168.0.165:8080/task/uploadFile", HttpMethod.POST, request,
                byte[].class);
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        // TODO Auto-generated method stub
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
//            Toast.makeText(MainActivity.this, R.string.uploaded, Toast.LENGTH_LONG).show();
    }
}
