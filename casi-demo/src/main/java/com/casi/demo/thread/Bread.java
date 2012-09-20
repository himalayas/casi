package com.casi.demo.thread;

/**
 * User: Think
 * Date: 12-9-19
 * Time: 下午9:01
 */
public class Bread {
    private long index;
    private String type;
    private int size;
    private int weight;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bread bread = (Bread) o;

        if (index != bread.index) return false;
        if (size != bread.size) return false;
        if (weight != bread.weight) return false;
        if (type != null ? !type.equals(bread.type) : bread.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (index ^ (index >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + size;
        result = 31 * result + weight;
        return result;
    }

    @Override
    public String toString() {
        return "Bread{" +
                "index=" + index +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", weight=" + weight +
                '}';
    }
}
