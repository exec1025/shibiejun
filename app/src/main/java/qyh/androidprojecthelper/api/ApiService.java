package qyh.androidprojecthelper.api;

import qyh.androidprojecthelper.bean.AccessTokenBean;
import qyh.androidprojecthelper.bean.AnimalRecognitionResultBean;
import qyh.androidprojecthelper.bean.CarRecognitionResultBean;
import qyh.androidprojecthelper.bean.DishRecognitionResultBean;
import qyh.androidprojecthelper.bean.FlowerRecognitionResultBean;
import qyh.androidprojecthelper.bean.GirlData;
import qyh.androidprojecthelper.bean.WebRecognitionResultBean;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 描述：Retrofit定义网络请求部分
 * Created by czn on 2018/9/17.
 */
public interface ApiService {
    /**
     * 图片URL   http://gank.io/api/data/test/20/1 接口测试
     * @param size
     * @param page
     * @return
     */
    @GET("data/test/{size}/{page}")
    Observable<GirlData> getListData(
//            @Header("Cache-Control") String cacheControl,
            @Path("size") int size,
            @Path("page") int page);

    /**
     *
     * @param grantType 固定参数为client_credentials
     * @param clientId 应用的API Key
     * @param clientSecret 应用的Secret Key
     * @return observable对象用于rxjava,从AccessTokenBean中可以获得服务器返回的access token
     */
    @POST("oauth/2.0/token")
    Observable<AccessTokenBean> getAccessToken(@Query("grant_type") String grantType, @Query("client_id") String clientId, @Query("client_secret") String clientSecret);


    /**
     * @param accessToken 通过API key和Serect key获取的access_token
     * @param image 图像数据通过base64编码后进行urlencode后的String
     * @return observable对象用于Rxjava,从FlowerRecognitionResultBean中获得的花卉识别的信息
     */
    @POST("rest/2.0/image-classify/v1/plant")
    @FormUrlEncoded
    Observable<FlowerRecognitionResultBean> getFlowerRecognitionResultByImage(
            @Field("access_token") String accessToken,
            @Field("image") String image,
            @Field("baike_num") Integer baikeNum);

    /**
     * @param accessToken 通过API key和Serect key获取的access_token
     * @param image 图像数据通过base64编码后进行urlencode后的String
     * @return observable对象用于Rxjava,从AnimalRecognitionResultBean中获得的动物识别的信息
     */
    @POST("rest/2.0/image-classify/v1/animal")
    @FormUrlEncoded
    Observable<AnimalRecognitionResultBean> getAnimalRecognitionResultByImage(
            @Field("access_token") String accessToken,
            @Field("image") String image,
            @Field("baike_num") Integer baikeNum);

    /**
     * @param accessToken 通过API key和Serect key获取的access_token
     * @param image 图像数据通过base64编码后进行urlencode后的String
     * @return observable对象用于Rxjava,从CarRecognitionResultBean中获得的车型识别的信息
     */
    @POST("rest/2.0/image-classify/v1/car")
    @FormUrlEncoded
    Observable<CarRecognitionResultBean> getCarRecognitionResultByImage(
            @Field("access_token") String accessToken,
            @Field("image") String image,
            @Field("baike_num") Integer baikeNum);

    /**
     * @param accessToken 通过API key和Serect key获取的access_token
     * @param image 图像数据通过base64编码后进行urlencode后的String
     * @return observable对象用于Rxjava,从DishRecognitionResultBean中获得的菜品识别的信息
     */
    @POST("rest/2.0/image-classify/v2/dish")
    @FormUrlEncoded
    Observable<DishRecognitionResultBean> getDishRecognitionResultByImage(
                    @Field("access_token") String accessToken,
                    @Field("image") String image,
                    @Field("filter_threshold") float filterThreshold,
                    @Field("baike_num") Integer baikeNum);

    /**
     * @param image 图像数据通过base64编码后进行urlencode后的String
     * @param recognitioresultbean 百度接口获取的json发送给web后端
     * @return observable对象用于Rxjava,从DishRecognitionResultBean中获得的菜品识别的信息
     */
    @POST("index/user")
    @FormUrlEncoded
    Observable<WebRecognitionResultBean> getWebRecognitionResultByImage(
            @Field("image") String image,
            @Field("recognitioresultbean") String  recognitioresultbean);

}
