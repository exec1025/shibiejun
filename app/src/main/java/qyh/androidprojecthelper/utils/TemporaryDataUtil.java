package qyh.androidprojecthelper.utils;

import java.util.ArrayList;
import java.util.List;

public class TemporaryDataUtil {

    public static ArrayList<TemporaryDataBean> getData(){
        String [][]Json = {
                {"蒲公英","https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1632963637,2470744254&fm=26&gp=0.jpg","这是蒲公英"},
                {"银杏","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1543318572986&di=3a2f951fd49fc60ecd4a569df334d0af&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3D69fbbe2d5943fbf2d121ae60d817a0f5%2Fb3119313b07eca80ca9fa2c29b2397dda1448379.jpg","这是银杏"},
                {"柴犬","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1543318959385&di=fc917eea70060a5c84f0ce02f4d1437f&imgtype=0&src=http%3A%2F%2Fimage.biaobaiju.com%2Fuploads%2F20180302%2F13%2F1519969319-TMFaAjsqBd.jpg","这是柴犬"},
                {"荷花","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1543319076382&di=1aec221c559eac70ac8c8057ff3b820d&imgtype=0&src=http%3A%2F%2Fpicture.ik123.com%2Fuploads%2Fallimg%2F130328%2F10-13032Q40147.jpg","这是荷花"},
                {"鲁冰花","https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2713313931,2354717407&fm=26&gp=0.jpg","这是鲁冰花"},
                {"橘猫","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1543319222530&di=2e368cfb99b955fe83b62c4718e4c16f&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201804%2F12%2F20180412143703_43SAe.thumb.700_0.png","这是橘猫"},
                {"兰博基尼","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1543321918066&di=8657320f662e3fae6835ae08e5e9d636&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20170426%2F94d503d4b5e645bea83ac44d9dbae659_th.jpeg","这是兰博基尼"},
        };
        ArrayList<TemporaryDataBean> mlist = new ArrayList<TemporaryDataBean>();
        for(int i = 0; i < Json.length; i++){
            mlist.add(new TemporaryDataBean(Json[i][0], Json[i][1], Json[i][2]));
        }
        return mlist;
    }

    public static String getDataJson(){
        String Json = "";
        return Json;
    }

    public static class TemporaryDataBean{
        private String name;
        private String baike_url;
        private String image_url;
        private String description;

        public TemporaryDataBean(String name, String image_url, String description){
            this.name = name;
            this.image_url = image_url;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBaike_url() {
            return baike_url;
        }

        public void setBaike_url(String baike_url) {
            this.baike_url = baike_url;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
