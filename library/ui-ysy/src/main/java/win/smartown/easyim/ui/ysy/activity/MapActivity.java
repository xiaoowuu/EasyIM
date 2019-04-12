package win.smartown.easyim.ui.ysy.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.lang.ref.WeakReference;
import java.util.List;

import win.smartown.easyim.ui.ysy.R;
import win.smartown.easyim.ui.ysy.entity.Location;

/**
 * 类描述：
 *
 * @author xiaoowuu@gmail.com
 * 创建时间：2019/4/12 9:48
 */
public class MapActivity extends Activity implements View.OnClickListener, BaiduMap.OnMapClickListener, OnGetGeoCoderResultListener {

    public static void showMap(Context context, double latitude, double longitude, String address) {
        Intent intent = new Intent(context, MapActivity.class);
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);
        intent.putExtra("address", address);
        context.startActivity(intent);
    }

    public static final int RESULT_SEND_LOCATION = 0x101;

    private MapView mapView;
    private BaiduMap map;
    private LocationClient locationClient;
    private LocateListener locateListener;
    private InfoWindow infoWindow;

    private GeoCoder geoCoder;
    private Location location;
    private boolean onlyShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(getApplicationContext());
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
        SDKInitializer.setHttpsEnable(true);

        setContentView(R.layout.activity_map);
        mapView = findViewById(R.id.map_view);
        findViewById(R.id.iv_back).setOnClickListener(this);

        map = mapView.getMap();
        map.setMyLocationEnabled(true);
        map.animateMapStatus(MapStatusUpdateFactory.zoomTo(18));

        map.setMyLocationEnabled(true);
        map.setOnMapClickListener(this);

        geoCoder = GeoCoder.newInstance();
        geoCoder.setOnGetGeoCodeResultListener(this);
        locationClient = new LocationClient(this);
        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);

        //设置locationClientOption
        locationClient.setLocOption(option);

        //注册LocationListener监听器
        locateListener = new LocateListener(this);
        locationClient.registerLocationListener(locateListener);

        Intent intent = getIntent();
        if (intent.hasExtra("latitude")) {
            onlyShow = true;
            LatLng latLng = new LatLng(intent.getDoubleExtra("latitude", 0), intent.getDoubleExtra("longitude", 0));
            addMarker(latLng);
            showInfoWindow(new Location(latLng, intent.getStringExtra("address")));
            map.animateMapStatus(MapStatusUpdateFactory.newLatLng(latLng));
            return;
        }
        checkLocationPermission();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mapView. onResume ()，实现地图生命周期管理
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mapView. onPause ()，实现地图生命周期管理
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mapView.onDestroy()，实现地图生命周期管理
        locationClient.unRegisterLocationListener(locateListener);
        locationClient.stop();
        map.setMyLocationEnabled(false);
        mapView.onDestroy();
        mapView = null;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_back) {
            finish();
        } else if (id == R.id.tv_send) {
            if (location != null) {
                Intent intent = new Intent();
                intent.putExtra("location", location);
                setResult(RESULT_SEND_LOCATION, intent);
                finish();
            }
        }
    }

    private void checkLocationPermission() {
        AndPermission.with(this).runtime().permission(Permission.Group.LOCATION)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        locationClient.start();
                    }
                })
                .start();
    }

    @Override
    public void onMapClick(LatLng latLng) {
        showInfoWindow(latLng);
    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        showInfoWindow(mapPoi.getPosition());
        return false;
    }

    private void showInfoWindow(LatLng latLng) {
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
    }

    private void showInfoWindow(Location location) {
        if (location == null) {
            return;
        }
        this.location = location;

        View view = LayoutInflater.from(this).inflate(R.layout.layout_marker, null);
        TextView textView = view.findViewById(R.id.tv_address);
        textView.setText(location.getName());
        if (onlyShow) {
            view.findViewById(R.id.tv_send).setVisibility(View.GONE);
        } else {
            view.findViewById(R.id.tv_send).setOnClickListener(this);
        }
        infoWindow = new InfoWindow(view, location.getLatLng(), -100);
        map.showInfoWindow(infoWindow);
    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        if (reverseGeoCodeResult != null && reverseGeoCodeResult.error == SearchResult.ERRORNO.NO_ERROR) {
            addMarker(reverseGeoCodeResult.getLocation());
            showInfoWindow(new Location(reverseGeoCodeResult.getLocation(), reverseGeoCodeResult.getAddress()));
        }
    }

    private void addMarker(LatLng latLng) {
        map.clear();
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions().position(latLng).icon(bitmap).anchor(0.5f, 1f);
        //在地图上添加Marker，并显示
        map.addOverlay(option);
    }

    private static class LocateListener extends BDAbstractLocationListener {

        private WeakReference<MapActivity> reference;

        public LocateListener(MapActivity activity) {
            this.reference = new WeakReference<>(activity);
        }

        @Override
        public void onReceiveLocation(BDLocation location) {
            MapActivity mapActivity = reference.get();
            if (mapActivity != null && location != null) {
                mapActivity.locationClient.stop();
                MyLocationData locData = new MyLocationData.Builder()
                        .accuracy(location.getRadius())
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(location.getDirection()).latitude(location.getLatitude())
                        .longitude(location.getLongitude()).build();
                mapActivity.map.setMyLocationData(locData);
                mapActivity.map.animateMapStatus(MapStatusUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
                mapActivity.showInfoWindow(new LatLng(location.getLatitude(), location.getLongitude()));
            }
        }
    }

}
