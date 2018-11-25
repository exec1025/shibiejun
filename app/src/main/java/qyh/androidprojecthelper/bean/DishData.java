package qyh.androidprojecthelper.bean;

import java.util.List;

/**
 * 描述：
 * Created by czn on 2018/11/22.
 */

public class DishData {
    private boolean isError;
    private List<DishRecognitionResultBean> results;

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public void setResults(List<DishRecognitionResultBean> results) {
        this.results = results;
    }

    public List<DishRecognitionResultBean> getResults() {
        return results;
    }
}
