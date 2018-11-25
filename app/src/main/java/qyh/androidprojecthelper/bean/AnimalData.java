package qyh.androidprojecthelper.bean;

import java.util.List;

/**
 * 描述：
 * Created by czn on 2018/11/22.
 */

public class AnimalData {
    private boolean isError;
    private List<AnimalRecognitionResultBean> results;

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public void setResults(List<AnimalRecognitionResultBean> results) {
        this.results = results;
    }

    public List<AnimalRecognitionResultBean> getResults() {
        return results;
    }
}
