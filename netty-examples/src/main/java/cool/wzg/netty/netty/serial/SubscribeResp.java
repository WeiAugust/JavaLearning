package cool.wzg.netty.netty.serial;

import java.io.Serializable;

/**
 * @ClassName SubscribeResp
 * @Description TODO
 * @Author wzg
 * @Date 2020/11/24 9:33
 * @Version 1.0
 **/
public class SubscribeResp{

//    private static final long serialVersionUID = 1L;

    private int subReqID;

    private int respCode;

    private String desc;

    @Override
    public String toString() {
        return "SubscribeResp{" +
                "subReqID=" + subReqID +
                ", respCode=" + respCode +
                ", desc='" + desc + '\'' +
                '}';
    }

    public int getSubReqID() {
        return subReqID;
    }

    public void setSubReqID(int subReqID) {
        this.subReqID = subReqID;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
