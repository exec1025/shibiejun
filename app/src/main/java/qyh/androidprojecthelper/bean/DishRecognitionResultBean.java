package qyh.androidprojecthelper.bean;

import java.util.List;

/**
 * 描述：菜品识别结果信息
 * Created by czn on 2018/11/22.
 */

public class DishRecognitionResultBean {
    /**
     "log_id": 7357081719365269362,
     "result_num": 5,
     "result": [
     {
     "calorie": "119",
     "has_calorie": true,
     "name": "酸汤鱼",
     "probability": "0.396031"
     "baike_info": {
     "baike_url": "http://baike.baidu.com/item/%E9%85%B8%E6%B1%A4%E9%B1%BC/1754055",
     "description": "酸汤鱼，是黔桂湘交界地区的一道侗族名菜，与侗族相邻的苗、水、瑶等少数民族也有相似菜肴，但其中以贵州侗族酸汤鱼最为有名，据考证此菜肴最早源于黎平县雷洞镇牙双一带。制作原料主要有鱼肉、酸汤、山仓子等香料。成菜后，略带酸味、幽香沁人、鲜嫩爽口开胃，是贵州“黔系”菜肴的代表作之一。这道菜通常先自制酸汤，之后将活鱼去掉内脏，入酸汤煮制。"
     }
     },
     {
     "calorie": "38",
     "has_calorie": true,
     "name": "原味黑鱼煲",
     "probability": "0.265432",

     },
     {
     "calorie": "144",
     "has_calorie": true,
     "name": "椒鱼片",
     "probability": "0.0998993"
     },
     {
     "calorie": "98",
     "has_calorie": true,
     "name": "酸菜鱼",
     "probability": "0.0701917"
     },
     {
     "calorie": "257.65",
     "has_calorie": true,
     "name": "柠檬鱼",
     "probability": "0.0471465"
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
        return "DishRecognitionResultBean{" +
                "log_id='" + log_id + '\'' +
                ", result=" + result +
                '}';
    }

    public static class ResultBean{
        private String calorie;
        private boolean has_calorie;
        private String name;
        private String probability;
        private Baike_infoBean baike_info;

        public void setCalorie(String calorie) {
            this.calorie = calorie;
        }

        public void setHas_calorie(boolean has_calorie) {
            this.has_calorie = has_calorie;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setProbability(String probability) {
            this.probability = probability;
        }

        public void setBaike_info(Baike_infoBean baike_info) {
            this.baike_info = baike_info;
        }

        public String getCalorie() {
            return calorie;
        }

        public boolean isHas_calorie() {
            return has_calorie;
        }

        public String getProbability() {
            return probability;
        }

        public String getName() {
            return name;
        }

        public String getprobability() {
            return probability;
        }

        public Baike_infoBean getBaike_info() {
            return baike_info;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "calorie='" + calorie + '\'' +
                    ", has_calorie='" + has_calorie + '\'' +
                    ", name='" + name + '\'' +
                    ", probability='" + probability + '\'' +
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
