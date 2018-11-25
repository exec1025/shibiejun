package qyh.androidprojecthelper.bean;

/**
 * 描述：web后端请求结果信息
 * Created by czn on 2018/11/22.
 */

public  class WebRecognitionResultBean {
    private String id;
    private String username;
    private String password;

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "WebRecognitionResultBean{" +
                "id='" + id + '\'' +
                ", username=" + username + '\'' +
                ", password=" + password +
                '}';
    }
}
