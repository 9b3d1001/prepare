package Serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;

public class SerializationExample {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        SingletonReadResolve singletonReadResolve = SingletonReadResolve.getINSTANCE();
        System.out.println("Before Serialization " + singletonReadResolve.getMessage());
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("write.ser"))) {
            objectOutputStream.writeObject(singletonReadResolve);
        }
        Thread.sleep(1000);
        SingletonReadResolve deserializedSingletonReadResolve = null;
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("write.ser"))) {
            deserializedSingletonReadResolve = (SingletonReadResolve) objectInputStream.readObject();
        }
        System.out.println("After Deserialization " + deserializedSingletonReadResolve.getMessage());
        System.out.println(singletonReadResolve == deserializedSingletonReadResolve);

        SingletonEnum singletonEnum = SingletonEnum.INSTANCE;
        singletonEnum.setMessage(LocalDateTime.now().toString());
        System.out.println("Before Serialization " + singletonEnum.getMessage());
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("write.ser"))) {
            objectOutputStream.writeObject(singletonEnum);
        }
        Thread.sleep(1000);
        SingletonEnum deserializedSingletonEnum = null;
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("write.ser"))) {
            deserializedSingletonEnum = (SingletonEnum) objectInputStream.readObject();
        }
        System.out.println("After Deserialization " + deserializedSingletonEnum.getMessage());
        System.out.println(singletonEnum == deserializedSingletonEnum);
    }
}
