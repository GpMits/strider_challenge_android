package com.example.gustavo.taskmanager;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.example.gustavo.taskmanager.model.Task;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Gustavo on 20/02/2017.
 */

public class HttpRequestTask extends AsyncTask<Void, Void, Task[]> {
    private MainActivity mainActivity;

    public HttpRequestTask(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    protected Task[] doInBackground(Void... params) {
        try {
            final String url = "http://192.168.0.165:8080/task/list-pending";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            mainActivity.tasks = restTemplate.getForObject(url, Task[].class);

            return mainActivity.tasks;
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return null;
    }

    protected void onPostExecute(Task[] tasks) {
        final ListView listaTask = (ListView) mainActivity.findViewById(R.id.listaTasks);
        mainActivity.adaptador = new ListAdapter(mainActivity, tasks);
        listaTask.setAdapter(mainActivity.adaptador);

    }

}
