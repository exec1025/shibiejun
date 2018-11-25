package qyh.androidprojecthelper.bean;

import java.util.List;

/**
 * 描述:动物识别结果信息
 * Created by czn on 2018/11/22.
 */

public class AnimalRecognitionResultBean {
    /**
     *"log_id": 7392482912853822863,
     "result": [{
     "score": "0.993811",
     "name": "叉角羚",
     "baike_info": {
     "baike_url": "http://baike.baidu.com/item/%E5%8F%89%E8%A7%92%E7%BE%9A/801703",
     "description": "叉角羚(学名：Antilocapra americana)：在角的中部角鞘有向前伸的分枝，故名。体型中等，体长1-1.5米，尾长7.5-10厘米，肩高81-104厘米，成体重36-60千克，雌体比雄体小；背面为红褐色，颈部有黑色鬃毛，腹部和臀部为白色，颊面部和颈部两侧有黑色块斑；毛被下面为绒毛，上覆以粗糙、质脆的长毛，由于某些皮肤肌的作用，能使其毛被呈不同角度，以利于保暖或散热。植食。叉角羚奔跑速度非常快，最高时速达100千米。一次跳跃可达3.5-6米。善游泳。夏季组成小群活动，冬季则集结成上百只的大群。为寻找食物和水源，一年中常进行几次迁移。性机警，视觉敏锐，能看到数千米外的物体。遇险时，臀部的白色毛能立起，向同伴告警。分布于北美洲。"
     }
     },
     {
     "score": "0.000289439",
     "name": "印度羚"
     },
     {
     "score": "0.000186248",
     "name": "藏羚羊"
     },
     {
     "score": "0.000147176",
     "name": "跳羚"
     },
     {
     "score": "0.000134434",
     "name": "驯鹿"
     },
     {
     "score": "9.86555e-05",
     "name": "高鼻羚羊"
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
        return "AnimalRecognitionResultBean{" +
                "log_id='" + log_id + '\'' +
                ", result=" + result +
                '}';
    }

    public static class ResultBean{
        private String name;
        private String score;
        private ResultBean.Baike_infoBean baike_info;

        public void setName(String name) {
            this.name = name;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public void setBaike_info(ResultBean.Baike_infoBean baike_info) {
            this.baike_info = baike_info;
        }

        public String getName() {
            return name;
        }

        public String getScore() {
            return score;
        }

        public ResultBean.Baike_infoBean getBaike_info() {
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
