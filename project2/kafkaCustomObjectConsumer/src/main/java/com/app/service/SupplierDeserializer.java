package com.app.service;

import com.app.model.Supplier;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

public class SupplierDeserializer implements Deserializer<Supplier> {

    private String encoding="UTF8";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public Supplier deserialize(String topic, byte[] data) {

        try {
        if (data==null)
            return null;
            System.out.println("check1");
        ByteBuffer buf = ByteBuffer.wrap(data);
        int id=buf.getInt();
        int sizeOfName = buf.getInt();
        byte[] nameBytes = new byte[sizeOfName];
            System.out.println("check2");
        buf.get(nameBytes);
            System.out.println("check3");
        String deserializedName = new String(nameBytes,encoding);
        int sizeOfDate = buf.getInt();

        byte[] dateBytes = new byte[sizeOfDate];
        buf.get(dateBytes);
        String stringDate = new String(dateBytes,encoding);
        DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");

        return new Supplier(id,deserializedName,df.parse(stringDate));


        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to Supplier");
        }
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
