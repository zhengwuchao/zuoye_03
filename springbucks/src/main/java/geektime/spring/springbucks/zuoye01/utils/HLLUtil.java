package geektime.spring.springbucks.zuoye01.utils;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import net.agkn.hll.HLL;
import net.agkn.hll.HLLType;

import java.nio.charset.Charset;
import java.util.Base64;


/**
 * @author zhengwuchao
 * @date Time:
 * @des: (五题)基于 HLL 实现点击量计数；
 */
public class HLLUtil {
    /**
     * log2m 桶数的log-base-2 比如桶数为64，则log2m=6，该参数应在4-30之间
     */
    private static final int LOG2M = 8;
    /**
     * 每个桶的位数，在1-8之间（最大为一个字节）
     */
    private static final int REG_WIDTH = 8;
    /**
     * 当EXPLICIT提升为SPARSE的阈值，取值为1-18
     */
    private static final int EXP_THRESH = 5;
    private static final HashFunction HASH_FUNCTION = Hashing.murmur3_128(1853);

    public static SerializableHLL newHLL() {
        /**
         * 第三个参数：标识SPARSE是否被使用
         * 第四个参数：计算的类型
         * EXPLICIT 进准存储，基于Long的Hashset
         * SPARSE 稀疏计算，数据量不大时为节省空间，只保存有值的桶
         * FULL 大部分桶有值时，用位向量存储桶内内容
         */
        HLL hll = new HLL(LOG2M, REG_WIDTH, EXP_THRESH, false, HLLType.EXPLICIT);
        return new SerializableHLL(hll);
    }

    public static SerializableHLL newHLL(byte[] hllSpace) {
        HLL hll = HLL.fromBytes(hllSpace);
        return new SerializableHLL(hll);
    }

    public static SerializableHLL newHLLFromBytesBase64(String base64) {
        return new SerializableHLL(HLL.fromBytes(Base64.getDecoder().decode(base64)));
    }

    /**
     * 使用的是EXPLICIT计算方式所以需要计算Hash这里使用的是Guava 工具包
     *
     * @return hash值
     */
    public static long hash(CharSequence charSequence) {
        return HASH_FUNCTION.hashString(charSequence, Charset.forName("UTF-8")).asLong();
    }


}
