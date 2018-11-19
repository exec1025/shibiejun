package qyh.androidprojecthelper.model;

import java.util.List;
import qyh.androidprojecthelper.api.Api;
import qyh.androidprojecthelper.baserx.RxSchedulers;
import qyh.androidprojecthelper.bean.FlowerData;
import qyh.androidprojecthelper.bean.FlowerRecognitionResultBean;
import qyh.androidprojecthelper.contract.FlowerContract;
import rx.Observable;
import rx.functions.Func1;

/**
 * 描述:花卉信息Model
 * Created by lenovo on 2018/10/6.
 */

public class FlowerModel{

//    @Override
//    public Observable<List<FlowerRecognitionResultBean>> getFlowerRecognitionResultByImage(String accessToken, String image) {
//        return Api.getInstance().service.getFlowerRecognitionResultByImage(accessToken, image)
//                .map(new Func1<FlowerData, List<FlowerRecognitionResultBean>>() {
//                    @Override
//                    public List<FlowerRecognitionResultBean> call(FlowerData flowerData) {
//                        return flowerData.getResults();
//                    }
//                }).compose(RxSchedulers.<List<FlowerRecognitionResultBean>>io_main());
//    }
}
