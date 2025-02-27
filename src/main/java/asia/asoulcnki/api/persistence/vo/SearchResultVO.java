package asia.asoulcnki.api.persistence.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

@Data
public class SearchResultVO implements Serializable {

    @JsonProperty("rpid")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long rpid;

    @JsonProperty("type_id")
    @JSONField(name = "type_id")
    private int typeId;

    @JsonProperty("mid")
    private long mid;

    @JsonProperty("uid")
    private long uid;

    @JsonProperty("oid")
    @JsonSerialize(using = ToStringSerializer.class)
    private long oid;

    @JsonProperty("ctime")
    private int ctime;

    @JsonProperty("m_name")
    @JSONField(name = "m_name")
    private String mName;

    @JsonProperty("content")
    private String content;

    @JsonProperty("like_num")
    @JSONField(name = "like_num")
    private int likeNum;
}
