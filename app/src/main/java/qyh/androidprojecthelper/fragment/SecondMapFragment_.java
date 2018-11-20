package qyh.androidprojecthelper.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.MyLocationStyle;

import butterknife.BindView;
import qyh.androidprojecthelper.R;
import qyh.androidprojecthelper.base.BaseFragment;

/**
 * Created by lenovo on 2018/9/29.
 */

public class SecondMapFragment_ extends BaseFragment implements CompoundButton.OnCheckedChangeListener,LocationSource, AMapLocationListener {
    //@BindView(R.id.map)
    public MapView mapView;
    //@BindView(R.id.btn_mapchange)
    public ToggleButton toggleButton;

    private Context mContext;
    private AMap aMap;
    private MyLocationStyle myLocationStyle;
    private OnLocationChangedListener mListener;
    private AMapLocationClient locationClient;
    private AMapLocationClientOption clientOption;

    public static SecondMapFragment_ newInstance(){
        SecondMapFragment_ fragment = new SecondMapFragment_();
        return fragment;
    }

    public SecondMapFragment_(){
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second_map, container, false);
        initMapView(savedInstanceState,view);
        initlistener();
        return view;
    }

    @Override
    protected int getLayoutResource() {
        mContext = getActivity();
        return R.layout.fragment_second_map;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
    }

    protected void initMapView(Bundle savedInstanceState,View view) {
        mapView= (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        if (aMap==null) {
            aMap=mapView.getMap();
        }
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
        myLocationStyle.strokeColor(Color.BLACK);// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));// 设置圆形的填充颜色
        myLocationStyle.strokeWidth(0.1f);// 设置圆形的边框粗细

        aMap.setMyLocationStyle(myLocationStyle);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        //aMap.getUiSettings().setTiltGesturesEnabled(false);
        aMap.setLocationSource(this);
        aMap.setMyLocationEnabled(true);
        toggleButton=(ToggleButton) view.findViewById(R.id.btn_mapchange);
    }

    private void initlistener(){
        toggleButton.setOnCheckedChangeListener(this);
    }

    /**
     * 激活定位
     * @param onLocationChangedListener
     */
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener=onLocationChangedListener;
        if(locationClient==null){
            locationClient=new AMapLocationClient(getActivity());
            clientOption=new AMapLocationClientOption();
            locationClient.setLocationListener(this);
            clientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//高精度定位
            clientOption.setOnceLocationLatest(true);//设置单次精确定位
            locationClient.setLocationOption(clientOption);
            locationClient.startLocation();
        }
    }

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener=null;
        if(locationClient!=null){
            locationClient.stopLocation();
            locationClient.onDestroy();
        }
        locationClient=null;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if(isChecked){
            aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
        }else {
            aMap.setMapType(AMap.MAP_TYPE_NORMAL);
        }
    }

    /**
     * 位置改变
     * @param aMapLocation
     */
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null&&aMapLocation != null) {
            if (aMapLocation != null
                    &&aMapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode()+ ": " + aMapLocation.getErrorInfo();
                //Toast.makeText(getActivity(), errText, Toast.LENGTH_SHORT).show();
                Log.e("AmapErr",errText);
            }
        }
    }

    /**
     * 必须重写以下方法，高德开发者手册要求。。。
     */
    @Override
    public void onResume(){
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        if(locationClient!=null){
            locationClient.onDestroy();
        }
    }
}
