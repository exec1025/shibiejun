识别君项目
# 采用安卓项目快速开发框架

# 核心代码
**1. View 中代码，泛型中传入P 和m,并绑定彼此之间的关系**
````java
public class FirstTabFragment extends BaseFragment<FirstPresenter,FirstModel>

 @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }
````
**2. Presenter中代码，观察者模式，model中请求数据后，会回调到presenterz中**
````java
 @Override
    public void getFirstListDataRequest(int size, int page) {

        mRxManage.add(mModel.getListData(size,page).subscribe(new RxSubscriber<List<FirstBean>>(mContext,false) {
            @Override
            public void onStart() {
                super.onStart();
                mView.showLoading(mContext.getString(R.string.loading));
            }
            @Override
            protected void _onNext(List<FirstBean> firstBeen) {
                mView.showListData(firstBeen);
                mView.stopLoading();
            }
            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
````
  **3. Model中代码，主要是就是请求网络，将结果发送到presenterz中**
  ````java
   @Override
      public Observable<List<FirstBean>> getListData(int size, int page) {

          return Api.getInstance().service.getListData(size,page)
                  .map(new Func1<GirlData, List<FirstBean>>() {
              @Override
              public List<FirstBean> call(GirlData girlData) {
                  return girlData.getResults();
              }
        }).compose(RxSchedulers.<List<FirstBean>>io_main());
      }


   # 主要核心代码就是以上这些，完美的实现请求网络这个过程，通过rxJava观察者机制减少接口的使用，大大减少代码中无用的类！
