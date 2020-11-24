package cool.wzg.netty.netty.serial;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @ClassName TestSubscribeReqProto
 * @Description TODO
 * @Author wzg
 * @Date 2020/11/24 10:42
 * @Version 1.0
 **/
public class TestSubscribeReqProto {

    private static byte[] encode(SubscribeReqProto.SubscribeReq req) {
        return req.toByteArray();
    }

    private static SubscribeReqProto.SubscribeReq decode(byte[] body) throws InvalidProtocolBufferException {
        return SubscribeReqProto.SubscribeReq.parseFrom(body);
    }

    private static SubscribeReqProto.SubscribeReq createSubscribeReq() {
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setAddress("南京");
        builder.setSubReqID(1);
        builder.setUserName("a");
        builder.setProductName("zookeeper");
        builder.setPhoneNumber("12345");
        return builder.build();
    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
        SubscribeReqProto.SubscribeReq req = createSubscribeReq();
        System.out.println("Before encode: "+req.toString());
        SubscribeReqProto.SubscribeReq req2 = decode(encode(req));
        System.out.println("After decode: "+req2.toString());
        System.out.println("Assert equal : --> "+req2.equals(req));
    }
}
