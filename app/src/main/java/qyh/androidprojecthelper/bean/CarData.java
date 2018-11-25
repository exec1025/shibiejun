package qyh.androidprojecthelper.bean;

import java.util.List;

/**
 * 描述：
 * Created by czn on 2018/11/22.
 */

public class CarData {
    private boolean isError;
    private List<CarRecognitionResultBean> results;

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public void setResults(List<CarRecognitionResultBean> results) {
        this.results = results;
    }

    public List<CarRecognitionResultBean> getResults() {
        return results;
    }
}
