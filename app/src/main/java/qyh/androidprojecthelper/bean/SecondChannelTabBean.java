package qyh.androidprojecthelper.bean;

/**
 * 描述：
 * Created by czn on 2018/9/17.
 */
public class SecondChannelTabBean {
    private String name;
    private String id;

    public SecondChannelTabBean(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
