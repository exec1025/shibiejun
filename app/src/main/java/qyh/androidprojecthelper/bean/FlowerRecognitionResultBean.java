package qyh.androidprojecthelper.bean;

import java.util.List;

/**
 * 描述:花卉识别结果信息
 * Created by czn on 2018/10/6.
 */

public class FlowerRecognitionResultBean {
    /**
     *
     log_id = '5277068303464013782', result = [ResultBean {
     name = '向日葵', score = '0.99601978063583', baike_info = Baike_infoBean {
     baike_url = 'http://baike.baidu.com/item/åæ¥èµ/6106', image_url = 'http://imgsrc.baidu.com/baike/pic/item/1ad5ad6eddc451da90e58429bcfd5266d11632e4.jpg', description = '向日葵(拉丁文：Helianthus annuusL.)，为木兰纲、菊科、向日葵属的一年生草本植物。高1～3.5米。茎直立，圆形多棱角，质硬被白色粗硬毛。广卵形的叶片通常互生，先端锐突或渐尖，有基出3脉，边缘具粗锯齿，两面粗糙，被毛，有长柄。头状花序，直径10～30厘米，单生于茎顶或枝端。总苞片多层，叶质，覆瓦状排列，被长硬毛，夏季开花，花序边缘生中性的黄色舌状花，不结实。花序中部为两性管状花，棕色或紫色，能结实。矩卵形瘦果，果皮木质化，灰色或黑色，称葵花籽。原产南美洲，驯化种由西班牙人于1510年从北美带到欧洲，最初为观赏用。19世纪末，又被从俄国引回北美洲。中国均有栽培。向日葵种子叫葵花籽，常炒制之后做为零食食用，味美，也可以榨葵花籽油用于食用，油渣可以做饲料。'
     }
     }, ResultBean {
     name = '铁筷子', score = '0.15472760796547', baike_info = null
     }, ResultBean {
     name = '心叶向日葵', score = '0.0016477250028402', baike_info = null
     }, ResultBean {
     name = '金鸡菊', score = '0.0013097303453833', baike_info = null
     }, ResultBean {
     name = '堆心菊', score = '0.00094700057525188', baike_info = null
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
