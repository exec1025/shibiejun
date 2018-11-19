package qyh.androidprojecthelper.bean;

import java.util.List;

/**
 * 描述:花卉识别结果信息
 * Created by lenovo on 2018/10/6.
 */

public class FlowerRecognitionResultBean {
    /**
     * log_id": 1705495792822072357,
     *"result": [{
        "score": 0.99979120492935,
        "name": "莲",
        "baike_info": {
            "baike_url": "http://baike.baidu.com/item/%E8%8E%B2/2717141",
            "description": "莲(Nelumbo nucifera)，又称荷、荷花、莲花、芙蕖、鞭蓉、水芙蓉、水芝、水芸、水旦、水华等，溪客、玉环是其雅称，未开的花蕾称菡萏，已开的花朵称鞭蕖，莲科，属多年生水生宿根草本植物，其地下茎称藕，能食用，叶入药，莲子为上乘补品，花可供观赏。是我国十大名花之一。是印度的国花。莲，双子叶植物，睡莲科。多年生挺水草本植物。根状茎横走，粗而肥厚，节间膨大，内有纵横通气孔道，节部缢缩。叶基生，挺出水面，盾形，直径30-90cm，波状边缘，上面深绿色，下面浅绿色。叶柄有小刺，长1-2m，挺出水面。花单生，直径10-25cm，椭圆花瓣多数，白色或粉红色；花柄长1-2m。花托在果期膨大，直径5-10cm，海绵质。坚果椭圆形和卵圆形，长1.5-2.0cm，灰褐色。种子卵圆形，长1.2-1.7cm，种皮红棕色。生于池塘、浅湖泊及稻田中。中国南北各省有自生或栽培，经济价值高。人们习惯上称种子为“莲子”、地下茎为“藕”、花托为“莲蓬”、叶为“荷叶”。"
            }
        },
        {
         "score": 0.00015144718054216,
         "name": "红睡莲"
        },
        {
         "score": 1.2172759852547e-05,
         "name": "白睡莲"
         },
        {
         "score": 6.305016540864e-06,
         "name": "延药睡莲"
        },
        {
         "score": 3.6133328649157e-06,
         "name": "华夏慈姑"
        }]
     */
    private String log_id;
    private List<ResultBean> result;

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public String getLog_id() {
        return log_id;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "FlowerRecognitionResultBean{" +
                "log_id='" + log_id + '\'' +
                ", result=" + result +
                '}';
    }

    public static class ResultBean{
        private String name;
        private String score;
        private Baike_infoBean baike_info;

        public void setName(String name) {
            this.name = name;
        }

        public void setScore(String score) {
            this.score = score;
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

        public Baike_infoBean getBaike_info() {
            return baike_info;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "name='" + name + '\'' +
                    ", score='" + score + '\'' +
                    ", baike_info=" + baike_info +
                    '}';
        }

        public static class Baike_infoBean{
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
