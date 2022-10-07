package geektime.spring.springbucks.zuoye01.utils;

import net.agkn.hll.HLL;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

public class SerializableHLL implements Serializable {
    private static final long serialVersionUID = 1L;

    private transient HLL targetHLL;

    public SerializableHLL(HLL targetHLL) {
        this.targetHLL = targetHLL;
    }

    public synchronized void addRaw(final long rawValue) {
        targetHLL.addRaw(rawValue);
    }

    public synchronized long cardinality() {
        return targetHLL.cardinality();
    }

    public synchronized byte[] toBytes(){
        return targetHLL.toBytes();
    }

    public synchronized String toBytesBase64(){
        return Base64.getEncoder().encodeToString(targetHLL.toBytes());
    }

    public synchronized void merge(SerializableHLL hll){
        this.targetHLL.union(hll.targetHLL);
    }

    public synchronized void merge(byte[] hllSpace){
        this.targetHLL.union(HLL.fromBytes(hllSpace));
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        byte[] bs = targetHLL.toBytes();
        oos.writeInt(bs.length);
        oos.write(bs);
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
        ois.defaultReadObject();
        int byteLength = ois.readInt();
        byte[] bs = new byte[byteLength];
        ois.read(bs);
        targetHLL = HLL.fromBytes(bs);
    }
}
