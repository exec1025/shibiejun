package qyh.androidprojecthelper.bean;

import java.util.List;

/**
 * 描述:车型识别结果信息
 * Created by czn on 2018/11/22.
 */

public class CarRecognitionResultBean {
    /**
     Content-Type: application/json;charset=UTF-8
     {
     "log_id": 4086212218842203806,
     "location_result": {
     "width": 447,
     "top": 226,
     "height": 209,
     "left": 188
     },
     "result": [{
     "baike_info": {
     "baike_url": "http://baike.baidu.com/item/%E5%B8%83%E5%8A%A0%E8%BF%AAChiron/20419512",
     "description": "布加迪Chiron是法国跑车品牌布加迪出品的豪华超跑车。配置四涡轮增压发动机，420 公里每小时，有23种颜色的选择，售价高达260万美元。"
     },
     "score": 0.98793351650238,
     "name": "布加迪Chiron",
     "year": "无年份信息"
     },
     {
     "score": 0.0021970034576952,
     "name": "奥迪RS5",
     "year": "2011-2017"
     },
     {
     "score": 0.0021096928976476,
     "name": "奥迪RS4",
     "year": "无年份信息"
     },
     {
     "score": 0.0015581247862428,
     "name": "奥迪RS7",
     "year": "2014-2016"
     },
     {
     "score": 0.00082337751518935,
     "name": "布加迪威航",
     "year": "2004-2015"
     }],
     "color_result": "颜色无法识别"
     }
     */
    private String log_id;
    private LocationResultBean location_result;
    private List<ResultBean> result;
    private String color_result;

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public void setLocation_result(LocationResultBean location_result){
        this.location_result = location_result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public void setColor_result(String color_result){
        this.color_result = color_result;
    }

    public String getLog_id() {
        return log_id;
    }

    public LocationResultBean getLocation_result(){
        return location_result;
    }

    public String getColor_result(){
        return color_result;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "CarRecognitionResultBean{" +
                "log_id='" + log_id + '\'' +
                ",location_result=" + location_result + '\''+
                ", result=" + result +'\''+
                ", color_result=" + color_result +
                '}';
    }

    public static class LocationResultBean{
        private String width;
        private String top;
        private String height;
        private String left;

        public void setWidth(String width) {
            this.width = width;
        }

        public void setTop(String top) {
            this.top = top;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public void setLeft(String left) {
            this.left = left;
        }

        public String getWidth() {
            return width;
        }

        public String getTop() {
            return top;
        }

        public String getHeight() {
            return height;
        }

        public String getLeft() {
            return left;
        }

        @Override
        public String toString() {
            return "LocationResultBean{" +
                    "width='" + width + '\'' +
                    ", top='" + top + '\'' +
                    ", height='" + height + '\'' +
                    ", left=" + left +
                    '}';
        }
    }
    public static class ResultBean{
        private String name;
        private String score;
        private String year;
        private Baike_infoBean baike_info;

        public void setName(String name) {
            this.name = name;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public void setYear(String year){
            this.year = year;
        }

        public void setBaike_info(Baike_infoBean baike_info) {
            this.baike_info = baike_info;
        }

        public String getName() {
            return name;
        }

        public String getScore() {
            return score;
        }

        public String getYear() {
            return year;
        }

        public Baike_infoBean getBaike_info() {
            return baike_info;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "name='" + name + '\'' +
                    ", score='" + score + '\'' +
                    ", year='" + year + '\'' +
                    ", baike_info=" + baike_info +
                    '}';
        }

        private static class Baike_infoBean{
            private String baike_url;
            private String image_url;
            private String description;

            public void setBaike_url(String baike_url) {
                this.baike_url = baike_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getBaike_url() {
                return baike_url;
            }

            public String getImage_url() {
                return image_url;
            }

            public String getDescription() {
                return description;
            }

            @Override
            public String toString() {
                return "Baike_infoBean{" +
                        "baike_url='" + baike_url + '\'' +
                        ", image_url='" + image_url + '\'' +
                        ", description='" + description + '\'' +
                        '}';
            }
        }
    }
}
