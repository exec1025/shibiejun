package qyh.androidprojecthelper.bean;

import java.util.List;

/**
 * 描述：
 * Created by lenovo on 2018/10/6.
 */

public class FlowerData {
    private boolean isError;
    private List<FlowerRecognitionResultBean> results;

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public void setResults(List<FlowerRecognitionResultBean> results) {
        this.results = results;
    }

    public List<FlowerRecognitionResultBean> getResults() {
        return results;
    }
}
