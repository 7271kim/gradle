package datastructure;

import java.util.List;

public class MinMaxGraphResult {

    private int weight;
    private List<Integer> pathList;

    public MinMaxGraphResult(int weight, List<Integer> pathList) {
        this.weight = weight;
        this.pathList = pathList;
    }

    public MinMaxGraphResult() {}

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<Integer> getPathList() {
        return pathList;
    }

    public void setPathList(List<Integer> pathList) {
        this.pathList = pathList;
    }

}
