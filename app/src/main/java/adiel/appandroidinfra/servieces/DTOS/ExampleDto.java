package adiel.appandroidinfra.servieces.DTOS;

import android.os.Parcel;
import android.os.Parcelable;

import adiel.appandroidinfra.servieces.interfaces.GateServerDto;

/**
 * Created by recntrek7 on 16/03/17.
 */

public class ExampleDto implements Parcelable,GateServerDto{

    String name;
    int age;

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }

    protected ExampleDto(Parcel in) {
        name=in.readString();
        age=in.readInt();
    }


    public ExampleDto(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "ExampleDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static final Creator<ExampleDto> CREATOR = new Creator<ExampleDto>() {
        @Override
        public ExampleDto createFromParcel(Parcel in) {
            return new ExampleDto(in);
        }

        @Override
        public ExampleDto[] newArray(int size) {
            return new ExampleDto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


}
